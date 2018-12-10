package com.how2java.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderDAO {

    /**
     * 添加订单
     * @param map
     * @return 1-成功 0-失败
     */
    int add(Map<String,Object> map);

    /**
     * 删除订单
     * @param id
     * @return 1-成功 0-失败
     */
    int deleteById(String id);

    /**
     * 修改订单
     * @param map
     * @return 1-成功 0-失败
     */
    int updateById(Map<String,Object> map);

    /**
     * 根据columns查找单个订单
     * @param map
     * @return null-失败
     */
    Map<String,Object> findOneByColumns(Map<String,String> map);

    /**
     * 查询订单列表
     * @param map
     * @return
     */
    List<Map<String,Object>> list(Map<String,String> map);
}
