package com.projectm.org.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface OrgMapper {

    List<Map> selectOrgByMemCode(@Param("params") Map params);

    List<Map> _getOrgList(@Param("params") Map params);
}
