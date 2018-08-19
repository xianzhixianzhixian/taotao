package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    /**
     * 打开首页
     * @return
     */
    @RequestMapping("/index")
    public String showIndex(Model model){
        String addJson = contentService.getContentList();
        model.addAttribute("ad1",addJson);
        return "index";
    }

    @RequestMapping(value = "/httpClient/post", method = RequestMethod.POST)
    @ResponseBody
    public String testPost(String name,String passwd){
        return "username: " + name + " password:" + passwd;
    }
}
