package com.taotao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 测试分页插件
 * @author 樊钰丰 2017/12/16
 */
public class TestPageHelper {

    public static void main(String[] args){
        //创建一个spring容器
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从spring容器中获得Mapper代理对象
        TbItemMapper tbItemMapper=applicationContext.getBean(TbItemMapper.class);
        //执行查询，并分页
        TbItemExample example=new TbItemExample();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> list=tbItemMapper.selectByExample(example);
        //取商品列表
        for (TbItem tbItem : list){
            System.out.println(tbItem.getTitle());
        }
        //取分页信息
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        long total=pageInfo.getTotal();
        System.out.println("共有商品："+total);
    }
}
