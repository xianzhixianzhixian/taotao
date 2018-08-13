package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;

public class FTPTest {

    public static void main(String[] args){
        try {
            //创建一个FTPClient
            FTPClient ftpClient=new FTPClient();
            //创建FTP链接
            ftpClient.connect("192.168.56.101",21);
            //登录FTP服务器，使用用户名和密码
            ftpClient.login("ftpuser","ftp123456");
//            ftpClient.set
            //读取本地文件
            FileInputStream fileInputStream=new FileInputStream(new File("E:\\Images\\日出.jpg"));
           //设置上传的路径
            ftpClient.changeWorkingDirectory("/home/ftpuser/6.jpg");
            //修改上传文件的格式
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件，第一个参数：服务器端文档名，第二个参数：要上传的文件的inputStream
            ftpClient.storeFile("hello.jpg",fileInputStream);
            //关闭连接
            ftpClient.logout();
            ftpClient.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
