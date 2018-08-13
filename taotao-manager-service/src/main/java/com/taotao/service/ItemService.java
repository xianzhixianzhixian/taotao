package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 测试SSM整合结果
 * @author 樊钰丰 2017/12/16
 */
public interface ItemService {

    /**
     * 获取商品实体
     * @param id
     * @return
     */
    TbItem getItemById(Long id);

    /**
     * 获取商品列表
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getItemList(Integer page,Integer rows);

    /**
     * 向数据库中存入商品
     * @param item
     * @param description
     * @return
     */
    TaotaoResult createItem(TbItem item,String description,String itemParam) throws Exception;
}
