package com.example.mapStudy.entity;

import lombok.*;
import lombok.extern.java.Log;

import java.util.logging.Level;


/**
 * 测试lombok的注解
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.bean
 * @Author: 99451
 * @CreateTime: 2022-09-06  21:14
 * @Description: TODO
 * @Version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public @Data @Log class Car {
    @NonNull
    private String name ;
    @NonNull
    private String model;

    public static void main(String... args) {
        log.setLevel(Level.WARNING);
        log.log(Level.INFO,log.getName());
        CarBuilder builder = Car.builder();
//        builder.name(builder.name);
    }


}
