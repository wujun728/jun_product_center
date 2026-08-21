package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectDraft;

/**
 * 项目底稿Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectDraftService 
{
    /**
     * 查询项目底稿
     * 
     * @param id 项目底稿主键
     * @return 项目底稿
     */
    public PjProjectDraft selectPjProjectDraftById(String id);

    /**
     * 查询项目底稿列表
     * 
     * @param pjProjectDraft 项目底稿
     * @return 项目底稿集合
     */
    public List<PjProjectDraft> selectPjProjectDraftList(PjProjectDraft pjProjectDraft);

    /**
     * 新增项目底稿
     * 
     * @param pjProjectDraft 项目底稿
     * @return 结果
     */
    public int insertPjProjectDraft(PjProjectDraft pjProjectDraft);

    /**
     * 修改项目底稿
     * 
     * @param pjProjectDraft 项目底稿
     * @return 结果
     */
    public int updatePjProjectDraft(PjProjectDraft pjProjectDraft);

    /**
     * 批量删除项目底稿
     * 
     * @param ids 需要删除的项目底稿主键集合
     * @return 结果
     */
    public int deletePjProjectDraftByIds(String[] ids);

    /**
     * 删除项目底稿信息
     * 
     * @param id 项目底稿主键
     * @return 结果
     */
    public int deletePjProjectDraftById(String id);
}
