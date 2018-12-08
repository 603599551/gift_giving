package com.how2java.springboot.modules.user.controller;

import com.how2java.springboot.bean.UserBean;
import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.user.service.UserSrv;
import com.how2java.springboot.utils.JsonHashMap;
import com.how2java.springboot.utils.UserSessionUtil;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.event.ObjectChangeListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.utils.BeanUtil.ObjectToMapUtil;
import static com.how2java.springboot.utils.BeanUtil.objectToInt;
import static com.how2java.springboot.utils.BeanUtil.objectToString;
import static com.how2java.springboot.utils.CookieUtil.setCookie;
import static com.how2java.springboot.utils.JsonHashMap.RESULT_FAIL;
import static com.how2java.springboot.utils.UserSessionUtil.SESSION_USER;

/**
 * 用户管理：用户信息的增删改查+登录
 * @author CaryZ
 * @date 2018-12-08
 */
@RestController
@RequestMapping("/user")
public class UserCtrl {
//
//    @Autowired
//    UserDAOImplement userImplement;
//
//    @RequestMapping("/list")
//    public List<Map<String,Object>> list(){
//        return userImplement.list();
//    }
//
//    @RequestMapping("/add")
//    public void add(@RequestParam Map<String, String> map) {
//        userImplement.add(map);
//    }
//
//    @RequestMapping("/deleteById")
//    public void deleteById(String id) {
//        userImplement.deleteById(id);
//    }
//
//    @RequestMapping("/updateById")
//    public void updateById(Map<String, Object> map) {
//        userImplement.updateById(map);
//    }
//
//    @RequestMapping("/findOneById")
//    public Map<String, Object> findOneById(String id) {
//        return userImplement.findOneById(id);
//    }

    @Autowired
    UserSrv userSrv;

    /**
     * 用户登录
     * 1.验证账号密码是否正确
     * 2.resultMap的data存到session中
     * 3.设置cookie，包括user_id,JSESSIONID,isAdmin
     * 登录正确：
     * {
     *     "code":1,
     *     "message":"--成功",
     *     "data":{
     *         "id":"",
     *         "nickname":""
     *     }
     * }
     * 登录失败：
     * {
     *     "code":0,
     *     "message":"--失败"
     * }
     * @date 2018-12-08
     * @param parameterMap
     * @return
     */
    @RequestMapping("/login")
    public JsonHashMap login(@RequestParam Map<String,String> parameterMap,HttpServletRequest request,HttpSession session){
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,Object> resultMap=userSrv.loginIn(parameterMap);
            if (RESULT_FAIL==objectToInt(resultMap.get("code"))){
                jhm.putFail("登录失败！");
            }else {
                //UserBean里的内置对象不为null，但UserBean为空！！坑！！
                UserBean userBean=(UserBean)resultMap.get("data");
                session.setAttribute(SESSION_USER,userBean);
                setCookie("user_id",userBean.getId());
                setCookie("isAdmin",userBean.getRoleId());
                setCookie("JSESSIONID",session.getId());
                jhm.putSuccess(userBean.getUser());
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

}
