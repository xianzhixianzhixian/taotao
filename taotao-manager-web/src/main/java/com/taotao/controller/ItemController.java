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

    /*
        @PathVariable将/item/{itemId}中的itemId映射到Long itemId中的itemId
        通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入
        参中：URL 中的 {xxx} 占位符可以通过@PathVariable("xxx") 绑定到操作
        方法的入参中
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId") Long itemId){
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
    private TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
        TaotaoResult result=itemService.createItem(item,desc,itemParams);
        return result;
    }
}
