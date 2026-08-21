package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectProdessTask;

/**
 * 项目进度与任务(WBS)Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectProdessTaskService 
{
    /**
     * 查询项目进度与任务(WBS)
     * 
     * @param id 项目进度与任务(WBS)主键
     * @return 项目进度与任务(WBS)
     */
    public PjProjectProdessTask selectPjProjectProdessTaskById(String id);

    /**
     * 查询项目进度与任务(WBS)列表
     * 
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 项目进度与任务(WBS)集合
     */
    public List<PjProjectProdessTask> selectPjProjectProdessTaskList(PjProjectProdessTask pjProjectProdessTask);

    /**
     * 新增项目进度与任务(WBS)
     * 
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 结果
     */
    public int insertPjProjectProdessTask(PjProjectProdessTask pjProjectProdessTask);

    /**
     * 修改项目进度与任务(WBS)
     * 
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 结果
     */
    public int updatePjProjectProdessTask(PjProjectProdessTask pjProjectProdessTask);

    /**
     * 批量删除项目进度与任务(WBS)
     * 
     * @param ids 需要删除的项目进度与任务(WBS)主键集合
     * @return 结果
     */
    public int deletePjProjectProdessTaskByIds(String[] ids);

    /**
     * 删除项目进度与任务(WBS)信息
     * 
     * @param id 项目进度与任务(WBS)主键
     * @return 结果
     */
    public int deletePjProjectProdessTaskById(String id);
}
