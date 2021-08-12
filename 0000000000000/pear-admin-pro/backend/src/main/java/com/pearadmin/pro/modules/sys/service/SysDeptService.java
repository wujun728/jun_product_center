package com.pearadmin.pro.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pearadmin.pro.common.tools.support.server.server.Sys;
import com.pearadmin.pro.modules.sys.domain.SysDept;

import java.util.List;

public interface SysDeptService extends IService<SysDept> {

    List<SysDept> tree();

    List<SysDept> treeAndChildren(String parent);

}
