package com.ruoyi.serial.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.enums.WhetherStatus;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.serial.domain.CodeConfigRule;
import com.ruoyi.serial.enums.RuleTypeEnum;
import com.ruoyi.serial.enums.SeqResetTypeEnum;
import com.ruoyi.serial.module.CodeConfigDTO;
import com.ruoyi.serial.service.ICodeConfigRuleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.serial.mapper.CodeConfigMapper;
import com.ruoyi.serial.domain.CodeConfig;
import com.ruoyi.serial.service.ICodeConfigService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 编号配置Service业务层处理
 *
 * @author wocurr.com
 */
@Slf4j
@Service
public class CodeConfigServiceImpl implements ICodeConfigService {
    @Autowired
    private CodeConfigMapper codeConfigMapper;
    @Autowired
    private ICodeConfigRuleService codeConfigRuleService;

    /**
     * 查询编号配置
     *
     * @param id 编号配置主键
     * @return 编号配置
     */
    @Override
    public CodeConfigDTO getCodeConfigById(String id) {
        CodeConfigDTO result = codeConfigMapper.selectCodeConfigDTOById(id);
        if (result != null) {
            List<CodeConfigRule> rules = codeConfigRuleService.listCodeConfigRuleByConfigId(id);
            result.setRules(rules);
        }
        return result;
    }

    /**
     * 查询编号配置列表
     *
     * @param codeConfig 编号配置
     * @return 编号配置
     */
    @Override
    public List<CodeConfig> listCodeConfig(CodeConfig codeConfig) {
        return codeConfigMapper.selectCodeConfigList(codeConfig);
    }

    @Override
    public List<CodeConfig> serialOptions() {
        return codeConfigMapper.selectAllSerialOptions();
    }

    /**
     * 新增编号配置
     *
     * @param codeConfig 编号配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveCodeConfig(CodeConfigDTO codeConfig) {
        String id = IdUtils.fastSimpleUUID();
        // 保存子规则
        saveRules(id, codeConfig.getRules());
        Date now = DateUtils.getNowDate();
        codeConfig.setId(id);
        codeConfig.setCreateTime(now);
        codeConfig.setCreateId(SecurityUtils.getUserIdStr());
        return codeConfigMapper.insertCodeConfig(codeConfig);
    }

    /**
     * 修改编号配置changeStatus
     *
     * @param codeConfig 编号配置
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCodeConfig(CodeConfigDTO codeConfig) {
        String id = codeConfig.getId();
        CodeConfig config = codeConfigMapper.selectCodeConfigById(id);
        if (config == null) {
            throw new BaseException("记录不存在");
        }
        // 删除子规则
        codeConfigRuleService.updateDelFlagByConfigId(id);
        // 保存子规则
        saveRules(id, codeConfig.getRules());
        codeConfig.setUpdateId(SecurityUtils.getUserIdStr());
        codeConfig.setUpdateTime(DateUtils.getNowDate());
        return codeConfigMapper.updateCodeConfig(codeConfig);
    }

    /**
     * 自增当前序号
     *
     * @param id
     * @return
     */
    @Override
    public int incrCurrentSeq(String id) {
        return codeConfigMapper.incrCurrentSeq(id);
    }

    /**
     * 批量删除编号配置（软删除）
     *
     * @param id 需要删除的编号配置主键
     * @return 结果
     */
    @Override
    public int deleteCodeConfigById(String id) {
        CodeConfig config = codeConfigMapper.selectCodeConfigById(id);
        if (config == null) {
            throw new BaseException("记录不存在");
        }
        return codeConfigMapper.updateDelFlagById(id);
    }

    /**
     * 修改启用状态
     *
     * @param codeConfig 编号配置
     * @return
     */
    @Override
    public int changeEnableFlag(CodeConfig codeConfig) {
        CodeConfig config = codeConfigMapper.selectCodeConfigById(codeConfig.getId());
        if (config == null) {
            throw new BaseException("记录不存在");
        }
        return codeConfigMapper.changeEnableFlag(codeConfig);
    }

    @Override
    public void restSeq() {
        List<CodeConfig> list = codeConfigMapper.selectCodeConfigList(new CodeConfig());
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        LocalDate currentDate = LocalDate.now();
        // 判断是否为周一
        boolean isMONDAY = currentDate.getDayOfWeek() == DayOfWeek.MONDAY;
        // 判断是否为每月1号
        boolean isFirstDayOfMonth = currentDate.getDayOfMonth() == 1;
        // 判断是否为每年1月1号
        boolean isFirstDayOfYear = currentDate.getMonthValue() == 1 && currentDate.getDayOfMonth() == 1;

        List<String> idList = new ArrayList<>();
        for(CodeConfig codeConfig : list) {
            List<CodeConfigRule> rules = codeConfigRuleService.listCodeConfigRuleByConfigId(codeConfig.getId());
            if (CollectionUtils.isEmpty(rules)) {
                continue;
            }
            boolean isReset = false;
            for(CodeConfigRule rule : rules) {
                if (!RuleTypeEnum.SEQ.getCode().equals(rule.getRuleType())) {
                    continue;
                }
                isReset = isReset(rule.getSeqResetType(), isMONDAY, isFirstDayOfMonth, isFirstDayOfYear);
            }
            if (isReset) {
                idList.add(codeConfig.getId());
            }
        };
        if (CollectionUtils.isNotEmpty(idList)) {
            codeConfigMapper.updateCurrentSeq(idList);
        }
    }

    /**
     * 保存子规则
     *
     * @param id
     * @param configRules
     */
    private void saveRules(String id, List<CodeConfigRule> configRules) {
        // 保存子规则
        List<CodeConfigRule> rules = new ArrayList<>();
        int sort = 0;
        Date now = DateUtils.getNowDate();
        for (CodeConfigRule rule : configRules) {
            rule.setId(IdUtils.fastSimpleUUID());
            rule.setConfigId(id);
            rule.setSort(++sort);
            rule.setSeqResetType(SeqResetTypeEnum.getByCode(rule.getSeqResetType()).getCode());
            rule.setDelFlag(WhetherStatus.NO.getCode());
            rule.setCreateTime(now);
            rules.add(rule);
        }
        codeConfigRuleService.batchAdd(rules);
    }

    /**
     * 判断是否需要重置
     * @param seqResetType
     * @param isMONDAY
     * @param isFirstDayOfMonth
     * @param isFirstDayOfYear
     * @return
     */
    private boolean isReset(String seqResetType, boolean isMONDAY, boolean isFirstDayOfMonth, boolean isFirstDayOfYear) {
        boolean isReset = false;
        SeqResetTypeEnum seqResetTypeEnum = SeqResetTypeEnum.getByCode(seqResetType);
        switch(seqResetTypeEnum) {
            case NONE:
                break;
            case DAY:
                isReset = true;
                break;
            case WEEK:
                if (isMONDAY) {
                    isReset = true;
                }
                break;
            case MONTH:
                if (isFirstDayOfMonth) {
                    isReset = true;
                }
                break;
            case YEAR:
                if (isFirstDayOfYear) {
                    isReset = true;
                }
                break;
            default:
                break;
        }
        return isReset;
    }
}
