package com.canthny.redis.delay.queue.enums;

/**
 * Description： 延时消息状态枚举
 * Created By tanghao on 2019/8/23
 */
public enum DelayMsgStatusEnum {

    WAIT_DEAL("1", "待处理"),
    PROCESSED_NO_CONFIRM("2", "已处理未确认"),
    PROCESSED_CONFIRMED("3", "已处理并确认"),
    FAILED("4", "处理失败"),
    ;

    private String code;
    private String desc;

    DelayMsgStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}