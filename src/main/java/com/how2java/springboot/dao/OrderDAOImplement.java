package com.how2java.springboot.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class OrderDAOImplement implements OrderDAO {
    @Override
    public void add(Map<String, Object> map) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void updateById(Map<String, Object> map) {

    }

    @Override
    public Map<String, Object> findOneById(String id) {
        return null;
    }

    @Override
    public List<Map<String, Object>> list() {
        return null;
    }
}
