package com.pearadmin.modules.sys.service;

import com.pearadmin.modules.sys.domain.SysPower;
import java.util.List;

/**
 * Describe: 权限服务接口类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
public interface ISysPowerService {

    /**
     * Describe: 根据条件查询权限列表数据
     * Param: SysPower
     * Return: 返回用户列表数据
     * */
    List<SysPower> list(SysPower sysPower);

    /**
     * Describe: 保存权限数据
     * Param: SysPower
     * Return: 操作结果
     * */
    boolean save(SysPower sysPower);

    /**
     * Describe: 根据 id 获取权限数据
     * Param: SysPower
     * Return: 操作结果
     * */
    SysPower getById(String id);

    /**
     * Describe: 修改权限数据
     * Param: SysPower
     * Return: 操作结果
     * */
    boolean update(SysPower sysPower);

    /**
     * Describe: 根据 id 删除用户数据
     * Param: id
     * Return: 操作结果
     * */
    boolean remove(String id);
}
