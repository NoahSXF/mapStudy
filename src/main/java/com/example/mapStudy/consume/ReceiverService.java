package com.example.mapStudy.consume;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.consume
 * @Author: 99451
 * @CreateTime: 2022-09-07  22:15
 * @Description: TODO
 * @Version: 1.0
 */
import com.example.mapStudy.config.RabbitDirectConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceiverService {

//    @RabbitListener(queues = RabbitDirectConfig.DIRECT_QUEUE)
    public void  receiveMsg(Message message, Channel channel){
        // 消息标识
        long deliveryTag= message.getMessageProperties().getDeliveryTag();
        try {
            byte [] bytes=message.getBody();
            String mess=new String(bytes);
            System.out.println("mess = " + mess);
//            int l=2/2/0;
            //  手动确认签收 第一个参数是消息标记 第二个参数fasle 只确认当前消息，true表示之前所有的消息都确认成功
            channel.basicAck(deliveryTag,false);


        } catch (Exception e) {
            try {
                // 标示签收失败，再次放入队列中  第三个参烽 requeue 再次放入队列
                channel.basicNack(deliveryTag,false,true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }


    }
}
