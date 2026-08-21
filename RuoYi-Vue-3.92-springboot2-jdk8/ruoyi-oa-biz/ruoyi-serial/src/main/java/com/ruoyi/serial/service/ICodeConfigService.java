package com.ruoyi.serial.service;

import java.util.List;
import com.ruoyi.serial.domain.CodeConfig;
import com.ruoyi.serial.module.CodeConfigDTO;

/**
 * 编号配置Service接口
 * 
 * @author wocurr.com
 */
public interface ICodeConfigService {
    /**
     * 查询编号配置
     * 
     * @param id 编号配置主键
     * @return 编号配置
     */
    public CodeConfigDTO getCodeConfigById(String id);

    /**
     * 查询编号配置列表
     * 
     * @param codeConfig 编号配置
     * @return 编号配置集合
     */
    public List<CodeConfig> listCodeConfig(CodeConfig codeConfig);

    /**
     * 查询所有有效的编号
     * @return
     */
    public List<CodeConfig> serialOptions();

    /**
     * 新增编号配置
     * 
     * @param codeConfig 编号配置
     * @return 结果
     */
    public int saveCodeConfig(CodeConfigDTO codeConfig);

    /**
     * 修改编号配置
     * 
     * @param codeConfig 编号配置
     * @return 结果
     */
    public int updateCodeConfig(CodeConfigDTO codeConfig);

    /**
     * 自增当前流水号
     *
     * @param id 编号配置主键
     * @return
     */
    public int incrCurrentSeq(String id);

    /**
     * 批量删除编号配置
     * 
     * @param id 需要删除的编号配置主键集合
     * @return 结果
     */
    public int deleteCodeConfigById(String id);

    /**
     * 修改启用状态
     *
     * @param codeConfig 编号配置
     * @return
     */
    public int changeEnableFlag(CodeConfig codeConfig);

    /**
     * 重置流水号
     */
    public void restSeq();
}
