package com.example.mapStudy.consume;

import org.springframework.stereotype.Component;


/*
@Component
@Log4j2
public class DirectReceiver {

//    @RabbitListener(queues= "#{directQueue.name}")
    public void recvOne(String obj) {
        log.info("DirectReceiver, receive the queue msg:"+obj);
    }
}*/

/**
 * 监听的队列名称DirectQueue
 * 消费者
 *
 * @author mapei
 */
@Component
//@RabbitListener(queues = "MapQueue")
public class DirectReceiver {
    //    @RabbitHandler
    public void receiver(String message) {

        System.out.println("DirectReceiver消费者收到消息: " + message.toString());
    }
}
