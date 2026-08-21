package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserHire;

/**
 * 录用审批Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface HrUserHireMapper 
{
    /**
     * 查询录用审批
     * 
     * @param id 录用审批主键
     * @return 录用审批
     */
    public HrUserHire selectHrUserHireById(String id);

    /**
     * 查询录用审批列表
     * 
     * @param hrUserHire 录用审批
     * @return 录用审批集合
     */
    public List<HrUserHire> selectHrUserHireList(HrUserHire hrUserHire);

    /**
     * 新增录用审批
     * 
     * @param hrUserHire 录用审批
     * @return 结果
     */
    public int insertHrUserHire(HrUserHire hrUserHire);

    /**
     * 修改录用审批
     * 
     * @param hrUserHire 录用审批
     * @return 结果
     */
    public int updateHrUserHire(HrUserHire hrUserHire);

    /**
     * 删除录用审批
     * 
     * @param id 录用审批主键
     * @return 结果
     */
    public int deleteHrUserHireById(String id);

    /**
     * 批量删除录用审批
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrUserHireByIds(String[] ids);
}
