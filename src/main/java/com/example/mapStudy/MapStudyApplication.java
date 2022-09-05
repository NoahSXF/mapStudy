package com.example.mapStudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mapStudy.mapper")
public class MapStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapStudyApplication.class, args);
    }

}
