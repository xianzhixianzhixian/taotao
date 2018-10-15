package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * solr服务更新service
 * @author: xianzhixianzhixian on 2018/10/15
 */
public interface ItemService {

    /**
     * 将所有的商品数据导入solr服务中
     * @return
     */
    TaotaoResult importAllItems();
}
