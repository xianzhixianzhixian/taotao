package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

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
    TaotaoResult createUser(TbUser user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    TaotaoResult userLogin(String username,String password);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    TaotaoResult getUserByToken(String token);
}