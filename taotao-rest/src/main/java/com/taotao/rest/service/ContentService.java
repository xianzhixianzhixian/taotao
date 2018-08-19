package com.taotao.rest.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * 获取内容分类service
 * @author xianzhixianzhixian
 */
public interface ContentService {

    /**
     * 根据contentCid获取分类列表
     * @param contentCid
     * @return
     */
    List<TbContent> getContentList(Long contentCid);
}
