package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.PjContract;

/**
 * 业务约定书Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IPjContractService 
{
    /**
     * 查询业务约定书
     * 
     * @param id 业务约定书主键
     * @return 业务约定书
     */
    public PjContract selectPjContractById(String id);

    /**
     * 查询业务约定书列表
     * 
     * @param pjContract 业务约定书
     * @return 业务约定书集合
     */
    public List<PjContract> selectPjContractList(PjContract pjContract);

    /**
     * 新增业务约定书
     * 
     * @param pjContract 业务约定书
     * @return 结果
     */
    public int insertPjContract(PjContract pjContract);

    /**
     * 修改业务约定书
     * 
     * @param pjContract 业务约定书
     * @return 结果
     */
    public int updatePjContract(PjContract pjContract);

    /**
     * 批量删除业务约定书
     * 
     * @param ids 需要删除的业务约定书主键集合
     * @return 结果
     */
    public int deletePjContractByIds(String[] ids);

    /**
     * 删除业务约定书信息
     * 
     * @param id 业务约定书主键
     * @return 结果
     */
    public int deletePjContractById(String id);
}
