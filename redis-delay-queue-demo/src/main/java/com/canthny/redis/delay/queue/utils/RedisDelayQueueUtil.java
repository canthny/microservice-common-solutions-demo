package com.canthny.redis.delay.queue.utils;


import com.canthny.redis.delay.queue.constant.RedisDelayQueueConstant;

/**
 * Description：工具类
 * Created By tanghao on 2019/7/24
 */
public class RedisDelayQueueUtil {

    public static String getHashKey(String topic){
        return new StringBuilder(topic).append(RedisDelayQueueConstant.HASH_KEY).toString();
    }

    public static String getDelaySetKey(String topic){
        return new StringBuilder(topic).append(RedisDelayQueueConstant.DELAY_SET).toString();
    }

    public static String getBlockedQueueKey(String topic){
        return new StringBuilder(topic).append(RedisDelayQueueConstant.BLOCKED_QUEUE).toString();
    }
}
