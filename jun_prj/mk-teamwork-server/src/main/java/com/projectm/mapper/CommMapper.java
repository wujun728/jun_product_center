package com.projectm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface CommMapper {
    List<Map> customQueryItem(@Param("sqlContent") String sqlContent);
    Map customQueryItemOne(@Param("sqlContent") String sqlContent);
    IPage<Map> customQueryItem(IPage<Map> page, @Param("sqlContent") String sqlContent);
}
