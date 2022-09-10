package com.example.mapStudy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mapStudy.bean.Car;
import com.example.mapStudy.bean.Dog;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @ResponseBody
    String hello(String name, List<Map<String, String>> list) {
        LocalDateTime date = LocalDateTime.now();
        return date.format(DateTimeFormatter.ISO_DATE);
    }

    @Test
    public void getName(){
        /**
         * MAP
         */
        JSONObject jsonObject = JSONObject.parseObject("{\"key\":\"value\"}");
        Car car = new Car();
        car.setName("汽车");
        car.setModel("SUV");
        String s = JSON.toJSONString(car);
        Dog dog = JSONObject.parseObject( s, Dog.class);
        System.out.println(dog);
        BeanUtils.copyProperties(dog, car);


        ArrayList<Car> list = new ArrayList<>();
        list.add(car);
        list.add(new Car());
        String s2 = JSON.toJSONString(list);
        System.out.println(s2);

        /**
         * 数字 字符串 对象 数组 （false true null）
         */
        JSONObject.

    }

}
