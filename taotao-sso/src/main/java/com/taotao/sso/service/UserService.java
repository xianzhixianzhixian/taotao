package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

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
}
