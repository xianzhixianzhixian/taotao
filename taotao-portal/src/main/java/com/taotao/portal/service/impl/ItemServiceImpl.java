package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.StrUtil;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: xianzhixianzhixian on 2018/10/24
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITME_INFO_URL}")
    private String ITME_INFO_URL;
    @Value("${ITEM_DESC_URL}")
    private String ITEM_DESC_URL;
    @Value("${ITEM_PARAM_URL}")
    private String ITEM_PARAM_URL;

    @Override
    public ItemInfo searchItemBaseInfo(Long itemId) {

        try {
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_INFO_URL + itemId);
            if(!StrUtil.testTrimEmpty(json)){
                TaotaoResult result = TaotaoResult.formatToPojo(json, ItemInfo.class);
                if(result.getStatus() == 200){
                    ItemInfo item = (ItemInfo) result.getData();
                    return item;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TbItemDesc searchItemDescInfo(Long itemId) {
        return null;
    }

    @Override
    public TbItemParamItem searchItemParamInfo(Long itemId) {
        return null;
    }
}
