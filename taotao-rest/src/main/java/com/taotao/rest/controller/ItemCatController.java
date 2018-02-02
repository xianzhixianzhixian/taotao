package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * 商品分类列表
 * 钰丰 2018/2/2
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

//    @RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
//    @ResponseBody
//    public String getItemCatList(String callback){
//        //callback为方法名称
//        CatResult catResult=itemCatService.getCatItemList();
//        //把pojo转换成字符串
//        String json= JsonUtils.objectToJson(catResult);
//        //拼装返回值
//        String result=callback+"("+json+");";
//        return result;
//    }

    @RequestMapping("/itemcat/list")
    @ResponseBody
    public Object getItemCatList(String callback){ //callback是js方法名
        //callback为方法名称
        CatResult catResult=itemCatService.getCatItemList();
        //把pojo转换成字符串
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(catResult);
        //拼装返回值
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
