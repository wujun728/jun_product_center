package com.ruoyi.workflow.mapper;

import java.util.List;
import com.ruoyi.workflow.domain.Form;

/**
 * 流程表单Mapper接口
 * 
 * @author wocurr.com
 */
public interface FormMapper {
    /**
     * 查询流程表单
     * 
     * @param id 流程表单主键
     * @return 流程表单
     */
    public Form selectFormById(String id);

    /**
     * 查询流程表单列表
     * 
     * @param form 流程表单
     * @return 流程表单集合
     */
    public List<Form> selectFormList(Form form);

    /**
     * 新增流程表单
     * 
     * @param form 流程表单
     * @return 结果
     */
    public int insertForm(Form form);

    /**
     * 修改流程表单
     * 
     * @param form 流程表单
     * @return 结果
     */
    public int updateForm(Form form);

    /**
     * 删除流程表单
     * 
     * @param id 流程表单主键
     * @return 结果
     */
    public int deleteFormById(String id);

    /**
     * 批量删除流程表单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFormByIds(String[] ids);
}
