package com.taotao.rest.dao.impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 集群版jedis dao
 * jedisCluster不用关闭
 * @author: xianzhixianzhixian on 2018/8/29
 */
public class JedisClientCluster implements JedisClient {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) {
        String str = jedisCluster.get(key);
        return str;
    }

    @Override
    public String set(String key, String value) {
        String str = jedisCluster.set(key,value);
        return str;
    }

    @Override
    public String hget(String hkey, String key) {
        String str = jedisCluster.hget(hkey,key);
        return str;
    }

    @Override
    public Long hset(String hkey, String key, String value) {
        Long result = jedisCluster.hset(hkey, key, value);
        return result;
    }

    @Override
    public Long incr(String key) {
        Long result = jedisCluster.incr(key);
        return result;
    }

    @Override
    public Long expire(String key, Integer second) {
        Long result = jedisCluster.expire(key, second);
        return result;
    }

    @Override
    public Long ttl(String key) {
        Long result = jedisCluster.ttl(key);
        return result;
    }

    @Override
    public Long del(String key) {
        Long result = jedisCluster.del(key);
        return result;
    }

    @Override
    public Long hdel(String hkey, String key) {
        Long result = jedisCluster.hdel(hkey,key);
        return result;
    }
}
