package com.example.mapStudy.controller;

import com.example.mapStudy.bean.Car;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 熟悉Redis的常用命令
 *
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: Noah
 * @CreateTime: 2022-09-10  21:46
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/selectRedisObject")
    public Map<String, Object> selectRedisObject() {
        Map<String, Object> map = new HashMap<>(16);
        Car car = new Car("东风", "SUV");
//        redisTemplate.opsForValue().set("car", car);
        Car o = (Car) redisTemplate.opsForValue().get("car");
//        Car car1 = JSONObject.parseObject(o.toString(), Car.class);
        map.put("car", o);
        return map;
    }
}
