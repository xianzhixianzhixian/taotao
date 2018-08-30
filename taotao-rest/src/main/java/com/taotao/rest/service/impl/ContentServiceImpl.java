package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClientSingle;
import com.taotao.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClientSingle jedisClientSingles;
    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(Long contentCid) {
        //从缓冲中读取内容
        try{
            String result = jedisClientSingles.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");
            if(!StrUtil.testTrimEmpty(result)){
                //把字符串转换成list
                List<TbContent> list = JsonUtils.jsonToList(result,TbContent.class);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据内容分类id查询内容分类列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = contentMapper.selectByExample(example);
        //向缓存中添加内容
        try{
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClientSingles.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"",cacheString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
