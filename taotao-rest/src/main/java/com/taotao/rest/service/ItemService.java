package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 商品service
 * @author: xianzhixianzhixian on 2018/10/22
 */
public interface ItemService {

    /**
     * 查询商品基本信息
     * @param itemId
     * @return
     */
    TaotaoResult searchItemBaseInfo(Long itemId);

    /**
     * 查询商品描述信息
     * @param itemId
     * @return
     */
    TaotaoResult searchItemDescInfo(Long itemId);

    /**
     * 查询商品规格参数信息
     * @param itemId
     * @return
     */
    TaotaoResult searchItemParamInfo(Long itemId);
}
