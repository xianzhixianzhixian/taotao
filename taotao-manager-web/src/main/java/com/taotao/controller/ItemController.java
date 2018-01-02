package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import javax.xml.ws.WebServiceRef;

/**
 * 测试SSM整合的结果的controller
 * @author KevinSmith 2017/12/16
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody //这个注解是返回JSON数据时用到的
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem=itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(int page,int rows){
        EasyUIDataGridResult result=itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value="/item/save",method = RequestMethod.POST)
    @ResponseBody
    private TaotaoResult createItem(TbItem item,String desc) throws Exception{
        TaotaoResult result=itemService.createItem(item,desc);
        return result;
    }
}
