package com.ccgydx.spring.boot.speed.kill.system.rabbitmq;

import com.ccgydx.spring.boot.speed.kill.system.config.MqConfig;
import com.ccgydx.spring.boot.speed.kill.system.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/24 20:27
 * @Version 1.0
 **/
@Service
public class MqSender {

    private static Logger log= LoggerFactory.getLogger(MqSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message){
        String s = RedisService.beenToString(message);
        log.info("send message:"+s);
        amqpTemplate.convertAndSend(MqConfig.QUEUE,s);
    }

    public void sendTopic(Object message){
        String s = RedisService.beenToString(message);
        log.info("send message:"+s);
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key1",s+"1");
        amqpTemplate.convertAndSend(MqConfig.TOPIC_EXCHANGE,"topic.key2",s+"2");
    }

    public void sendFanout(Object message){
        String s = RedisService.beenToString(message);
        log.info("send message:"+s);
        amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE,"",s+"1");
        amqpTemplate.convertAndSend(MqConfig.FANOUT_EXCHANGE,"",s+"2");
    }

    public void sendHeader(Object message){
        String s = RedisService.beenToString(message);
        log.info("send message:"+s);
        MessageProperties properties=new MessageProperties();
        properties.setHeader("header1","value1");
        properties.setHeader("header2","value2");
        Message obj=new Message(s.getBytes(),properties);
        amqpTemplate.convertAndSend(MqConfig.HEADERS_EXCHANGE,"",obj);
    }
}
