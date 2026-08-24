package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectDailyMapper;
import com.ruoyi.qixing.domain.PjProjectDaily;
import com.ruoyi.qixing.service.IPjProjectDailyService;

/**
 * 项目日报周报Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectDailyServiceImpl implements IPjProjectDailyService
{
    @Autowired
    private PjProjectDailyMapper pjProjectDailyMapper;

    /**
     * 查询项目日报周报
     *
     * @param id 项目日报周报主键
     * @return 项目日报周报
     */
    @Override
    public PjProjectDaily selectPjProjectDailyById(String id)
    {
        return pjProjectDailyMapper.selectPjProjectDailyById(id);
    }

    /**
     * 查询项目日报周报列表
     *
     * @param pjProjectDaily 项目日报周报
     * @return 项目日报周报
     */
    @Override
    public List<PjProjectDaily> selectPjProjectDailyList(PjProjectDaily pjProjectDaily)
    {
        return pjProjectDailyMapper.selectPjProjectDailyList(pjProjectDaily);
    }

    /**
     * 新增项目日报周报
     *
     * @param pjProjectDaily 项目日报周报
     * @return 结果
     */
    @Override
    public int insertPjProjectDaily(PjProjectDaily pjProjectDaily)
    {if (pjProjectDaily.getId() == null || pjProjectDaily.getId().length() == 0)
        {
            pjProjectDaily.setId(String.valueOf(IdWorker.getId()));
        }

        pjProjectDaily.setCreateTime(DateUtils.getNowDate());
        return pjProjectDailyMapper.insertPjProjectDaily(pjProjectDaily);
    }

    /**
     * 修改项目日报周报
     *
     * @param pjProjectDaily 项目日报周报
     * @return 结果
     */
    @Override
    public int updatePjProjectDaily(PjProjectDaily pjProjectDaily)
    {
        pjProjectDaily.setUpdateTime(DateUtils.getNowDate());
        return pjProjectDailyMapper.updatePjProjectDaily(pjProjectDaily);
    }

    /**
     * 批量删除项目日报周报
     *
     * @param ids 需要删除的项目日报周报主键
     * @return 结果
     */
    @Override
    public int deletePjProjectDailyByIds(String[] ids)
    {
        return pjProjectDailyMapper.deletePjProjectDailyByIds(ids);
    }

    /**
     * 删除项目日报周报信息
     *
     * @param id 项目日报周报主键
     * @return 结果
     */
    @Override
    public int deletePjProjectDailyById(String id)
    {
        return pjProjectDailyMapper.deletePjProjectDailyById(id);
    }
}
