package com.taotao.portal.service;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;

/**
 * 商品信息服务
 * @author: xianzhixianzhixian on 2018/10/24
 */
public interface ItemService {

    /**
     * 获取商品基本信息
     * @param itemId
     * @return
     */
    ItemInfo searchItemBaseInfo(Long itemId);

    /**
     * 获取商品描述信息
     * @param itemId
     * @return
     */
    TbItemDesc searchItemDescInfo(Long itemId);

    /**
     * 获取商品参数信息
     * @param itemId
     * @return
     */
    TbItemParamItem searchItemParamInfo(Long itemId);
}
