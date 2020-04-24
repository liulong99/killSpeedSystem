package com.ccgydx.spring.boot.speed.kill.system.service.Impl;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import com.ccgydx.spring.boot.speed.kill.system.redis.key.OrderKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ccgydx.spring.boot.speed.kill.system.mapper.MiaoshaOrderMapper;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaOrderService;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService{

    @Resource
    private MiaoshaOrderMapper miaoshaOrderMapper;

    @Autowired
    private RedisService redisService;

    /**
     * 判断用户是否秒杀过商品
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderbyUserIdAndGoodId(long userId, long goodsId) {
        return redisService.get(OrderKey.getMiaoshaOrderByUidGid,""+userId+"_"+goodsId,MiaoshaOrder.class);
    }
}
