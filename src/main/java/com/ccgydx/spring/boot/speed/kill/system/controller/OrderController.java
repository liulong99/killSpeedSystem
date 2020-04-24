package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import com.ccgydx.spring.boot.speed.kill.system.domain.povo.GoodsVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.povo.OrderDetailVo;
import com.ccgydx.spring.boot.speed.kill.system.service.GoodsService;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import com.ccgydx.spring.boot.speed.kill.system.service.OrderInfoService;
import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/24 14:55
 * @Version 1.0
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, MiaoshaUser miaoshaUser, @RequestParam("orderId")long orderId){
        if(miaoshaUser==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo=orderInfoService.getOrderById(orderId);
        if(orderInfo==null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        Long goodsId = orderInfo.getGoodsId();
        GoodsVo goodsVo = goodsService.getGoodsVoByGoodsId(goodsId);
        OrderDetailVo orderDetailVo=new OrderDetailVo();
        orderDetailVo.setGoodsVo(goodsVo);
        orderDetailVo.setMiaoshaUser(miaoshaUser);
        orderDetailVo.setOrderInfo(orderInfo);
        return Result.success(orderDetailVo);
    }
}
