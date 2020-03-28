package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.RegisterVo;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import com.ccgydx.spring.boot.speed.kill.system.util.CodeMsg;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @Description :注册类
 * @Author 刘龙
 * @Date 2020/3/22 21:10
 * @Version 1.0
 **/
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    /**
     * 返回一个注册页面
     * @return
     */
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    /**
     * 真正实现注册的方法
     * @param registerVo
     * @return
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public Result<Boolean> doRegister(@Valid RegisterVo registerVo){
        String mobile = registerVo.getMobile();
        String formPass = registerVo.getPassword();
        String nickname = registerVo.getNickname();
        boolean b = miaoshaUserService.register(registerVo);
        if(!b){
           return Result.error(CodeMsg.REGISTER_ERROR);
        }
        return Result.success(true);
    }
}
