package com.ccgydx.spring.boot.speed.kill.system.redis;

/**
 * @Description :订单模块key的前缀
 * @Author 刘龙
 * @Date 2020/3/11 14:33
 * @Version 1.0
 **/
public class OrderKey extends BasePrefix {

    private OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getById = new OrderKey("id");
    public static OrderKey getByName = new OrderKey("name");
}
