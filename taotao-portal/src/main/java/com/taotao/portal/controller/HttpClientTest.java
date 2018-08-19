package com.taotao.portal.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 测试Httpclient
 * @author: xianzhixianzhixian on 2018/8/19
 */
public class HttpClientTest {

    public void doGet() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个GET对象
        HttpGet get =  new HttpGet("http://www.baidu.com");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //获得返回结果
        int code = response.getStatusLine().getStatusCode();
        String contentType = response.getHeaders("content-type")[0].getValue();
        HttpEntity entity = response.getEntity();
        String entityString = EntityUtils.toString(entity, "UTF8");
        System.out.println("code " + code + " content-type " + contentType + " entity " + entityString);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    public static void main(String[] args){
        try {
            new HttpClientTest().doGet();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
