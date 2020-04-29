package com.canthny.zk.redis.client.domain;

import com.canthny.zk.redis.client.enums.RedisNodeRuleEnum;

/**
 * Description： redis节点信息
 * Created By tanghao on 2020/4/28
 */
public class RedisNode {

    private String host;

    private int port;

    private RedisNodeRuleEnum role;

    public RedisNode(){

    }

    public RedisNode(String host,int port){
        this.host = host;
        this.port = port;
        this.role = null;
    }

    public RedisNode(String host,int port,RedisNodeRuleEnum role){
        this.host = host;
        this.port = port;
        this.role = role;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public RedisNodeRuleEnum getRole() {
        return role;
    }

    public void setRole(RedisNodeRuleEnum role) {
        this.role = role;
    }

    public static RedisNode parseFromConnectString(String connectString){
        String[] nodeIpPort = connectString.trim().split("\\:");
        if (nodeIpPort.length < 2) {
            throw new RuntimeException("nodeInfo is incorrect?");
        }

        int port = Integer.valueOf(nodeIpPort[1].trim());
        String host = nodeIpPort[0].trim();
        return new RedisNode(host,port,RedisNodeRuleEnum.MASTER);
    }

    public String getRedisConnectStr(){
        return new StringBuilder(this.host).append(":").append(this.port).toString();
    }
}
