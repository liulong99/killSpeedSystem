package com.ccgydx.spring.boot.speed.kill.system.redis;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/22 22:43
 * @Version 1.0
 **/
public class GoodsKey extends BasePrefix{

    private GoodsKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }
    public static GoodsKey getGoodsList=new GoodsKey(60,"goodsList");
    public static GoodsKey getGoodsDetail=new GoodsKey(60,"goodsDetail");
}
