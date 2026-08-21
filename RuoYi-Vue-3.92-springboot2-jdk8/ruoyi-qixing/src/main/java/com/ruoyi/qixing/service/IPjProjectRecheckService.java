package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectRecheck;

/**
 * 项目复核Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectRecheckService 
{
    /**
     * 查询项目复核
     * 
     * @param id 项目复核主键
     * @return 项目复核
     */
    public PjProjectRecheck selectPjProjectRecheckById(String id);

    /**
     * 查询项目复核列表
     * 
     * @param pjProjectRecheck 项目复核
     * @return 项目复核集合
     */
    public List<PjProjectRecheck> selectPjProjectRecheckList(PjProjectRecheck pjProjectRecheck);

    /**
     * 新增项目复核
     * 
     * @param pjProjectRecheck 项目复核
     * @return 结果
     */
    public int insertPjProjectRecheck(PjProjectRecheck pjProjectRecheck);

    /**
     * 修改项目复核
     * 
     * @param pjProjectRecheck 项目复核
     * @return 结果
     */
    public int updatePjProjectRecheck(PjProjectRecheck pjProjectRecheck);

    /**
     * 批量删除项目复核
     * 
     * @param ids 需要删除的项目复核主键集合
     * @return 结果
     */
    public int deletePjProjectRecheckByIds(String[] ids);

    /**
     * 删除项目复核信息
     * 
     * @param id 项目复核主键
     * @return 结果
     */
    public int deletePjProjectRecheckById(String id);
}
