package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysLog;
import com.pearadmin.pro.modules.sys.param.SysLogRequest;

import java.util.List;

public interface SysLogService extends IService<SysLog> {

    /**
     * 获取日志列表
     *
     * @param request 查询参数
     * */
    List<SysLog> list(SysLogRequest request);

    /**
     * 获取日志列表
     *
     * @param request 查询参数
     * */
    PageResponse<SysLog> page(SysLogRequest request);
}
