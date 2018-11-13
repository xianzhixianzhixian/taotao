package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户管理service
 * @author: xianzhixianzhixian on 2018/10/28
 */
public interface UserService {

    /**
     * 数据校验
     * @param content
     * @param type
     * @return
     */
    TaotaoResult checkData(String content, Integer type);

    /**
     * 注册用户
     * @param user
     * @return
     */
    TaotaoResult createUser(TbUser user) throws Exception;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    TaotaoResult userLogout(String token);
}
