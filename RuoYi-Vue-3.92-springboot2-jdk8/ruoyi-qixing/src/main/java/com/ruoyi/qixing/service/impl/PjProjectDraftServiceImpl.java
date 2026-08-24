package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectDraftMapper;
import com.ruoyi.qixing.domain.PjProjectDraft;
import com.ruoyi.qixing.service.IPjProjectDraftService;

/**
 * 项目底稿Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectDraftServiceImpl implements IPjProjectDraftService
{
    @Autowired
    private PjProjectDraftMapper pjProjectDraftMapper;

    /**
     * 查询项目底稿
     *
     * @param id 项目底稿主键
     * @return 项目底稿
     */
    @Override
    public PjProjectDraft selectPjProjectDraftById(String id)
    {
        return pjProjectDraftMapper.selectPjProjectDraftById(id);
    }

    /**
     * 查询项目底稿列表
     *
     * @param pjProjectDraft 项目底稿
     * @return 项目底稿
     */
    @Override
    public List<PjProjectDraft> selectPjProjectDraftList(PjProjectDraft pjProjectDraft)
    {
        return pjProjectDraftMapper.selectPjProjectDraftList(pjProjectDraft);
    }

    /**
     * 新增项目底稿
     *
     * @param pjProjectDraft 项目底稿
     * @return 结果
     */
    @Override
    public int insertPjProjectDraft(PjProjectDraft pjProjectDraft)
    {if (pjProjectDraft.getId() == null || pjProjectDraft.getId().length() == 0)
        {
            pjProjectDraft.setId(String.valueOf(IdWorker.getId()));
        }

        pjProjectDraft.setCreateTime(DateUtils.getNowDate());
        return pjProjectDraftMapper.insertPjProjectDraft(pjProjectDraft);
    }

    /**
     * 修改项目底稿
     *
     * @param pjProjectDraft 项目底稿
     * @return 结果
     */
    @Override
    public int updatePjProjectDraft(PjProjectDraft pjProjectDraft)
    {
        pjProjectDraft.setUpdateTime(DateUtils.getNowDate());
        return pjProjectDraftMapper.updatePjProjectDraft(pjProjectDraft);
    }

    /**
     * 批量删除项目底稿
     *
     * @param ids 需要删除的项目底稿主键
     * @return 结果
     */
    @Override
    public int deletePjProjectDraftByIds(String[] ids)
    {
        return pjProjectDraftMapper.deletePjProjectDraftByIds(ids);
    }

    /**
     * 删除项目底稿信息
     *
     * @param id 项目底稿主键
     * @return 结果
     */
    @Override
    public int deletePjProjectDraftById(String id)
    {
        return pjProjectDraftMapper.deletePjProjectDraftById(id);
    }
}
