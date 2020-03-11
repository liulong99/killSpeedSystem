package com.ccgydx.spring.boot.speed.kill.system.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Description : Redis连接配制类
 * @Author 刘龙
 * @Date 2020/3/10 23:56
 * @Version 1.0
 **/

@Component
@Data
public class RedisConfig implements Serializable {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    //单位 秒
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.poolMaxTotal}")
    private int poolMaxTotal;

    @Value("${spring.redis.poolMaxIdle}")
    private int poolMaxIdle;

    //单位 秒
    @Value("${spring.redis.poolMaxWait}")
    private int poolMaxWait;

}
