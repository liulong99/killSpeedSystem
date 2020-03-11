package com.ccgydx.spring.boot.speed.kill.system.redis;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/11 14:21
 * @Version 1.0
 **/
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();
}
