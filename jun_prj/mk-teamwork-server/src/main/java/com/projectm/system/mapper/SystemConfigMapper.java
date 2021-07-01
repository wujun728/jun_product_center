package com.projectm.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.projectm.system.domain.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {
}
