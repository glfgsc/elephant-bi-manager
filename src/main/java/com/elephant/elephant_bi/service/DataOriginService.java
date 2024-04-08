package com.elephant.elephant_bi.service;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.domain.pojo.Page;
import com.elephant.elephant_bi.vo.DataOriginPageVO;

import java.util.List;

public interface DataOriginService {
    int insert(DataOrigin dataOrigin);
    int updateById(DataOrigin dataOrigin);
    Page<DataOrigin> selectByPage(DataOriginPageVO dataOriginPageVO);
    List<DataOrigin> selectAll();
    int checkName(String name);
}
