package com.example.mapStudy.consume;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.consume
 * @Author: 99451
 * @CreateTime: 2022-09-07  22:15
 * @Description: TODO
 * @Version: 1.0
 */

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

//@Component
public class ReceiverService {

    /**
     * 定义消费端
     *
     * @date 2022/9/8 20:01
     * @Param message
     * @Param channel
     * @return: void
     */
//    @RabbitListener(queues = RabbitDirectConfig.DIRECT_QUEUE)
    public void receiveMsg(Message message, Channel channel) {
        // 消息标识
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            byte[] bytes = message.getBody();
            String mess = new String(bytes);
            System.out.println("mess = " + mess);
//            int l=2/2/0;
            //  手动确认签收 第一个参数是消息标记 第二个参数fasle 只确认当前消息，true表示之前所有的消息都确认成功
//            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            try {
                // 标示签收失败，再次放入队列中  第三个参数 requeue 再次放入队列
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("sxfPublisher.queue1"),
            exchange = @Exchange("publisher.direct"),
            key={"1","50"}
    ))
    public void listenDirectQueue1(String msg) throws InterruptedException {
        System.out.println("listenDirectQueue1 消费者接收到消息 ：【" + msg + "】");
    }

    /**
     * value = @Queue("队列名") springboot启动后根据监听器直接生成队列
     * exchange = @Exchange("交换机名字") 对应接口中convertAndSend的交换机名字
     * key = {key的值} 对应routingKey的值
     *
     * @date 2022/9/8 20:19
     * @Param msg
     * @return: void
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("sxfPublisher.queue2"),
            exchange = @Exchange("publisher.direct"),
            key={"red","yellow"}
    ))
    public void listenDirectQueue2(String msg) throws InterruptedException {
        System.err.println("listenDirectQueue2 消费者接收到消息 ：【" + msg + "】");
    }
}
