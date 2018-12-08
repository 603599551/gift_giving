package com.how2java.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BudPurchaseDAO {

    public void add(Map<String,Object> map);

    public void deleteById(String id);

    public void updateById(Map<String,Object> map);

    public Map<String,Object> findOneById(String id);

    public List<Map<String,Object>> list();
}