package com.canthny.zk.redis.client;

import com.canthny.zk.redis.client.zk.MasterNodeWatcher;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Description： redisClient测试
 * Created By tanghao on 2020/4/29
 */
public class RedisClientTest extends BaseTests{
    Logger logger = LoggerFactory.getLogger(RedisClientTest.class);

    @Resource
    RedisMasterAndSlaveClient redisMasterAndSlaveClient;

    @Test
    public void testGet(){
        for(int i=0;i<200;i++){
            try{
                logger.info(redisMasterAndSlaveClient.getString("test"));
            }catch (Exception e){
                logger.error("Exception:{}",e);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
