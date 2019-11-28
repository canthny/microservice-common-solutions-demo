package com.canthny.quartz.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 任务调度表
 * Created by welink on 2017/11/30.
 */
@Entity
@Table(name = "T_QUARTZ_SCHEDULE_TRIGGER")
@DynamicInsert
@DynamicUpdate
public class ScheduleTrigger {
    @ApiModelProperty(value="时间表达式")
    private String cron;  //时间表达式
    @ApiModelProperty(value = "调度状态")
    private String status; //使用状态 0：禁用   1：启用
    @ApiModelProperty(value = "任务名称")
    private String jobName; //任务名称
    @ApiModelProperty(value="任务分组")
    private String jobGroup; //任务分组
    @Column(name="cron",length = 100)
    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
    @Column(name="status",length = 4)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name="JOB_NAME",length = 255)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    @Column(name="JOB_GROUP",length = 255)
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
}
