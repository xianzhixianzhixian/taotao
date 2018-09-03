package com.taotao.rest.service.impl;

import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.dao.JedisClientSingle;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类服务
 * 钰丰 2018/2/2
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private JedisClientSingle jedisClientSingles;
    @Value("${ITEM_CAT_KEY}")
    private String ITEM_CAT_KEY;

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Override
    public CatResult getCatItemList() {

        try {
            String str = jedisClientSingles.get(ITEM_CAT_KEY);
            if (!StrUtil.testTrimEmpty(str)) {
                CatResult catResult = new CatResult();
                List cartList = JsonUtils.jsonToList(str, Object.class);
                catResult.setData(cartList);
                return catResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        CatResult catResult=new CatResult();
        List cartList = getCatList(0L);
        catResult.setData(cartList);

        try{
            jedisClientSingles.set(ITEM_CAT_KEY, JsonUtils.objectToJson(cartList));
        }catch (Exception e){
            e.printStackTrace();
        }
        return catResult;
    }

    /**
     * 查询分类列表
     * @param parentId
     * @return
     */
    private List getCatList(Long parentId){
        //创建查询条件
        TbItemCatExample example=new TbItemCatExample();
        Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //返回值list
        List<TbItemCat> list=itemCatMapper.selectByExample(example);
        //执行查询
        List resultList=new ArrayList<>();
        int count=0;
        //向list中添加节点
        for(TbItemCat tbItemCat : list){
            //判断是否为父节点
            if(tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/'" + tbItemCat.getId() + ".html>" + tbItemCat.getName() + "</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/" + tbItemCat.getId() + ".html>");
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count++;
                //第一层只取14条记录
                if(parentId == 0 && count>=14){
                    break;
                }
            }else{
                //叶子节点
                resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }
        return resultList;
    }
}
