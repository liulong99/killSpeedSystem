package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.service.GoodsService;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description : 商品列表
 * @Author 刘龙
 * @Date 2020/3/16 16:03
 * @Version 1.0
 **/
@Controller
@RequestMapping("goods")
@Api(description="商品管理")
public class GoodsController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 跳转到商品列表页面
     * @param model
     * @param model
     * @param miaoshaUser
     * @return
     */
    @ApiOperation(value = "返回商品页面")
    @RequestMapping("/toList")
    public String toList(Model model,MiaoshaUser miaoshaUser){
        model.addAttribute("miaoshaUser",miaoshaUser);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    /**
     * 返回商品详情页面
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商品详情页面")
    @RequestMapping("/toDetail/{goodsId}")
    public String toDetail(Model model, MiaoshaUser miaoshaUser, @PathVariable("goodsId")long goodsId){
        model.addAttribute("miaoshaUser",miaoshaUser);
        GoodsVo goodsVo=goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goodsVo",goodsVo);
        long startAt = goodsVo.getStartDate().getTime();
        long endAt = goodsVo.getEndDate().getTime();
        long now = System.currentTimeMillis();
        //miaoshaStatus=0表示秒杀未开始，=1表示秒杀进行中，=2表示秒杀已经结束
        int miaoshaStatus=0;
        //秒杀还有多久开始 remainSecond=0表示进行中，>0表示还在倒计时，<0表示已经结束
        int remainSecond=0;
        //判断秒杀是否进行
        if(now<startAt){
            //秒杀未开始，倒计时
            miaoshaStatus=0;
            remainSecond=(int)(startAt-now)/1000;
        }else if(now>endAt){
            //秒杀结束
            miaoshaStatus=2;
            remainSecond=-1;
        }else{
            //秒杀进行中
            miaoshaStatus=1;
            remainSecond=0;
        }
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSecond",remainSecond);
        return "goods_detail";
    }


}
