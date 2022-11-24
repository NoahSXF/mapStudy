package com.example.mapStudy.service;

import com.example.mapStudy.bean.Password;
import com.github.pagehelper.PageInfo;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.service
 * @Author: Noah
 * @CreateTime: 2022-11-24  19:25
 * @Description: TODO
 * @Version: 1.0
 */
public interface PasswordServer {
    int insert(Password record);

    int insertSelective(Password record);

    PageInfo<Password> selectByPassword(Password password, Integer pageNum, Integer pageSize);

    int update(Password password);
}
