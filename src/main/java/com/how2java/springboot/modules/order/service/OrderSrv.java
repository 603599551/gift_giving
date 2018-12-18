package com.how2java.springboot.modules.order.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.how2java.springboot.dao.OrderDAOImpl;
import com.how2java.springboot.dao.UserDAOImpl;
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
import static com.how2java.springboot.utils.BeanUtil.objectToString;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderSrv {

    @Autowired
    OrderDAOImpl orderDAOimpl;
    @Autowired
    UserDAOImpl userDAOImpl;

    /**
     * @description 添加订单
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * {
     *     "item":"商品信息",
     *     "quantity":"商品数量?",
     *     "total_price":"订单总价",
     *     "username":"手机号/用户名",
     *     "realname":"姓名",
     *     "address":"地址",
     *     "type":"虚拟的virtual,真实的real",
     *     "remark":"备注"
     * }
     * @return 添加成功--true 失败--false
     */
    public boolean add(Map<String,Object> parameterMap) throws PcException{
        try{
            //赠送礼物，赠送人花芽减少
            Map<String,String> paraMap1=new HashMap<>(2);
            paraMap1.put("id",(String)parameterMap.get("sender_id"));
            Map<String,Object> paraMap2=userDAOImpl.findOneByColumns(paraMap1);
            if (paraMap2==null){
                return false;
            }
            paraMap2.put("bud_quantity",objectToInt(paraMap2.get("bud_quantity"))-objectToInt(parameterMap.get("total_price")));
            if (userDAOImpl.updateById(paraMap2)==0){
                return false;
            }
            Map<String,String> paraMap3=new HashMap<>(2);
            paraMap3.put("username",(String)parameterMap.get("username"));
            Map<String,Object> paraMap4=userDAOImpl.findOneByColumns(paraMap3);
            //如果赠予人不存在，自动注册一个
            if (paraMap4==null){
                paraMap4=new HashMap<>();
                paraMap4.put("id",getUUID());
                paraMap4.put("role_id","0");
                paraMap4.put("username",parameterMap.get("username"));
                paraMap4.put("realname",parameterMap.get("realname"));
                paraMap4.put("address",parameterMap.get("address"));
                if (userDAOImpl.add(paraMap4)==0){
                    return false;
                }
            }
            parameterMap.put("receiver_id",paraMap4.get("id"));
            //接收虚拟礼物，接收人花芽增加
            if ("virtual".equals(String.valueOf(parameterMap.get("type")))){
                paraMap2.put("bud_quantity",objectToInt(paraMap4.get("bud_quantity"))+objectToInt(parameterMap.get("total_price")));
                if (userDAOImpl.updateById(paraMap4)==0){
                    return false;
                }
            }
            parameterMap.put("item",JSON.toJSONString(parameterMap.get("item")));
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
            List<Map<String, String>> orderList=orderDAOimpl.list(parameterMap);
            Map<String,String> userMap1=new HashMap<>();
            List<Map<String, Object>> userList=userDAOImpl.list(userMap1);
            Map<String,Object> userMap2=new HashMap<>();
            //key-- 用户id value--用户信息
            for (Map<String, Object> user:userList){
                userMap2.put(String.valueOf(user.get("id")),user);
            }
            List<Map<String, Object>> resultList=new ArrayList<>();
            for (Map<String, String> map:orderList){
                Map<String,Object> resultMap=new HashMap<>();
                resultMap.putAll(map);
                resultMap.put("item", JSONObject.parseObject(map.get("item")));
                resultMap.put("receiver_data",userMap2.get(resultMap.get("receiver_id")));
                resultMap.put("sender_data",userMap2.get(resultMap.get("sender_id")));
                resultList.add(resultMap);
            }
            return resultList;
        }catch (Exception e){
            throw new PcException(SELECT_EXCEPTION,e.getMessage());
        }
    }

}
