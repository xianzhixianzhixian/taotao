package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转，打开后台管理页面首页
 * @author KevinSmith 2017/12/16
 */
@Controller
public class PageController {

    /**
     * 打开首页
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * 打开其它页面
     * 返回值String代表逻辑视图
     */
    @RequestMapping("/{page}")
    public String showIndex(@PathVariable String page){
        return page;
    }
}
