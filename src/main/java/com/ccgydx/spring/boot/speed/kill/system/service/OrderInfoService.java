package com.ccgydx.spring.boot.speed.kill.system.service;

import com.ccgydx.spring.boot.speed.kill.system.domain.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;

public interface OrderInfoService{

    /**
     * 下订单，并生成订单
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    OrderInfo createOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo);
}
