package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
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

    /**
     * 添加内容分类具体信息
     * @param content
     * @return
     */
    TaotaoResult insertContent(TbContent content) throws Exception;

    /**
     * 根据id删除内容分类包含的具体信息
     * @param ids
     * @return
     */
    TaotaoResult deleteContent(String ids) throws Exception;

    /**
     * 修改content内容
     * @param content
     * @return
     */
    TaotaoResult editContent(TbContent content) throws Exception;
}
