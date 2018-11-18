package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 购物车商品controller
 * @author: xianzhixianzhixian
 * @date: 2018/11/18 4:01 PM
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("/success")
    public String showSuccess(){
        return "cartSuccess";
    }

    @RequestMapping("/add/{itemId}")
    public String addItemsToCart(@PathVariable("itemId") Long itemId, @RequestParam(defaultValue = "1") Integer num,
                                 HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = cartService.addCartItem(itemId, num, request, response);
        return "redirect:/cart/success.html";
    }

    @RequestMapping("/update/{itemId}")
    public String updateItemsToCart(@PathVariable("itemId") Long itemId, @RequestParam(defaultValue = "1") Integer num,
                                 HttpServletRequest request, HttpServletResponse response){
        TaotaoResult result = cartService.updateCartItem(itemId, num, request, response);
        return "redirect:/cart/cart.html";
    }

    /**
     * 展示所有购物车的商品
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/cart")
    public String getCartItems(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<CartItem> cartList = cartService.getAllCartItems(request, response);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    /**
     * 删除购物车中的商品
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delete/{itemId}")
    public String deleteCartItem(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpServletResponse response){
        cartService.deleteCartItem(itemId, request, response);
        return "redirect:/cart/cart.html";
    }
}
