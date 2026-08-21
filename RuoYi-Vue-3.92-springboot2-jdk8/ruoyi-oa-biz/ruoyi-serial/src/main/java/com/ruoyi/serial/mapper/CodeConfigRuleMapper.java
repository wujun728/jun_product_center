package com.ruoyi.serial.mapper;

import java.util.List;
import com.ruoyi.serial.domain.CodeConfigRule;

/**
 * 编号配置规则Mapper接口
 * 
 * @author wocurr.com
 */
public interface CodeConfigRuleMapper {
    /**
     * 查询编号配置规则
     * 
     * @param id 编号配置规则主键
     * @return 编号配置规则
     */
    public CodeConfigRule selectCodeConfigRuleById(String id);

    /**
     * 根据配置ID查询规则列表
     * @param configId
     * @return
     */
    public List<CodeConfigRule> selectCodeConfigRuleListByConfigId(String configId);

    /**
     * 查询编号配置规则列表
     * 
     * @param codeConfigRule 编号配置规则
     * @return 编号配置规则集合
     */
    public List<CodeConfigRule> selectCodeConfigRuleList(CodeConfigRule codeConfigRule);

    /**
     * 新增编号配置规则
     * 
     * @param codeConfigRule 编号配置规则
     * @return 结果
     */
    public int insertCodeConfigRule(CodeConfigRule codeConfigRule);

    /**
     * 修改编号配置规则
     * 
     * @param codeConfigRule 编号配置规则
     * @return 结果
     */
    public int updateCodeConfigRule(CodeConfigRule codeConfigRule);

    /**
     * 删除编号配置规则
     * 
     * @param id 编号配置规则主键
     * @return 结果
     */
    public int deleteCodeConfigRuleById(String id);

    /**
     * 根据配置ID删除
     * @param configId
     * @return
     */
    public int deleteByConfigId(String configId);

    /**
     * 批量删除编号配置规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCodeConfigRuleByIds(String[] ids);

    /**
     * 批量新增
     * @param rules 编号规则列表
     * @return
     */
    public int batchAdd(List<CodeConfigRule> rules);

    /**
     * 更新删除标识
     *
     * @param configId 编号配置ID
     * @return
     */
    int updateDelFlagByConfigId(String configId);
}
