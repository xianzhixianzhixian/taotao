package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }
}
