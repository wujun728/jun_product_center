package com.pearadmin.system.service;

import com.pearadmin.system.domain.SysLog;
import com.pearadmin.common.plugin.logging.aop.enums.LoggingType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Describe: 日 志 服 务 接 口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 */
public interface ISysLogService {

    /**
     * Describe: 执 行 插 入 操 作
     * Param: logging
     * Return: boolean
     */
    boolean save(SysLog sysLog);

    /**
     * Describe: 执 行 查 询 操 作
     * Param: loggingType
     * Return: 日志列表
     */
    List<SysLog> data(LoggingType loggingType, LocalDateTime startTime,LocalDateTime endTime);

    /**
     * Describe: 根 据 id 查 询 日 志
     * Param: id
     * Return: Logging
     */
    SysLog getById(String id);

    /**
     * Describe: 根据 operateName 查询日志
     * Param: operateName
     * Return: 日志列表
     */
    List<SysLog> selectTopLoginLog(String operateName);
}
