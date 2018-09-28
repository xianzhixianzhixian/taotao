package com.taotao.rest.solr;

import org.apache.solr.client.solrj.*;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

/**
 * 测试solrj客户端
 * @author: xianzhixianzhixian on 2018/9/27
 */
public class SolrTest {

    /**
     * 这里要注意一个点就是baseSolrUrl
     * 请求路径是solr/taotao的原因是，solr里创建了一个名为taotao的core，正确的请求链接应为slor/{corename}
     */
    public static void addDocument() throws Exception {
        //创建链接
        SolrClient solrClient = new HttpSolrClient.Builder("http://192.168.56.107:8983/solr/taotao").withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //把文档对象写入索引库
        document.addField("id","test001");
        document.addField("item_title","测试商品");
        document.addField("item_price",1234545);
        UpdateResponse updateResponse = solrClient.add(document);
        //提交
        solrClient.commit();
    }

    public static void deleteDocunment() throws Exception {
        //创建链接
        SolrClient solrClient = new HttpSolrClient.Builder("http://192.168.56.107:8983/solr/taotao").withConnectionTimeout(10000).withSocketTimeout(60000).build();

        //solrClient.deleteById("test001");
        solrClient.deleteByQuery("*:*");
        //提交
        solrClient.commit();
    }

    public static void main(String[] args){
        try {
            //addDocument();
            deleteDocunment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
