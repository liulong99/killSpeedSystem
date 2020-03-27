package com.ccgydx.spring.boot.speed.kill.system.service;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;

public interface MiaoshaOrderService{

    /**
     * 判断用户是否秒杀过商品
     * @param userId
     * @param goodsId
     * @return
     */
    MiaoshaOrder getMiaoshaOrderbyUserIdAndGoodId(long userId, long goodsId);
}
