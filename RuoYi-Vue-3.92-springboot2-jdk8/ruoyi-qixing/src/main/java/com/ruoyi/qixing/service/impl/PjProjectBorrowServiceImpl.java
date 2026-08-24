package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectBorrowMapper;
import com.ruoyi.qixing.domain.PjProjectBorrow;
import com.ruoyi.qixing.service.IPjProjectBorrowService;

/**
 * 项目借阅Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectBorrowServiceImpl implements IPjProjectBorrowService
{
    @Autowired
    private PjProjectBorrowMapper pjProjectBorrowMapper;

    /**
     * 查询项目借阅
     *
     * @param id 项目借阅主键
     * @return 项目借阅
     */
    @Override
    public PjProjectBorrow selectPjProjectBorrowById(String id)
    {
        return pjProjectBorrowMapper.selectPjProjectBorrowById(id);
    }

    /**
     * 查询项目借阅列表
     *
     * @param pjProjectBorrow 项目借阅
     * @return 项目借阅
     */
    @Override
    public List<PjProjectBorrow> selectPjProjectBorrowList(PjProjectBorrow pjProjectBorrow)
    {
        return pjProjectBorrowMapper.selectPjProjectBorrowList(pjProjectBorrow);
    }

    /**
     * 新增项目借阅
     *
     * @param pjProjectBorrow 项目借阅
     * @return 结果
     */
    @Override
    public int insertPjProjectBorrow(PjProjectBorrow pjProjectBorrow)
    {if (pjProjectBorrow.getId() == null || pjProjectBorrow.getId().length() == 0)
        {
            pjProjectBorrow.setId(String.valueOf(IdWorker.getId()));
        }

        pjProjectBorrow.setCreateTime(DateUtils.getNowDate());
        return pjProjectBorrowMapper.insertPjProjectBorrow(pjProjectBorrow);
    }

    /**
     * 修改项目借阅
     *
     * @param pjProjectBorrow 项目借阅
     * @return 结果
     */
    @Override
    public int updatePjProjectBorrow(PjProjectBorrow pjProjectBorrow)
    {
        pjProjectBorrow.setUpdateTime(DateUtils.getNowDate());
        return pjProjectBorrowMapper.updatePjProjectBorrow(pjProjectBorrow);
    }

    /**
     * 批量删除项目借阅
     *
     * @param ids 需要删除的项目借阅主键
     * @return 结果
     */
    @Override
    public int deletePjProjectBorrowByIds(String[] ids)
    {
        return pjProjectBorrowMapper.deletePjProjectBorrowByIds(ids);
    }

    /**
     * 删除项目借阅信息
     *
     * @param id 项目借阅主键
     * @return 结果
     */
    @Override
    public int deletePjProjectBorrowById(String id)
    {
        return pjProjectBorrowMapper.deletePjProjectBorrowById(id);
    }
}
