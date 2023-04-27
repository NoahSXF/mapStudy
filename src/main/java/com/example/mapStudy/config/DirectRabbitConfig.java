package com.example.mapStudy.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置队列
 *
 * @author mapei
 */
@Configuration
public class DirectRabbitConfig {
    public static final String EXCHANGE_NAME = "fanout-order-exchange";
    public static final String SMS_QUEUE = "sms-fanout-queue";
    public static final String EMAIL_QUEUE = "email-fanout-queue";
    public static final String WECHAT_QUEUE = "wechat-fanout-queue";

    /**队列 起名:DirectQueue 队列持久化*/
    @Bean
    public Queue smsQueue() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        //        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //return new Queue("DirectQueue",true,true,false);

        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(SMS_QUEUE, true);
    }
    @Bean
    public Queue emailQueue() {

        return new Queue(EMAIL_QUEUE, true);
    }
    @Bean
    public Queue weChatQueue() {
        return new Queue(WECHAT_QUEUE, true);
    }

    /**
     * Direct交换机 起名:DirectExchange 交换机持久化
     * */
   /* @Bean
    DirectExchange DirectExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, true);
    }*/
    @Bean
    public FanoutExchange fanoutExchange() {
        /**
         * FanoutExchange的参数说明:
         * 1. 交换机名称
         * 2. 是否持久化 true：持久化，交换机一直保留 false：不持久化，用完就删除
         * 3. 是否自动删除 false：不自动删除 true：自动删除
         */
        return new FanoutExchange(EXCHANGE_NAME, true, false);
    }


    /**
     * 绑定  将队列和交换机绑定, 并设置用于匹配键：DirectRouting
     **/
   /* @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(DirectQueue()).to(DirectExchange()).with("DirectRouting");
    }*/
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding weChatBinding() {
        return BindingBuilder.bind(weChatQueue()).to(fanoutExchange());
    }
    /**
     * gkghkgj
     * @return
     */
    @Bean
    DirectExchange lonelyDirectExchange() {
        return new DirectExchange("lonelyDirectExchange");
    }

}
