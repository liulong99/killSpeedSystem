package com.ccgydx.spring.boot.speed.kill.system.redis;

/**
 * @Description : 用户模块key的前缀
 * @Author 刘龙
 * @Date 2020/3/11 14:32
 * @Version 1.0
 **/
public class UserKey extends BasePrefix {

    private UserKey(String prefix){
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
