package com.taotao.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 内容管理service
 * @author xianzhixianzhixian on 20180816
 */
@Service
public class ContenServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_SYNC_URL}")
    private String REST_CONTENT_SYNC_URL;

    @Override
    public List<TbContent> listContent(Long categoryId) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contentList = contentMapper.selectByExample(example);
        if(contentList==null || contentList.size() == 0){
            contentList = new ArrayList<>();
        }
        return contentList;
    }

    @Override
    public TaotaoResult insertContent(TbContent content){
        //补全pojo内容
        Date date = new Date();
        content.setCreated(date);
        content.setUpdated(date);
        contentMapper.insertSelective(content);

        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        }catch (Exception e){
            e.printStackTrace();
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteContent(String ids) {

        int count = 0;
        List<Long> idList = StrUtil.StringToLongArray(ids,",");
        for(Long id : idList){
            contentMapper.deleteByPrimaryKey(id);
            //添加缓存同步逻辑
            try {
                TbContent content = contentMapper.selectByPrimaryKey(id);
                HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(count == 0){
            return TaotaoResult.build(500,"删除错误");
        }
        return TaotaoResult.ok(count);
    }

    @Override
    public TaotaoResult editContent(TbContent content) {
        int count = contentMapper.updateByPrimaryKeySelective(content);
        //添加缓存同步逻辑
        try {
            HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
        }catch (Exception e){
            e.printStackTrace();
        }
        if(count == 0){
            return TaotaoResult.build(500,"修改错误");
        }
        return TaotaoResult.ok(count);
    }
}
