package com.taotao.service.impl;

import com.taotao.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试查询某个商品
 * @author 樊钰丰 2017/12/16
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(Long id) {
        //TbItem item=tbItemMapper.selectByPrimaryKey(id);

        // 添加查询条件，用查询条件进行查询
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> list=tbItemMapper.selectByExample(example);
        if (list!=null && list.size()>0){
            TbItem item=list.get(0);
            return item;
        }
        return null;
    }
}
