package com.how2java.springboot.modules.budpurchase.controller;

import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.budpurchase.service.BudPurchaseSrv;
import com.how2java.springboot.utils.JsonHashMap;
import com.how2java.springboot.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 花芽订单管理
 * @author CaryZ
 * @date 2018-12-15
 */
@RestController
@RequestMapping("/budOrder")
public class BudPurchaseCtrl {

    @Autowired
    BudPurchaseSrv budPurchaseSrv;

    /**
     * @description 添加花芽订单
     * @author CaryZ
     * @date 2018-12-15
     * @param parameterMap
     * {
     *     "quantity":"商品数量",
     *     "total_price":"订单总价"
     * }
     * @return jsonHashMap
     */
    @RequestMapping("/add")
    public JsonHashMap add(@RequestBody Map<String, Object> parameterMap, HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("creator_id",usu.getUserId());
            boolean flag=budPurchaseSrv.add(parameterMap);
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
     * @description 查询花芽订单列表
     * @author CaryZ
     * @date 2018-12-09
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/list")
    public JsonHashMap list(@RequestParam Map<String, String> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,List<Map<String,String>>> listMap=new HashMap<>(1);
            listMap.put("list",budPurchaseSrv.list(parameterMap));
            jhm.putSuccess(listMap);
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }
}
