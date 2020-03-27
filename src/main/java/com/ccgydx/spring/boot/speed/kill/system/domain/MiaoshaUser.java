package com.ccgydx.spring.boot.speed.kill.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Table(name = "miaosha_user")
public class MiaoshaUser implements Serializable {
    /**
     * 用户ID，手机号码
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(value = "用户账号")
    private Long id;

    @Column(name = "nickname")
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * MD5(MD5(pass明文+固定salt)+salt)
     */
    @ApiModelProperty(value = "用户密码")
    @Column(name = "`password`")
    private String password;

    @Column(name = "salt")
    @ApiModelProperty(value = "随机字符串")
    private String salt;

    /**
     * 头像，云存储的ID
     */
    @Column(name = "head")
    @ApiModelProperty(value = "头像")
    private String head;

    /**
     * 注册时间
     */
    @Column(name = "register_date")
    @ApiModelProperty(value = "注册时间")
    private Date registerDate;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_date")
    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginDate;

    /**
     * 登录次数
     */
    @Column(name = "login_count")
    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    private static final long serialVersionUID = 1L;
}