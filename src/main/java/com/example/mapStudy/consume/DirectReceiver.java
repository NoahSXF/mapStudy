package com.example.mapStudy.consume;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 定义消费端
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.consume
 * @Author: 99451
 * @CreateTime: 2022-09-06  23:06
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Log4j2
public class DirectReceiver {

//    @RabbitListener(queues= "#{directQueue.name}")
    public void recvOne(String obj) {
        log.info("DirectReceiver, receive the queue msg:"+obj);
    }
}