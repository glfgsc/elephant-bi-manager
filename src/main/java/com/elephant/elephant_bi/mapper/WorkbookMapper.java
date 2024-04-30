package com.elephant.elephant_bi.mapper;

import com.elephant.elephant_bi.domain.pojo.Workbook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkbookMapper {
    void insert(Workbook workbook);

    List<Workbook> selectByParentId(@Param("parentId") int parentId, @Param("sortColumn") String sortColumn);

    Workbook selectByParentIdByName(Workbook workbook);
    void update(Workbook workbook);

    void delete(Workbook workbook);
}
