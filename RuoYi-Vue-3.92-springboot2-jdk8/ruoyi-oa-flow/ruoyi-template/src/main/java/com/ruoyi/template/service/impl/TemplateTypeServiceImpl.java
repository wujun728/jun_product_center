package com.ruoyi.template.service.impl;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.template.mapper.TemplateTypeMapper;
import com.ruoyi.template.domain.TemplateType;
import com.ruoyi.template.service.ITemplateTypeService;

/**
 * 模板分类Service业务层处理
 * 
 * @author wocurr.com
 * @date 2025-08-07
 */
@Slf4j
@Service
public class TemplateTypeServiceImpl implements ITemplateTypeService {
    @Autowired
    private TemplateTypeMapper templateTypeMapper;

    /**
     * 查询模板分类
     * 
     * @param id 模板分类主键
     * @return 模板分类
     */
    @Override
    public TemplateType getTemplateTypeById(String id) {
        return templateTypeMapper.selectTemplateTypeById(id);
    }

    /**
     * 查询模板分类列表
     * 
     * @param templateType 模板分类
     * @return 模板分类
     */
    @Override
    public List<TemplateType> listTemplateType(TemplateType templateType) {
        return templateTypeMapper.selectTemplateTypeList(templateType);
    }

    @Override
    public List<TemplateType> listTemplateType(List<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return templateTypeMapper.selectTemplateTypeByIds(ids);
    }

    @Override
    public List<TemplateType> listAllEnabledTemplateType() {
        return templateTypeMapper.listAllEnabledTemplateType();
    }

    /**
     * 新增模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    @Override
    public int saveTemplateType(TemplateType templateType) {
        if (StringUtils.isBlank(templateType.getName())) {
            throw new BaseException("参数错误");
        }
        List<TemplateType> templateTypeList = templateTypeMapper.selectTemplateTypeByName(templateType.getName());
        if (CollectionUtils.isNotEmpty(templateTypeList)) {
            throw new BaseException("名称已存在，不能重复");
        }
        templateType.setId(IdUtils.fastSimpleUUID());
        templateType.setCreateId(SecurityUtils.getUserIdStr());
        templateType.setCreateTime(DateUtils.getNowDate());
        return templateTypeMapper.insertTemplateType(templateType);
    }

    /**
     * 修改模板分类
     * 
     * @param templateType 模板分类
     * @return 结果
     */
    @Override
    public int updateTemplateType(TemplateType templateType) {
        templateType.setUpdateId(SecurityUtils.getUserIdStr());
        templateType.setUpdateTime(DateUtils.getNowDate());
        return templateTypeMapper.updateTemplateType(templateType);
    }

    /**
     * 批量删除模板分类
     * 
     * @param ids 需要删除的模板分类主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeByIds(String[] ids) {
        return templateTypeMapper.deleteTemplateTypeByIds(ids);
    }

    /**
     * 删除模板分类信息
     * 
     * @param id 模板分类主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeById(String id) {
        return templateTypeMapper.deleteTemplateTypeById(id);
    }

    @Override
    public int changeEnableFlag(TemplateType templateType) {
        TemplateType config = templateTypeMapper.selectTemplateTypeById(templateType.getId());
        if (config == null) {
            throw new BaseException("记录不存在");
        }
        return templateTypeMapper.changeEnableFlag(templateType);
    }
}
