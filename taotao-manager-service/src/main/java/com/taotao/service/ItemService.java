package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

/**
 * 商品管理service
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

    /**
     * 通过商品id删除商品信息
     * @param ids
     * @return
     */
    TaotaoResult deleteItems(List<Long> ids) throws Exception;

    /**
     * 通过商品id下架商品
     * @param ids
     * @return
     */
    TaotaoResult updateInstockItems(List<Long> ids) throws Exception;

    /**
     * 通过商品id上架商品
     * @param ids
     * @return
     */
    TaotaoResult updateReshelfItems(List<Long> ids) throws Exception;

    /**
     * 更新商品信息
     * @param item
     * @param desc
     * @param itemParams
     * @param itemParamItemId
     * @return
     */
    TaotaoResult updateItem(TbItem item, String desc, String itemParams, Long itemParamItemId) throws Exception;
}
