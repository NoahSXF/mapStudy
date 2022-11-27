package com.example.mapStudy.dao;

import com.example.mapStudy.entity.Ip;

import java.util.List;

public interface IpMapper {
    int insert(Ip record);

    int insertSelective(Ip record);

    List<Ip> selectIp();
}