package com.how2java.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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
     * 添加用户
     * @param map
     */
    @Override
    public void add(Map<String, String> map) {
        map.put("id", UUID.randomUUID().toString());
        userDAO.add(map);
    }

    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteById(String id) {
        userDAO.deleteById(id);
    }

    /**
     * 修改用户
     * @param map
     */
    @Override
    public void updateById(Map<String, Object> map) {
        userDAO.updateById(map);
    }

    /**
     * 查找单个用户
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findOneById(String id) {
        return userDAO.findOneById(id);
    }

    /**
     * 查询用户列表
     * @return
     */
    @Override
    public List<Map<String, Object>> list() {
        return userDAO.list();
    }

    /**
     * 根据账号、密码查询用户
     * @param map
     * @return
     */
    @Override
    public Map<String, Object> findOneByUserNamePwd(Map<String, String> map) {
        return userDAO.findOneByUserNamePwd(map);
    }

}
