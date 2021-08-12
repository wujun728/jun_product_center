package com.pearadmin.pro.modules.not.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.not.domain.SysAnnounceUser;
import com.pearadmin.pro.modules.not.param.SysAnnounceUserRequest;
import java.util.List;

public interface SysAnnounceUserService extends IService<SysAnnounceUser> {

    /**
     * 获取用户公告列表
     *
     * @param request 参数实体
     *
     * @return {@link SysAnnounceUser}
     * */
    List<SysAnnounceUser> list(SysAnnounceUserRequest request);

    /**
     * 获取用户公告列表 (分页)
     *
     * @param request 参数实体
     *
     * @return {@link SysAnnounceUser}
     * */
    PageResponse<SysAnnounceUser> page(SysAnnounceUserRequest request);

}
