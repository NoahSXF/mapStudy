package com.example.mapStudy.mapper;

import com.example.mapStudy.bean.Password;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PasswordMapper {
    int insert(Password record);

    int insertSelective(Password record);

    List<Password> selectByPassword(Password password);

    int update(Password password);
}