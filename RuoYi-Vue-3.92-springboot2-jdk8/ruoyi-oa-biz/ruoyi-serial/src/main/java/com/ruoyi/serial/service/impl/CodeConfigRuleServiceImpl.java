package com.ruoyi.serial.service.impl;

import com.ruoyi.serial.domain.CodeConfigRule;
import com.ruoyi.serial.mapper.CodeConfigRuleMapper;
import com.ruoyi.serial.service.ICodeConfigRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 编号配置规则Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class CodeConfigRuleServiceImpl implements ICodeConfigRuleService {
    @Autowired
    private CodeConfigRuleMapper codeConfigRuleMapper;

    /**
     * 根据configId查询编号配置规则列表
     *
     * @param configId 编号配置规则主键
     * @return
     */
    @Override
    public List<CodeConfigRule> listCodeConfigRuleByConfigId(String configId) {
        return codeConfigRuleMapper.selectCodeConfigRuleListByConfigId(configId);
    }

    /**
     * 根据configId更新删除标志
     *
     * @param configId 编号配置规则主键
     * @return
     */
    @Override
    public int updateDelFlagByConfigId(String configId) {
        return codeConfigRuleMapper.updateDelFlagByConfigId(configId);
    }

    /**
     * 批量添加
     *
     * @param rules 规则列表
     * @return
     */
    @Override
    public int batchAdd(List<CodeConfigRule> rules) {
        if (CollectionUtils.isEmpty(rules)) {
            return 0;
        }
        return codeConfigRuleMapper.batchAdd(rules);
    }
}
