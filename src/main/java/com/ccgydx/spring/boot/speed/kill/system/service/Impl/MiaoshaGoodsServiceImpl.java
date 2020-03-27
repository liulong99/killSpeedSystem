package com.ccgydx.spring.boot.speed.kill.system.service.Impl;

import com.ccgydx.spring.boot.speed.kill.system.domain.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaGoods;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ccgydx.spring.boot.speed.kill.system.mapper.MiaoshaGoodsMapper;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaGoodsService;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;

@Service
public class MiaoshaGoodsServiceImpl implements MiaoshaGoodsService{

    @Resource
    private MiaoshaGoodsMapper miaoshaGoodsMapper;

    @Autowired
    OrderInfoService orderInfoService;

    @Transactional
    @Override
    public OrderInfo miaosha(MiaoshaUser miaoshaUser, GoodsVo goodsVo) {
        //1、减库存
        MiaoshaGoods miaoshaGoods=new MiaoshaGoods();
        Example example=new Example(MiaoshaGoods.class);
        example.createCriteria().andEqualTo("goodsId",goodsVo.getId());
        miaoshaGoods.setStockCount(goodsVo.getStockCount()-1);
        miaoshaGoods.setStartDate(goodsVo.getStartDate());
        miaoshaGoods.setEndDate(goodsVo.getEndDate());
        miaoshaGoods.setMiaoshaPrice(goodsVo.getMiaoshaPrice());
        miaoshaGoods.setGoodsId(goodsVo.getId());
        miaoshaGoodsMapper.updateByExample(miaoshaGoods,example);
        //2、下订单   3、写入秒杀订单
        OrderInfo orderInfo=orderInfoService.createOrder(miaoshaUser,goodsVo);
        return orderInfo;
    }
}
