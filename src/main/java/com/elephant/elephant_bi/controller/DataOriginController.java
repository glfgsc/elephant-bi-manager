package com.elephant.elephant_bi.controller;

import com.elephant.elephant_bi.config.DBContextHolder;
import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.service.DBChangeService;
import com.elephant.elephant_bi.service.DataOriginService;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
import com.elephant.elephant_bi.vo.DataOriginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/dataorigin")
public class DataOriginController {
    @Autowired
    private DataOriginService dataOriginService;

    @Autowired
    private DBChangeService dbChangeService;

    @Autowired
    @Qualifier("dynamicJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/selectAll")
    public List<DataOrigin> selectAll(@RequestBody DataOriginPageVO dataOriginPageVO){
        return dataOriginService.selectAll(dataOriginPageVO.getName());
    }

    @PostMapping("/select")
    public Page<DataOrigin> select(@RequestBody DataOriginPageVO dataOriginPageVO){
        return dataOriginService.selectByPage(dataOriginPageVO);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody DataOrigin dataOrigin) throws Exception {
        boolean flag = dbChangeService.testDb(dataOrigin);

        if(flag && dataOrigin.getId() == null){
            dataOriginService.insert(dataOrigin);
            return 1;
        }else if(flag && dataOrigin.getId() != null){
            dataOriginService.updateById(dataOrigin);
            return 1;
        }
        return 0;
    }

    @PostMapping("/test")
    public boolean test(@RequestBody DataOrigin dataOrigin) throws Exception {
        return dbChangeService.testDb(dataOrigin);
    }

    @GetMapping("/checkName")
    public boolean checkName(@RequestParam String name){
        int i = dataOriginService.checkName(name);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @PostMapping("/schema")
    public List<Map<String, Object>> getSchemas(@RequestBody DataOrigin dataOrigin){
        return dataOriginService.getSchemas(dataOrigin);
    }

    @PostMapping("/table")
    public List<Map<String, Object>> getTables(@RequestBody DataOriginVO dataOriginVO){
        return dataOriginService.getTables(dataOriginVO);
    }

    @PostMapping("/field")
    public List<Map<String, Object>> getFields(@RequestBody DataOriginVO dataOriginVO){
        return dataOriginService.getFields(dataOriginVO);
    }

    @PostMapping("/data")
    public List<Map<String, Object>> getSqlData(@RequestBody DataOriginVO dataOriginVO){
        return dataOriginService.getSqlData(dataOriginVO);
    }
}
