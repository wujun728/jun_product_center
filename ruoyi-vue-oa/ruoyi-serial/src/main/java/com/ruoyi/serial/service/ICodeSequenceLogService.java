package com.ruoyi.serial.service;

import java.util.List;
import com.ruoyi.serial.domain.CodeSequenceLog;

/**
 * 编号生成日志Service接口
 * 
 * @author wocurr.com
 */
public interface ICodeSequenceLogService {
    /**
     * 查询编号生成日志
     * 
     * @param id 编号生成日志主键
     * @return 编号生成日志
     */
    public CodeSequenceLog getCodeSequenceLogById(Long id);

    /**
     * 查询编号生成日志列表
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 编号生成日志集合
     */
    public List<CodeSequenceLog> listCodeSequenceLog(CodeSequenceLog codeSequenceLog);

    /**
     * 新增编号生成日志
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 结果
     */
    public int saveCodeSequenceLog(CodeSequenceLog codeSequenceLog);
}
