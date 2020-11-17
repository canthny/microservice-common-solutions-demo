package com.canthny.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description： TODO
 * Created By tanghao on 2020/11/10
 */
@MapperScan(basePackages="com.canthny.mysql.data.init.dao")
@SpringBootApplication(scanBasePackages = "com.canthny.mysql")
public class ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class,args);
    }
}
