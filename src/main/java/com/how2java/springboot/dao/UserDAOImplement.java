package com.how2java.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.how2java.springboot.utils.DateUtil.getDateTime;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

/**
 * 对系统中用户进行添加、信息修改（包括密码修改）和删除的维护
 * @author CaryZ
 * @date 2018-11-30
 */
@Repository
public class UserDAOImplement implements UserDAO {
    @Autowired
    UserDAO userDAO;

    /**
     * @description 添加用户
     * 1.账户余额、花芽数量、花冠数量为0
     * 2.创建、修改时间为当前时间
     * @param map
     * @return 用户id
     */
    @Override
    public int add(Map<String, Object> map) {
        map.put("id", getUUID());
        map.put("balance",0);
        map.put("bud_quantity",0);
        map.put("bud_quantity",0);
        map.put("create_time",getDateTime());
        map.put("modify_time",getDateTime());
        return userDAO.add(map);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public int deleteById(String id) {
        return userDAO.deleteById(id);
    }

    /**
     * 修改用户
     * @param map
     */
    @Override
    public int updateById(Map<String, Object> map) {
        return userDAO.updateById(map);
    }

    /**
     * 根据columns查找单个用户
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> findOneByColumns(Map<String,String> map) {
        return userDAO.findOneByColumns(map);
    }

    /**
     * 查询用户列表
     * @return
     */
    @Override
    public List<Map<String, Object>> list(Map<String,String> map) {
        return userDAO.list(map);
    }


}
