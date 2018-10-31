package com.taotao.service;

/**
 * 展示商品规格参数service
 * @author xianzhixianzhixian 2018/10/24
 */
public interface ItemParamItemService {

    /**
     * 获取商品参数信息，返回html代码段
     * @param itemId
     * @return
     */
	String getItemParamHtmlByItemId(Long itemId);

    /**
     * 获取商品参数信息，返回html代码段
     * @param itemId
     * @return
     */
    String getItemParamByItemId(Long itemId);
}
