package com.how2java.springboot.common.controller;

import com.how2java.springboot.dao.ProductDAOImpl;
import com.how2java.springboot.dao.UserDAOImpl;
import com.how2java.springboot.utils.JsonHashMap;
import com.how2java.springboot.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.how2java.springboot.utils.BeanUtil.objectToInt;
import static com.how2java.springboot.utils.UUIDTool.getUUID;

@RestController
public class PicUploadController {

    @Autowired
    UserDAOImpl userDAOImpl;
    @Autowired
    ProductDAOImpl productDAOImpl;

    /**
     * 图片上传
     * @author CaryZ
     * @date 2018-12-18
     * {
     *   "choice":"0:用户头像  1:商品图片 2:用户美照",
     *   "id":"商品id"
     * }
     * @param fileUpload
     * @return
     */
    @RequestMapping("/upload")
    public Object upload(MultipartFile fileUpload, int choice, String id, HttpServletRequest request){
        Map<String, Object> parameterMap=new HashMap<>();
        parameterMap.put("choice",choice);
        parameterMap.put("id",id);
        JsonHashMap jhm=new JsonHashMap();
        //获取文件名
        String fileName = fileUpload.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = getUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = "F:\\workspace\\springboot\\src\\main\\resources\\templates\\uploaded\\";
        try {
            //将图片保存到static文件夹里
            fileUpload.transferTo(new File(filePath+fileName));
            String relativeURL="/uploaded/"+fileName;
            if (objectToInt(parameterMap.get("choice"))==0){
                UserSessionUtil usu=new UserSessionUtil(request);
                parameterMap.put("id",usu.getUserId());
                parameterMap.put("head",relativeURL);
                userDAOImpl.updateById(parameterMap);
            }else if (objectToInt(parameterMap.get("choice"))==1){
                parameterMap.put("picture",relativeURL);
                productDAOImpl.updateById(parameterMap);
            } else if (objectToInt(parameterMap.get("choice"))==2){
                UserSessionUtil usu=new UserSessionUtil(request);
                parameterMap.put("id",usu.getUserId());
                Map<String,String> map1=new HashMap<>();
                map1.put("id",usu.getUserId());
                Map<String,Object> user=userDAOImpl.findOneByColumns(map1);
                if (user.get("picture")==null){
                    parameterMap.put("picture",relativeURL);
                }else {
                    parameterMap.put("picture",user.get("picture")+","+relativeURL);
                }
                userDAOImpl.updateById(parameterMap);
            }
            userDAOImpl.updateById(parameterMap);
            jhm.putSuccess("http://49.140.62.153"+relativeURL);
        } catch (Exception e) {
            e.printStackTrace();
            jhm.putError("no");
        }
        return jhm;
    }
}