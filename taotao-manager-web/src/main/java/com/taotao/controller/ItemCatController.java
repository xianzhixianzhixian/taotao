package com.taotao.controller;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.service.ItemCatService;

/**
 * 商品分类管理controller
 * @author 樊钰丰
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/**
	 * parentId和id不同名，@RequestParam把parentId映射到id，id是jsp里定义的变量
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	private List<EasyUITreeNode> getCatList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		List<EasyUITreeNode> list = itemCatService.getCatList(parentId);
		return list;
	}
}
