package com.elephant.elephant_bi.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBContextHolder {
    private final static Logger log = LoggerFactory.getLogger(DBContextHolder.class);
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    // 切换数据源
    public static void setDataSource(String dataSource) {
        contextHolder.set(dataSource);
        log.info("已切换到数据源:{}",dataSource);
    }

    // 获取数据源
    public static String getDataSource() {
        return contextHolder.get();
    }

    // 删除数据源
    public static void clearDataSource() {
        contextHolder.remove();
        log.info("已切换到主数据源");
    }
}
