package com.ccgydx.spring.boot.speed.kill.system.redis.key;

import com.ccgydx.spring.boot.speed.kill.system.redis.BasePrefix;

/**
 * @Description : 用户模块key的前缀
 * @Author 刘龙
 * @Date 2020/3/11 14:32
 * @Version 1.0
 **/
public class MiaoshaUserKey extends BasePrefix {

    //设置cookie有效时间2天
    public static final int TOKEN_EXPIRE=3600*24*2;

    private MiaoshaUserKey(int expireSeconds,String prefix){
        super(expireSeconds,prefix);
    }

    public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE,"token");
    public static MiaoshaUserKey getById = new MiaoshaUserKey(0,"id");
}
