package com.canthny.quartz.controller;

import com.canthny.common.base.BaseResponse;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description： 定时任务Controller
 * Created By tanghao on 2019/8/26
 */
@RestController
@Api(value = "任务调度接口")
public class ScheduleController {

    @PostMapping
    public BaseResponse addNewJob(){

        return BaseResponse.buildSuccessReponse();
    }

    @GetMapping
    public void getJobList(){

    }
}
