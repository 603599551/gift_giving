package com.how2java.springboot.modules.user.service;

import com.how2java.springboot.bean.UserBean;
import com.how2java.springboot.dao.UserDAOImpl;
import com.how2java.springboot.exception.PcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.*;
import static com.how2java.springboot.utils.BeanUtil.objectToString;

/**
 * @description 用户管理
 * 用户信息的增删改查+登录
 * @author CaryZ
 * @date 2018-12-08
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserSrv{

    @Autowired
    UserDAOImpl userDAOImpl;

    /**
     * @description 用户登录
     * 1.验证账号密码是否正确
     * 2.将用户登录信息存到userBean,userBean---data
     * 登录正确：
     * {
     *     "code":1,
     *     "data":{
     *         "id":"",
     *         "nickname":""
     *     }
     * }
     * 登录失败：
     * {
     *     "code":0,
     * }
     * @author CaryZ
     * @date 2018-12-08
     * @param parameterMap
     * @return resultMap
     */
    public Map<String,Object> loginIn(Map<String,String> parameterMap) throws PcException {
        Map<String,Object> resultMap=new HashMap<>();
        Map<String,Object> user=new HashMap<>();
        try{
            user= userDAOImpl.findOneByColumns(parameterMap);
            //账号密码错误
            if (user==null){
                resultMap.put("code",0);
                return resultMap;
            }else {
                resultMap.put("code",1);
            }
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
        UserBean userBean=new UserBean(user);
        resultMap.put("data",userBean);
        return resultMap;
    }

    /**
     * @description 用户注册
     * 1.验证用户名是否重复?
     * 2.不重复则添加
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * {
     *     "username":"用户名--",
     *     "password":"密码--",
     *     "nickname":"昵称--",
     *     "phone":"电话",
     *     "realname":"真实姓名",
     *     "birthday":"生日",
     *     "head":"头像",
     *     "picture":"美照",
     *     "address":"地址",
     *     "personalized_signature":"个性签名",
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException{
        Map<String,String> paraMap=new HashMap<>(1);
        paraMap.put("username",objectToString(parameterMap.get("username")));
        //用户名重复则添加失败
        try{
            Map<String,Object> user= userDAOImpl.findOneByColumns(paraMap);
            if (user!=null){
                return false;
            }
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
        //添加用户
        try{
            return userDAOImpl.add(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(ADD_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 删除用户
     * @author CaryZ
     * @date 2018-12-09
     * @param id 用户id
     * @return 删除成功--true 失败--false
     * @throws PcException
     */
    public boolean deleteById(String id) throws PcException{
        try{
            return userDAOImpl.deleteById(id)==0? false:true;
        }catch (Exception e){
            throw new PcException(DELETE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 修改用户信息
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return 修改成功--true 失败--false
     * @throws PcException
     */
    public boolean updateById(Map<String,Object> parameterMap) throws PcException{
        try{
            return userDAOImpl.updateById(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(UPDATE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 查询用户列表
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return list
     * @throws PcException
     */
    public List<Map<String, Object>> list(Map<String,String> parameterMap) throws PcException{
        try{
            return userDAOImpl.list(parameterMap);
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 根据columns查询单个用户
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return list
     * @throws PcException
     */
//    public Map<String, Object> findOneByColumns(Map<String,String> parameterMap) throws PcException{
//        try{
//            return userDAOImplement.findOneByColumns(parameterMap);
//        }catch (Exception e){
//            throw new PcException(SELECT_EXCEPTION,e.getMessage());
//        }
//    }

}
