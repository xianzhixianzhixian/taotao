package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 请求并接收taotao-search返回的数据
 * @author: xianzhixianzhixian on 2018/10/17
 */
public interface SearchService {

    /**
     * 搜索商品
     * @param queryString
     * @param page
     * @return
     */
    SearchResult search(String queryString, Integer page);
}
