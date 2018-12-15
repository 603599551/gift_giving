package com.how2java.springboot.modules.budpurchase.service;

import com.alibaba.fastjson.JSONObject;
import com.how2java.springboot.dao.BudPurchaseDAO;
import com.how2java.springboot.dao.BudPurchaseDAOImpl;
import com.how2java.springboot.exception.PcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.ADD_EXCEPTION;
import static com.how2java.springboot.exception.PcExceptionCode.SELECT_EXCEPTION;

@Service
@Transactional(rollbackFor=Exception.class)
public class BudPurchaseSrv {

    @Autowired
    BudPurchaseDAOImpl budPurchaseDAOImpl;
    /**
     * @description 添加花芽订单
     * @author CaryZ
     * @date 2018-12-15
     * @param parameterMap
     * {
     *     "quantity":"商品数量",
     *     "total_price":"订单总价"
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException {
        try{

            return budPurchaseDAOImpl.add(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(ADD_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 查询订单列表
     * @author CaryZ
     * @date 2018-12-15
     * @param parameterMap
     * @return list
     * @throws PcException
     */
    public List<Map<String, String>> list(Map<String,String> parameterMap) throws PcException {
        try{
            return budPurchaseDAOImpl.list(parameterMap);
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }

}
