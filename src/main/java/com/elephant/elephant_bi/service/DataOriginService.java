package com.elephant.elephant_bi.service;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
import com.elephant.elephant_bi.vo.DataOriginVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DataOriginService {
    int insert(DataOrigin dataOrigin);
    int updateById(DataOrigin dataOrigin);
    Page<DataOrigin> selectByPage(DataOriginPageVO dataOriginPageVO);
    List<DataOrigin> selectAll(@Param("name")String name);
    int checkName(String name);
    List<Map<String, Object>> getSchemas(DataOrigin dataOrigin);
    List<Map<String, Object>> getTables(DataOriginVO dataOriginVO);
    List<Map<String, Object>> getFields(DataOriginVO dataOriginVO);
    List<Map<String, Object>> getSqlData(DataOriginVO dataOriginVO);
}
