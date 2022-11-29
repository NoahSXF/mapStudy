package com.example.mapStudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;

/**
 * 主线程
 * 开启定时调度 -> @EnableScheduling
 *
 * @author Noah
 * @date 2022/9/10 11:58
 * @return: null
 */
@SpringBootApplication
@MapperScan("com.example.mapStudy.dao")
@EnableScheduling
@Resource()
public class MapStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapStudyApplication.class, args);
    }

}
