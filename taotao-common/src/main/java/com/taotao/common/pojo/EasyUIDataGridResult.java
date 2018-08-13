package com.taotao.common.pojo;

import java.util.List;

/**
 * EasyUI支持的返回数据
 * @author 樊钰丰 2017/12/16
 */
public class EasyUIDataGridResult {
    private Long total;
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
