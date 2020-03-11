package com.ccgydx.spring.boot.speed.kill.system.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Description :Redis方法类
 * @Author 刘龙
 * @Date 2020/3/11 0:04
 * @Version 1.0
 **/
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;

    /**
     * 根据 key 获取 value
     * @param prefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(KeyPrefix prefix,String key,Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            //将String类型转成T类型返回出去
            T t = stringToBean(str, clazz);
            return t;
        }finally {
            //释放连接，放到returnResource中
            returnToPool(jedis);
        }
    }


    /**
     * 设置 key-value 键值对放到 redis 中
     * @param prefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    public <T> boolean set(KeyPrefix prefix,String key,T value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //将T类型转成String类型放入redis中
            String str = beenToString(value);
            if(str == null || str.length() <= 0){
                return false;
            }
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if(seconds <= 0){
                //永不过期
                jedis.set(realKey,str);
            }else{
                //存在过期时间
                jedis.setex(realKey,seconds,str);
            }
            return true;
        }finally {
            //释放连接，放到returnResource中
            returnToPool(jedis);
        }
    }

    /**
     * 判断redis缓存中是否存在key
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> boolean exits(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        }finally {
            //释放连接，放到returnResource中
            returnToPool(jedis);
        }
    }

    /**
     * 自增
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long incr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        }finally {
            //释放连接，放到returnResource中
            returnToPool(jedis);
        }
    }

    /**
     * 自减
     * @param prefix
     * @param key
     * @param <T>
     * @return
     */
    public <T> Long decr(KeyPrefix prefix,String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        }finally {
            //释放连接，放到returnResource中
            returnToPool(jedis);
        }
    }

    /**
     * String 类型转 T
     * @param str
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> T stringToBean(String str,Class<T> clazz){
        if(str == null || str.length()<= 0 || clazz == null){
            return null;
        }else if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else{
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    /**
     * T 类型转 String
     * @param value
     * @param <T>
     * @return
     */
    private <T> String beenToString(T value){
        if(value == null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return ""+value;
        }else if(clazz == String.class){
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class){
            return ""+value;
        }else{
            return JSON.toJSONString(value);
        }
    }

    /**
     * 释放连接
     * @param jedis
     */
    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

}
