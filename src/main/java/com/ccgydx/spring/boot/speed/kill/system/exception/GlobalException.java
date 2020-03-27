package com.ccgydx.spring.boot.speed.kill.system.exception;

import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/17 11:13
 * @Version 1.0
 **/

public class GlobalException extends RuntimeException{

    private CodeMsg cm;

    public GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm=cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
