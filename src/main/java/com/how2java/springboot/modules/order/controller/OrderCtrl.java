package com.how2java.springboot.modules.order.controller;

import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.order.service.OrderSrv;
import com.how2java.springboot.utils.JsonHashMap;
import com.how2java.springboot.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理
 * @author CaryZ
 * @date 2018-12-09
 */
@RestController
@RequestMapping("/order")
public class OrderCtrl {

    @Autowired
    OrderSrv orderSrv;

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
     * @return jsonHashMap
     */
    @RequestMapping("/add")
    public JsonHashMap add(@RequestBody Map<String, Object> parameterMap, HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("sender_id",usu.getUserId());
            parameterMap.put("creator_id",usu.getUserId());
            parameterMap.put("modifier_id",usu.getUserId());
            boolean flag=orderSrv.add(parameterMap);
            if (flag){
                jhm.putMessage("添加成功！");
            }else {
                jhm.putFail("添加失败！");
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 删除订单
     * @author CaryZ
     * @date 2018-12-09
     * @param id 订单id
     * @return jsonHashMap
     */
    @RequestMapping("/deleteById")
    public JsonHashMap deleteById(@RequestBody String id) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            boolean flag=orderSrv.deleteById(id);
            if (flag){
                jhm.putMessage("删除成功！");
            }else {
                jhm.putFail("删除失败！");
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 修改订单信息
     * 订单状态、派送时间、派送人
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * {
     *     "id":"订单id--",
     *     "state":"订单状态",
     *     "delivery_time":"派送时间",
     *     "delivery_id":"派送人id"
     * }
     * @return jsonHashMap
     */
    @RequestMapping("/updateById")
    public JsonHashMap updateById(@RequestBody Map<String, Object> parameterMap,HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("modifier_id",usu.getUserId());
            boolean flag=orderSrv.updateById(parameterMap);
            if (flag){
                jhm.putMessage("修改成功！");
            }else {
                jhm.putFail("修改失败！");
            }
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }

    /**
     * @description 查询订单列表
     * 完全匹配查询：订单编号、赠送人id、赠予人id、订单状态、派送时间等 （待前端确认）
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * {
     *     "num":"订单编号",
     *     "sender_id":"赠送人id",
     *     "receiver_id":"赠予人id",
     *     "state":"订单状态",
     *     "delivery_time":"派送时间"
     * }
     * @return jsonHashMap
     */
    @RequestMapping("/list")
    public JsonHashMap list(@RequestBody Map<String, String> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,List<Map<String,Object>>> listMap=new HashMap<>(1);
            listMap.put("list",orderSrv.list(parameterMap));
            jhm.putSuccess(listMap);
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }
}
