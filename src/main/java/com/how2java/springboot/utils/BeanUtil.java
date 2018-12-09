package com.how2java.springboot.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础工具类
 * @author CaryZ
 * @date 2018-12-07
 */
public class BeanUtil {

    /**
     * object转String对象
     * @author CaryZ
     * @date 2018-12-07
     * @param object
     * @return
     */
    public static String objectToString(Object object){
        return String.valueOf(object);
    }

    /**
     * object转int对象
     * @author CaryZ
     * @date 2018-12-08
     * @param object
     * @return int
     */
    public static int objectToInt(Object object){
        return Integer.parseInt(objectToString(object));
    }

}
