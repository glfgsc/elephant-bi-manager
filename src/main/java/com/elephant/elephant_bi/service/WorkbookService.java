package com.elephant.elephant_bi.service;

import com.elephant.elephant_bi.domain.pojo.Workbook;

import java.util.List;

public interface WorkbookService {
    void insert(Workbook workbook);
    List<Workbook> selectByParentId(int parentId);
    void update(Workbook workbook);
}
