package com.elephant.elephant_bi.service.impl;

import com.elephant.elephant_bi.config.DBContextHolder;
import com.elephant.elephant_bi.config.DynamicDataSource;
import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.mapper.DataOriginMapper;
import com.elephant.elephant_bi.service.DBChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DBChangeService")
public class DBChangeServiceImpl implements DBChangeService {
    @Autowired
    private DataOriginMapper dataOriginMapper;

    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Override
    public boolean changeDb(DataOrigin dataOrigin) throws Exception {
        if(testDb(dataOrigin)){
            return dynamicDataSource.createDataSource(dataOrigin);
        }else{
            return false;
        }
    }

    @Override
    public boolean testDb(DataOrigin dataOrigin) throws Exception {
        return dynamicDataSource.testDatasource(dataOrigin);
    }
}
