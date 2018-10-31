package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 测试查询某个商品
 * @author 樊钰丰 2017/12/16
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_SYNC_URL}")
    private String REST_ITEM_SYNC_URL;

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

    /**
     * 商品列表分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        //查询商品列表
        TbItemExample tbItemExample=new TbItemExample();
        //分页处理
        PageHelper.offsetPage(page,rows);
        List<TbItem> list=tbItemMapper.selectByExample(tbItemExample);
        //创建一个返回值对象
        EasyUIDataGridResult easyUIDataGridResult=new EasyUIDataGridResult();
        //取记录总条数，这里要把list作为参数，否则拿不到总条数
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        easyUIDataGridResult.setTotal(pageInfo.getTotal());
        System.out.println(pageInfo.getTotal());
        //设置List
        easyUIDataGridResult.setRows(list);
        return easyUIDataGridResult;
    }

    /**
     * 向数据库中插入商品信息
     * @param item
     * @param description
     * @return
     */
    @Override
    public TaotaoResult createItem(TbItem item,String description,String itemParam) throws Exception{
        //item补全
        //生成商品ID
        Long itemId= IDUtils.genItemId();
        item.setId(itemId);
        //商品状态，1正常，2下架，3删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        //插入到数据库
        tbItemMapper.insert(item);
        //添加商品描述信息
        TaotaoResult result=insertItemDescription(itemId,description);
        if(result.getStatus()!=200){
            //抛异常时，spring会自动回滚数据库事务
            throw new Exception();
        }
        //添加规格参数
        result=insertItemParam(itemId,itemParam);
        if(result.getStatus()!=200){
            //抛异常时，spring会自动回滚数据库事务
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult selectItemDesc(Long itemId) {
        return null;
    }

    @Override
    public TaotaoResult selectItemParam(Long itemId) {
        return null;
    }

    @Override
    public TaotaoResult deleteItems(List<Long> ids) throws Exception {
        int count = 0;
        TbItem item = new TbItem();
        for (Long id : ids) {
            item.setId(id);
            item.setStatus(new Byte("3"));
            count += tbItemMapper.updateByPrimaryKeySelective(item);
            //缓存同步逻辑
            try {
                HttpClientUtil.doPost(REST_BASE_URL+REST_ITEM_SYNC_URL+id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (count == 0){
            return TaotaoResult.build(500, "删除商品错误");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateInstockItems(List<Long> ids) throws Exception {
        int count = 0;
        TbItem item = new TbItem();
        for (Long id : ids) {
            item.setId(id);
            item.setStatus(new Byte("2"));
            count += tbItemMapper.updateByPrimaryKeySelective(item);
            //缓存同步逻辑
            try {
                HttpClientUtil.doPost(REST_BASE_URL+REST_ITEM_SYNC_URL+id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (count == 0){
            return TaotaoResult.build(500, "下架商品错误");
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult updateReshelfItems(List<Long> ids) throws Exception {
        int count = 0;
        TbItem item = new TbItem();
        for (Long id : ids) {
            item.setId(id);
            item.setStatus(new Byte("1"));
            count += tbItemMapper.updateByPrimaryKeySelective(item);
            //缓存同步逻辑
            try {
                HttpClientUtil.doPost(REST_BASE_URL+REST_ITEM_SYNC_URL+id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (count == 0){
            return TaotaoResult.build(500, "上架商品错误");
        }
        return TaotaoResult.ok();
    }

    /**
     * 插入商品描述
     * @param description
     */
    private TaotaoResult insertItemDescription(Long itemId,String description) throws Exception {
        TbItemDesc itemDesc=new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(description);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        try{
            tbItemDescMapper.insert(itemDesc);
        }catch (Exception e){
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }

    /**
     * 添加商品详细规格参数
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaotaoResult insertItemParam(Long itemId,String itemParam) throws Exception {
        //创建一个pojo
        TbItemParamItem tbItemParamItem=new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParam);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        //向表中插入数据
        try {
            itemParamItemMapper.insert(tbItemParamItem);
        }catch (Exception e){
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
