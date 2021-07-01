package com.projectm.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.projectm.system.domain.Notify;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
@Mapper
public interface NotifyMapper extends BaseMapper<Notify> {

    @Select("SELECT * FROM team_notify WHERE `to` = #{params.to} AND terminal = #{params.terminal} ")
    IPage<Map> getAllNotifyByParams(IPage<Map> page, @Param("params") Map params);

}
