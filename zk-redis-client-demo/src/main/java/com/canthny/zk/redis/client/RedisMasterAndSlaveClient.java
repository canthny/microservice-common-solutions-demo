package com.canthny.zk.redis.client;

import com.canthny.zk.redis.client.domain.RedisNode;
import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;
import com.canthny.zk.redis.client.util.ZkOperUtil;
import com.canthny.zk.redis.client.zk.MasterNodeWatcher;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * Description： redis客户端
 * Created By tanghao on 2020/4/28
 */
@Component
public class RedisMasterAndSlaveClient {

    private final MasterNodeWatcher masterNodeWatcher = new MasterNodeWatcher();

    public ZooKeeper zooKeeper = null;

    private static RedisNode currentMasterNode = null;

    private static Jedis jedis = null;

    @Value("${zk.connectString}")
    private String connectString;

    @PostConstruct
    private void initClient(){
        try {
            zooKeeper = new ZooKeeper(connectString,60000,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String masterNodeInfo = ZkOperUtil.getData(zooKeeper,"/redis/master",masterNodeWatcher, null);
        currentMasterNode = RedisNode.parseFromConnectString(masterNodeInfo);
        masterNodeWatcher.setCurrentMaster(currentMasterNode);
        jedis = new Jedis(currentMasterNode.getHost(),currentMasterNode.getPort());
    }

    public String getString(String key){
        if(null==this.jedis){
            throw new NullPointerException("client not init");
        }
        return jedis.get(key);
    }

    public void changeMaster(RedisNode newMasterNode){
        currentMasterNode.setHost(newMasterNode.getHost());
        currentMasterNode.setPort(newMasterNode.getPort());
        currentMasterNode.setRole(RedisNodeRuleEnum.MASTER);
        jedis.close();
        jedis = new Jedis(currentMasterNode.getHost(),currentMasterNode.getPort());
    }
}
