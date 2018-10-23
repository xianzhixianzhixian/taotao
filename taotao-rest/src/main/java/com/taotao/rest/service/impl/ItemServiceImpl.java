package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.rest.dao.JedisClientSingle;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: xianzhixianzhixian on 2018/10/22
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private JedisClientSingle jedisClientSingle;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private String REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult searchItemBaseInfo(Long itemId) {
        try {
            //从缓存中取商品id对应的信息
            String json = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + ":base");
            if(!StrUtil.testTrimEmpty(json)){
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return TaotaoResult.ok(item);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
        try {
            //把商品信息写入缓存
            //设置key的有效期,hash不支持设置过期时间
            //使用key的命名方式
            //基本信息： redis_item_key:商品id:base json
            //描述： redis_item_key:商品id:desc json
            //规格参数：redis_item_key:商品id:param json
            jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":base", JsonUtils.objectToJson(item));
            jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":base", Integer.valueOf(REDIS_ITEM_EXPIRE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(item);
    }
}
