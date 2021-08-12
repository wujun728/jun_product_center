package com.pearadmin.system.service.impl;

import com.pearadmin.system.domain.SysLog;
import com.pearadmin.common.plugin.logging.aop.enums.LoggingType;
import com.pearadmin.common.plugin.logging.aop.enums.RequestMethod;
import com.pearadmin.system.mapper.SysLogMapper;
import com.pearadmin.system.service.ISysLogService;
import com.pearadmin.common.tools.secure.SecurityUtil;
import com.pearadmin.common.tools.servlet.ServletUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Describe: 日 志 服 务 接 口 实 现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SysLogServiceImpl implements ISysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public boolean save(SysLog sysLog) {
        sysLog.setOperateAddress(ServletUtil.getRemoteHost());
        sysLog.setMethod(ServletUtil.getRequestURI());
        sysLog.setCreateTime(LocalDateTime.now());
        sysLog.setRequestMethod(RequestMethod.valueOf(ServletUtil.getMethod()));
        sysLog.setOperateUrl(ServletUtil.getRequestURI());
        sysLog.setBrowser(ServletUtil.getBrowser());
        sysLog.setRequestBody(ServletUtil.getQueryParam());
        sysLog.setSystemOs(ServletUtil.getSystem());
        sysLog.setOperateName(null != SecurityUtil.currentUser() ? SecurityUtil.currentUser().getName() : "未登录用户");
        int result = sysLogMapper.insert(sysLog);
        return result > 0;
    }

    @Override
    public List<SysLog> data(LoggingType loggingType,LocalDateTime startTime,LocalDateTime endTime) {
        return sysLogMapper.selectList(loggingType,startTime,endTime);
    }

    @Override
    public SysLog getById(String id) {
        return sysLogMapper.getById(id);
    }

    @Override
    public List<SysLog> selectTopLoginLog(String operateName) {
        return sysLogMapper.selectTopLoginLog(operateName);
    }

}
