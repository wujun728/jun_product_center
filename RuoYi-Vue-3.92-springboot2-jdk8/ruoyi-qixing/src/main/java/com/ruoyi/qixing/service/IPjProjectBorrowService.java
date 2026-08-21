package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjProjectBorrow;

/**
 * 项目借阅Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjProjectBorrowService 
{
    /**
     * 查询项目借阅
     * 
     * @param id 项目借阅主键
     * @return 项目借阅
     */
    public PjProjectBorrow selectPjProjectBorrowById(String id);

    /**
     * 查询项目借阅列表
     * 
     * @param pjProjectBorrow 项目借阅
     * @return 项目借阅集合
     */
    public List<PjProjectBorrow> selectPjProjectBorrowList(PjProjectBorrow pjProjectBorrow);

    /**
     * 新增项目借阅
     * 
     * @param pjProjectBorrow 项目借阅
     * @return 结果
     */
    public int insertPjProjectBorrow(PjProjectBorrow pjProjectBorrow);

    /**
     * 修改项目借阅
     * 
     * @param pjProjectBorrow 项目借阅
     * @return 结果
     */
    public int updatePjProjectBorrow(PjProjectBorrow pjProjectBorrow);

    /**
     * 批量删除项目借阅
     * 
     * @param ids 需要删除的项目借阅主键集合
     * @return 结果
     */
    public int deletePjProjectBorrowByIds(String[] ids);

    /**
     * 删除项目借阅信息
     * 
     * @param id 项目借阅主键
     * @return 结果
     */
    public int deletePjProjectBorrowById(String id);
}
