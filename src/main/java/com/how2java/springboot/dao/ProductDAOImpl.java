package com.how2java.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.how2java.springboot.utils.DateUtil.getDateTime;
import static com.how2java.springboot.utils.OrderNumGenerator.getOrderIdByUUId;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    ProductDAO productDAO;

    /**
     * 添加商品
     *
     * @param map
     * @return 1-成功 0-失败
     */
    @Override
    public int add(Map<String, Object> map) {
        map.put("id", getUUID());
        map.put("create_time",getDateTime());
        map.put("modify_time",getDateTime());
        return productDAO.add(map);
    }

    /**
     * 删除商品
     *
     * @param id
     * @return 1-成功 0-失败
     */
    @Override
    public int deleteById(String id) {
        return productDAO.deleteById(id);
    }

    /**
     * 修改商品
     *
     * @param map
     * @return 1-成功 0-失败
     */
    @Override
    public int updateById(Map<String, Object> map) {
        map.put("modify_time",getDateTime());
        return productDAO.updateById(map);
    }

    /**
     * 根据columns查找单个商品
     *
     * @param map
     * @return null-失败
     */
    @Override
    public Map<String, Object> findOneByColumns(Map<String, String> map) {
        return productDAO.findOneByColumns(map);
    }

    /**
     * 查询商品列表
     *
     * @param map
     * @return
     */
    @Override
    public List<Map<String, String>> list(Map<String, String> map) {
        return productDAO.list(map);
    }
}
