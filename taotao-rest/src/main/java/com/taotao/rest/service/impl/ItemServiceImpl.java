package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xianzhixianzhixian on 2018/10/22
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TaotaoResult searchItemBaseInfo(Long itemId) {
        TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
        return TaotaoResult.ok(item);
    }
}
