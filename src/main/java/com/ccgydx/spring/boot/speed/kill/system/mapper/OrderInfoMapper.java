package com.ccgydx.spring.boot.speed.kill.system.mapper;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.MyMapper;

public interface OrderInfoMapper extends MyMapper<OrderInfo> {

    @Insert("insert into miaosha_order(user_id,goods_id,order_id) values (#{userId},#{goodsId},#{orderId})")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}