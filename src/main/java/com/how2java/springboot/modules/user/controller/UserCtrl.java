package com.how2java.springboot.modules.user.controller;

import com.how2java.springboot.bean.UserBean;
import com.how2java.springboot.common.controller.BaseCtrl;
import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.user.service.UserSrv;
import com.how2java.springboot.utils.JsonHashMap;
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
public class UserCtrl{

    @Autowired
    UserSrv userSrv;

    /**
     * @description 用户登录
     * 1.验证账号密码是否正确
     * 2.resultMap的data存到session中
     * 3.设置cookie，包括user_id,JSESSIONID,isAdmin
     * @date 2018-12-08
     * @param parameterMap
     * {
     *     "username":"用户名",
     *     "password":"密码"
     * }
     * @return JsonHashMap
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
     */
    @RequestMapping("/login")
    public JsonHashMap login(@RequestParam Map<String,String> parameterMap,HttpSession session){
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
                Map<String,Map<String,Object>> userMap=new HashMap<>(1);
                userMap.put("user",userBean.getUser());
                jhm.putSuccess(userMap);
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 用户注册
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/add")
    public JsonHashMap add(@RequestParam Map<String, Object> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
           boolean flag=userSrv.add(parameterMap);
           if (flag){
               jhm.putMessage("注册成功！");
           }else {
               jhm.putFail("注册失败！");
           }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 删除用户
     * @author CaryZ
     * @date 2018-12-09
     * @param id 用户id
     * @return jsonHashMap
     */
    @RequestMapping("/deleteById")
    public JsonHashMap deleteById(@RequestParam String id) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            boolean flag=userSrv.deleteById(id);
            if (flag){
                jhm.putMessage("删除成功！");
            }else {
                jhm.putFail("删除失败！");
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 修改用户信息
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/updateById")
    public JsonHashMap updateById(@RequestParam Map<String, Object> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            boolean flag=userSrv.updateById(parameterMap);
            if (flag){
                jhm.putMessage("修改成功！");
            }else {
                jhm.putFail("修改失败！");
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 查询用户列表
     * 完全匹配查询：昵称、电话、姓名等 （待前端确认）
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/list")
    public JsonHashMap list(@RequestParam Map<String, String> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,List<Map<String,Object>>> listMap=new HashMap<>(1);
            listMap.put("list",userSrv.list(parameterMap));
            jhm.putSuccess(listMap);
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }
}
