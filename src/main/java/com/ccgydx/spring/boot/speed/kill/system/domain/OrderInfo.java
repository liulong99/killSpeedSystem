package com.ccgydx.spring.boot.speed.kill.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "order_info")
public class OrderInfo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

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

    /**
     * 收货地址ID
     */
    @Column(name = "delivery_addr_id")
    private Long deliveryAddrId;

    /**
     * 冗余过来的商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    @Column(name = "goods_count")
    private Integer goodsCount;

    /**
     * 商品单价
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 1pc,2android,3ios
     */
    @Column(name = "order_channel")
    private Byte orderChannel;

    /**
     * 订单状态：0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     */
    @Column(name = "`status`")
    private Byte status;

    /**
     * 订单创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 支付时间
     */
    @Column(name = "pay_date")
    private Date payDate;

    private static final long serialVersionUID = 1L;
}