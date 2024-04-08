package com.elephant.elephant_bi.service.impl;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.mapper.DataOriginMapper;
import com.elephant.elephant_bi.service.DataOriginService;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DataOriginService")
public class DataOriginServiceImpl implements DataOriginService {
    @Autowired
    private DataOriginMapper dataOriginMapper;
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
    public List<DataOrigin> selectAll() {
        return dataOriginMapper.selectAll();
    }

    @Override
    public int checkName(String name) {
        return dataOriginMapper.checkName(name);
    }
}
