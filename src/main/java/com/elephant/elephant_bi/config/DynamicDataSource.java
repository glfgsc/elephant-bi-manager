package com.elephant.elephant_bi.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.alibaba.fastjson.JSONObject;
import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Set;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private boolean debug = true;
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicDefaultTargetDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DBContextHolder.getDataSource();
        if (!StringUtils.isEmpty(datasource)) {
            Map<Object, Object> dynamicTargetDataSources = this.dynamicTargetDataSources;
            if (dynamicTargetDataSources.containsKey(datasource)) {
                log.info("---当前数据源：" + datasource + "---");
            } else {
                log.info("不存在的数据源：");
                return null;
            }
        }else {
            log.info("---当前数据源：默认数据源---");
        }

        return datasource;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {

        super.setTargetDataSources(targetDataSources);

        this.dynamicTargetDataSources = targetDataSources;

    }

    // 创建数据源
    public boolean createDataSource(DataOrigin dataOrigin) {
        try {
            try {
                //连接数据库
                Class.forName(dataOrigin.getDriver());
                DriverManager.getConnection(dataOrigin.getUrl(), dataOrigin.getUser(), dataOrigin.getPassword());
            } catch (Exception e) {
                log.error("数据源配置有误");
                return false;
            }
            DruidDataSource druidDataSource = new DruidDataSource();
            druidDataSource.setName(dataOrigin.getName());
            druidDataSource.setDriverClassName(dataOrigin.getDriver());
            druidDataSource.setUrl(dataOrigin.getUrl());
            druidDataSource.setUsername(dataOrigin.getUser());
            druidDataSource.setPassword(dataOrigin.getPassword());
            druidDataSource.setInitialSize(dataOrigin.getConnectionPool().getInteger("initialSize"));
            druidDataSource.setMaxActive(dataOrigin.getConnectionPool().getInteger("maxActive"));
            druidDataSource.setMaxIdle(dataOrigin.getConnectionPool().getInteger("maxIdle"));
            druidDataSource.setMinIdle(dataOrigin.getConnectionPool().getInteger("minIdle"));
            druidDataSource.setMaxWait(dataOrigin.getConnectionPool().getInteger("maxWait"));
            druidDataSource.setValidationQuery(dataOrigin.getConnectionPool().getString("validationQuery"));
            druidDataSource.setTestOnBorrow(dataOrigin.getConnectionPool().getBoolean("testOnBorrow"));
            druidDataSource.setTestOnReturn(dataOrigin.getConnectionPool().getBoolean("testOnReturn"));
            druidDataSource.setTestWhileIdle(dataOrigin.getConnectionPool().getBoolean("testWhileIdle"));
            druidDataSource.setTimeBetweenEvictionRunsMillis(dataOrigin.getConnectionPool().getInteger("timeBetweenEvictionRunsMillis"));
            druidDataSource.setNumTestsPerEvictionRun(dataOrigin.getConnectionPool().getInteger("numTestsPerEvictionRun"));
            druidDataSource.setMinEvictableIdleTimeMillis(dataOrigin.getConnectionPool().getInteger("minEvictableIdleTimeMillis"));
            druidDataSource.init();
            this.dynamicTargetDataSources.put(dataOrigin.getName(), druidDataSource);
            setTargetDataSources(this.dynamicTargetDataSources);// 将map赋值给父类的TargetDataSources
            super.afterPropertiesSet();// 将TargetDataSources中的连接信息放入resolvedDataSources管理
            log.info(dataOrigin.getName()+"数据源初始化成功");
            return true;
        } catch (Exception e) {
            log.error(e + "");
            return false;
        }
    }

    // 删除数据源
    public boolean delDatasources(String name) {
        Map<Object, Object> dynamicTargetDataSources = this.dynamicTargetDataSources;
        if (dynamicTargetDataSources.containsKey(name)) {
            Set<DruidDataSource> druidDataSourceInstances = DruidDataSourceStatManager.getDruidDataSourceInstances();
            for (DruidDataSource l : druidDataSourceInstances) {
                if (name.equals(l.getName())) {
                    dynamicTargetDataSources.remove(name);
                    DruidDataSourceStatManager.removeDataSource(l);
                    setTargetDataSources(dynamicTargetDataSources);// 将map赋值给父类的TargetDataSources
                    super.afterPropertiesSet();// 将TargetDataSources中的连接信息放入resolvedDataSources管理
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    // 测试数据源连接是否有效
    public boolean testDatasource(DataOrigin dataOrigin) {
        try {
            Class.forName(dataOrigin.getDriver());
            DriverManager.getConnection(dataOrigin.getUrl(), dataOrigin.getUser(), dataOrigin.getPassword());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setDefaultTargetDataSource( Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDefaultTargetDataSource = defaultTargetDataSource;
    }

    public void createDataSourceWithCheck(DataOrigin dataOrigin) throws Exception {
        log.info("正在检查数据源："+dataOrigin.getName());
        Map<Object, Object> dynamicTargetDataSources = this.dynamicTargetDataSources;
        if (dynamicTargetDataSources.containsKey(dataOrigin.getName())) {
            log.info("数据源"+dataOrigin.getName()+"之前已经创建，准备测试数据源是否正常...");
            DruidDataSource druidDataSource = (DruidDataSource) dynamicTargetDataSources.get(dataOrigin.getName());
            boolean rightFlag = true;
            Connection connection = null;
            try {
                log.info(dataOrigin.getName()+"数据源的概况->当前闲置连接数："+druidDataSource.getPoolingCount());
                long activeCount = druidDataSource.getActiveCount();
                log.info(dataOrigin.getName()+"数据源的概况->当前活动连接数："+activeCount);
                if(activeCount > 0) {
                    log.info(dataOrigin.getName()+"数据源的概况->活跃连接堆栈信息："+druidDataSource.getActiveConnectionStackTrace());
                }
                log.info("准备获取数据库连接...");
                connection = druidDataSource.getConnection();
                log.info("数据源"+dataOrigin.getName()+"正常");
            } catch (Exception e) {
                log.error(e.getMessage(),e); //把异常信息打印到日志文件
                rightFlag = false;
                log.info("缓存数据源"+dataOrigin.getName()+"已失效，准备删除...");
                if(delDatasources(dataOrigin.getName())) {
                    log.info("缓存数据源删除成功");
                } else {
                    log.info("缓存数据源删除失败");
                }
            } finally {
                if(null != connection) {
                    connection.close();
                }
            }
            if(rightFlag) {
                log.info("不需要重新创建数据源");
                return;
            } else {
                log.info("准备重新创建数据源...");
                createDataSource(dataOrigin);
                log.info("重新创建数据源完成");
            }
        } else {
            createDataSource(dataOrigin);
        }
    }

}
