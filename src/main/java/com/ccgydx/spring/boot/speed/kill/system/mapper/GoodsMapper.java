package com.ccgydx.spring.boot.speed.kill.system.mapper;

import com.ccgydx.spring.boot.speed.kill.system.domain.Goods;
import com.ccgydx.spring.boot.speed.kill.system.domain.GoodsVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface GoodsMapper extends MyMapper<Goods> {
    /**
     * 商品和秒杀商品列表
     * @return
     */
    public List<GoodsVo> listGoodsVo();

    /**
     * 秒杀商品详情列表
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);
}