package com.ccgydx.spring.boot.speed.kill.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/22 20:35
 * @Version 1.0
 **/
@Controller
@RequestMapping("forget")
public class ForgetController {

    @RequestMapping("/forgetPassword")
    public String forgetPassword(){
        return "forgetPassword";
    }
}
