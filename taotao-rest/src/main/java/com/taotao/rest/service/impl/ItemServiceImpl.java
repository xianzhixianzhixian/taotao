package com.taotao.rest.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.dao.impl.JedisClientSingle;
import com.taotao.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xianzhixianzhixian on 2018/10/22
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
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

    @Override
    public TaotaoResult searchItemDescInfo(Long itemId) {
        try {
            //从缓存中取商品id对应的信息
            String json = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + ":desc");
            if(!StrUtil.testTrimEmpty(json)){
                TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
                return TaotaoResult.ok(itemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
        try {
            //把商品信息写入缓存
            //设置key的有效期,hash不支持设置过期时间
            //使用key的命名方式
            //基本信息： redis_item_key:商品id:base json
            //描述： redis_item_key:商品id:desc json
            //规格参数：redis_item_key:商品id:param json
            jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":desc", JsonUtils.objectToJson(itemDesc));
            jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc", Integer.valueOf(REDIS_ITEM_EXPIRE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult searchItemParamInfo(Long itemId) {
        try {
            //从缓存中取商品id对应的信息

            String json = jedisClientSingle.get(REDIS_ITEM_KEY + ":" + itemId + ":param");
            if(!StrUtil.testTrimEmpty(json)){
                TbItemParamItem itemParamItem = JsonUtils.jsonToPojo(json, TbItemParamItem.class);
                return TaotaoResult.ok(itemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> itemParamItemList = tbItemParamItemMapper.selectByExample(example);
        if(!StrUtil.listNull(itemParamItemList)) {
            try {
                //把商品信息写入缓存
                //设置key的有效期,hash不支持设置过期时间
                //使用key的命名方式
                //基本信息： redis_item_key:商品id:base json
                //描述： redis_item_key:商品id:desc json
                //规格参数：redis_item_key:商品id:param json
                jedisClientSingle.set(REDIS_ITEM_KEY + ":" + itemId + ":param", JsonUtils.objectToJson(itemParamItemList.get(0)));
                jedisClientSingle.expire(REDIS_ITEM_KEY + ":" + itemId + ":param", Integer.valueOf(REDIS_ITEM_EXPIRE));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return TaotaoResult.ok(itemParamItemList.get(0));
        }
        //自己定义的错误码400，和http错误码区分开，两者是不一样的东西
        return TaotaoResult.build(400, "无此商品信息");
    }
}
