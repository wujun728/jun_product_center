package com.ruoyi.serial.mapper;

import java.util.List;
import com.ruoyi.serial.domain.CodeConfig;
import com.ruoyi.serial.module.CodeConfigDTO;

/**
 * 编号配置Mapper接口
 * 
 * @author wocurr.com
 */
public interface CodeConfigMapper {
    /**
     * 查询编号配置
     * 
     * @param id 编号配置主键
     * @return 编号配置
     */
    public CodeConfig selectCodeConfigById(String id);

    /**
     * 查询编号配置
     * @param id
     * @return
     */
    public CodeConfigDTO selectCodeConfigDTOById(String id);

    /**
     * 根据业务类型查询编号配置
     * @param businessType
     * @return
     */
    public CodeConfigDTO selectByBusinessType(String businessType);

    /**
     * 查询编号配置列表
     * 
     * @param codeConfig 编号配置
     * @return 编号配置集合
     */
    public List<CodeConfig> selectCodeConfigList(CodeConfig codeConfig);

    /**
     * 查询所有有效的编号
     * @return
     */
    public List<CodeConfig> selectAllSerialOptions();

    /**
     * 新增编号配置
     * 
     * @param codeConfig 编号配置
     * @return 结果
     */
    public int insertCodeConfig(CodeConfig codeConfig);

    /**
     * 修改编号配置
     * 
     * @param codeConfig 编号配置
     * @return 结果
     */
    public int updateCodeConfig(CodeConfig codeConfig);

    /**
     * 禁用启用状态
     * @param businessType
     * @return
     */
    public int disableEnableFlag(String businessType);

    /**
     * 修改启用状态
     * @param codeConfig
     * @return
     */
    public int changeEnableFlag(CodeConfig codeConfig);

    /**
     * 流水号自增
     * @param id
     * @return
     */
    public int incrCurrentSeq(String id);

    /**
     * 删除编号配置
     * 
     * @param id 编号配置主键
     * @return 结果
     */
    public int deleteCodeConfigById(String id);

    /**
     * 批量删除编号配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCodeConfigByIds(String[] ids);

    /**
     * 批量更新删除标识
     *
     * @param id
     * @return
     */
    int updateDelFlagById(String id);

    /**
     * 重置序号为1
     * @return
     */
    int updateCurrentSeq(List<String> list);
}
