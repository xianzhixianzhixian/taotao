package com.taotao.service;

import com.taotao.pojo.TbItemDesc;

/**
 * 商品描述管理service
 * @author: xianzhixianzhixian on 2018/10/30
 */
public interface ItemDescService {

    /**
     * 通过itemId查询商品描述信息
     * @param itemId
     * @return
     */
    TbItemDesc selectItemDescByItemId(Long itemId);
}
