package com.ccgydx.spring.boot.speed.kill.system.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Description :Md5加密工具类
 * @Author 刘龙
 * @Date 2020/3/16 14:34
 * @Version 1.0
 **/
public class Md5Util {

    public static final String salt="1a2b3c4d";
    /**
     * 第一次md5加密 将用户输入的密码加密存入form表单
     * @param inputPass 用户输入的密码
     * @return
     */
    public static String inputPassToFormPass(String inputPass){
        //if inputPass=123456     then str=1a21234562b
        String str=""+salt.charAt(0)+salt.charAt(1)+salt.charAt(2)+inputPass+salt.charAt(2)+salt.charAt(3);
        return md5(str);
    }

    /**
     * 第二次md5加密 将表单的密码再进行加密存入数据库
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDbPass(String formPass,String salt){
        String str=""+salt.charAt(0)+salt.charAt(1)+salt.charAt(2)+formPass+salt.charAt(2)+salt.charAt(3);
        return md5(str);
    }

    /**
     * 将上面两次加密放在一起了
     * @param inputPass
     * @param salt
     * @return
     */
    public static String inputPassToDbPass(String inputPass,String salt){
        String formPass=inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass,salt);
        return dbPass;
    }

    /**
     * commons 自带md5加密
     * @param str
     * @return
     */
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

}
