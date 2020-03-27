package com.ccgydx.spring.boot.speed.kill.system.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "miaosha_order")
public class MiaoshaOrder implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订单ID
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    private static final long serialVersionUID = 1L;
}