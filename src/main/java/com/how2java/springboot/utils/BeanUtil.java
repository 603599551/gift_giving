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

    /**
     * 利用java反射将object转Map对象
     */
    public static Map<String, Object> ObjectToMapUtil(Object obj) throws IllegalAccessException {
        Map<String,Object> map=new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}
