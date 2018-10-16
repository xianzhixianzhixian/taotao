package com.taotao.search.service.impl;

import com.taotao.search.dao.SearchDao;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xianzhixianzhixian on 2018/10/16
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        //创建查询对象
        SolrQuery solrQuery = new SolrQuery();
        //设置查询条件
        solrQuery.setQuery(queryString);
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //设置默认搜索域
        solrQuery.set("df","item_keywords");
        //设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style='color:red'>");
        solrQuery.setHighlightSimplePost("</em>");
        //执行查询
        SearchResult searchResult = searchDao.search(solrQuery);
        Long resultCount = searchResult.getRecordCount();
        Long pageCount = resultCount/rows;
        if(resultCount%rows > 0){
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(Long.valueOf(page));
        return searchResult;
    }
}