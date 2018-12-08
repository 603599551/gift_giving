package com.how2java.springboot.utils;

import java.util.HashMap;

/**
 * 后台响应通用工具类
 * 请求成功，数据正确：
 *      code:1
 *      data:具体数据
 * 请求成功，数据错误：
 *      code:0
 *      message:错误原因
 * 请求失败，运行异常：
 *      code:-1
 *      message:服务器异常
 * @author CaryZ
 * @date 2018-12-06
 */
public class JsonHashMap extends HashMap<String,Object> {

    public static final int RESULT_FAIL=0;
    public static final int RESULT_ERROR=-1;
    public static final int RESULT_SUCCESS=1;

    public static final String CODE="code";
    public static final String DATA="data";
    public static final String MESSAGE="message";

    public static final String FAIL_MESSAGE="数据加载失败！";
    public static final String ERROR_MESSAGE="服务器异常！";

    /**
     * 构造方法默认code为1
     */
    public JsonHashMap(){
        put(CODE,RESULT_SUCCESS);
    }

    /**
     * 设置键值对 key-value
     * @param key
     * @param value
     * @return
     */
    @Override
    public JsonHashMap put(String key, Object value){
        super.put(key,value);
        return this;
    }

    /**
     * 设置code值
     * 请求成功，数据正确：
     *      code:1
     * 请求成功，数据错误：
     *      code:0
     * 请求失败，运行异常：
     *      code:-1
     * @param code
     * @return this
     */
    public JsonHashMap putCode(int code){
        return this.put(CODE,code);
    }

    /**
     * 设置message值
     * 请求成功，数据错误：
     *      message:错误原因
     * 请求失败，运行异常：
     *      message:服务器异常
     * @param message
     * @return
     */
    public JsonHashMap putMessage(String message){
        return this.put(MESSAGE,message);
    }

    /**
     * 请求成功，数据正确
     * @param object 请求成功的响应数据
     * @return this
     */
    public JsonHashMap putSuccess(Object object){
        return this.put(DATA,object);
    }

    /**
     * 请求成功，数据错误
     * @return this
     */
    public JsonHashMap putFail(){
        return this.putCode(RESULT_FAIL).putMessage(FAIL_MESSAGE);
    }

    /**
     * 请求成功，数据错误
     * @param message 错误提示信息
     * @return this
     */
    public JsonHashMap putFail(String message){
        return this.putCode(RESULT_FAIL).putMessage(message);
    }

    /**
     * 请求失败，运行异常
     * @return this
     */
    public JsonHashMap putError(){
        return this.putCode(RESULT_ERROR).putMessage(ERROR_MESSAGE);
    }

    /**
     * 请求失败，运行异常
     * @param message 异常提示信息
     * @return this
     */
    public JsonHashMap putError(String message){
        return this.putCode(RESULT_ERROR).putMessage(message);
    }
}
