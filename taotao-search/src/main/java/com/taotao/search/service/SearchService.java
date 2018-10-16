package com.taotao.search.service;

import com.taotao.search.pojo.SearchResult;

/**
 * 搜索服务service
 * @author: xianzhixianzhixian on 2018/10/16
 */
public interface SearchService {

    SearchResult search(String queryString, Integer page, Integer rows) throws  Exception;
}
