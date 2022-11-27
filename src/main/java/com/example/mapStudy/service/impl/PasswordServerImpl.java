package com.example.mapStudy.service.impl;

import com.example.mapStudy.dao.PasswordMapper;
import com.example.mapStudy.entity.Password;
import com.example.mapStudy.service.PasswordServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        PageHelper.startPage(pageNum, pageSize);
        List<Password> passwords = mapper.selectByPassword(password);
        return new PageInfo<>(passwords);
    }

    @Override
    public int update(Password password) {
        return mapper.update(password);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = TransactionException.class)
    public int update(List<Password> list) {
        list.forEach(x -> {
            Password entity = new Password();
            entity.setId(x.getId());
            entity.setIndex(x.getIndex() + 1);
            update(entity);
        });
        int i = updateList(list);
        return 1;
    }

    public int updateList(List<Password> list) {
        List<Password> arrayList = new ArrayList<>(16);
        list.forEach(x -> {
            Password entity = new Password();
            entity.setId(x.getId());
            entity.setIndex(x.getIndex() + 1);
            arrayList.add(entity);
        });
        return mapper.updateList(arrayList);
    }
}
