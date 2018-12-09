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
    public int add(Map<String,Object> map);

    /**
     * 删除用户
     * @param id
     */
    public int deleteById(String id);

    /**
     * 修改用户
     * @param map
     */
    public int updateById(Map<String,Object> map);

    /**
     * 根据columns查找单个用户
     * @param map
     * @return
     */
    public Map<String,Object> findOneByColumns(Map<String,String> map);

    /**
     * 查询用户列表
     * @return
     */
    public List<Map<String,Object>> list(Map<String,String> map);


}
