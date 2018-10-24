package com.taotao.portal.service.impl;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.StrUtil;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public String searchItemDescInfo(Long itemId) {
        //延迟一秒加载
        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_DESC_URL + itemId);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemDesc.class);
            if (result.getStatus() == 200){
                TbItemDesc itemDesc = (TbItemDesc) result.getData();
                if (itemDesc!=null && !StrUtil.testTrimEmpty(itemDesc.getItemDesc())){
                    return itemDesc.getItemDesc();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String searchItemParamInfo(Long itemId) {
        try{
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITEM_PARAM_URL + itemId);
            TaotaoResult result = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (result.getStatus() == 200){
                TbItemParamItem itemParamItem = (TbItemParamItem) result.getData();
                if (itemParamItem!=null && !StrUtil.testTrimEmpty(itemParamItem.getParamData())){
                    String paramData = itemParamItem.getParamData();
                    //生成html
                    // 把规格参数json数据转换成java对象
                    List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
                    StringBuffer sb = new StringBuffer();
                    sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
                    sb.append("    <tbody>\n");
                    for(Map m1:jsonList) {
                        sb.append("        <tr>\n");
                        sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                        sb.append("        </tr>\n");
                        List<Map> list2 = (List<Map>) m1.get("params");
                        for(Map m2:list2) {
                            sb.append("        <tr>\n");
                            sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                            sb.append("            <td>"+m2.get("v")+"</td>\n");
                            sb.append("        </tr>\n");
                        }
                    }
                    sb.append("    </tbody>\n");
                    sb.append("</table>");
                    return sb.toString();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
