package com.taotao.search.mapper;

import com.taotao.search.pojo.Item;

import java.util.List;

/**
 * 从数据库中查询solr服务中需要用到的字段
 * @author: xianzhixianzhixian on 2018/10/15
 */
public interface ItemMapper {

    List<Item> listItemList();
}
