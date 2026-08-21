package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksPayroll;

/**
 * 工资审核发放Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaPomsWorkmarksPayrollMapper 
{
    /**
     * 查询工资审核发放
     * 
     * @param id 工资审核发放主键
     * @return 工资审核发放
     */
    public OaPomsWorkmarksPayroll selectOaPomsWorkmarksPayrollById(String id);

    /**
     * 查询工资审核发放列表
     * 
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 工资审核发放集合
     */
    public List<OaPomsWorkmarksPayroll> selectOaPomsWorkmarksPayrollList(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll);

    /**
     * 新增工资审核发放
     * 
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 结果
     */
    public int insertOaPomsWorkmarksPayroll(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll);

    /**
     * 修改工资审核发放
     * 
     * @param oaPomsWorkmarksPayroll 工资审核发放
     * @return 结果
     */
    public int updateOaPomsWorkmarksPayroll(OaPomsWorkmarksPayroll oaPomsWorkmarksPayroll);

    /**
     * 删除工资审核发放
     * 
     * @param id 工资审核发放主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksPayrollById(String id);

    /**
     * 批量删除工资审核发放
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksPayrollByIds(String[] ids);
}
