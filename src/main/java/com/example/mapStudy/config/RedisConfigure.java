package com.example.mapStudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <p>ClassName: RedisConfigure</p >
 * <p>Description: redisTemplate自定义序列化配置，更改其默认序列化器</p >
 * <p>Date: 2022/09/04</p >
 *
 * @author 99451
 */
@Configuration
public class RedisConfigure {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());

        return redisTemplate;
    }


    /**
     * 使用Jackson序列化器，key使用字符串
     *
     * @date 2022/9/4 22:31
     * @return: org.springframework.data.redis.serializer.RedisSerializer<java.lang.String>
     */
    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 使用Jackson序列化器，value使用Object
     *
     * @date 2022/9/4 22:32
     * @return: org.springframework.data.redis.serializer.RedisSerializer<java.lang.Object>
     */
    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

}