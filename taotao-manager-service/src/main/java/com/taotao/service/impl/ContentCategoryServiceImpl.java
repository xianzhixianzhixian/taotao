package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理service
 * @author xianzhixianzhixian on 20180813
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EasyUITreeNode> getCategoryList(long parentId) {
        //根据parentId查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for(TbContentCategory category : list){
            //创建一个节点
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setState(category.getIsParent() ? "closed" : "open");
            node.setText(category.getName());
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        //需要用到mybatis主键返回
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1); //1正常,2删除
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insertSelective(contentCategory);
        //查询父节点记录
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()){
            parent.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        return TaotaoResult.ok(contentCategory);
    }
}
