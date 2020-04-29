package com.canthny.zk.redis.client.util;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Description： zk操作
 * Created By tanghao on 2020/4/28
 */
@Component
public class ZkOperUtil {

    private static ZooKeeper zooKeeper = null;

    @Value("${zk.connectString}")
    private String connectString;

    @PostConstruct
    public void init(){
        try {
            zooKeeper = new ZooKeeper(connectString,60000,new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    //do nothing
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String path, Watcher watcher, Stat stat){
        try {
            if(null==watcher){
                return new String(zooKeeper.getData(path,false,stat));
            }else{
                return new String(zooKeeper.getData(path,watcher,stat));
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean setData(String path, String content, int version){
        try {
            Stat stat = zooKeeper.setData(path,content.getBytes("UTF-8"),version);
        } catch (KeeperException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void createNode(String path,String content){
        try {
            zooKeeper.create(path,content.getBytes("UTF-8"),null, CreateMode.EPHEMERAL);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String path){
        try {
            zooKeeper.delete(path,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }
}
