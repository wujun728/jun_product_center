package com.pearadmin.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.not.domain.SysAnnounce;
import com.pearadmin.pro.modules.not.param.SysAnnounceRequest;
import java.util.List;

public interface SysAnnounceService extends IService<SysAnnounce> {

    /**
     * 获取公告列表
     *
     * @param request 参数实体
     *
     * @return {@link SysAnnounce}
     * */
    List<SysAnnounce> list(SysAnnounceRequest request);

    /**
     * 获取公告列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysAnnounce}
     * */
    PageResponse<SysAnnounce> page(SysAnnounceRequest request);

}
