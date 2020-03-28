package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.LoginVo;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Description : 登录
 * @Author 刘龙
 * @Date 2020/3/16 16:03
 * @Version 1.0
 **/
@Controller
@RequestMapping("login")
@Api(description = "登录管理")
public class LoginController {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/toLogin")
    @ApiOperation(value = "跳转登录页面")
    public String toLogin(){
        return "login";
    }

    /**
     * ajax异步跳转回来
     * @param response
     * @param loginVo
     * @return
     */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        //参数校验
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //验证通过才能登录
        //login只能为true 出错的时候不会返回false而是抛出异常
        boolean login = miaoshaUserService.login(response,loginVo);
        return Result.success(true);
    }
}
