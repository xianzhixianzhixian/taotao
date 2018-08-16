package com.taotao.service;

import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * 内容管理service
 * @author xianzhixianzhixian on 20180816
 */
public interface ContentService {

    /**
     * 获取内容管理中标题包含的所有内容
     * @param categoryId
     * @return
     */
    List<TbContent> listContent(Long categoryId);
}
