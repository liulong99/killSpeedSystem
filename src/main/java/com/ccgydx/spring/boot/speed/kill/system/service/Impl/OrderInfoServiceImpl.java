package com.ccgydx.spring.boot.speed.kill.system.service.Impl;
import java.util.Date;

import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import com.ccgydx.spring.boot.speed.kill.system.redis.key.OrderKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ccgydx.spring.boot.speed.kill.system.mapper.OrderInfoMapper;
import com.ccgydx.spring.boot.speed.kill.system.service.OrderInfoService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


@Service
public class OrderInfoServiceImpl implements OrderInfoService{

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 下订单，并生成订单
     * @param miaoshaUser
     * @param goodsVo
     * @return
     */
    @Transactional
    @Override
    public OrderInfo createOrder(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        //2、下订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setUserId(miaoshaUser.getId());
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(goodsVo.getMiaoshaPrice());
        orderInfo.setOrderChannel((byte)0);
        orderInfo.setStatus((byte)0);
        orderInfo.setCreateDate(new Date());
        orderInfo.setPayDate(new Date());
        orderInfoMapper.insert(orderInfo);
        Long orderId = orderInfo.getId();
        //3、写入秒杀订单
        MiaoshaOrder miaoshaOrder=new MiaoshaOrder();
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(miaoshaUser.getId());
        miaoshaOrder.setGoodsId(goodsVo.getId());
        orderInfoMapper.insertMiaoshaOrder(miaoshaOrder);
        redisService.set(OrderKey.getMiaoshaOrderByUidGid,""+miaoshaOrder.getId()+"_"+goodsVo.getId(),miaoshaOrder);
        return orderInfo;
    }

    /**
     * 根据订单id获取订单信息
    * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderById(long orderId) {
        Example example=new Example(OrderInfo.class);
        example.createCriteria().andEqualTo("id",orderId);
        return orderInfoMapper.selectOneByExample(example);
    }
}
