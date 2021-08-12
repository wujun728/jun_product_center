package com.pearadmin.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pearadmin.common.web.domain.request.PageDomain;
import com.pearadmin.system.domain.SysDictType;
import com.pearadmin.system.mapper.SysDictDataMapper;
import com.pearadmin.system.mapper.SysDictTypeMapper;
import com.pearadmin.system.service.ISysDictDataService;
import com.pearadmin.system.service.ISysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Describe: 字典类型服务实现类
 * Author: 就 眠 仪 式
 * CreateTime: 2019/10/23
 * */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    @Resource
    private SysDictTypeMapper sysDictTypeMapper;

    @Resource
    private ISysDictDataService iSysDictDataService;

    @Resource
    private SysDictDataMapper sysDictDataMapper;

    /**
     * Describe: 根据条件查询用户列表数据
     * Param: SysDictType
     * Return: List<SysDictType>
     * */
    @Override
    public List<SysDictType> list(SysDictType sysDictType) {
        return sysDictTypeMapper.selectList(sysDictType);
    }

    /**
     * Describe: 根据条件查询用户列表数据 分页
     * Param: SysDictType
     * Return: PageInfo<SysDictType>
     * */
    @Override
    public PageInfo<SysDictType> page(SysDictType sysDictType, PageDomain pageDomain) {
        PageHelper.startPage(pageDomain.getPage(),pageDomain.getLimit());
        List<SysDictType> list = sysDictTypeMapper.selectList(sysDictType);
        return new PageInfo<>(list);
    }

    /**
     * Describe: 保存字典数据
     * Param: SysDictType
     * Return: Boolean
     * */
    @Override
    public Boolean save(SysDictType sysDictType) {
        Integer result = sysDictTypeMapper.insert(sysDictType);
        if(result > 0){
            iSysDictDataService.refreshCacheTypeCode(sysDictType.getTypeCode());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 查询字典类型
     * Param: id
     * Return: 返回字典类型信息
     * */
    @Override
    public SysDictType getById(String id) {
        return sysDictTypeMapper.selectById(id);
    }

    /**
     * Describe: 根据 ID 修改字典类型
     * Param: SysDictType
     * Return: Boolean
     * */
    @Override
    public Boolean updateById(SysDictType sysDictType) {
        int result = sysDictTypeMapper.updateById(sysDictType);
        SysDictType dictType = sysDictTypeMapper.selectById(sysDictType.getId());
        if(result > 0){
            iSysDictDataService.refreshCacheTypeCode(dictType.getTypeCode());
            return true;
        }else{
            return false;
        }
    }

    /**
     * Describe: 根据 ID 删除字典类型
     * Param: id
     * Return: Boolean
     * */
    @Override
    public Boolean remove(String id) {
        SysDictType sysDictType =  sysDictTypeMapper.selectById(id);
        if(sysDictType!=null) {
             sysDictTypeMapper.deleteById(id);
             sysDictDataMapper.deleteByCode(sysDictType.getTypeCode());
        }
        iSysDictDataService.refreshCacheTypeCode(sysDictType.getTypeCode());
        return true;
    }
}
