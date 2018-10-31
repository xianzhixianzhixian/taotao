package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * redis缓存同步服务controller
 * @author: xianzhixianzhixian on 2018/9/3
 */
@Controller
@RequestMapping("/cache/sync")
public class JedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/content/{contentCid}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult contentCacheSync(@PathVariable Long contentCid){
        TaotaoResult result = redisService.syncContent(contentCid);
        return result;
    }

    @RequestMapping(value = "/item/{itemId}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult itemCacheSync(@PathVariable("itemId") Long itemId){
        TaotaoResult result = redisService.syncItem(itemId);
        return result;
    }
}
