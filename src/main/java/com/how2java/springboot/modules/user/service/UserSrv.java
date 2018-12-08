package com.how2java.springboot.modules.user.service;

import com.how2java.springboot.bean.UserBean;
import com.how2java.springboot.dao.UserDAOImplement;
import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.utils.JsonHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.event.ObjectChangeListener;
import java.util.HashMap;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.SELECT_EXCEPTION;

@Service
@Transactional
public class UserSrv{

    @Autowired
    UserDAOImplement userDAOImplement;

    /**
     * 用户登录
     * 1.验证账号密码是否正确
     * 2.将用户登录信息存到userBean,userBean---data
     * @param parameterMap
     * @return resultMap
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
     */
    public Map<String,Object> loginIn(Map<String,String> parameterMap) throws PcException {
        Map<String,Object> resultMap=new HashMap<>();
        Map<String,Object> user=new HashMap<>();
        try{
            user=userDAOImplement.findOneByUserNamePwd(parameterMap);
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
        resultMap.put("data",userBean.getUser());
        return resultMap;
    }
}
