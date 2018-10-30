package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @author xianzhixianzhixian on 20180813
 * 首页内容分类展示service
 */
public interface ContentCategoryService {

    /**
     * 根据parentId获取treeNode的内容
     * @param parentId
     * @return
     */
    List<EasyUITreeNode> getCategoryList(Long parentId);

    /**
     * 添加节点数据
     * @param parentId
     * @param name
     * @return
     */
    TaotaoResult insertContentCategory(Long parentId, String name) throws Exception;

    /**
     * 根据id删除节点
     * @param parentId
     * @param id
     * @return
     */
    TaotaoResult deleteContentCategory(Long parentId, Long id) throws Exception;

    /**
     * 根据id重命名节点
     * @param id
     * @param name
     * @return
     */
    TaotaoResult updateContentCategory(Long id, String name) throws Exception;
}
