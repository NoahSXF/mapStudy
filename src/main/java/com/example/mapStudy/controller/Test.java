package com.example.mapStudy.controller;

import com.example.mapStudy.bean.Person;
import com.example.mapStudy.bean.PersonExample;
import com.example.mapStudy.mapper.PersonDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class Test {
    @Resource
    private PersonDao personDao;

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
        person.setForUpdate(false);
        person.or().andNameEqualTo("张三");
        List<Person> people = personDao.selectByExample(person);
        map.put("result", people);
        return map;
    }

    /**
     * 测试redis连接
     *
     * @date 2022/9/4 20:54
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    @GetMapping("/selectRedis")
    public Map<String, Object> selectRedis() {
        Map<String, Object> map = new HashMap<>(16);
        Person person = new Person();
        person.setName("张三");
        person.setAge("18");
        int t = 100;
        for (int i = 0; i < t; i++) {
            redisTemplate.opsForValue().set("张三" + i, String.valueOf(t - i));
            map.put(String.valueOf(i), t - i);
        }
        return map;
    }
}
