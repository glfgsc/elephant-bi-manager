package com.elephant.elephant_bi.service.impl;

import com.elephant.elephant_bi.domain.pojo.Workbook;
import com.elephant.elephant_bi.mapper.WorkbookMapper;
import com.elephant.elephant_bi.service.WorkbookService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WorkbookService")
public class WorkbookServiceImpl implements WorkbookService {
    @Autowired
    private WorkbookMapper workbookMapper;
    @Override
    public void insert(Workbook workbook) {
        workbookMapper.insert((workbook));
    }

    @Override
    public List<Workbook> selectByParentId(@Param("parentId") int parentId) {
        return workbookMapper.selectByParentId(parentId);
    }
}
