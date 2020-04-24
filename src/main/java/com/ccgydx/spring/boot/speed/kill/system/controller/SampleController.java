package com.ccgydx.spring.boot.speed.kill.system.controller;

import com.ccgydx.spring.boot.speed.kill.system.rabbitmq.MqSender;
import com.ccgydx.spring.boot.speed.kill.system.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/24 20:39
 * @Version 1.0
 **/
@Controller
@RequestMapping("demo")
public class SampleController {
    @Autowired
    private MqSender sender;

    @RequestMapping("mq")
    @ResponseBody
    public Result<String> mq(){
        sender.send("hello,rabbitmq");
        return Result.success("发送成功");
    }

    @RequestMapping("mq/topic")
    @ResponseBody
    public Result<String> topic(){
        sender.sendTopic("hello,rabbitmq");
        return Result.success("发送成功");
    }

    @RequestMapping("mq/fanout")
    @ResponseBody
    public Result<String> fanout(){
        sender.sendFanout("hello,rabbitmq");
        return Result.success("发送成功");
    }

    @RequestMapping("mq/header")
    @ResponseBody
    public Result<String> header(){
        sender.sendHeader("hello,rabbitmq");
        return Result.success("发送成功");
    }

}

