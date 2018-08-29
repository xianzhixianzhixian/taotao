package com.taotao.rest.jedis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
        nodes.add(new HostAndPort("192.168.56.102", 7001));
        nodes.add(new HostAndPort("192.168.56.102", 7002));
        nodes.add(new HostAndPort("192.168.56.103", 7003));
        nodes.add(new HostAndPort("192.168.56.103", 7004));
        nodes.add(new HostAndPort("192.168.56.104", 7005));
        nodes.add(new HostAndPort("192.168.56.104", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("a", "10");
        System.out.println(cluster.get("a"));
        //关闭连接
        try{
            cluster.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 测试Redis集群和Spring整合
     */
    public static void testJedisSpringConfig(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("jedisCluster");
        System.out.println(jedisCluster.get("a"));
    }

    public static void main(String[] args){
        testJedisCluster();
        testJedisSpringConfig();
    }
}
