package com.taotao.search.pojo;

import java.util.List;

/**
 * 查询结果实体类
 * @author: xianzhixianzhixian on 2018/10/16
 */
public class SearchResult {

    private List<Item> itemList; //商品列表
    private Long recordCount; //总计路数
    private Long pageCount; //总页数
    private Long curPage; //当前页

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

}
