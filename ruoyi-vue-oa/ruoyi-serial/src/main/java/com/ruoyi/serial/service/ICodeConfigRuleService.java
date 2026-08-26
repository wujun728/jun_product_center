package com.ruoyi.serial.service;

import java.util.List;
import com.ruoyi.serial.domain.CodeConfigRule;

/**
 * 编号配置规则Service接口
 * 
 * @author wocurr.com
 */
public interface ICodeConfigRuleService {

    /**
     * 根据configId查询编号配置规则列表
     * @param configId
     * @return
     */
    public List<CodeConfigRule> listCodeConfigRuleByConfigId(String configId);

    /**
     * 根据configId删除
     * @param configId
     * @return
     */
    public int updateDelFlagByConfigId(String configId);

    /**
     * 批量添加
     * @param rules
     * @return
     */
    public int batchAdd(List<CodeConfigRule> rules);
}
