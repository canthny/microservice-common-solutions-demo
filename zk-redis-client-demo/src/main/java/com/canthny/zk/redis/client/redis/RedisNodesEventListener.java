package com.canthny.zk.redis.client.redis;

import com.canthny.zk.redis.client.domain.RedisNode;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description： redis节点事件监听器
 * Created By tanghao on 2020/4/28
 */
@Component
public class RedisNodesEventListener {

    private final List<RedisNode> redisNodes = new ArrayList<>();

    //开启监听
    public void startListener(RedisNode master){
        redisNodes.add(master);
        Jedis jedisToMaster = new Jedis(master.getHost(),master.getPort());
        String replicationInfo = jedisToMaster.info("Replication");
    }
}
