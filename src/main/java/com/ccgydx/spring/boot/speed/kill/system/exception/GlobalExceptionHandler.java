package com.ccgydx.spring.boot.speed.kill.system.exception;

import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Description :全局异常拦截器
 * @Author 刘龙
 * @Date 2020/3/16 22:08
 * @Version 1.0
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 拦截所有的异常
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandle(HttpServletRequest request,Exception e){
//        e.printStackTrace()；
        if(e instanceof GlobalException){
            GlobalException ex=(GlobalException) e;
            CodeMsg cm = ex.getCm();
            return  Result.error(cm);
        }
        //如果异常是我们绑定的异常
        else if(e instanceof BindException){
            BindException ex=(BindException) e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        }else{
            //打印看看是什么异常
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
