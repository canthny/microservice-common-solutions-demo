package com.canthny.zk.redis.client.redis;

import com.alibaba.fastjson.JSONObject;
import com.canthny.zk.redis.client.domain.RedisMasterAndSlaveInfo;
import com.canthny.zk.redis.client.domain.RedisNode;
import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;
import com.canthny.zk.redis.client.util.ZkOperUtil;
import com.canthny.zk.redis.client.zk.MasterNodeWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Description： redis节点事件监听器
 * Created By tanghao on 2020/4/28
 */
@Component
public class RedisNodesEventListener {

    Logger logger = LoggerFactory.getLogger(RedisNodesEventListener.class);

    private static RedisMasterAndSlaveInfo redisNodes = null;

    private final static String MASTER = "master";

    private volatile boolean flag = true;

    private int masterChangeCount = 0;

    @Resource
    ZkOperUtil zkOperUtil;

    //开启监听
    public void startListener(RedisNode node) {
        redisNodes = initNodes(node);
        Thread listen = new Thread(){
            @Override
            public void run() {
                while(flag){
                    RedisNode master = redisNodes.getMaster();
                    boolean isOk = false;
                    int i = 0;
                    //对master节点进行ping操作，异常则重试，重试一定时间后认为master节点down了
                    while(!isOk && i<5){
                        Jedis jedis = new Jedis(master.getHost(),master.getPort());
                        try{
                            jedis.ping();
                            isOk = true;
                        }catch (JedisConnectionException ex){
                            logger.error("master ping fail");
                        }finally{
                            if(null!=jedis){
                                jedis.close();
                            }
                        }
                        if(!isOk){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        i++;
                    }
                    //如果master节点ping超时了，尝试获取新的master
                    int j = 0;
                    while (!isOk && j<10){
                        List<RedisNode> slaves = redisNodes.getSlaves();
                        RedisNode masterNew = null;
                        Iterator<RedisNode> it = slaves.iterator();
                        while (it.hasNext()){
                            RedisNode slave = it.next();
                            Jedis jedis = new Jedis(slave.getHost(),slave.getPort());
                            String replicationInfo = jedis.info("Replication");
                            String[] infos = replicationInfo.split("\r\n");
                            String roleStr = infos[1];
                            String role = roleStr.split(":")[1];
                            if(MASTER.equals(role)){
                                it.remove();
                                masterNew = slave;
                            }
                            jedis.close();
                        }
                        if(null==masterNew){
                            j++;
                        }else{
                            zkOperUtil.setData("/redis-master",masterNew.getRedisConnectStr(),masterChangeCount);
                            masterChangeCount++;
                            redisNodes.setMaster(masterNew);
                            logger.info("master change ok, new master is:"+JSONObject.toJSONString(masterNew));
                            isOk = true;
                        }
                        if(!isOk){
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    if(!isOk){
                        logger.error("master is down,can not find new master!");
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        listen.start();
    }

    public void shutDownListener(){
        flag = false;
    }

    private RedisMasterAndSlaveInfo initNodes(RedisNode node) {
        RedisMasterAndSlaveInfo redisMasterAndSlaveInfo = new RedisMasterAndSlaveInfo();
        Jedis jedis = new Jedis(node.getHost(),node.getPort());
        String replicationInfo = jedis.info("Replication");
        System.out.println(replicationInfo);
        String[] infos = replicationInfo.split("\r\n");
        String roleStr = infos[1];
        String role = roleStr.split(":")[1];
        System.out.println(role);
        if(MASTER.equals(role)){
            node.setRole(RedisNodeRuleEnum.MASTER);
            redisMasterAndSlaveInfo.setMaster(node);
            List<RedisNode> slaves = getSlavesFromMaster(infos);
            redisMasterAndSlaveInfo.setSlaves(slaves);
        }else{
            String masterHost = infos[2].split(":")[1];
            String masterPort = infos[3].split(":")[1];
            RedisNode master = new RedisNode(masterHost,Integer.parseInt(masterPort),RedisNodeRuleEnum.MASTER);
            redisMasterAndSlaveInfo.setMaster(master);
            Jedis jedisMaster = new Jedis(master.getHost(),master.getPort());
            String replicationInfoMaster = jedisMaster.info("Replication");
            String[] infosMaster = replicationInfoMaster.split("\r\n");
            List<RedisNode> slaves = getSlavesFromMaster(infosMaster);
            redisMasterAndSlaveInfo.setSlaves(slaves);
            jedisMaster.close();
        }
        jedis.close();
        System.out.println(JSONObject.toJSONString(redisMasterAndSlaveInfo));
        return redisMasterAndSlaveInfo;
    }

    private List<RedisNode> getSlavesFromMaster(String[] replicationInfos) {
        List<RedisNode> slaves = new ArrayList<>();
        for(int i=3;i<replicationInfos.length;i++){
            String info = replicationInfos[i];
            if(info.startsWith("slave")){
                String tempNodeInfo = info.split(":")[1];
                String[] tempArray = tempNodeInfo.split(",");
                String ip = tempArray[0].split("=")[1];
                String port = tempArray[1].split("=")[1];
                RedisNode slave = new RedisNode(ip,Integer.parseInt(port));
                slaves.add(slave);
            }else{
                break;
            }
        }
        return slaves;
    }
}
