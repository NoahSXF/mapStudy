package com.example.mapStudy.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

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

    @Autowired
    private RabbitDirectSender rabbitDirectSender;

    private static final String SUCCESS = "success";

    /**
     * Direct模式， 发送消息
     * @return
     */
    @GetMapping("/directMessage")
    public String directMessage() {
        rabbitDirectSender.sendMsg("from directMessage, random number: " +
                new Random().nextInt(100));
        return SUCCESS;
    }


}

