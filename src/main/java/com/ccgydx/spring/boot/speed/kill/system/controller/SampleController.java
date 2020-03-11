package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import com.ccgydx.spring.boot.speed.kill.system.redis.UserKey;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/3/11 1:25
 * @Version 1.0
 **/
@RestController
public class SampleController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/hello")
    public String hello(){
        return "hello spring boot";
    }

    @GetMapping(value = "/redis/get")
    @ResponseBody
    public Result<Long> redisGet(){
        Long value1 = redisService.get(UserKey.getById,""+1, Long.class);
        return Result.success(value1);
    }

//    @GetMapping(value = "/redis/set")
//    @ResponseBody
//    public Result<String> redisSet(){
//        boolean b= redisService.set("key2","value2");
//        String str = redisService.get("key2",String.class);
//        return Result.success(str);
//    }

}
