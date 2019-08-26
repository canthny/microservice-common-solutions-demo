package com.canthny.redis.delay.queue.job;

import com.canthny.redis.delay.queue.constant.RedisDelayQueueConstant;
import com.canthny.redis.delay.queue.enums.DelayMsgBusiCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;

/**
 * Description：消息扫描job
 * Created By tanghao on 2019/8/8
 */
public class MsgScanJob {

    private static final Logger log = LoggerFactory.getLogger(MsgScanJob.class);

    @Autowired
    RedisTemplate redisTemplate;

    private void scanWaitDealMsg(){
        log.info("RedisDelayQueueScaner start working!");
        for(DelayMsgBusiCodeEnum temp: DelayMsgBusiCodeEnum.values()){
            String script = "local ids = redis.call('ZRANGEBYSCORE',KEYS[1]..'" + RedisDelayQueueConstant.DELAY_SET + "','-inf',ARGV[1])\n"+
                    "for i,v in ipairs(ids) do \n " +
                    "redis.call('LPUSH',KEYS[1]..'" + RedisDelayQueueConstant.BLOCKED_QUEUE + "',ids[i]) \n" +
                    "redis.call('ZREM',KEYS[1]..'" + RedisDelayQueueConstant.DELAY_SET + "',ids[i]) \n " +
                    "end";
            try{
                redisTemplate.execute(RedisScript.of(script),Arrays.asList(temp.getTopic()),String.valueOf(System.currentTimeMillis()));
            }catch (Exception e){
                log.error("RedisDelayQueueScaner eval script error!Exception:",e);
            }
        }
    }
}
