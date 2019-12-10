package com.canthny.distribute.lock.redis;

import com.canthny.common.base.BaseResponse;
import com.canthny.common.base.RespEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Random;
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

    private final Random sleepTime = new Random();

    /**
     * 获取锁id，并给锁设置一个过期时间，通常这个过期时间应该大于业务处理时间，防止未处理完就释放了锁
     * 如果锁被其他线程占有则直接返回失败，需要业务端自行处理
     * 适用于多抢一的并发场景
     * @param key
     * @param expireTime
     * @return
     */
    public BaseResponse lock(String key, long expireTime){
        String lockId = UUID.randomUUID().toString();
        try{
            boolean result = redisTemplate.opsForValue().setIfAbsent(key,lockId,expireTime, TimeUnit.SECONDS);
            if(!result){
                logger.error("RedisDistributeLocker setIfAbsent return false");
                return BaseResponse.buildResponse(RespEnum.FAILURE);
            }
        }catch (Exception e){
            logger.error("RedisDistributeLocker setIfAbsent exception:{}",e);
            return BaseResponse.buildResponse(RespEnum.SYS_UNKNOWN_ERROR,e.getMessage());
        }
        return BaseResponse.buildResponse(RespEnum.SUCCESS,lockId);
    }

    /**
     * 带重试机制，如果获取锁失败，则休眠一小段时间后重试一定的次数
     * 适用于业务高并发处理的场景
     * @param key
     * @param expireTime
     * @param retryTimes
     * @return
     */
    public BaseResponse lockWithRetry(String key, long expireTime, int retryTimes){
        BaseResponse result = null;
        if(retryTimes==0){
            logger.info("get lock fail, you have no retryTimes");
            return BaseResponse.buildResponse(RespEnum.FAILURE,"retryTimes is 0");
        }
        result = this.lock(key, expireTime);
        if(result.isSuccess()){
            return result;
        }
        logger.info("get lock fail, retry...left times:{}",retryTimes-1);
        try {
            Thread.sleep(sleepTime.nextInt(1000));
        } catch (Exception e) {
            logger.error("retry sleep exception:{}",e);
        }
        return lockWithRetry(key,expireTime,retryTimes-1);
    }

    /**
     * 解锁，通过lua脚本保证操作原子性，并且要验证缓存中的lockid是否匹配持有者的lockid，匹配才可以释放锁，防止释放了其他线程的锁
     * 若业务处理超时，锁已经超时释放的情况也需要考虑进去，即判断lockid为空或已经不是当前锁的时候不操作直接返回释放成功/失败，视业务情况而定
     * @param key
     * @param lockId
     * @return
     */
    public boolean unlock(String key, String lockId){
        String script = "local lockid =  redis.call('get', KEYS[1]) \n" +
                "if lockid == \"null\" then return 1 \n" +
                "elseif lockid == ARGV[1] then return redis.call('del', KEYS[1]) \n" +
                "else return 1 end";
        RedisScript redisScript = new DefaultRedisScript(script,Long.class);
        try{
            Long result = (Long)redisTemplate.execute(redisScript, Collections.singletonList(key),lockId);
            if(result>0){
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
