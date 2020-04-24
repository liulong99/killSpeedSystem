package com.ccgydx.spring.boot.speed.kill.system.redis;

import com.ccgydx.spring.boot.speed.kill.system.config.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Description : 创建 jedisPool 类
 * @Author 刘龙
 * @Date 2020/3/11 1:34
 * @Version 1.0
 **/
@Service
public class RedisPoolFactory {

    @Autowired
    private RedisConfig redisConfig;

    /**
     * 返回一个 JedisPool 对象，用于生成 jedis
     * @return
     */
    @Bean
    public JedisPool jedisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jedisPool;
    }

}
