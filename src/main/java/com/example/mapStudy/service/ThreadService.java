package com.example.mapStudy.service;

public interface ThreadService {


    /**
     * 执行异步任务
     *
     * @param fileName
     * @date 2022/9/10 11:54
     * @return: void
     */
    void copyText(String fileName);

    void writeText(String fileName);
}
