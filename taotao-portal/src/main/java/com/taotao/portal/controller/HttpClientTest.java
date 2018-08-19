package com.taotao.portal.controller;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Experimental;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试Httpclient
 * @author: xianzhixianzhixian on 2018/8/19
 */
public class HttpClientTest {

    /**
     * 不带参数的get请求
     * @throws Exception
     */
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

    /**
     * 带请求参数的get
     * @throws Exception
     */
    public void doGetParam() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://www.baidu.com/s");
        uriBuilder.addParameter("wd", "微服务");
        //创建一个GET对象
        HttpGet get =  new HttpGet(uriBuilder.build());
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

    /**
     * 不带参数的post请求
     * @throws Exception
     */
    public void doPost() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpClient/post.html");
        //执行请求
        CloseableHttpResponse response = httpClient.execute(post);
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

    /**
     * 带请求参数的post
     * @throws Exception
     */
    public void doPostParam() throws Exception {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个post对象
        HttpPost post = new HttpPost("http://localhost:8082/httpClient/post.html");
        //创建一个Entity对象,模拟表单
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("name","hello"));
        kvList.add(new BasicNameValuePair("passwd","world"));
        //包装成一个Entity对象
        StringEntity entity = new UrlEncodedFormEntity(kvList, "UTF8");
        //设置entity
        post.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(post);
        //获得返回结果
        int code = response.getStatusLine().getStatusCode();
        String contentType = response.getHeaders("content-type")[0].getValue();
        HttpEntity responseEntity = response.getEntity();
        String entityString = EntityUtils.toString(responseEntity, "UTF8");
        System.out.println("code " + code + " content-type " + contentType + " entity " + entityString);
        //关闭httpclient
        response.close();
        httpClient.close();
    }

    public static void main(String[] args){
        try {
            new HttpClientTest().doPostParam();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
