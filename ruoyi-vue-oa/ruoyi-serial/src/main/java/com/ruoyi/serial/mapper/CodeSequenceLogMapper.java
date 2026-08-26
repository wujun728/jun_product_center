package com.ruoyi.serial.mapper;

import java.util.List;
import com.ruoyi.serial.domain.CodeSequenceLog;

/**
 * 编号生成日志Mapper接口
 * 
 * @author wocurr.com
 */
public interface CodeSequenceLogMapper {
    /**
     * 查询编号生成日志
     * 
     * @param id 编号生成日志主键
     * @return 编号生成日志
     */
    public CodeSequenceLog selectCodeSequenceLogById(Long id);

    /**
     * 查询编号生成日志列表
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 编号生成日志集合
     */
    public List<CodeSequenceLog> selectCodeSequenceLogList(CodeSequenceLog codeSequenceLog);

    /**
     * 新增编号生成日志
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 结果
     */
    public int insertCodeSequenceLog(CodeSequenceLog codeSequenceLog);

    /**
     * 修改编号生成日志
     * 
     * @param codeSequenceLog 编号生成日志
     * @return 结果
     */
    public int updateCodeSequenceLog(CodeSequenceLog codeSequenceLog);

    /**
     * 删除编号生成日志
     * 
     * @param id 编号生成日志主键
     * @return 结果
     */
    public int deleteCodeSequenceLogById(Long id);

    /**
     * 批量删除编号生成日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCodeSequenceLogByIds(Long[] ids);
}
