package com.pearadmin.system.service.impl;

import com.pearadmin.system.domain.SysPower;
import com.pearadmin.system.domain.SysRole;
import com.pearadmin.system.mapper.SysPowerMapper;
import com.pearadmin.system.mapper.SysRolePowerMapper;
import com.pearadmin.system.service.ISysPowerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Describe: 权限服务实现类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SysPowerServiceImpl implements ISysPowerService {

    /**
     * 引入服务
     * */
    @Resource
    private SysPowerMapper sysPowerMapper;

    /**
     * 引入角色权限服务
     * */
    @Resource
    private SysRolePowerMapper sysRolePowerMapper;

    /**
     * Describe: 查询权限列表
     * Param: SysPower
     * Return: 执行结果
     * */
    @Override
    public List<SysPower> list(SysPower sysPower) {
        return sysPowerMapper.selectList(sysPower);
    }

    /**
     * Describe: 保存权限
     * Param: SysPower
     * Return: 执行结果
     * */
    @Override
    public boolean save(SysPower sysPower) {
        int result =  sysPowerMapper.insert(sysPower);
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询权限
     * Param: id
     * Return: 返回前线信息
     * */
    @Override
    public SysPower getById(String id) {
        return sysPowerMapper.selectById(id);
    }

    /**
     * Describe: 修改权限数据
     * Param: SysPower
     * Return: 执行结果
     * */
    @Override
    public boolean update(SysPower sysPower) {
        int result = sysPowerMapper.updateById(sysPower);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 删除权限信息
     * Param: id
     * Return: 执行结果
     * */
    @Override
    @Transactional
    public boolean remove(String id) {
        sysPowerMapper.deleteById(id);
        sysRolePowerMapper.deleteByPowerId(id);
        return true;
    }

    /**
     * Describe: 根据 parentId 查询权限
     * Param: parentId
     * Return: 操作结果
     * */
    @Override
    public List<SysPower> selectByParentId(String parentId) {
        return sysPowerMapper.selectListByParentId(parentId);
    }
}
