package com.example.mapStudy.scheduling;

import com.example.mapStudy.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.scheduling
 * @Author: Noah
 * @CreateTime: 2022-09-10  12:01
 * @Description: TODO
 * @Version: 1.0
 */
@Component
@Slf4j
public class SchedulesAddFile {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private ThreadService threadService;

    private String fileName = "C:\\Users\\99451\\Documents\\GitHub\\mapStudy\\src\\main\\resources\\testInput.txt";

//    @Scheduled(cron = "0/2 * * * * ?")
    public void addFile() {
        threadPoolTaskExecutor.execute(() -> {
            threadService.copyText(fileName);
        });
        threadPoolTaskExecutor.execute(() -> {
            threadService.writeText(fileName);
        });
    }
}
