package com.elephant.elephant_bi.vo;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;

import java.io.Serializable;

public class DataOriginVO implements Serializable {
    private DataOrigin dataOrigin;
    private String tableName;
    private Integer rows;

    public Integer getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "DataOriginVO{" +
                "dataOrigin=" + dataOrigin +
                ", tableName='" + tableName + '\'' +
                ", rows=" + rows +
                '}';
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public DataOrigin getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(DataOrigin dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
