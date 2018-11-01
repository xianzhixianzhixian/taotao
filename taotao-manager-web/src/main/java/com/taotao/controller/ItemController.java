package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.StrUtil;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
import com.taotao.service.ItemParamItemService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理controller
 * @author KevinSmith 2017/12/16
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemParamItemService itemParamItemService;
    @Autowired
    private ItemDescService itemDescService;

    /*
        @PathVariable将/item/{itemId}中的itemId映射到Long itemId中的itemId
        通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入
        参中：URL 中的 {xxx} 占位符可以通过@PathVariable("xxx") 绑定到操作
        方法的入参中
     */
    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public TbItem getItemById(@PathVariable("itemId") Long itemId) {
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping(value = "/item/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
        EasyUIDataGridResult result = itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value = "/item/query/item/param/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemParam(@PathVariable("id") Long id){
        TaotaoResult result = null;
        try{
            result = itemParamItemService.getItemParamByItemId(id);
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/item/query/item/desc/{id}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemDesc(@PathVariable("id") Long id){
        TaotaoResult result = null;
        try{
            TbItemDesc itemDesc = itemDescService.selectItemDescByItemId(id);
            result = TaotaoResult.ok(itemDesc);
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value="/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item,String desc,String itemParams) {
        TaotaoResult result = null;
        try {
            result = itemService.createItem(item, desc, itemParams);
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/item/instock", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult instockItems(String ids){
        TaotaoResult result = null;
        try{
            result = itemService.updateInstockItems(StrUtil.StringToLongArray(ids,","));
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/item/reshelf", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult reshelfItems(String ids){
        TaotaoResult result = null;
        try{
            result = itemService.updateReshelfItems(StrUtil.StringToLongArray(ids,","));
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/item/delete", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteItems(String ids){
        TaotaoResult result = null;
        try{
            result = itemService.deleteItems(StrUtil.StringToLongArray(ids,","));
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }

    @RequestMapping(value = "/item/update", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult updateItem(TbItem item, String desc, String itemParams, Long itemParamId){
        TaotaoResult result = null;
        try{
            result = itemService.updateItem(item, desc, itemParams, itemParamId);
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return result;
    }
}
