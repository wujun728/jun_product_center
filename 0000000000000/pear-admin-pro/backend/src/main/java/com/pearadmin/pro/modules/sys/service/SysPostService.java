package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysPost;
import com.pearadmin.pro.modules.sys.param.SysPostRequest;
import java.util.List;

public interface SysPostService extends IService<SysPost> {

    /**
     * 获取岗位列表
     *
     * @param request 查询参数
     *
     * @return {@link SysPost}
     * */
    List<SysPost> list(SysPostRequest request);

    /**
     * 获取岗位列表 (分页)
     *
     * @param request 查询参数
     *
     * @return {@link SysPost}
     * */
    PageResponse<SysPost> page(SysPostRequest request);
}
