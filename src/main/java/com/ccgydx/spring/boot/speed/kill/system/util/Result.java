package com.ccgydx.spring.boot.speed.kill.system.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description :统一结果集
 * @Author 刘龙
 * @Date 2020/3/10 21:57
 * @Version 1.0
 **/
@Data
public class Result<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    /**
     * 成功时调用
     * @param data 表示传的成功信息
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     * @param codeMsg 包含失败的code和msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }

    /**
     * 成功时默认构造函数，封装3个信息
     * @param data
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 失败时的默认构造函数，封装两个信息
     * @param codeMsg
     */
    private Result(CodeMsg codeMsg){
        if(codeMsg == null){
            return;
        }else{
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }
}
