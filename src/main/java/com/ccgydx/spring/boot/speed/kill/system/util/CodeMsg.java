package com.ccgydx.spring.boot.speed.kill.system.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description : 失败时的封装对象类
 * @Author 刘龙
 * @Date 2020/3/10 22:09
 * @Version 1.0
 **/
@Data
public class CodeMsg implements Serializable {
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty(value = "异常信息")
    private String msg;

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回带参数的错误码
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object...args){
        int code=this.code;
        String message=String.format(this.msg,args);
        return new CodeMsg(code,message);
    }

    public static CodeMsg SUCCESS=new CodeMsg(0,"success");
    //通用异常
    public static CodeMsg SERVER_ERROR=new CodeMsg(500100,"服务端异常");
    public static CodeMsg BIND_ERROR=new CodeMsg(500101,"参数校验异常：%s");

    //登录模块异常5002XX
    public static CodeMsg SESSION_ERROR=new CodeMsg(500210,"Session不存在或已经失效");
    public static CodeMsg PASSWORD_EMPTY=new CodeMsg(500211,"登录密码不能为空");
    public static CodeMsg MOBILE_EMPTY=new CodeMsg(500212,"手机号不能为空");
    public static CodeMsg MOBILE_ERROR=new CodeMsg(500213,"手机号格式不正确");
    public static CodeMsg USER_NOT_EXIST=new CodeMsg(500214,"用户不存在");
    public static CodeMsg PASSWORD_ERROR=new CodeMsg(500215,"用户名密码不一致");
    public static CodeMsg REGISTER_ERROR=new CodeMsg(500216,"注册失败");


    //商品模块异常5003XX

    //订单模块异常5004XX
    public static CodeMsg ORDER_NOT_EXIST=new CodeMsg(500400,"订单不存在");

    //秒杀模块异常5005XX
    public static CodeMsg MIAO_SHA_OVER=new CodeMsg(500500,"商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA=new CodeMsg(500501,"不能重复秒杀");
}
