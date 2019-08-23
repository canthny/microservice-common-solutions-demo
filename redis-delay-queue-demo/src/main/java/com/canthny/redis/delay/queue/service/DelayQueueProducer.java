package com.canthny.redis.delay.queue.service;

import com.alibaba.fastjson.JSON;
import com.canthny.redis.delay.queue.domain.DelayMsgInfo;
import com.canthny.redis.delay.queue.enums.DelayMsgBusiCodeEnum;
import com.canthny.redis.delay.queue.enums.DelayMsgStatusEnum;
import com.canthny.redis.delay.queue.utils.RedisDelayQueueUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * Description：延时队列生产者
 * Created By tanghao on 2019/8/8
 */
public class DelayQueueProducer {

    private static final Logger log = LoggerFactory.getLogger(DelayQueueProducer.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加延时消息
     * @param busiCodeEnum 业务编码
     * @param busiObj 业务对象json字符串
     * @return
     */
    public boolean addToDelayQueue(DelayMsgBusiCodeEnum busiCodeEnum, String busiObj){
        DelayMsgInfo msgInfo = new DelayMsgInfo();
        msgInfo.setMsgId(UUID.randomUUID().toString());//全局消息流水号
        msgInfo.setBusiCode(busiCodeEnum.getCode());
        msgInfo.setTtl(busiCodeEnum.getTtl());
        msgInfo.setStatus(DelayMsgStatusEnum.WAIT_DEAL.getCode());
        msgInfo.setCreateTime(new Date());
        msgInfo.setBusiObj(busiObj);
        String msgContent = JSON.toJSONString(msgInfo);
        try{
            redisTemplate.opsForHash().put(RedisDelayQueueUtil.getHashKey(msgInfo.getTopic()),msgInfo.getMsgId(),msgContent);
            redisTemplate.opsForZSet().add(RedisDelayQueueUtil.getDelaySetKey(msgInfo.getTopic()),msgInfo.getMsgId(),msgInfo.getCreateTime().getTime()+msgInfo.getTtl());
            log.info("add msg success");
            return true;
        }catch (Exception e){
            log.error("add msg Exception:",e);
            return false;
        }
    }
}
