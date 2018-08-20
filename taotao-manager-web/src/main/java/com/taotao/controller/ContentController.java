package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容管理controller
 * @author xianzhixianzhixian on 20180816
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult listContents(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId){
        List<TbContent> contentList = contentService.listContent(categoryId);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(contentList);
        result.setTotal((long) contentList.size());
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent content){
        return contentService.insertContent(content);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult editContent(TbContent content){
        return contentService.editContent(content);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult deleteContent(String ids){
        return contentService.deleteContent(ids);
    }
}
