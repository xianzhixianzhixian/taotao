package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @author: xianzhixianzhixian on 2018/9/3
 */
public interface RedisService {

    /**
     * 同步首页大广告位redis缓存
     * @param contentCid
     * @return
     */
    TaotaoResult syncContent(Long contentCid);

    //TODO商品信息修改后的缓存同步问题
}
