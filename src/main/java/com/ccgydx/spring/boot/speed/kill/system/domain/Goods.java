package com.ccgydx.spring.boot.speed.kill.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "goods")
public class Goods implements Serializable {
    /**
     * 商品ID
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品标题
     */
    @Column(name = "goods_title")
    private String goodsTitle;

    /**
     * 商品图片
     */
    @Column(name = "goods_img")
    private String goodsImg;

    /**
     * 商品详情
     */
    @Column(name = "goods_detail")
    private String goodsDetail;

    /**
     * 商品价格
     */
    @Column(name = "goods_price")
    private BigDecimal goodsPrice;

    /**
     * 商品库存
     */
    @Column(name = "goods_stock")
    private Integer goodsStock;

    private static final long serialVersionUID = 1L;
}