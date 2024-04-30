package com.elephant.elephant_bi.service.impl;

import com.elephant.elephant_bi.config.DBContextHolder;
import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.mapper.DataOriginMapper;
import com.elephant.elephant_bi.service.DBChangeService;
import com.elephant.elephant_bi.service.DataOriginService;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
import com.elephant.elephant_bi.vo.DataOriginVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("DataOriginService")
public class DataOriginServiceImpl implements DataOriginService {
    @Autowired
    private DataOriginMapper dataOriginMapper;

    @Autowired
    private DBChangeService dbChangeService;

    @Autowired
    @Qualifier("dynamicJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(DataOrigin dataOrigin) {
        dataOriginMapper.insert(dataOrigin);
        return 0;
    }

    @Override
    public int updateById(DataOrigin dataOrigin) {
        dataOriginMapper.updateById(dataOrigin);
        return 0;
    }

    @Override
    public Page<DataOrigin> selectByPage(DataOriginPageVO dataOriginPageVO) {
        PageHelper.startPage(dataOriginPageVO.getPageNum(),dataOriginPageVO.getPageSize());
        List<DataOrigin> dataOrigins = dataOriginMapper.selectByPage(dataOriginPageVO);
        PageInfo<DataOrigin> dataOriginPageInfo = new PageInfo<>(dataOrigins);
        return new Page<DataOrigin>(dataOrigins,dataOriginPageInfo.getTotal(),dataOriginPageInfo.getNextPage());
    }



    @Override
    public List<DataOrigin> selectAll(String name) {
        return dataOriginMapper.selectAll(name);
    }

    @Override
    public int checkName(String name) {
        return dataOriginMapper.checkName(name);
    }

    @Override
    public List<Map<String, Object>> getSchemas(DataOrigin dataOrigin) {
        try{
            if(dbChangeService.testDb(dataOrigin)){
                dbChangeService.changeDb(dataOrigin);
                DBContextHolder.setDataSource(dataOrigin.getName());
                List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT schema_name as schema_name FROM information_schema.schemata");
                DBContextHolder.clearDataSource();
                return maps;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        DBContextHolder.clearDataSource();
        return null;
    }

    @Override
    public List<Map<String, Object>> getTables(DataOriginVO dataOriginVO) {
        try{
            if(dbChangeService.testDb(dataOriginVO.getDataOrigin())){
                dbChangeService.changeDb(dataOriginVO.getDataOrigin());
                DBContextHolder.setDataSource(dataOriginVO.getDataOrigin().getName());
                List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT table_name as name,'false' as isLeaf \n" +
                        "FROM information_schema.tables\n" +
                        "WHERE table_name like '%"+dataOriginVO.getTableName()+"%' and table_schema = '"+(dataOriginVO.getDataOrigin().getDatabaseSchema() == null || dataOriginVO.getDataOrigin().getDatabaseSchema().equals("") ? dataOriginVO.getDataOrigin().getDatabaseName() : dataOriginVO.getDataOrigin().getDatabaseSchema())+"'");
                DBContextHolder.clearDataSource();
                return maps;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        DBContextHolder.clearDataSource();
        return null;
    }

    @Override
    public List<Map<String, Object>> getFields(DataOriginVO dataOriginVO) {
        try{
            if(dbChangeService.testDb(dataOriginVO.getDataOrigin())){
                dbChangeService.changeDb(dataOriginVO.getDataOrigin());
                DBContextHolder.setDataSource(dataOriginVO.getDataOrigin().getName());
                String sql = "";
                if("MySQL".equals(dataOriginVO.getDataOrigin().getDatabaseType())){
                    sql = "select concat(column_name,'(',column_type,')') as name,'true' as isLeaf from information_schema.columns \n" +
                            "where TABLE_SCHEMA = '"+dataOriginVO.getDataOrigin().getDatabaseName()+"' and "+"TABLE_NAME = '" + dataOriginVO.getTableName()+"'";
                }else if("Postgresql".equals(dataOriginVO.getDataOrigin().getDatabaseType())){
                    sql = "select concat(column_name,'(',data_type,')') as name,'true' as isLeaf from information_schema.columns \n" +
                            "where table_schema = '"+dataOriginVO.getDataOrigin().getDatabaseSchema()+"' and "+"table_name = '" + dataOriginVO.getTableName()+"'";
                }
                List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
                DBContextHolder.clearDataSource();
                return maps;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        DBContextHolder.clearDataSource();
        return null;
    }

    @Override
    public List<Map<String, Object>> getSqlData(DataOriginVO dataOriginVO) {
        try{
            if(dbChangeService.testDb(dataOriginVO.getDataOrigin())){
                dbChangeService.changeDb(dataOriginVO.getDataOrigin());
                DBContextHolder.setDataSource(dataOriginVO.getDataOrigin().getName());
                String sql = "select * from "
                        + (dataOriginVO.getDataOrigin().getDatabaseSchema() == null || dataOriginVO.getDataOrigin().getDatabaseSchema().equals("") ? dataOriginVO.getDataOrigin().getDatabaseName() : dataOriginVO.getDataOrigin().getDatabaseSchema())
                        + "."
                        + dataOriginVO.getTableName()
                        + " limit "
                        + dataOriginVO.getRows();
                List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
                DBContextHolder.clearDataSource();
                return maps;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
