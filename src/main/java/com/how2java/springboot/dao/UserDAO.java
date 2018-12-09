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
     * @return 1-成功 0-失败
     */
    int add(Map<String,Object> map);

    /**
     * 删除用户
     * @param id
     * @return 1-成功 0-失败
     */
    int deleteById(String id);

    /**
     * 修改用户
     * @param map
     * @return 1-成功 0-失败
     */
    int updateById(Map<String,Object> map);

    /**
     * 根据columns查找单个用户
     * @param map
     * @return null-失败
     */
    Map<String,Object> findOneByColumns(Map<String,String> map);

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    List<Map<String,Object>> list(Map<String,String> map);


}
