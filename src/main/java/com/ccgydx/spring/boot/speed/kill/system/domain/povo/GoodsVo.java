package com.ccgydx.spring.boot.speed.kill.system.domain.povo;

import com.ccgydx.spring.boot.speed.kill.system.domain.Goods;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description :商品和秒杀商品聚合实体类
 * @Author 刘龙
 * @Date 2020/3/26 15:38
 * @Version 1.0
 **/
@Data
public class GoodsVo extends Goods implements Serializable {
    private static final long serialVersionUID = -397317728843625464L;
    /**
     * 秒杀价
     */
    @Column(name = "miaosha_price")
    private BigDecimal miaoshaPrice;

    /**
     * 库存数量
     */
    @Column(name = "stock_count")
    private Integer stockCount;

    /**
     * 秒杀开始时间
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 秒杀结束时间
     */
    @Column(name = "end_date")
    private Date endDate;

}
