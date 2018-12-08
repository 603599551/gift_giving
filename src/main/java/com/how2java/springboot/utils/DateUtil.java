package com.how2java.springboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author CaryZ
 * @date 2018-12-06
 */
public class DateUtil {

    public static final TimeZone TZ_SHANGHAI=TimeZone.getTimeZone("Asia/Shanghai");

    public DateUtil(){

    }

    /**
     * 获取当前日期 例：2018-12-06
     * @author CaryZ
     * @date 2018-12-06
     * @return
     */
    public static String getDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TZ_SHANGHAI);
        String sDate=sdf.format(new Date());
        return sDate;
    }

    /**
     * 获取当前时间 例：2018-12-06 21:33:22
     * @author CaryZ
     * @date 2018-12-06
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TZ_SHANGHAI);
        String sDate=sdf.format(new Date());
        return sDate;
    }

}
