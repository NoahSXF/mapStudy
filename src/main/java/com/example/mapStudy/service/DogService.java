package com.example.mapStudy.service;

import com.alibaba.fastjson.JSON;
import com.example.mapStudy.entity.Dog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mapei
 */
@Service
@Slf4j
public class DogService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void createOrder(Dog dog) {
        String body = JSON.toJSONString(dog);
        log.info("订单信息：{}", body);
        //交换机名称
        String exchangeName = "fanout-order-exchange";
        //路由key 由于我们实现的是fanout模式（广播模式），不需要路由key，所有的消费者都可以进行监听和消费
        String routeKey = "1";
        //发送mq消息
        rabbitTemplate.convertAndSend(exchangeName, routeKey, body);
        log.info("rabbitmq发送广播模式消息成功。。。");
    }
}
