package com.taotao.portal.controller;

import com.taotao.common.utils.StrUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * taotao-portal的商品搜索controller
 * @author: xianzhixianzhixian on 2018/10/18
 */
@Controller
@RequestMapping("")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(value = "q", defaultValue = "电脑") String queryString, @RequestParam(value = "page",defaultValue = "1") Integer page, Model model){
        SearchResult searchResult = searchService.search(queryString, page);
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);
        return "search";
    }
}
