package com.elephant.elephant_bi.mapper;

import com.elephant.elephant_bi.domain.pojo.Workbook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkbookMapper {
    void insert(Workbook workbook);

    List<Workbook> selectByParentId(int parentId);
}
