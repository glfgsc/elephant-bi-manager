package com.elephant.elephant_bi.controller;


import com.elephant.elephant_bi.domain.pojo.Workbook;
import com.elephant.elephant_bi.service.WorkbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/workbook")
public class WorkbookController {
    @Autowired
    private WorkbookService workbookService;

    @GetMapping("/select")
    public List<Workbook> selectByParentId(int parentId,String sortColumn){
        return workbookService.selectByParentId(parentId,sortColumn);
    }

    @PostMapping("/insert")
    public Workbook insert(@RequestBody Workbook workbook){
        return workbookService.insert(workbook);
    }

    @PostMapping("/update")
    public int update(@RequestBody Workbook workbook){
        workbookService.update(workbook);
        return 1;
    }

    @GetMapping("/checkName")
    public boolean checkName(Workbook workbook){
        return workbookService.checkName(workbook);
    }

    @GetMapping("/delete")
    public int delete(Workbook workbook){
        workbookService.delete(workbook);
        return 1;
    }
}
