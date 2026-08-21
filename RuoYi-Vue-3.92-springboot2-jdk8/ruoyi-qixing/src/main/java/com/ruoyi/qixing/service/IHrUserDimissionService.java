package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.HrUserDimission;

/**
 * 离职Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IHrUserDimissionService 
{
    /**
     * 查询离职
     * 
     * @param id 离职主键
     * @return 离职
     */
    public HrUserDimission selectHrUserDimissionById(String id);

    /**
     * 查询离职列表
     * 
     * @param hrUserDimission 离职
     * @return 离职集合
     */
    public List<HrUserDimission> selectHrUserDimissionList(HrUserDimission hrUserDimission);

    /**
     * 新增离职
     * 
     * @param hrUserDimission 离职
     * @return 结果
     */
    public int insertHrUserDimission(HrUserDimission hrUserDimission);

    /**
     * 修改离职
     * 
     * @param hrUserDimission 离职
     * @return 结果
     */
    public int updateHrUserDimission(HrUserDimission hrUserDimission);

    /**
     * 批量删除离职
     * 
     * @param ids 需要删除的离职主键集合
     * @return 结果
     */
    public int deleteHrUserDimissionByIds(String[] ids);

    /**
     * 删除离职信息
     * 
     * @param id 离职主键
     * @return 结果
     */
    public int deleteHrUserDimissionById(String id);
}
