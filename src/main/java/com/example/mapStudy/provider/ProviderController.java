package com.example.mapStudy.provider;

import com.example.mapStudy.config.RabbitDirectConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.provider
 * @Author: 99451
 * @CreateTime: 2022-09-06  23:08
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class ProviderController {

    @Resource
    private RabbitDirectSender rabbitDirectSender;

    @Resource
    RabbitTemplate rabbitTemplate;

    private static final String SUCCESS = "success";

    /**
     * Direct模式， 发送消息
     *
     * @date 2022/9/7 19:43
     * @return: java.lang.String
     */
    @GetMapping("/directMessage")
    public String directMessage() {
        rabbitDirectSender.sendMsg("from directMessage, random number: " +
                new Random().nextInt(100));
        return SUCCESS;
    }


    @GetMapping("/send/{msg}")
    public String sendMqInfo(@PathVariable("msg") String msg) {
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE, RabbitDirectConfig.DIRECT_QUEUE, msg, new CorrelationData(UUID.randomUUID().toString()));
        return "it is msg:" + msg;
    }

    @GetMapping(value = "/sendFanout/{msg}")
    public String sendMqInfoFanout(@PathVariable("msg") String msg) {
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE + "fanout", RabbitDirectConfig.DIRECT_QUEUE + "fanout", msg, new CorrelationData(UUID.randomUUID().toString()));
        return "it is msg:" + msg;
    }

}

