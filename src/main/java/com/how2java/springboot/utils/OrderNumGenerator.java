package com.how2java.springboot.utils;

import java.util.UUID;

/**
 * 生成订单编号的公共类
 * @author CaryZ
 * @date 2018-12-06
 */
public class OrderNumGenerator {

    /**
     * 通过UUID生成16位唯一订单号
     * @author CaryZ
     * @date 2018-12-06
     * @return 订单号
     */
    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

}
