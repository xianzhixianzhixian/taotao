package com.taotao.search.dao;

import com.taotao.common.utils.StrUtil;
import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: xianzhixianzhixian on 2018/10/16
 */
@Repository
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrClient solrClient;

    @Override
    public SearchResult search(SolrQuery query) throws Exception {

        SearchResult result = new SearchResult();

        QueryResponse response = solrClient.query(query);
        SolrDocumentList documentList = response.getResults();
        List<Item> itemList = new ArrayList<>();
        result.setItemList(itemList);
        result.setRecordCount(documentList.getNumFound());
        //取高亮显示
        Map<String,Map<String,List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument document : documentList){
            Item item = new Item();
            item.setId((String) document.get("id"));
            //取高亮显示结果
            List<String> list = highlighting.get(document.get("id")).get("item_title");
            String title = "";
            if (list!=null && list.size() != 0){
                title = list.get(0);
            } else {
                title = (String) document.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) document.get("item_image"));
            item.setPrice((Long) document.get("item_price"));
            item.setSell_point((String) document.get("item_sell_point"));
            item.setCategory_name((String) document.get("item_category_name"));
            itemList.add(item);
        }
        return result;
    }
}
