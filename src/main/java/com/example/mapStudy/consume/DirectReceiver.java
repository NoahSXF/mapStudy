package com.example.mapStudy.consume;

import com.example.mapStudy.config.DirectRabbitConfig;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


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
 *监听的队列名称DirectQueue
 * 消费者
 * @author mapei
 */
//@RabbitListener(queues = "MapQueue")
//@Component
public class DirectReceiver {
    /*@RabbitListener(queues = DirectRabbitConfig.EMAIL_QUEUE)*/
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(DirectRabbitConfig.EMAIL_QUEUE),
            exchange = @Exchange("fanout-order-exchange"),
            key={"1","50"}
    ))
    public void receiver(String message) {

        System.out.println("EMAIL_QUEUE消费者收到消息: " + message.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(DirectRabbitConfig.WECHAT_QUEUE),
            exchange = @Exchange("fanout-order-exchange"),
            key={"2","50"}
    ))
    public void receiver1(String message) {

        System.out.println("WECHAT_QUEUE消费者收到消息: " + message.toString());
    }
}
