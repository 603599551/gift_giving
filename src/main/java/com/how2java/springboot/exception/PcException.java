package com.how2java.springboot.exception;

/**
 * Pc端异常类
 * @author CaryZ
 * @date 2018-12-06
 */
public class PcException extends BaseException {

    private int code;
    private String message;

    public PcException(int code,String message){
        super(message);
        this.code=code;
        this.message=message;
    }

    public int getCode(){
        return this.code;
    }

    public String getMsg(){
        return this.message;
    }

    public void setCode(int code){
        this.code=code;
    }

    public void setMsg(String message){
        this.message=message;
    }
}
