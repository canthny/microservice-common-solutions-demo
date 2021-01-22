package com.canthny.pay.system.account.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description： nacos配置中心测试
 * Created By tanghao on 2021/1/22
 */
@RestController
@RequestMapping("/nacos-config")
@RefreshScope
public class NacosConfigTestController {

    @Value("${config.test.content:1}")
    private String content;

    @GetMapping("/testGet")
    public String testGet(){
        return content;
    }
}
