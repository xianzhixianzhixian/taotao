package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * 用户登录service
 * @author: xianzhixianzhixian
 * @date: 2018/11/13 10:18 PM
 */
public interface UserService {

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    TbUser getUserByToken(String token);
}
