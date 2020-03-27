package com.ccgydx.spring.boot.speed.kill.system.service.Impl;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.ccgydx.spring.boot.speed.kill.system.mapper.MiaoshaOrderMapper;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaOrderService;
import tk.mybatis.mapper.entity.Example;

@Service
public class MiaoshaOrderServiceImpl implements MiaoshaOrderService{

    @Resource
    private MiaoshaOrderMapper miaoshaOrderMapper;

    /**
     * 判断用户是否秒杀过商品
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public MiaoshaOrder getMiaoshaOrderbyUserIdAndGoodId(long userId, long goodsId) {
        Example example=new Example(MiaoshaOrder.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId).andEqualTo("goodsId",goodsId);
        System.out.println("userId: "+userId+", goodsId: "+goodsId);
        MiaoshaOrder miaoshaOrder = miaoshaOrderMapper.selectOneByExample(example);
        System.out.println("------查看用户是否重复秒杀-----");
        System.out.println("miaoshaOrder"+miaoshaOrder+"kkk");
        return miaoshaOrder;
    }
}
