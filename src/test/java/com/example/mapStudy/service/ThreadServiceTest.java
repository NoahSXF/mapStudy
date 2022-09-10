package com.example.mapStudy.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

//-----------------------测试----------------------------
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ThreadServiceTest {
    @Autowired
    private ThreadService threadService;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private String fileName = "C:\\Users\\99451\\Documents\\GitHub\\mapStudy\\src\\main\\resources\\testInput.txt";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void writeText() {
        File file = new File("C:\\Users\\99451\\Documents\\GitHub\\mapStudy\\src\\main\\resources\\testInput.txt");
        threadService.writeText(fileName);
    }

    /**
     * 调用线程异步处理
     *
     * @date 2022/9/10 11:56
     * @return: void
     */
    @Test
    void test2() {
        threadPoolTaskExecutor.execute(() -> {
            threadService.writeText(fileName);
        });
        log.info("main执行完毕");
    }
}