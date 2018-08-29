package com.taotao.rest.jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * Jedis客户端测试类
 * @author: xianzhixianzhixian on 2018/8/29
 */
public class JedisTest {

    /**
     * 测试Jedis连接集群
     */
    public static void testJedisCluster(){
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.56.102",7001));
        nodes.add(new HostAndPort("192.168.56.102",7002));
        nodes.add(new HostAndPort("192.168.56.103",7003));
        nodes.add(new HostAndPort("192.168.56.103",7004));
        nodes.add(new HostAndPort("192.168.56.104",7005));
        nodes.add(new HostAndPort("192.168.56.104",7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("a", "10");
        System.out.println(cluster.get("a"));
    }

    public static void main(String[] args){
        testJedisCluster();
    }
}
