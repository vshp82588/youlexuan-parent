package com.offcn.entity;

import java.io.Serializable;
import java.util.List;

/*
* 远程服务调用，进行数据传输，需要将pojo实现序列化接口
* */
public class PageResult implements Serializable {

    private Long total ;//总记录数

    private List rows;//当前页显示的数据

    public PageResult() {
    }

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
