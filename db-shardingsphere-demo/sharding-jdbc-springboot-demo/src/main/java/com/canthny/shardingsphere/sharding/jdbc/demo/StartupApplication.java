package com.canthny.shardingsphere.sharding.jdbc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Description： TODO
 * Created By tanghao on 2019/11/27
 */
@EnableJpaRepositories(basePackages = { "com.canthny.shardingsphere.springboot.jpa.dao" })
//这里如果用这个注解扫描就需要跟StartupApplication一个package下或者在interface上加@Repository注解
//@ComponentScan(value = {"com.canthny.shardingsphere.sharding.jdbc.demo","com.canthny.shardingsphere.springboot.jpa.dao"})
@EntityScan(basePackages = "com.canthny.shardingsphere.springboot.jpa.entity")
@SpringBootApplication
public class StartupApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class,args);
    }
}
