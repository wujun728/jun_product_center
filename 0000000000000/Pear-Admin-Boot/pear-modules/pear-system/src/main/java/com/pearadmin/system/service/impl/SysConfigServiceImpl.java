package com.pearadmin.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysConfig;
import com.pearadmin.system.mapper.SysConfigMapper;
import com.pearadmin.system.service.ISysConfigService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 系统配置服务接口实现
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

    /**
     * 系统配置数据库操作接口
     * */
    @Resource
    private SysConfigMapper sysConfigMapper;

    /**
     * Describe: 根据条件查询系统配置列表数据
     * Param: SysConfig
     * Return: List<SysConfig>
     * */
    @Override
    public List<SysConfig> list(SysConfig param) {
        return sysConfigMapper.selectList(param);
    }

    /**
     * Describe: 根据条件查询系统配置列表数据 分页
     * Param: SysConfig
     * Return: PageInfo<SysConfig>
     * */
    @Override
    public PageInfo<SysConfig> page(SysConfig param, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<SysConfig> list = sysConfigMapper.selectList(param);
        return new PageInfo<>(list);
    }

    /**
     * Describe: 保存系统配置数据
     * Param: SysConfig
     * Return: Boolean
     * */
    @Override
    public Boolean save(SysConfig sysConfig) {
        Integer result = sysConfigMapper.insert(sysConfig);
        if(result > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询系统配置
     * Param: id
     * Return: 返回系统配置信息
     * */
    @Override
    public SysConfig getById(String id) {
        return sysConfigMapper.selectById(id);
    }

    /**
     * Describe: 根据 Code 查询系统配置
     * Param: code
     * Return: 返回系统配置信息
     * */
    @Override
    public SysConfig getByCode(String code) {
        return sysConfigMapper.selectByCode(code);
    }

    /**
     * Describe: 根据 ID 修改系统配置
     * Param: SysConfig
     * Return: Boolean
     * */
    @Override
    public Boolean updateById(SysConfig sysConfig) {
        int result = sysConfigMapper.updateById(sysConfig);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 删除系统配置
     * Param: id
     * Return: Boolean
     * */
    @Override
    public Boolean remove(String id) {
        Integer result = sysConfigMapper.deleteById(id);
        return result > 0;
    }

    /**
     * Describe: 根据 ID 批量删除系统配置
     * Param: ids
     * Return: Boolean
     * */
    @Override
    public Boolean batchRemove(String[] ids) {
        Integer result = sysConfigMapper.deleteByIds(ids);
        return result > 0;
    }

}
