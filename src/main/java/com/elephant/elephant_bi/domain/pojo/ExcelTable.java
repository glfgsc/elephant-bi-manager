package com.elephant.elephant_bi.domain.pojo;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;

public class ExcelTable implements Serializable {
    private int id;
    private String name;
    private String creator;
    private String createTime;
    private String updateTime;
    private String comment;
    private int workbookId;
    private JSONArray data;
    private JSONArray fields;
    private int sheetIndex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getWorkbookId() {
        return workbookId;
    }

    public void setWorkbookId(int workbookId) {
        this.workbookId = workbookId;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public JSONArray getFields() {
        return fields;
    }

    public void setFields(JSONArray fields) {
        this.fields = fields;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    @Override
    public String toString() {
        return "ExcelTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", comment='" + comment + '\'' +
                ", workbookId=" + workbookId +
                ", data=" + data +
                ", fields=" + fields +
                ", sheetIndex=" + sheetIndex +
                '}';
    }
}
