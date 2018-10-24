package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品信息controller
 * @author: xianzhixianzhixian on 2018/10/24
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.GET)
    public String searchItemBaseInfo(@PathVariable("itemId") Long itemId, Model model) {
        ItemInfo item = itemService.searchItemBaseInfo(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String searchItemDescInfo(@PathVariable("itemId") Long itemId) {
        return itemService.searchItemDescInfo(itemId);
    }

    @RequestMapping(value = "/item/param/{itemId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String searchItemParamInfo(@PathVariable("itemId") Long itemId) {
        return itemService.searchItemParamInfo(itemId);
    }
}
