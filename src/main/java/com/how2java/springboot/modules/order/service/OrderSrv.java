package com.how2java.springboot.modules.order.service;

import com.how2java.springboot.dao.OrderDAOImpl;
import com.how2java.springboot.exception.PcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.*;
import static com.how2java.springboot.utils.BeanUtil.objectToString;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderSrv {

    @Autowired
    OrderDAOImpl orderDAOimpl;

    /**
     * @description 添加订单
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * {
     *     "item":"商品信息",
     *     "quantity":"商品数量?",
     *     "total_price":"订单总价",
     *     "receiver_id":"赠予人id",
     *     "remark":"备注"
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException{
        try{

            return orderDAOimpl.add(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(ADD_EXCEPTION,e.getMessage());
        }
    }


    /**
     * @description 删除订单
     * @author CaryZ
     * @date 2018-12-09
     * @param id 订单id
     * @return 删除成功--true 失败--false
     * @throws PcException
     */
    public boolean deleteById(String id) throws PcException{
        try{
            return orderDAOimpl.deleteById(id)==0? false:true;
        }catch (Exception e){
            throw new PcException(DELETE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 修改订单信息
     * 订单状态、派送时间、派送人
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return 修改成功--true 失败--false
     * @throws PcException
     */
    public boolean updateById(Map<String,Object> parameterMap) throws PcException{
        try{
            return orderDAOimpl.updateById(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(UPDATE_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 查询订单列表
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return list
     * @throws PcException
     */
    public List<Map<String, Object>> list(Map<String,String> parameterMap) throws PcException {
        try{
            return orderDAOimpl.list(parameterMap);
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }



}
