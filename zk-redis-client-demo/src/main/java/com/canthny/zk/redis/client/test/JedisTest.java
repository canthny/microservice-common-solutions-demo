package com.canthny.zk.redis.client.test;

import redis.clients.jedis.Jedis;

/**
 * Description： TODO
 * Created By tanghao on 2020/4/29
 */
public class JedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.8.145",6379);
        jedis.get("");
    }
}
