package com.elephant.elephant_bi.domain.pojo;

import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;

public class SqlTable implements Serializable {
    private int id;
    private String name;
    private String creator;
    private String createTime;
    private String updateTime;
    private String comment;
    private int shareAnalysis;
    private int dataOriginId;
    private int workbookId;
    private String sqlText;
    private JSONArray params;

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

    public int getShareAnalysis() {
        return shareAnalysis;
    }

    public void setShareAnalysis(int shareAnalysis) {
        this.shareAnalysis = shareAnalysis;
    }

    public int getDataOriginId() {
        return dataOriginId;
    }

    public void setDataOriginId(int dataOriginId) {
        this.dataOriginId = dataOriginId;
    }

    public int getWorkbookId() {
        return workbookId;
    }

    public void setWorkbookId(int workbookId) {
        this.workbookId = workbookId;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public JSONArray getParams() {
        return params;
    }

    public void setParams(JSONArray params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "SqlTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", comment='" + comment + '\'' +
                ", shareAnalysis=" + shareAnalysis +
                ", dataOriginId=" + dataOriginId +
                ", workbookId=" + workbookId +
                ", sqlText='" + sqlText + '\'' +
                ", params=" + params +
                '}';
    }
}
