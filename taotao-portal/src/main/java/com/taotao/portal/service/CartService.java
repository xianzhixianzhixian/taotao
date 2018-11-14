package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车service
 * @author: xianzhixianzhixian
 * @date: 2018/11/14 8:29 PM
 */
public interface CartService {

    /**
     * 添加商品到购物车
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
}
