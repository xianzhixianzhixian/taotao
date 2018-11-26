package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

/**
 * 订单service
 * @author: xianzhixianzhixian
 * @date: 2018/11/26 10:51 PM
 */
public interface OrderService {

    /**
     * 创建订单
     * @param order
     * @return
     */
    String createOrder(Order order);
}
