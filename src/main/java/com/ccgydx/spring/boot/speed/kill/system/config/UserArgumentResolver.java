package com.ccgydx.spring.boot.speed.kill.system.config;

import com.ccgydx.spring.boot.speed.kill.system.domain.MiaoshaUser;
import com.ccgydx.spring.boot.speed.kill.system.service.Impl.MiaoshaUserServiceImpl;
import com.ccgydx.spring.boot.speed.kill.system.service.MiaoshaUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description :addArgumentResolvers的解析类
 * @Author 刘龙
 * @Date 2020/3/17 15:29
 * @Version 1.0
 **/
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MiaoshaUserService miaoshaUserService;

    /**
     * 解析的初始化方法，判断methodParameter的类型是否是MiaoshaUser
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getParameterType();
        return parameterType== MiaoshaUser.class;
    }

    /**
     * supportsParameter()方法返回为真才执行这个方法，这个方法才是真正将token从cookie里获取返回给Controller
     * @param methodParameter
     * @param modelAndViewContainer
     * @param nativeWebRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        String paramToken=request.getParameter(MiaoshaUserServiceImpl.COOKIE_NAME_TOKEN);
        String cookieToken=getCookieValue(request,MiaoshaUserServiceImpl.COOKIE_NAME_TOKEN);
        if(StringUtils.isEmpty(paramToken)&&StringUtils.isEmpty(cookieToken)){
            return null;
        }
        String token= StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        MiaoshaUser miaoshaUser=miaoshaUserService.getUserByToken(response,token);
        return miaoshaUser;
    }

    /**
     * 从request里获取cookieToken
     * @param request
     * @param token
     * @return
     */
    private String getCookieValue(HttpServletRequest request,String token){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals(token)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
