package com.ruoyi.serial.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.serial.mapper.CodeSequenceLogMapper;
import com.ruoyi.serial.domain.CodeSequenceLog;
import com.ruoyi.serial.service.ICodeSequenceLogService;

/**
 * 编号生成日志Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class CodeSequenceLogServiceImpl implements ICodeSequenceLogService {
    @Autowired
    private CodeSequenceLogMapper codeSequenceLogMapper;

    /**
     * 查询编号生成日志
     * 
     * @param id 编号生成日志主键
     * @return 编号生成日志
     */
    @Override
    public CodeSequenceLog getCodeSequenceLogById(Long id) {
        return codeSequenceLogMapper.selectCodeSequenceLogById(id);
    }

    /**
     * 查询编号生成日志列表
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 编号生成日志
     */
    @Override
    public List<CodeSequenceLog> listCodeSequenceLog(CodeSequenceLog codeSequenceLog) {
        return codeSequenceLogMapper.selectCodeSequenceLogList(codeSequenceLog);
    }

    /**
     * 新增编号生成日志
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 结果
     */
    @Override
    public int saveCodeSequenceLog(CodeSequenceLog codeSequenceLog) {
        codeSequenceLog.setCreateTime(DateUtils.getNowDate());
        return codeSequenceLogMapper.insertCodeSequenceLog(codeSequenceLog);
    }
}
