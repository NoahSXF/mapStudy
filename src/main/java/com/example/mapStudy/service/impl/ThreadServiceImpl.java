package com.example.mapStudy.service.impl;

import com.example.mapStudy.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.service.impl
 * @Author: Noah
 * @CreateTime: 2022-09-10  10:04
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {
    @Override
    public void copyText(String fileName) {
        log.info("线程-" + Thread.currentThread().getId() + "在执行复制");
        try {
            File file = new File(fileName);
            List<String> lines = FileUtils.readLines(file, "utf8");
            File copyFile = new File(fileName + "_copy.txt");
            lines.stream().forEach(string -> {
                try {
                    FileUtils.writeStringToFile(copyFile, string, "utf8", true);
                    FileUtils.writeStringToFile(copyFile, "\r\n", "utf8", true);
                } catch (IOException e) {
                    log.info(e.getMessage());
                }
            });
            log.info("写入完毕");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    public void writeText(String fileName) {
        log.info("线程-" + Thread.currentThread().getId() + "在执行写入");
        try {
            File file = new File(fileName);
            FileUtils.writeStringToFile(file, "新写入" + Thread.currentThread().getId(), "utf8", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("线程-" + Thread.currentThread().getId() + "写入完成");
    }

}
