package com.canthny.zk.redis.client;

import com.canthny.zk.redis.client.util.ZkOperUtil;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Description： TODO
 * Created By tanghao on 2020/4/29
 */
public class ZkTest extends BaseTests{

    @Resource
    ZkOperUtil zkOperUtil;

    @Test
    public void initRedisConfig(){

        zkOperUtil.setData("/redis-master","192.168.8.145:6479",0);
    }
}
