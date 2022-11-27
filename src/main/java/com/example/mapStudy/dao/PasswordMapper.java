package com.example.mapStudy.dao;

import com.example.mapStudy.entity.Password;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PasswordMapper {
    int insert(Password record);

    int insertSelective(Password record);

    List<Password> selectByPassword(Password password);

    int update(Password password);

    int updateList(@Param("list") List<Password> list);
}