package com.how2java.springboot.exception;

/**
 * 数据库CRUD操作报错对应不同的code值
 * @author CaryZ
 * @date 2018-12-06
 */
public interface PcExceptionCode {

    int ADD_EXCEPTION=10001;

    int DELETE_EXCEPTION=10002;

    int UPDATE_EXCEPTION=10003;

    int SELECT_EXCEPTION=10004;

}
