package com.canthny.zk.redis.client.zk;

import com.canthny.zk.redis.client.RedisMasterAndSlaveClient;
import com.canthny.zk.redis.client.domain.RedisNode;
import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;
import com.canthny.zk.redis.client.redis.RedisNodesEventListener;
import com.canthny.zk.redis.client.util.ZkOperUtil;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Description：主节点监听
 * Created By tanghao on 2020/4/28
 */
@Component
public class MasterNodeWatcher implements Watcher {

    Logger logger = LoggerFactory.getLogger(MasterNodeWatcher.class);

    @Resource
    RedisMasterAndSlaveClient redisMasterAndSlaveClient;

    @Resource
    ZkOperUtil zkOperUtil;

    private RedisNode currentMaster = new RedisNode();

    @Override
    public void process(WatchedEvent event) {
        if(Event.EventType.NodeDataChanged.getIntValue()==event.getType().getIntValue()){
            String masterNodeInfo = zkOperUtil.getData("/redis-master",null, null);
            RedisNode masterNew = RedisNode.parseFromConnectString(masterNodeInfo);
            if(!masterNew.getHost().equals(currentMaster.getHost())||masterNew.getPort()!=currentMaster.getPort()){
                logger.info("master node data change suc");
                currentMaster = masterNew;
                redisMasterAndSlaveClient.changeMaster(masterNew);
            }
        }else if(Event.EventType.NodeDeleted.getIntValue()==event.getType().getIntValue()){

        }
    }

    public void setCurrentMaster(RedisNode redisNode){
        currentMaster.setHost(redisNode.getHost());
        currentMaster.setPort(redisNode.getPort());
        currentMaster.setRole(RedisNodeRuleEnum.MASTER);
    }
}
