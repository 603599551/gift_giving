package com.how2java.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.how2java.springboot.utils.DateUtil.getDateTime;
import static com.how2java.springboot.utils.OrderNumGenerator.getOrderIdByUUId;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

/**
 * 订单-增删改查
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    OrderDAO orderDAO;

    /**
     * 添加订单
     *
     * @param map
     * @return 1-成功 0-失败
     */
    @Override
    public int add(Map<String, Object> map) {
        map.put("id", getUUID());
        map.put("num",getOrderIdByUUId());
        map.put("state","undelivered");
        map.put("create_time",getDateTime());
        map.put("modify_time",getDateTime());
        return orderDAO.add(map);
    }

    /**
     * 删除订单
     *
     * @param id
     * @return 1-成功 0-失败
     */
    @Override
    public int deleteById(String id) {
        return orderDAO.deleteById(id);
    }

    /**
     * 修改订单
     *
     * @param map
     * @return 1-成功 0-失败
     */
    @Override
    public int updateById(Map<String, Object> map) {
        map.put("modify_time",getDateTime());
        return orderDAO.updateById(map);
    }

    /**
     * 根据columns查找单个订单
     *
     * @param map
     * @return null-失败
     */
    @Override
    public Map<String, Object> findOneByColumns(Map<String, String> map) {
        return orderDAO.findOneByColumns(map);
    }

    /**
     * 查询订单列表
     *
     * @param map
     * @return
     */
    @Override
    public List<Map<String, Object>> list(Map<String, String> map) {
        return orderDAO.list(map);
    }
}
