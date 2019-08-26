package com.canthny.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description： 定时任务管理启动类
 * Created By tanghao on 2019/8/26
 */
@SpringBootApplication
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
