package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaOrder;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import com.ccgydx.spring.boot.speed.kill.system.service.GoodsService;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaGoodsService;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaOrderService;
import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :主要实现秒杀功能
 * @Author 刘龙
 * @Date 2020/3/26 20:50
 * @Version 1.0
 **/
@Controller
@RequestMapping("miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    MiaoshaOrderService miaoshaOrderService;

    @Autowired
    MiaoshaGoodsService miaoshaGoodsService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model,MiaoshaUser miaoshaUser){
        return Result.success(miaoshaUser);
    }

    /**
     * 秒杀功能实现
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @RequestMapping("/doMiaosha")
    public String doMiaosha(Model model, MiaoshaUser miaoshaUser,@RequestParam("goodsId")long goodsId){
        model.addAttribute("miaoshaUser",miaoshaUser);
        if(miaoshaUser==null){
            return "login";
        }
        //判断商品是否有库存
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        int stockCount = goodsVo.getStockCount();
        if(stockCount<=0){
            model.addAttribute("miaoshaErrorMsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_error";
        }
        //如果有库存判断是否已经秒杀到了，防止某些用户重复秒杀
        MiaoshaOrder miaoshaOrder=miaoshaOrderService.getMiaoshaOrderbyUserIdAndGoodId(miaoshaUser.getId(),goodsId);
        //如果用户已经秒杀到了
        if(miaoshaOrder!=null){
            model.addAttribute("miaoshaErrorMsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_error";
        }
        //既没有秒杀到也有库存的情况才能进行秒杀
        //1、减库存  2、下订单   3、写入秒杀订单
        OrderInfo orderInfo=miaoshaGoodsService.miaosha(miaoshaUser,goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goodsVo", goodsVo);
        return "order_detail";
    }
}
