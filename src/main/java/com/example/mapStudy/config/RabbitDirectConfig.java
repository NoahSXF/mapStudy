package com.example.mapStudy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.config
 * @Author: 99451
 * @CreateTime: 2022-09-06  23:01
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class RabbitDirectConfig {
    public final static String DIRECT_QUEUE = "directQueue";
    public final static String DIRECT_EXCHANGE = "directExchange";
    public static final String DIRECT_ROUTINGKEY = "directRoutingKey";


    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     *
     * direct模式队列
     *
     * @date 2022/9/6 23:02
     *
     * @return: Queue
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(directQueue()).to(defaultExchange()).with(DIRECT_ROUTINGKEY);
    }
}
