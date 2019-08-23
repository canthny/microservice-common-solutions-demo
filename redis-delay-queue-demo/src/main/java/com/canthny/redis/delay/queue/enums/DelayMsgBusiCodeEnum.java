package com.canthny.redis.delay.queue.enums;

/**
 * Description：延时消息业务类型枚举
 * Created By tanghao on 2019/8/23
 */
public enum DelayMsgBusiCodeEnum {

    ORDER_STATUS_CHECK("0001","订单状态轮询延时消息","{order_status_check}",15*60*1000,"orderStatusCheckServiceDelayMsgConsumer"),
    WAIT_DEAL_MSG_SEND("0002","待处理提醒发送延时消息","{wait_deal_msg_send}",30*60*1000,"waitDealMsgSendServiceDelayMsgConsumer"),
    ;

    DelayMsgBusiCodeEnum(String code, String desc, String topic, long ttl, String consumerServiceName){
        this.code = code;
        this.desc = desc;
        this.topic = topic;
        this.ttl = ttl;
        this.consumerServiceName = consumerServiceName;
    }

    private String code;
    private String desc;
    private String topic;
    private long ttl;
    private String consumerServiceName;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getTopic() {
        return topic;
    }

    public long getTtl() {
        return ttl;
    }

    public String getConsumerServiceName() {
        return consumerServiceName;
    }
}
