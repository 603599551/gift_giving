package com.how2java.springboot.modules.corallaorder.service;


import com.how2java.springboot.dao.CorollaOrderDAOImpl;
import com.how2java.springboot.dao.UserDAOImpl;
import com.how2java.springboot.exception.PcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.how2java.springboot.exception.PcExceptionCode.ADD_EXCEPTION;
import static com.how2java.springboot.exception.PcExceptionCode.SELECT_EXCEPTION;
import static com.how2java.springboot.utils.BeanUtil.objectToInt;

@Service
@Transactional(rollbackFor=Exception.class)
public class CorollaOrderSrv {

    @Autowired
    CorollaOrderDAOImpl corollaOrderDAOImpl;
    @Autowired
    UserDAOImpl userDAOImpl;

    /**
     * @description 添加花芽兑换花冠订单
     * @author CaryZ
     * @date 2018-12-16
     * @param parameterMap
     * {
     *     "bud_quantity":"花芽数量",
     *     "corolla_quantity":"花冠数量"
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException {
        try{
            Map<String,String> paraMap1=new HashMap<>(2);
            paraMap1.put("id",(String)parameterMap.get("user_id"));
            Map<String,Object> paraMap2=userDAOImpl.findOneByColumns(paraMap1);
            paraMap2.put("bud_quantity",objectToInt(paraMap2.get("bud_quantity"))-objectToInt(parameterMap.get("bud_quantity")));
            paraMap2.put("corolla_quantity",objectToInt(paraMap2.get("corolla_quantity"))+objectToInt(parameterMap.get("corolla_quantity")));
            if (userDAOImpl.updateById(paraMap2)==0){
                return false;
            }
            return corollaOrderDAOImpl.add(parameterMap)==0? false:true;
        }catch (Exception e){
            throw new PcException(ADD_EXCEPTION,e.getMessage());
        }
    }

    /**
     * @description 查询订单列表
     * @author CaryZ
     * @date 2018-12-16
     * @param parameterMap
     * @return list
     * @throws PcException
     */
    public List<Map<String, String>> list(Map<String,String> parameterMap) throws PcException {
        try{
            return corollaOrderDAOImpl.list(parameterMap);
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }
}
