package com.canthny.zk.redis.client.zk;

import com.canthny.zk.redis.client.RedisMasterAndSlaveClient;
import com.canthny.zk.redis.client.domain.RedisNode;
import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import javax.annotation.Resource;

/**
 * Description：主节点监听
 * Created By tanghao on 2020/4/28
 */
public class MasterNodeWatcher implements Watcher {

    @Resource
    RedisMasterAndSlaveClient redisMasterAndSlaveClient;

    private RedisNode currentMaster = new RedisNode();

    @Override
    public void process(WatchedEvent event) {
        if(Event.EventType.NodeDataChanged.getIntValue()==event.getType().getIntValue()){

        }else if(Event.EventType.NodeDeleted.getIntValue()==event.getType().getIntValue()){

        }
    }

    public void setCurrentMaster(RedisNode redisNode){
        currentMaster.setHost(redisNode.getHost());
        currentMaster.setPort(redisNode.getPort());
        currentMaster.setRole(RedisNodeRuleEnum.MASTER);
    }
}
