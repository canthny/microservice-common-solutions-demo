package com.canthny.quartz.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 定时任务监控
 * @author chao.deng
 * @date 2018/2/9
 */
@Data
public class ScheduleMonitor implements Serializable{

    private static final long serialVersionUID = 153037954767945518L;
    @ApiModelProperty(value = "任务名称")
    private String triggerName;

    @ApiModelProperty(value = "任务分组")
    private String triggerGroup;

    @ApiModelProperty(value = "当前状态")
    private String triggerState;

    @ApiModelProperty(value = "当前状态名称")
    private String triggerStateName;

    @ApiModelProperty(value = "下次执行时间")
    private Date nextFireTime;

    @ApiModelProperty(value = "上次执行时间")
    private Date prevFireTime;

    @ApiModelProperty(value = "任务第一次执行时间")
    private Date startTime;

    @ApiModelProperty(value = "时间表达式")
    private String cronExpression;

    @ApiModelProperty(value = "实例名称")
    private String instanceName;

    @ApiModelProperty(value = "上次检查时间")
    private Date lastCheckinTime;

    @ApiModelProperty(value = "检查间隔")
    private BigDecimal checkinInterval;

    @ApiModelProperty(value = "")
    private String misfireInstr;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "id")
    private Long id;

}
