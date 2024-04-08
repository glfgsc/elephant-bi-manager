package com.elephant.elephant_bi.mapper;

import com.elephant.elephant_bi.domain.pojo.DataOrigin;
import com.elephant.elephant_bi.vo.DataOriginPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataOriginMapper {
    int insert(DataOrigin dataOrigin);
    int updateById(DataOrigin dataOrigin);
    List<DataOrigin> selectByPage(DataOriginPageVO dataOriginPageVO);

    List<DataOrigin> selectAll();
    int checkName(String name);
}
