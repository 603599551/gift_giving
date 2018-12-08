package com.how2java.springboot.utils;

import java.util.UUID;

/**
 * UUID工具类
 * @author CaryZ
 * @date 2018-12-06
 */
public class UUIDTool {

    /**
     * id主键--UUID，保证不重复
     * @author CaryZ
     * @date 2018-12-06
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
