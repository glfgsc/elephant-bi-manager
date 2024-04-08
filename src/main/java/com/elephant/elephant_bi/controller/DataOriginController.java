package com.elephant.elephant_bi.controller;

import com.elephant.elephant_bi.config.DBContextHolder;
import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.service.DBChangeService;
import com.elephant.elephant_bi.service.DataOriginService;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
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

    @GetMapping("/selectAll")
    public List<DataOrigin> selectAll(){
        return dataOriginService.selectAll();
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
        System.out.println(name);
        int i = dataOriginService.checkName(name);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }

    @PostMapping("/schema")
    public List<Map<String, Object>> getSchemas(@RequestBody DataOrigin dataOrigin) throws Exception {
        boolean flag = dbChangeService.testDb(dataOrigin);
        if(flag){
            dbChangeService.changeDb(dataOrigin);
            DBContextHolder.setDataSource(dataOrigin.getName());
            List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT schema_name FROM information_schema.schemata");
            DBContextHolder.clearDataSource();
            return maps;
        }
        DBContextHolder.clearDataSource();
        return null;
    }
}
