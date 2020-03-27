package com.ccgydx.spring.boot.speed.kill.system.util;

import java.util.UUID;

/**
 * @Description :UUID工具类
 * @Author 刘龙
 * @Date 2020/3/17 13:44
 * @Version 1.0
 **/
public class UUIDUtils {
    public static String uuid(){
        //将生成的uuid中的横杠替换成空格返回
        return UUID.randomUUID().toString().replace("-","");
    }
}
