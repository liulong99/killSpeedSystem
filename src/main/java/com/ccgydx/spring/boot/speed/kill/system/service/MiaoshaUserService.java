package com.ccgydx.spring.boot.speed.kill.system.service;

import com.ccgydx.spring.boot.speed.kill.system.domain.povo.LoginVo;
import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.domain.povo.RegisterVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface MiaoshaUserService{
    /**
     * 根据id 查询判断用户是否存在
     * @param id
     * @return
     */
    public MiaoshaUser getById(Long id);

    /**
     * 登录
     * @param loginVo
     * @return
     */
    public boolean login(HttpServletResponse response, LoginVo loginVo);

    /**
     * 根据token的值从redis中取value
     * @param token
     * @return
     */
    public MiaoshaUser getUserByToken(HttpServletResponse response,String token);

    /**
     * 注册
     * @param registerVo
     * @return
     */
    public boolean register(RegisterVo registerVo);

    /**
     * 修改密码
     * @param token
     * @param id
     * @param formPass
     * @return
     */
    public boolean updatePassword(String token,long id,String formPass);
}
