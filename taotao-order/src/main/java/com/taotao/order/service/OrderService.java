package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbOrder;
import com.taotao.pojo.TbOrderItem;
import com.taotao.pojo.TbOrderShipping;

import java.util.List;

/**
 * 订单service
 * @author: xianzhixianzhixian
 * @date: 2018/11/20 9:53 PM
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     * @param itemList
     * @param orderShipping
     * @return
     */
    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) throws Exception;
}
