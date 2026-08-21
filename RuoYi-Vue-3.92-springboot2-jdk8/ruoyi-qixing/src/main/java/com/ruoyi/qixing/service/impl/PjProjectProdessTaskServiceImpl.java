package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectProdessTaskMapper;
import com.ruoyi.qixing.domain.PjProjectProdessTask;
import com.ruoyi.qixing.service.IPjProjectProdessTaskService;

/**
 * 项目进度与任务(WBS)Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectProdessTaskServiceImpl implements IPjProjectProdessTaskService
{
    @Autowired
    private PjProjectProdessTaskMapper pjProjectProdessTaskMapper;

    /**
     * 查询项目进度与任务(WBS)
     *
     * @param id 项目进度与任务(WBS)主键
     * @return 项目进度与任务(WBS)
     */
    @Override
    public PjProjectProdessTask selectPjProjectProdessTaskById(String id)
    {
        return pjProjectProdessTaskMapper.selectPjProjectProdessTaskById(id);
    }

    /**
     * 查询项目进度与任务(WBS)列表
     *
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 项目进度与任务(WBS)
     */
    @Override
    public List<PjProjectProdessTask> selectPjProjectProdessTaskList(PjProjectProdessTask pjProjectProdessTask)
    {
        return pjProjectProdessTaskMapper.selectPjProjectProdessTaskList(pjProjectProdessTask);
    }

    /**
     * 新增项目进度与任务(WBS)
     *
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 结果
     */
    @Override
    public int insertPjProjectProdessTask(PjProjectProdessTask pjProjectProdessTask)
    {
        pjProjectProdessTask.setCreateTime(DateUtils.getNowDate());
        return pjProjectProdessTaskMapper.insertPjProjectProdessTask(pjProjectProdessTask);
    }

    /**
     * 修改项目进度与任务(WBS)
     *
     * @param pjProjectProdessTask 项目进度与任务(WBS)
     * @return 结果
     */
    @Override
    public int updatePjProjectProdessTask(PjProjectProdessTask pjProjectProdessTask)
    {
        pjProjectProdessTask.setUpdateTime(DateUtils.getNowDate());
        return pjProjectProdessTaskMapper.updatePjProjectProdessTask(pjProjectProdessTask);
    }

    /**
     * 批量删除项目进度与任务(WBS)
     *
     * @param ids 需要删除的项目进度与任务(WBS)主键
     * @return 结果
     */
    @Override
    public int deletePjProjectProdessTaskByIds(String[] ids)
    {
        return pjProjectProdessTaskMapper.deletePjProjectProdessTaskByIds(ids);
    }

    /**
     * 删除项目进度与任务(WBS)信息
     *
     * @param id 项目进度与任务(WBS)主键
     * @return 结果
     */
    @Override
    public int deletePjProjectProdessTaskById(String id)
    {
        return pjProjectProdessTaskMapper.deletePjProjectProdessTaskById(id);
    }
}
