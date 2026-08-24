package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectMapper;
import com.ruoyi.qixing.domain.PjProject;
import com.ruoyi.qixing.service.IPjProjectService;

/**
 * 项目信息Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectServiceImpl implements IPjProjectService
{
    @Autowired
    private PjProjectMapper pjProjectMapper;

    /**
     * 查询项目信息
     *
     * @param id 项目信息主键
     * @return 项目信息
     */
    @Override
    public PjProject selectPjProjectById(String id)
    {
        return pjProjectMapper.selectPjProjectById(id);
    }

    /**
     * 查询项目信息列表
     *
     * @param pjProject 项目信息
     * @return 项目信息
     */
    @Override
    public List<PjProject> selectPjProjectList(PjProject pjProject)
    {
        return pjProjectMapper.selectPjProjectList(pjProject);
    }

    /**
     * 新增项目信息
     *
     * @param pjProject 项目信息
     * @return 结果
     */
    @Override
    public int insertPjProject(PjProject pjProject)
    {if (pjProject.getId() == null || pjProject.getId().length() == 0)
        {
            pjProject.setId(String.valueOf(IdWorker.getId()));
        }

        pjProject.setCreateTime(DateUtils.getNowDate());
        return pjProjectMapper.insertPjProject(pjProject);
    }

    /**
     * 修改项目信息
     *
     * @param pjProject 项目信息
     * @return 结果
     */
    @Override
    public int updatePjProject(PjProject pjProject)
    {
        pjProject.setUpdateTime(DateUtils.getNowDate());
        return pjProjectMapper.updatePjProject(pjProject);
    }

    /**
     * 批量删除项目信息
     *
     * @param ids 需要删除的项目信息主键
     * @return 结果
     */
    @Override
    public int deletePjProjectByIds(String[] ids)
    {
        return pjProjectMapper.deletePjProjectByIds(ids);
    }

    /**
     * 删除项目信息信息
     *
     * @param id 项目信息主键
     * @return 结果
     */
    @Override
    public int deletePjProjectById(String id)
    {
        return pjProjectMapper.deletePjProjectById(id);
    }
}
