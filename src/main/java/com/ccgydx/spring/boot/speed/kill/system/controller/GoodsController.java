package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.redis.GoodsKey;
import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import com.ccgydx.spring.boot.speed.kill.system.service.GoodsService;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Autowired
    private RedisService redisService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;


    /**
     * 跳转到商品列表页面
     * @param model
     * @param model
     * @param miaoshaUser
     * @return
     *
     *
     * jmeter
     * 样本 5000*10
     * 吞吐量 816.9
     * 异常71.58%
     */
    @ApiOperation(value = "返回商品页面")
    @RequestMapping(value = "/toList",produces = "text/html")
    @ResponseBody
    public String toList(HttpServletRequest request, HttpServletResponse response,Model model, MiaoshaUser miaoshaUser){
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if(!StringUtils.isEmpty(html)){
            return html;
        }

        model.addAttribute("miaoshaUser",miaoshaUser);
        //查询商品列表
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList",goodsList);

        //手动渲染
        WebContext ctx=new WebContext(request,response,request.getServletContext(),
                request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if(!StringUtils.isBlank(html)){
            redisService.set(GoodsKey.getGoodsList,"",html);
        }
        return html;
    }

    /**
     * 返回商品详情页面
     * @param model
     * @param miaoshaUser
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "商品详情页面")
    @RequestMapping(value = "/toDetail/{goodsId}",produces = "text/html")
    @ResponseBody
    public String toDetail(HttpServletRequest request,HttpServletResponse response,
                           Model model, MiaoshaUser miaoshaUser, @PathVariable("goodsId")long goodsId){
        model.addAttribute("miaoshaUser",miaoshaUser);

        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if(!StringUtils.isBlank(html)){
            return html;
        }

        //手动渲染
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
//        return "goods_detail";

        WebContext ctx=new WebContext(request,response,request.getServletContext(),
                request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if(!StringUtils.isBlank(html)){
            redisService.set(GoodsKey.getGoodsDetail,""+goodsId,html);
        }
        return html;
    }


}
