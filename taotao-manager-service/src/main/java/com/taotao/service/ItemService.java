package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * 测试SSM整合结果
 * @author 樊钰丰 2017/12/16
 */
public interface ItemService {

    TbItem getItemById(Long id);
    EasyUIDataGridResult getItemList(Integer page,Integer rows);
    TaotaoResult createItem(TbItem item);
}
