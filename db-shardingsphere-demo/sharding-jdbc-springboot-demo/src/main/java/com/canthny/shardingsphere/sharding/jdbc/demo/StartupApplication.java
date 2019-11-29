package com.canthny.shardingsphere.sharding.jdbc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Description： TODO
 * Created By tanghao on 2019/11/27
 */
@ComponentScan("com.canthny.shardingsphere.springboot.jpa")
@EntityScan(basePackages = "com.canthny.shardingsphere.springboot.jpa.entity")
@SpringBootApplication(exclude = JtaAutoConfiguration.class)
public class StartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class,args);
    }
}
