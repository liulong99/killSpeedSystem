package com.ccgydx.spring.boot.speed.kill.system.domain.povo;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.OrderInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/24 15:00
 * @Version 1.0
 **/
@Data
public class OrderDetailVo implements Serializable {
    private static final long serialVersionUID = -1876859391712795546L;
    private GoodsVo goodsVo;
    private OrderInfo orderInfo;
    private MiaoshaUser miaoshaUser;
}
