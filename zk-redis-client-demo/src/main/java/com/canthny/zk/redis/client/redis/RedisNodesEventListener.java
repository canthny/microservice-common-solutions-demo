package com.canthny.zk.redis.client.redis;

import com.alibaba.fastjson.JSONObject;
import com.canthny.zk.redis.client.domain.RedisMasterAndSlaveInfo;
import com.canthny.zk.redis.client.domain.RedisNode;
import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description： redis节点事件监听器
 * Created By tanghao on 2020/4/28
 */
@Component
public class RedisNodesEventListener {

    private static RedisMasterAndSlaveInfo redisNodes = null;

    private final static String MASTER = "master";

    //开启监听
    public void startListener(RedisNode node){
        redisNodes = initNodes(node);


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
