package com.how2java.springboot.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 对系统中用户进行添加、信息修改（包括密码修改）和删除的维护
 * @author CaryZ
 * @date 2018-11-30
 */
@Mapper
public interface UserDAO {

    /**
     * 添加用户
     * @param map
     */
    public void add(Map<String,String> map);

    /**
     * 删除用户
     * @param id
     */
    public void deleteById(String id);

    /**
     * 修改用户
     * @param map
     */
    public void updateById(Map<String,Object> map);

    /**
     * 根据id查找单个用户
     * @param id
     * @return
     */
    public Map<String,Object> findOneById(String id);

    /**
     * 查询用户列表
     * @return
     */
    public List<Map<String,Object>> list();

    /**
     * 根据账号、密码查询用户
     * @param map
     * @return
     */
    public Map<String,Object> findOneByUserNamePwd(Map<String,String> map);

}
