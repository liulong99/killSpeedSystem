package com.ccgydx.spring.boot.speed.kill.system.service;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;

public interface MiaoshaGoodsService{

    /**
     * 用户可以进行秒杀操作
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    OrderInfo miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo);
}
