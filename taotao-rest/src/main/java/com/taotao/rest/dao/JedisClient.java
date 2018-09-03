package com.taotao.rest.dao;

/**
 * Jedis dao
 * @author: xianzhixianzhixian on 2018/8/29
 */
public interface JedisClient {

    /**
     * 通过key获取redis中的value
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置redis中的key和value
     * @param key
     * @param value
     * @return
     */
    String set(String key, String value);

    /**
     * 获得hash值
     * @param hkey
     * @param key
     * @return
     */
    String hget(String hkey,String key);

    /**
     * 设置hash值
     * @param hkey
     * @param key
     * @param value
     * @return
     */
    Long hset(String hkey, String key, String value);

    /**
     * redis自增值
     * @param key
     * @return
     */
    Long incr(String key);

    /**
     * 设置key的过期时间(秒)
     * @param key
     * @param second
     * @return
     */
    Long expire(String key,Integer second);

    /**
     * 获取key的过期时间,返回值大于零表示过期时间,返回值为-1表示永不过期,返回值-2表示已过期
     * @param key
     * @return
     */
    Long ttl(String key);

    /**
     * 根据key删除对应的value
     * @param key
     * @return
     */
    Long del(String key);

    /**
     * 删除hash中的值
     * @param hkey
     * @param key
     * @return
     */
    Long hdel(String hkey,String key);
}
