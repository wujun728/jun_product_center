package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectRecheckMapper;
import com.ruoyi.qixing.domain.PjProjectRecheck;
import com.ruoyi.qixing.service.IPjProjectRecheckService;

/**
 * 项目复核Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectRecheckServiceImpl implements IPjProjectRecheckService
{
    @Autowired
    private PjProjectRecheckMapper pjProjectRecheckMapper;

    /**
     * 查询项目复核
     *
     * @param id 项目复核主键
     * @return 项目复核
     */
    @Override
    public PjProjectRecheck selectPjProjectRecheckById(String id)
    {
        return pjProjectRecheckMapper.selectPjProjectRecheckById(id);
    }

    /**
     * 查询项目复核列表
     *
     * @param pjProjectRecheck 项目复核
     * @return 项目复核
     */
    @Override
    public List<PjProjectRecheck> selectPjProjectRecheckList(PjProjectRecheck pjProjectRecheck)
    {
        return pjProjectRecheckMapper.selectPjProjectRecheckList(pjProjectRecheck);
    }

    /**
     * 新增项目复核
     *
     * @param pjProjectRecheck 项目复核
     * @return 结果
     */
    @Override
    public int insertPjProjectRecheck(PjProjectRecheck pjProjectRecheck)
    {
        pjProjectRecheck.setCreateTime(DateUtils.getNowDate());
        return pjProjectRecheckMapper.insertPjProjectRecheck(pjProjectRecheck);
    }

    /**
     * 修改项目复核
     *
     * @param pjProjectRecheck 项目复核
     * @return 结果
     */
    @Override
    public int updatePjProjectRecheck(PjProjectRecheck pjProjectRecheck)
    {
        pjProjectRecheck.setUpdateTime(DateUtils.getNowDate());
        return pjProjectRecheckMapper.updatePjProjectRecheck(pjProjectRecheck);
    }

    /**
     * 批量删除项目复核
     *
     * @param ids 需要删除的项目复核主键
     * @return 结果
     */
    @Override
    public int deletePjProjectRecheckByIds(String[] ids)
    {
        return pjProjectRecheckMapper.deletePjProjectRecheckByIds(ids);
    }

    /**
     * 删除项目复核信息
     *
     * @param id 项目复核主键
     * @return 结果
     */
    @Override
    public int deletePjProjectRecheckById(String id)
    {
        return pjProjectRecheckMapper.deletePjProjectRecheckById(id);
    }
}
