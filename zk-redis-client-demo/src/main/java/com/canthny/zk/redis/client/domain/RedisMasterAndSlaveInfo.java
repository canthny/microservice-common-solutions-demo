package com.canthny.zk.redis.client.domain;

import java.util.List;

/**
 * Description： 主从模式信息
 * Created By tanghao on 2020/4/29
 */
public class RedisMasterAndSlaveInfo {

    private RedisNode master;

    private List<RedisNode> slaves;

    public RedisNode getMaster() {
        return master;
    }

    public void setMaster(RedisNode master) {
        this.master = master;
    }

    public List<RedisNode> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<RedisNode> slaves) {
        this.slaves = slaves;
    }
}
