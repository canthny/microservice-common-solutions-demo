package com.canthny.redis.delay.queue.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Description：延时消息体
 * Created By tanghao on 2019/8/8
 */
public class DelayMsgInfo implements Serializable {

    private static final long serialVersionUID = 2823595943875114498L;
    private String topic;

    private String msgId;

    private String msgType;

    private String busiObj;

    private String status;

    private Date createTime;

    private long ttl;

    private int processCount;

    private String errorCode;

    private String errorMsg;

    private String extension;

    @Override
    public String toString() {
        return "DelayMsgInfo{" +
                "topic='" + topic + '\'' +
                ", msgId='" + msgId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", busiObj='" + busiObj + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", ttl=" + ttl +
                ", processCount=" + processCount +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getBusiObj() {
        return busiObj;
    }

    public void setBusiObj(String busiObj) {
        this.busiObj = busiObj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }

    public int getProcessCount() {
        return processCount;
    }

    public void setProcessCount(int processCount) {
        this.processCount = processCount;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
