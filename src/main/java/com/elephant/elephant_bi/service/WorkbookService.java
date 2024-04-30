package com.elephant.elephant_bi.service;

import com.elephant.elephant_bi.domain.pojo.Workbook;

import java.util.List;

public interface WorkbookService {
    Workbook insert(Workbook workbook);
    List<Workbook> selectByParentId(int parentId,String sortColumn);
    void update(Workbook workbook);

    boolean checkName(Workbook workbook);
    void delete(Workbook workbook);
}
