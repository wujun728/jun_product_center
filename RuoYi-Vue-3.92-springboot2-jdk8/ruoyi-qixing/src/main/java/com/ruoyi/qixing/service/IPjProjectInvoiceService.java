package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectInvoice;

/**
 * 项目开票Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectInvoiceService 
{
    /**
     * 查询项目开票
     * 
     * @param id 项目开票主键
     * @return 项目开票
     */
    public PjProjectInvoice selectPjProjectInvoiceById(String id);

    /**
     * 查询项目开票列表
     * 
     * @param pjProjectInvoice 项目开票
     * @return 项目开票集合
     */
    public List<PjProjectInvoice> selectPjProjectInvoiceList(PjProjectInvoice pjProjectInvoice);

    /**
     * 新增项目开票
     * 
     * @param pjProjectInvoice 项目开票
     * @return 结果
     */
    public int insertPjProjectInvoice(PjProjectInvoice pjProjectInvoice);

    /**
     * 修改项目开票
     * 
     * @param pjProjectInvoice 项目开票
     * @return 结果
     */
    public int updatePjProjectInvoice(PjProjectInvoice pjProjectInvoice);

    /**
     * 批量删除项目开票
     * 
     * @param ids 需要删除的项目开票主键集合
     * @return 结果
     */
    public int deletePjProjectInvoiceByIds(String[] ids);

    /**
     * 删除项目开票信息
     * 
     * @param id 项目开票主键
     * @return 结果
     */
    public int deletePjProjectInvoiceById(String id);
}
