package com.how2java.springboot.modules.product.service;

import com.alibaba.fastjson.JSONObject;
import com.how2java.springboot.dao.ProductDAOImpl;
import com.how2java.springboot.exception.PcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.*;
import static com.how2java.springboot.utils.BeanUtil.objectToInt;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

@Service
@Transactional(rollbackFor=Exception.class)
public class ProductSrv {

    @Autowired
    ProductDAOImpl productDAOImpl;

    /**
     * @description 添加商品
     * @author CaryZ
     * @date 2018-12-17
     * @param parameterMap
     * {
     *     "name":"商品名称",
     *     "picture":"商品图片",
     *     "price":"商品价格",
     *     "meaning":"商品含义",
     *     "type":"商品类型 virtual/real"
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException{
        try{
            return productDAOImpl.add(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(ADD_EXCEPTION,e.getMessage());
        }
    }


    /**
     * @description 删除商品
     * @author CaryZ
     * @date 2018-12-17
     * @param id 商品id
     * @return 删除成功--true 失败--false
     * @throws PcException
     */
    public boolean deleteById(String id) throws PcException{
        try{
            return productDAOImpl.deleteById(id)==0? false:true;
        }catch (Exception e){
            throw new PcException(DELETE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 修改商品信息
     * @author CaryZ
     * @date 2018-12-17
     * @param parameterMap
     * {
     *     "name":"商品名称",
     *     "picture":"商品图片",
     *     "price":"商品价格",
     *     "meaning":"商品含义",
     *     "type":"商品类型 virtual/real"
     * }
     *
     * @return 修改成功--true 失败--false
     * @throws PcException
     */
    public boolean updateById(Map<String,Object> parameterMap) throws PcException {
        try{
            return productDAOImpl.updateById(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(UPDATE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 查询商品列表
     * @author CaryZ
     * @date 2018-12-17
     * @param parameterMap
     * @return list
     * @throws PcException
     */
    public List<Map<String, Object>> list(Map<String,String> parameterMap) throws PcException {
        try{
            List<Map<String, String>> orderList=productDAOImpl.list(parameterMap);
            List<Map<String, Object>> resultList=new ArrayList<>();
            for (Map<String, String> map:orderList){
                Map<String,Object> resultMap=new HashMap<>();
                resultMap.putAll(map);
                resultList.add(resultMap);
            }
            return resultList;
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }
}
