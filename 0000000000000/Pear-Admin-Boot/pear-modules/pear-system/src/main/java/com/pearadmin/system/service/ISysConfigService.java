package com.pearadmin.system.service;

import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysConfig;
import java.util.List;

/**
 * Describe: 系统配置服务接口
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */

public interface ISysConfigService {

    /**
     * Describe: 根据条件查询系统配置列表数据
     * Param: SysConfig
     * Return: List<SysConfig>
     * */
    List<SysConfig> list(SysConfig param);

    /**
     * Describe: 根据条件查询系统配置列表数据 分页
     * Param: SysConfig
     * Return: PageInfo<SysConfig>
     * */
    PageInfo<SysConfig> page(SysConfig param, PageDomain pageDomain);

    /**
     * Describe: 根据 Id 查询系统配置
     * Param: id
     * Return: SysConfig
     * */
    SysConfig getById(String id);

    /**
     * Describe: 根据 code 查询系统配置
     * Param: code
     * Return: SysConfig
     * */
    SysConfig getByCode(String code);

    /**
     * Describe: 插入 SysConfig 数据
     * Param: SysConfig
     * Return: List<SysConfig>
     * */
    Boolean save(SysConfig sysConfig);

    /**
     * Describe: 修改 SysConfig 数据
     * Param: SysConfig
     * Return: Boolean
     * */
    Boolean updateById(SysConfig sysConfig);

    /**
     * Describe: 删除 SysConfig 数据
     * Param: id
     * Return: Boolean
     * */
    Boolean remove(String id);

    /**
     * Describe: 批量 SysConfig 数据
     * Param: ids
     * Return: Boolean
     * */
    Boolean batchRemove(String[] ids);
}
