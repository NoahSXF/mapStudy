package com.example.mapStudy.provider;

import com.example.mapStudy.config.RabbitDirectConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
public class RabbitDirectSender implements RabbitTemplate.ConfirmCallback  {

    private RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入
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
        if (ack) {
            log.info("消息已确认发送");
        } else {
            log.info("消息发送失败:" + cause);
        }
    }
}

