package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

import java.util.List;

/**
 * 规格参数模板Service
 * @author yufeng 2018/1/3
 */
public interface ItemParamService {

	/**
	 * 根据商品的规则参数id获取具体规则参数信息
	 * @param cid
	 * @return
	 */
	TaotaoResult getItemParamByCid(Long cid);

    /**
     * 新增商品规则参数
     * @param itemParam
     * @return
     */
	TaotaoResult insertItemParam(TbItemParam itemParam) throws Exception;

    /**
     * 获取所有的规则参数信息
     * @return
     */
	EasyUIDataGridResult selectItemParamList(Integer page, Integer rows);

    /**
     * 删除对应id的记录
     * @param ids
     * @return
     */
	TaotaoResult deleteItemParamsByIds(List<Long> ids) throws Exception;
}
