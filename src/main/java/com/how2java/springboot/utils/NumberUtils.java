package com.how2java.springboot.utils;

import java.math.BigDecimal;

/**
 * 处理数字的工具类
 * @author CaryZ
 * @date 2018-12-06
 */
public class NumberUtils {

    /**
     * 对double类型的数字四舍五入保留两位小数
     * @author CaryZ
     * @date 2018-12-06
     */
    public static double getDouble2(double d){
        double returnValue=0.0D;
        returnValue=(new BigDecimal(Double.toString(d)).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
        return returnValue;
    }

    /**
     * 对float类型的数字四舍五入保留两位小数
     * @author CaryZ
     * @date 2018-12-06
     */
    public static float getFloat2(float f){
        float returnValue=0.0f;
        returnValue=(new BigDecimal(Float.toString(f)).setScale(2,BigDecimal.ROUND_HALF_UP).floatValue());
        return returnValue;
    }

    /**
     * 对double类型的数字四舍五入保留整数
     * @author CaryZ
     * @date 2018-12-06
     */
    public static int getInt(double d){
        int returnValue=0;
        returnValue=(new BigDecimal(Double.toString(d)).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
        return returnValue;
    }

    /**
     * 对float类型的数字四舍五入保留整数
     * @author CaryZ
     * @date 2018-12-06
     */
    public static int getInt(float d){
        int returnValue=0;
        returnValue=(new BigDecimal(Float.toString(d)).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
        return returnValue;
    }
}
