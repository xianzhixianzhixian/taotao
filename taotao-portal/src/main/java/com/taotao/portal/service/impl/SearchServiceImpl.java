package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xianzhixianzhixian on 2018/10/17
 */
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, Integer page) {
        //查询参数
        Map<String,String> reqPara = new HashMap<>();
        reqPara.put("q",queryString);
        reqPara.put("page",page+"");
        try {
            //调用服务
            String json = HttpClientUtil.doGet(SEARCH_BASE_URL, reqPara);
            TaotaoResult result = TaotaoResult.formatToPojo(json, SearchResult.class);
            if( result.getStatus() == 200 ){
                SearchResult searchResult = (SearchResult) result.getData();
                return searchResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
