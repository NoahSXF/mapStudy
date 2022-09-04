package com.example.mapStudy.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: mapstudy
 * @BelongsPackage: com.example.mapstudy.pojo
 * @Author: 99451
 * @CreateTime: 2022-09-04  10:46
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class Dog {
    @Value("旺财")
    private String name;
    @Value("3")
    private Integer age;

    public Dog() {

    }

    public Dog(String name, Integer age) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
