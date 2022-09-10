package com.example.mapStudy.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送者
 */
@RestController
@RequestMapping("/mq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/direct/{message}")
    public void mqController(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend("DirectExchange", "DirectRouting", message);
        System.out.println("消息已发送!");
    }
}