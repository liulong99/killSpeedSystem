package com.ccgydx.spring.boot.speed.kill.system.rabbitmq;

import com.ccgydx.spring.boot.speed.kill.system.config.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Author 刘龙
 * @Date 2020/4/24 20:27
 * @Version 1.0
 **/
@Service
public class MqReceiver {

    private static Logger log=LoggerFactory.getLogger(MqReceiver.class);

    @RabbitListener(queues = MqConfig.QUEUE)
    public void receiver(String message){
        log.info("receive message:"+message);
    }

    @RabbitListener(queues = MqConfig.TOPIC_QUEUE1)
    public void receiverTopic1(String message){
        log.info("topic queue1 message:"+message);
    }

    @RabbitListener(queues = MqConfig.TOPIC_QUEUE2)
    public void receiverTopic2(String message){
        log.info("topic queue2 message:"+message);
    }

    @RabbitListener(queues = MqConfig.HEADER_QUEUE)
    public void receiverHeaderQueue(byte[] message){
        log.info("header queue message:"+new String(message));
    }
}
