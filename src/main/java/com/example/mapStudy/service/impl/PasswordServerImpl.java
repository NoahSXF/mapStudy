package com.example.mapStudy.service.impl;

import com.example.mapStudy.bean.Password;
import com.example.mapStudy.mapper.PasswordMapper;
import com.example.mapStudy.service.PasswordServer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.service.impl
 * @Author: Noah
 * @CreateTime: 2022-11-24  19:25
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
public class PasswordServerImpl implements PasswordServer {

    @Resource
    PasswordMapper mapper;

    @Override
    public int insert(Password record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Password record) {
        return mapper.insertSelective(record);
    }

    @Override
    public PageInfo<Password> selectByPassword(Password password, Integer pageNum, Integer pageSize) {
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List<Password> passwords = mapper.selectByPassword(password);
        return new PageInfo<>(passwords);
    }

    @Override
    public int update(Password password) {
        return mapper.update(password);
    }
}
