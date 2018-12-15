package com.how2java.springboot.dao;

import com.how2java.springboot.dao.BudPurchaseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.how2java.springboot.utils.DateUtil.getDateTime;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

@Repository
public class BudPurchaseDAOImpl implements BudPurchaseDAO {

    @Autowired
    BudPurchaseDAO budPurchaseDAO;

    @Override
    public int add(Map<String, Object> map) {
        map.put("id", getUUID());
        map.put("create_time",getDateTime());
        return budPurchaseDAO.add(map);
    }

//    @Override
//    public void deleteById(String id) {
//
//    }
//
//    @Override
//    public int updateById(Map<String, Object> map) {
//
//        return 1;
//    }
//
//    @Override
//    public Map<String, Object> findOneById(String id) {
//        return null;
//    }

    @Override
    public List<Map<String, String>> list(Map<String, String> map) {
        return budPurchaseDAO.list(map);
    }
}
