package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

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
    List<EasyUITreeNode> getCategoryList(long parentId);
}
