package com.how2java.springboot.utils;

import com.how2java.springboot.bean.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionUtil {

    public static final String SESSION_USER="session_user";

    private UserBean userBean;

    /**
     * 获取当前登录信息的session
     * @param request
     */
    public UserSessionUtil(HttpServletRequest request){
        HttpSession session=request.getSession();
        userBean=(UserBean) session.getAttribute(SESSION_USER);
    }

    public String getUserId(){
        return userBean.getId();
    }
    public String getUsername(){
        return userBean.getUsername();
    }
    public String getNickname(){
        return userBean.getNickname();
    }
    public String getPassword(){
        return userBean.getPassword();
    }
    public String getRemark(){
        return userBean.getRemark();
    }
    public String getRoleId(){
        return userBean.getRoleId();
    }
}
