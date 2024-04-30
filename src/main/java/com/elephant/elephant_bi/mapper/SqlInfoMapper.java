package com.elephant.elephant_bi.mapper;

import com.elephant.elephant_bi.domain.pojo.SqlInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SqlInfoMapper {
    void insert(SqlInfo sqlInfo);
    void delete(@Param("workbookId") int workbookId);
}
