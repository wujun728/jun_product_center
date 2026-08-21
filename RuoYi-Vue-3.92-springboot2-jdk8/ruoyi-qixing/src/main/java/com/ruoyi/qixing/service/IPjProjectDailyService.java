package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectDaily;

/**
 * 项目日报周报Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectDailyService 
{
    /**
     * 查询项目日报周报
     * 
     * @param id 项目日报周报主键
     * @return 项目日报周报
     */
    public PjProjectDaily selectPjProjectDailyById(String id);

    /**
     * 查询项目日报周报列表
     * 
     * @param pjProjectDaily 项目日报周报
     * @return 项目日报周报集合
     */
    public List<PjProjectDaily> selectPjProjectDailyList(PjProjectDaily pjProjectDaily);

    /**
     * 新增项目日报周报
     * 
     * @param pjProjectDaily 项目日报周报
     * @return 结果
     */
    public int insertPjProjectDaily(PjProjectDaily pjProjectDaily);

    /**
     * 修改项目日报周报
     * 
     * @param pjProjectDaily 项目日报周报
     * @return 结果
     */
    public int updatePjProjectDaily(PjProjectDaily pjProjectDaily);

    /**
     * 批量删除项目日报周报
     * 
     * @param ids 需要删除的项目日报周报主键集合
     * @return 结果
     */
    public int deletePjProjectDailyByIds(String[] ids);

    /**
     * 删除项目日报周报信息
     * 
     * @param id 项目日报周报主键
     * @return 结果
     */
    public int deletePjProjectDailyById(String id);
}
