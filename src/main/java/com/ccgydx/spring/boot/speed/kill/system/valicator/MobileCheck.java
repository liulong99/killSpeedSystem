package com.ccgydx.spring.boot.speed.kill.system.valicator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description :手机号校验类
 * @Author 刘龙
 * @Date 2020/3/16 17:53
 * @Version 1.0
 **/
public class MobileCheck {

    /**
     * 手机号验证
     * @param  mobile
     * @return 验证通过返回true
     */
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

}
