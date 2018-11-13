package com.taotao.portal.interceptor;

import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器，拦截器中@Value会失效
 * @author: xianzhixianzhixian
 * @date: 2018/11/13 9:46 PM
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //在Handler之前处理
        //返回值决定Handler是否执行。true执行，false不执行。
        //1、判断用户是否登录，从Cookie中取token，根据token换取用户信息，调用sso的接口，取不到用户信息，把用户请求的url作为参数传递给登录页面返回false
        String token = CookieUtils.getCookieValue(httpServletRequest, userService.PORTAL_USER_COOKIE_NAME);
        TbUser user = userService.getUserByToken(token);
        if (user ==null){
            httpServletResponse.sendRedirect(userService.SSO_BASE_URL+userService.SSO_PAGE_LOGIN+"?redirect="+httpServletRequest.getRequestURL());
            return false;
        }
        httpServletRequest.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //在Handler执行之后，返回ModelAndView之前执行
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //返回ModelAndView之后
        //响应用户之后
    }
}
