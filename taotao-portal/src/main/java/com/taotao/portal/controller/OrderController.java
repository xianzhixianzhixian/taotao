package com.taotao.portal.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.portal.pojo.CartItem;
import com.taotao.portal.pojo.Order;
import com.taotao.portal.service.CartService;
import com.taotao.portal.service.OrderService;
import com.taotao.portal.service.impl.OrderServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单controller
 * @author: xianzhixianzhixian
 * @date: 2018/11/26 10:15 PM
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, HttpServletResponse response, Model model){
        //使用购物车的服务从Cookie中取购物车商品数据
        List<CartItem> list = cartService.getAllCartItems(request, response);
        //传递给页面
        model.addAttribute("cartList", list);
        return "order-cart";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrder(Order order, Model model){
        //创建订单
        try {
            String orderId = orderService.createOrder(order);
            model.addAttribute("orderId", orderId);
            model.addAttribute("payment", order.getPayment());
            model.addAttribute("date", new DateTime().plusDays(Integer.parseInt(orderService.ORDER_PLUS_DAY)).toString("yyyy-MM-dd"));
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", ExceptionUtil.getStackTrace(e));
            return "error/exception";
        }
    }
}
