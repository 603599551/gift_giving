package com.how2java.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDAO {

    int add(Map<String,Object> map);


    int deleteById(String id);


    int updateById(Map<String,Object> map);

    Map<String,Object> findOneByColumns(Map<String,String> map);

    List<Map<String,String>> list(Map<String,String> map);
}
