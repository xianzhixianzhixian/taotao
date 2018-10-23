package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品信息controller
 * @author: xianzhixianzhixian on 2018/10/22
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/info/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemBaseInfo(@PathVariable("itemId") Long itemId){
        TaotaoResult result = itemService.searchItemBaseInfo(itemId);
        return result;
    }

    @RequestMapping(value = "/desc/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemDescInfo(@PathVariable("itemId") Long itemId){
        TaotaoResult result = itemService.searchItemDescInfo(itemId);
        return result;
    }

    @RequestMapping(value = "/param/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public TaotaoResult searchItemParaInfo(@PathVariable("itemId") Long itemId) {
        TaotaoResult result = itemService.searchItemParamInfo(itemId);
        return result;
    }
}
