package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.pojo.TbItem;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xianzhixianzhixian
 * @date: 2018/11/14 8:29 PM
 */
@Service
public class CartServiceImpl implements CartService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITEM_INFO_URL}")
    private String ITEM_INFO_URL;
    @Value("${CART_COOKIE_KEY}")
    private String CART_COOKIE_KEY;

    @Override
    public TaotaoResult addCartItem(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        CartItem cartItem = null;
        //获取购物车商品列表
        List<CartItem> list = getCartItemList(request);
        //判断购物车中是否存在商品
        for (CartItem item : list){
            if (item.getId() == itemId){
                cartItem = item;
                cartItem.setNum(cartItem.getNum()+num);
                break;
            }
        }
        if (cartItem == null) {
            cartItem = new CartItem();
            //根据商品id查询商品基本信息
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_INFO_URL + itemId);
            //把json转换成Java对象
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItem.class);
            if (result.getStatus() == 200) {
                TbItem item = (TbItem) result.getData();
                cartItem.setId(item.getId());
                cartItem.setTitle(item.getTitle());
                cartItem.setPrice(item.getPrice());
                cartItem.setImage(item.getImage() == null ? "" : item.getImage().split(",")[0]);
                cartItem.setNum(num);
            }
            //加入购物车
            list.add(cartItem);
        }
        CookieUtils.setCookie(request, response, CART_COOKIE_KEY, JsonUtils.objectToJson(list), true);
        return TaotaoResult.ok();
    }

    /**
     * 获取购物车商品列表
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request){
        String cartJson = CookieUtils.getCookieValue(request, CART_COOKIE_KEY, true);
        if (StrUtil.testTrimEmpty(cartJson)){
            return new ArrayList<>();
        }
        try {
            List<CartItem> list = JsonUtils.jsonToList(cartJson, CartItem.class);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
