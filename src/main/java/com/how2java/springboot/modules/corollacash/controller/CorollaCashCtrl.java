package com.how2java.springboot.modules.corollacash.controller;


import com.how2java.springboot.exception.PcException;
import com.how2java.springboot.modules.corallaorder.service.CorollaOrderSrv;
import com.how2java.springboot.modules.corollacash.service.CorollaCashSrv;
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
 * 花冠提现管理
 * @author CaryZ
 * @date 2018-12-16
 */
@RestController
@RequestMapping("/corollaCash")
public class CorollaCashCtrl {

    @Autowired
    CorollaCashSrv corollaCashSrv;

    /**
     * @description 添加花冠提现订单
     * @author CaryZ
     * @date 2018-12-16
     * @param parameterMap
     * {
     *     "corolla_quantity":"花冠数量",
     *     "total_price":"兑换金额"
     * }
     * @return jsonHashMap
     */
    @RequestMapping("/add")
    public JsonHashMap add(@RequestParam Map<String, Object> parameterMap, HttpServletRequest request) {
        JsonHashMap jhm=new JsonHashMap();
        UserSessionUtil usu=new UserSessionUtil(request);
        try{
            parameterMap.put("user_id",usu.getUserId());
            boolean flag=corollaCashSrv.add(parameterMap);
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
     * @description 查询订单列表
     * @author CaryZ
     * @date 2018-12-16
     * @param parameterMap
     * @return jsonHashMap
     */
    @RequestMapping("/list")
    public JsonHashMap list(@RequestParam Map<String, String> parameterMap) {
        JsonHashMap jhm=new JsonHashMap();
        try{
            Map<String,List<Map<String,String>>> listMap=new HashMap<>(1);
            listMap.put("list",corollaCashSrv.list(parameterMap));
            jhm.putSuccess(listMap);
        }catch (PcException e){
            e.printStackTrace();
            jhm.putError(e.getMsg());
        }
        return jhm;
    }
}
