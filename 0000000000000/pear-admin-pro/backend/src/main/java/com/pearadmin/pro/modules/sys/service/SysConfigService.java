package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysConfig;
import com.pearadmin.pro.modules.sys.param.SysConfigRequest;

import java.util.List;

public interface SysConfigService extends IService<SysConfig>{

    /**
     * 获取用户列表
     *
     * @param request 查询参数
     *
     * @return {@link SysConfig}
     * */
    List<SysConfig> list(SysConfigRequest request);

    /**
     * 获取用户列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysConfig}
     * */
    PageResponse<SysConfig> page(SysConfigRequest request);

}
