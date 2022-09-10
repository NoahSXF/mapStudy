package com.example.mapStudy.controller;

import com.alibaba.fastjson.JSON;
import com.example.mapStudy.MapStudyApplication;
import com.example.mapStudy.bean.Dog;
import com.example.mapStudy.config.DirectRabbitConfig;
import com.example.mapStudy.service.DogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
//@RequestMapping("/mq")
@Log4j2
@SpringBootTest(classes = {MapStudyApplication.class})
@RunWith(SpringRunner.class)
public class RabbitMqController {

    @Autowired
    private DogService dogService;

    @GetMapping("/direct/{message}")
   public void mqController(Dog dog) {
        /*rabbitTemplate.convertAndSend("DirectExchange", "DirectRouting", message);*/
//        rabbitTemplate.convertAndSend(DirectRabbitConfig.EXCHANGE_NAME, "", dog);
        System.out.println("消息已发送!");
    }

    @Test
    public void contextLoads() {
        for (long i = 1; i < 50; i++) {
            Dog dog = new Dog();
            dog.setName("哈巴狗");
            dog.setAge(3);
            dogService.createOrder(dog);
        }
    }



}