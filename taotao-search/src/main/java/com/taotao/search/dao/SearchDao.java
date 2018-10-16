package com.taotao.search.dao;

import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * solr查询接口
 * @author: xianzhixianzhixian on 2018/10/16
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
