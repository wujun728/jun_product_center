package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.web.base.page.PageResponse;
import com.pearadmin.pro.modules.sys.domain.SysDict;
import com.pearadmin.pro.modules.sys.param.SysDictRequest;
import java.util.List;

public interface SysDictService extends IService<SysDict> {

    /**
     * 获取字典类型列表
     *
     * @param request 查询参数
     * */
    List<SysDict> list(SysDictRequest request);

    /**
     * 获取字典类型列表 (分页)
     *
     * @param request 查询参数
     * */
    PageResponse<SysDict> page(SysDictRequest request);
}
