package com.example.mapStudy.provider;

import com.example.mapStudy.config.RabbitDirectConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * 封装发送端
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.provider
 * @Author: 99451
 * @CreateTime: 2022-09-06  23:07
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Log4j2
public class RabbitDirectSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnsCallback  {

    private RabbitTemplate rabbitTemplate;

    /**
     *
     * 构造方法注入
     *
     * @date 2022/9/7 19:45
     * @Param rabbitTemplate
     * @return: null
     */
    @Autowired
    public RabbitDirectSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //这是是设置回调能收到发送到响应，confirm()在下面解释
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 发送消息
     * @param content
     */
    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //convertAndSend(exchange:交换机名称,routingKey:路由关键字,object:发送的消息内容,correlationData:消息ID)
        rabbitTemplate.convertAndSend(RabbitDirectConfig.DIRECT_EXCHANGE, RabbitDirectConfig.DIRECT_ROUTINGKEY, content, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info(" 发送数据id:" + correlationData);
        if(ack){
            System.out.println("成功到达交换机");
            log.info("{}成功到达交换机",correlationData.getId());
        }else{
            System.out.println("未成功到达交换机，原因："+cause);
            log.info("{}未成功到达交换机，原因：{}",correlationData.getId(),cause);
        }
    }


    /**
     * 消息未成功到达该队列会触发回调该方法
     * @date 2022/9/7 21:38
     * @Param returnedMessage
     * @return: void
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        System.out.println("消息成功到达队列");
        log.info("{}消息成功到达队列",returnedMessage.getMessage().getMessageProperties().getMessageId());
    }
}

