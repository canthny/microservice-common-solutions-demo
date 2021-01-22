package com.canthny.pay.system.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Description： TODO
 * Created By tanghao on 2021/1/21
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AccountCenterStarter {

    public static void main(String[] args) {
        SpringApplication.run(AccountCenterStarter.class,args);
    }
}
