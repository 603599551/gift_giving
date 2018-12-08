package com.how2java.springboot.exception;

/**
 * 异常基类
 * @author CaryZ
 * @date 2018-12-06
 */
public class BaseException extends Exception {

    public BaseException(){

    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(Throwable cause){
        super(cause);
    }

    public BaseException(String message,Throwable cause){
        super(message,cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
