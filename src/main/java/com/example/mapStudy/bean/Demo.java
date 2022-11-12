package com.example.mapStudy.bean;

import lombok.Data;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.bean
 * @Author: Noah
 * @CreateTime: 2022-11-12  22:31
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class Demo {
    private String name;

    private String age;

    private String phone;

    public Demo(String name, String age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Demo() {
    }
}

