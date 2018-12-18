package com.how2java.springboot.modules.product.controller;


import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.product.service.ProductSrv;
import com.how2java.springboot.utils.JsonHashMap;
import com.how2java.springboot.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品管理
 * @author CaryZ
 * @date 2018-12-17
 */
@RestController
@RequestMapping("/product")
public class ProductCtrl {

    @Autowired
    ProductSrv productSrv;

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
     * @return jsonHashMap
     */
    @RequestMapping("/add")
    public JsonHashMap add(@RequestParam Map<String, Object> parameterMap, HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("creator_id",usu.getUserId());
            parameterMap.put("modifier_id",usu.getUserId());
            boolean flag=productSrv.add(parameterMap);
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
     * @description 删除商品
     * @author CaryZ
     * @date 2018-12-17
     * @param id 商品id
     * @return jsonHashMap
     */
    @RequestMapping("/deleteById")
    public JsonHashMap deleteById(@RequestParam String id) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            boolean flag=productSrv.deleteById(id);
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
     * @return jsonHashMap
     */
    @RequestMapping("/updateById")
    public JsonHashMap updateById(@RequestParam Map<String, Object> parameterMap,HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("modifier_id",usu.getUserId());
            boolean flag=productSrv.updateById(parameterMap);
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
     * @description 查询商品列表
     * @author CaryZ
     * @date 2018-12-17
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/list")
    public JsonHashMap list(@RequestParam Map<String, String> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,List<Map<String,Object>>> listMap=new HashMap<>(1);
            List<Map<String,Object>> orderList=productSrv.list(parameterMap);
            listMap.put("list",orderList);
            jhm.putSuccess(listMap);
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }
}
