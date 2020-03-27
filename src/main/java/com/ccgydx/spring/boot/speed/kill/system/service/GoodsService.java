package com.ccgydx.spring.boot.speed.kill.system.service;

import com.ccgydx.spring.boot.speed.kill.system.domain.GoodsVo;

import java.util.List;

public interface GoodsService{

    /**
     * 商品和秒杀商品列表
     * @return
     */
    public List<GoodsVo> listGoodsVo();

    /**
     * 秒杀商品详情列表，查看秒杀商品是否有库存
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(long goodsId);

}
