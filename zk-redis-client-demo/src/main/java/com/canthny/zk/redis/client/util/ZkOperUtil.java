package com.canthny.zk.redis.client.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * Description： zk操作
 * Created By tanghao on 2020/4/28
 */
public class ZkOperUtil {
    private static ZooKeeper zooKeeper = null;

    public static void init(){
        try {
            zooKeeper = new ZooKeeper("192.168.8.145:2181",60000,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getData(ZooKeeper zooKeeper, String path, Watcher watcher, Stat stat){
        try {
            return new String(zooKeeper.getData(path,watcher,stat));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
