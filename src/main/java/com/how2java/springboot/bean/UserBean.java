package com.how2java.springboot.bean;

import java.util.HashMap;
import java.util.Map;

import static com.how2java.springboot.utils.BeanUtil.objectToString;

/**
 * 用户登录信息Bean
 * @author CaryZ
 * @date 2018-12-07
 */
public class UserBean extends HashMap<String,Object>{

    private String id;
    private String username;
    private String nickname;
    private String password;
    private String remark;
    private String roleId;

    private Map<String,Object> user=new HashMap<>();

    public UserBean(){
        super();
    }

    public UserBean(Map<String,Object> map){
        super();
        this.id=objectToString(map.get("id"));
        this.username=objectToString(map.get("username"));
        this.nickname=objectToString(map.get("nickname"));
        this.password=objectToString(map.get("password"));
        this.remark=objectToString(map.get("remark"));
        this.roleId=objectToString(map.get("role_id"));
        user=map;
    }

    /**
     * setter方法
     */
    public void setId(String id){
        this.id=id;
        user.put("id",id);
    }
    public void setUsername(String username){
        this.username=username;
        user.put("username",username);
    }
    public void setNickname(String nickname){
        this.nickname=nickname;
        user.put("nickname",nickname);
    }
    public void setPassword(String password){
        this.password=password;
        user.put("password",password);
    }
    public void setRemark(String remark){
        this.remark=remark;
        user.put("remark",remark);
    }
    public void setRoleId(String roleId){
        this.roleId=roleId;
        user.put("role_id",roleId);
    }

    /**
     * getter方法
     */
    public String getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getNickname(){
        return this.nickname;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRemark(){
        return this.remark;
    }
    public String getRoleId(){
        return this.roleId;
    }
    public Map<String,Object> getUser(){
        return this.user;
    }

}
