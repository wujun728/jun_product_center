package com.pearadmin.system.mapper;

import com.pearadmin.system.domain.SysLog;
import com.pearadmin.common.plugin.logging.aop.enums.LoggingType;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Describe: 日 志 接 口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Mapper
public interface SysLogMapper {

    /**
     * Describe: 插入日志信息
     * Param: logging
     * Return: 影响行数
     * */
    int insert(SysLog sysLog);

    /**
     * Describe: 查询日志信息
     * Param: LoggingType
     * Return: 日志列表
     * */
    List<SysLog> selectList(LoggingType loggingType, LocalDateTime startTime,LocalDateTime endTime);

    /**
     * Describe: 根据 id 查询日志信息
     * Param: id
     * Return: Logging
     */
    SysLog getById(String id);

    /**
     * Describe: 根据 operateName 查询日志
     * Param: operateName
     * Return 日志列表
     */
    List<SysLog> selectTopLoginLog(String operateName);

}
