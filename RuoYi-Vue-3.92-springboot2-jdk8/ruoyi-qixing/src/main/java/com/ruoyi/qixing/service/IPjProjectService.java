package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProject;

/**
 * 项目信息Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectService 
{
    /**
     * 查询项目信息
     * 
     * @param id 项目信息主键
     * @return 项目信息
     */
    public PjProject selectPjProjectById(String id);

    /**
     * 查询项目信息列表
     * 
     * @param pjProject 项目信息
     * @return 项目信息集合
     */
    public List<PjProject> selectPjProjectList(PjProject pjProject);

    /**
     * 新增项目信息
     * 
     * @param pjProject 项目信息
     * @return 结果
     */
    public int insertPjProject(PjProject pjProject);

    /**
     * 修改项目信息
     * 
     * @param pjProject 项目信息
     * @return 结果
     */
    public int updatePjProject(PjProject pjProject);

    /**
     * 批量删除项目信息
     * 
     * @param ids 需要删除的项目信息主键集合
     * @return 结果
     */
    public int deletePjProjectByIds(String[] ids);

    /**
     * 删除项目信息信息
     * 
     * @param id 项目信息主键
     * @return 结果
     */
    public int deletePjProjectById(String id);
}
