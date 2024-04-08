package com.elephant.elephant_bi.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class DataOriginPageVO implements Serializable {
    private String name;
    private int pageNum;
    private int pageSize;

    @Override
    public String toString() {
        return "DataOriginPageVO{" +
                "name='" + name + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
