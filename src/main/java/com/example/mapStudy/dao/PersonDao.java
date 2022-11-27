package com.example.mapStudy.dao;

import com.example.mapStudy.entity.Person;
import com.example.mapStudy.entity.PersonExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mapei
 */
@Mapper
public interface PersonDao {
    long countByExample(PersonExample example);

    int deleteByExample(PersonExample example);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonExample example);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);
}