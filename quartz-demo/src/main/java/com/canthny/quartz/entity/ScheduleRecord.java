package com.canthny.quartz.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by welink on 2017/11/30.
 */
public class ScheduleRecord{
    @ApiModelProperty(value = "任务调度id")
    private Long scheduleId;
    @ApiModelProperty(value = "任务名称")
    private String jobName;
    @ApiModelProperty(value = "调度时间")
    private Date startTime;
    @ApiModelProperty(value="调度状态")
    private String status;

}
