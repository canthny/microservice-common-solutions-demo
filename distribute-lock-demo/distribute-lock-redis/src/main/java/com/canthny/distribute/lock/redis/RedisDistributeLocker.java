package com.canthny.distribute.lock.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Description： redis分布式锁实现类
 * Created By tanghao on 2019/12/9
 */
@Component
public class RedisDistributeLocker {

    private static Logger logger = LoggerFactory.getLogger(RedisDistributeLocker.class);

    @Autowired
    RedisTemplate redisTemplate;

    public String lock(String key, long expireTime){
        String lockId = UUID.randomUUID().toString();
        try{
            boolean result = redisTemplate.opsForValue().setIfAbsent(key,lockId,expireTime, TimeUnit.SECONDS);
            if(!result){
                logger.error("RedisDistributeLocker setIfAbsent return false");
                return null;
            }
        }catch (Exception e){
            logger.error("RedisDistributeLocker setIfAbsent exception:{}",e);
            return null;
        }
        return lockId;

    }

    public boolean unlock(String key, String lockId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return ARGV[1] end";
        RedisScript redisScript = new DefaultRedisScript(script);
        try{
            String result = (String)redisTemplate.execute(redisScript, CollectionUtils.arrayToList(key),lockId);
            if(!"1".equals(result)){
                logger.error("RedisDistributeLocker unlock false");
                return false;
            }
        }catch (Exception e){
            logger.error("RedisDistributeLocker unlock exception:{}",e);
            return false;
        }
        return true;
    }
}
