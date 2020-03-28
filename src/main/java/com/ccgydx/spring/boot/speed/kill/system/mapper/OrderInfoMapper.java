package com.ccgydx.spring.boot.speed.kill.system.mapper;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import tk.mybatis.mapper.MyMapper;

public interface OrderInfoMapper extends MyMapper<OrderInfo> {

    /**
     *写入订单
     * @param miaoshaOrder
     */
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}