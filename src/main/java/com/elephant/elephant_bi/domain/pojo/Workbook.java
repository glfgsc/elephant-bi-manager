package com.elephant.elephant_bi.domain.pojo;

import java.io.Serializable;

public class Workbook implements Serializable {
    private int id;
    private String name;
    private String creator;
    private String createTime;
    private String updateTime;
    private int parentId;
    private int shareAnalysis;
    private String position;
    private String size;
    private int type;
    private int fileType;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getShareAnalysis() {
        return shareAnalysis;
    }

    public void setShareAnalysis(int shareAnalysis) {
        this.shareAnalysis = shareAnalysis;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String path;

    @Override
    public String toString() {
        return "Workbook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", parentId=" + parentId +
                ", shareAnalysis=" + shareAnalysis +
                ", position='" + position + '\'' +
                ", size='" + size + '\'' +
                ", type=" + type +
                ", fileType=" + fileType +
                ", path='" + path + '\'' +
                '}';
    }
}
