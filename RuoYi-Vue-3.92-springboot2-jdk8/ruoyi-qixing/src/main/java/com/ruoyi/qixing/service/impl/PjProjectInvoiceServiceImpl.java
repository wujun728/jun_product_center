package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjProjectInvoiceMapper;
import com.ruoyi.qixing.domain.PjProjectInvoice;
import com.ruoyi.qixing.service.IPjProjectInvoiceService;

/**
 * 项目开票Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjProjectInvoiceServiceImpl implements IPjProjectInvoiceService
{
    @Autowired
    private PjProjectInvoiceMapper pjProjectInvoiceMapper;

    /**
     * 查询项目开票
     *
     * @param id 项目开票主键
     * @return 项目开票
     */
    @Override
    public PjProjectInvoice selectPjProjectInvoiceById(String id)
    {
        return pjProjectInvoiceMapper.selectPjProjectInvoiceById(id);
    }

    /**
     * 查询项目开票列表
     *
     * @param pjProjectInvoice 项目开票
     * @return 项目开票
     */
    @Override
    public List<PjProjectInvoice> selectPjProjectInvoiceList(PjProjectInvoice pjProjectInvoice)
    {
        return pjProjectInvoiceMapper.selectPjProjectInvoiceList(pjProjectInvoice);
    }

    /**
     * 新增项目开票
     *
     * @param pjProjectInvoice 项目开票
     * @return 结果
     */
    @Override
    public int insertPjProjectInvoice(PjProjectInvoice pjProjectInvoice)
    {if (pjProjectInvoice.getId() == null || pjProjectInvoice.getId().length() == 0)
        {
            pjProjectInvoice.setId(String.valueOf(IdWorker.getId()));
        }

        pjProjectInvoice.setCreateTime(DateUtils.getNowDate());
        return pjProjectInvoiceMapper.insertPjProjectInvoice(pjProjectInvoice);
    }

    /**
     * 修改项目开票
     *
     * @param pjProjectInvoice 项目开票
     * @return 结果
     */
    @Override
    public int updatePjProjectInvoice(PjProjectInvoice pjProjectInvoice)
    {
        pjProjectInvoice.setUpdateTime(DateUtils.getNowDate());
        return pjProjectInvoiceMapper.updatePjProjectInvoice(pjProjectInvoice);
    }

    /**
     * 批量删除项目开票
     *
     * @param ids 需要删除的项目开票主键
     * @return 结果
     */
    @Override
    public int deletePjProjectInvoiceByIds(String[] ids)
    {
        return pjProjectInvoiceMapper.deletePjProjectInvoiceByIds(ids);
    }

    /**
     * 删除项目开票信息
     *
     * @param id 项目开票主键
     * @return 结果
     */
    @Override
    public int deletePjProjectInvoiceById(String id)
    {
        return pjProjectInvoiceMapper.deletePjProjectInvoiceById(id);
    }
}
