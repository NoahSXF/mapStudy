package com.example.mapStudy.controller;

import com.example.mapStudy.dao.PersonDao;
import com.example.mapStudy.dao.TeacherDao;
import com.example.mapStudy.entity.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: 99451
 * @CreateTime: 2022-09-03  20:25
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private PersonDao personDao;

    @Resource
    private TeacherDao teacherDao;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 测试mysql
     * 查询person表
     *
     * @date 2022/9/4 0:15
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("/selectPerson")
    public Map<String, Object> selectPerson() {
        Map<String, Object> map = new HashMap<>(16);
        PersonExample person = new PersonExample();
        person.or().andNameEqualTo("张三");
        List<Person> people = personDao.selectByExample(person);
        map.put("result", people);
        return map;
    }

    /**
     * 查询mysql教师表
     *
     * @date 2022/9/5 0:42
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("/selectTeacher")
    public Map<String, Object> selectTeacher() {
        Map<String, Object> map = new HashMap<>(16);
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.or().andNameEqualTo("李寻欢");
        List<Teacher> teachers = teacherDao.selectByExample(teacherExample);
        map.put("result", teachers);
        return map;
    }

    /**
     * 测试redis连接(放入string，string)
     *
     * @date 2022/9/4 20:54
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("/selectRedisString")
    public Map<String, Object> selectRedisString() {
        Map<String, Object> map = new HashMap<>(16);
        int t = 100;
        for (int i = 0; i < t; i++) {
//            redisTemplate.opsForValue().set("张三" + i, String.valueOf(t - i));
            Object o = redisTemplate.opsForValue().get("张三" + i);
            map.put("张三" + i, o);
        }
        return map;
    }

    /**
     * 测试redis连接(放入string,Object)
     *
     * @date 2022/9/10 21:25
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("/selectRedisObject")
    public Map<String, Object> selectRedisObject() {
        Map<String, Object> map = new HashMap<>(16);
        Car car = new Car("东风", "SUV");
        redisTemplate.opsForValue().set("car", car);
        Object o = redisTemplate.opsForValue().get("car");
        map.put("car", o);
        return map;
    }

    @GetMapping("/selectRedisList")
    public Map<String, Object> selectRedisList() {
        Map<String, Object> map = new HashMap<>(16);
        List<Object> list = new ArrayList<>();
        Car car = new Car("东风", "SUV");
        list.add(car);
        redisTemplate.opsForValue().set("car", list);
        Object o = redisTemplate.opsForValue().get("car");
        map.put("car", o);
        return map;
    }
}
