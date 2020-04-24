package com.ccgydx.spring.boot.speed.kill.system.domain.povo;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/23 16:24
 * @Version 1.0
 **/
@Data
public class GoodsDetailVo implements Serializable {
    private static final long serialVersionUID = -4735414143909975939L;
    private int miaoshaStatus=0;
    private int remainSecond=0;
    private GoodsVo goodsVo;
    private MiaoshaUser miaoshaUser;
}
