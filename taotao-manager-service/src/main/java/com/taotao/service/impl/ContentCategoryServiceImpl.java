package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EasyUITreeNode> getCategoryList(Long parentId) {
        //根据parentId查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        criteria.andStatusEqualTo(1);
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
    public TaotaoResult insertContentCategory(Long parentId, String name) throws Exception {
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

    @Override
    public TaotaoResult deleteContentCategory(Long parentId, Long id) throws Exception {
        //获取所有子节点id
        int count = 0;
        List<Long> idList = listAllChildrenId(id); //获取所有子节点id列表
        //idList.add(id); //加入该节点本身，此处有一个逻辑错误，如果删除了该节点，那么再去查该节点时，其返回的对象里数据权威null，导致错误
        TbContentCategory category = new TbContentCategory();
        for(Long childId : idList){
            category.setId(childId);
            category.setStatus(2);
            count += contentCategoryMapper.updateByPrimaryKeySelective(category);
            //count += contentCategoryMapper.deleteByPrimaryKey(childId);
        }

        //获取该节点的parentId,若其parent没有子节点则该parent为子节点
        category = contentCategoryMapper.selectByPrimaryKey(id);
        int childrenCount = contentCategoryMapper.selectChildrenCount(category.getParentId());
        if (childrenCount == 0){
            category.setId(parentId);
            category.setIsParent(Boolean.FALSE);
            contentCategoryMapper.updateByPrimaryKeySelective(category);
        }

        //最后删除该节点
        category.setId(id);
        category.setStatus(2);
        count += contentCategoryMapper.updateByPrimaryKeySelective(category);
        //count += contentCategoryMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok(count);
    }

    @Override
    public TaotaoResult updateContentCategory(Long id, String name) throws Exception {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        int count = contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        if(count > 0){
            return TaotaoResult.ok(count);
        }else{
            return new TaotaoResult();
        }
    }

    /**
     * 根据parentId获取所有的子孙节点
     * @param parentId
     * @return
     */
    private List<Long> listAllChildrenId(Long parentId){

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria= example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        List<TbContentCategory> contentCategoryList = contentCategoryMapper.selectByExample(example);
        List<Long> idList = new ArrayList<>();
        for(TbContentCategory tb : contentCategoryList){
            idList.add(tb.getId());
            if(tb.getIsParent()){
                listAllChildrenId(tb.getId());
            }
        }
        return idList;
    }
}
