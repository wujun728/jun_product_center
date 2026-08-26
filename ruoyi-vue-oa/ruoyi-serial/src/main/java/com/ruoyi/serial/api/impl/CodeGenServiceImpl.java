package com.ruoyi.serial.api.impl;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.serial.api.ICodeGenService;
import com.ruoyi.serial.domain.CodeConfigRule;
import com.ruoyi.serial.domain.CodeSequenceLog;
import com.ruoyi.serial.enums.RuleTypeEnum;
import com.ruoyi.serial.module.CodeConfigDTO;
import com.ruoyi.serial.service.ICodeConfigService;
import com.ruoyi.serial.service.ICodeSequenceLogService;
import com.ruoyi.tools.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 编号生成服务实现类
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class CodeGenServiceImpl implements ICodeGenService {

    @Autowired
    private ICodeConfigService codeConfigService;
    @Autowired
    private ICodeSequenceLogService codeSequenceLogService;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private RedisCache redisCache;

    private static final String LOCK_KEY_SUFFIX = "code:gen:lock:";
    private static final String SEQ_KEY_SUFFIX = "code:gen:seq:";

    /**
     * 获取下一个编号
     *
     * @param confId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getNextCode(String confId) {
        if (StringUtils.isBlank(confId)) {
            throw new BaseException("参数错误");
        }
        String key = LOCK_KEY_SUFFIX + confId;
        String codeKey = SEQ_KEY_SUFFIX + confId;
        try {
            redisLock.tryLock(key, 10L, 20L, TimeUnit.SECONDS);
            CodeConfigDTO codeConfigDTO = codeConfigService.getCodeConfigById(confId);
            if (codeConfigDTO == null) {
                throw new BaseException("此类型未配置编号规则！");
            }
            if (!redisCache.hasKey(codeKey)) {
                redisCache.setCacheObject(codeKey, codeConfigDTO.getCurrentSeq());
            }
            long nowSeq = redisCache.getIncr(codeKey);
            log.info("获取编号，businessType：{}，当前编号：{}", confId, nowSeq);
            StringBuilder nextCode = new StringBuilder();
            List<CodeConfigRule> rules = codeConfigDTO.getRules();
            for (CodeConfigRule rule : rules) {
                addValueByRuleType(rule, nextCode, nowSeq);
            }
            // 更新最新流水号
            codeConfigService.incrCurrentSeq(codeConfigDTO.getId());
            // 插入编号记录
            CodeSequenceLog codeSequenceLog = new CodeSequenceLog();
            codeSequenceLog.setTitle(codeConfigDTO.getTitle());
            codeSequenceLog.setCode(nextCode.toString());
            codeSequenceLog.setCodeSeq((int) nowSeq);
            codeSequenceLog.setCreateTime(DateUtils.getNowDate());
            codeSequenceLogService.saveCodeSequenceLog(codeSequenceLog);
            return nextCode.toString();
        } catch (BaseException e) {
            throw new BaseException(e.getMessage());
        } catch (Exception e) {
            throw new BaseException("获取编号失败！");
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 根据规则类型添加值
     *
     * @param rule 规则
     * @param nextCode 编号code
     * @param nowSeq 当前流水号
     */
    private void addValueByRuleType(CodeConfigRule rule, StringBuilder nextCode, long nowSeq) {
        String ruleValue = rule.getRuleValue();
        RuleTypeEnum ruleTypeEnum = RuleTypeEnum.getByCode(rule.getRuleType());
        if (ruleTypeEnum == null) {
            throw new BaseException("编号规则类型错误！");
        }
        switch (ruleTypeEnum) {
            case FIXED:
                nextCode.append(ruleValue);
                break;
            case DATE:
                nextCode.append(DateUtils.parseDateToStr(ruleValue, DateUtils.getNowDate()));
                break;
            case SEQ:
                String seq = "";
                if (StringUtils.equals(Constants.YES_VALUE, rule.getPadZero())) {
                    seq = StringUtils.padl(nowSeq, Integer.parseInt(ruleValue));
                } else {
                    seq = String.valueOf(nowSeq);
                }
                nextCode.append(seq);
                break;
            default:
                throw new BaseException("编号规则类型错误！");
        }
    }
}
