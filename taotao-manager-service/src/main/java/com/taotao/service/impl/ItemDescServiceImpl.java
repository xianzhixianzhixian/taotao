package com.taotao.service.impl;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: xianzhixianzhixian on 2018/10/30
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        return itemDesc;
    }
}
