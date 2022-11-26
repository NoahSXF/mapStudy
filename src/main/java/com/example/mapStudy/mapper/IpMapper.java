package com.example.mapStudy.mapper;

import com.example.mapStudy.bean.Ip;

import java.util.List;

public interface IpMapper {
    int insert(Ip record);

    int insertSelective(Ip record);

    List<Ip> selectIp();
}