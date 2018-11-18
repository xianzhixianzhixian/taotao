package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 展示购物车所有商品
     * @param request
     * @param response
     * @return
     */
    List<CartItem> getAllCartItems(HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除购物车商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    TaotaoResult deleteCartItem(Long itemId, HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新购物车商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @return
     */
    TaotaoResult updateCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
}
