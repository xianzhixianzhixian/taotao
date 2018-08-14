package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemCatService {

	/**
	 * 获取节点数据
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getCatList(long parentId);
}
