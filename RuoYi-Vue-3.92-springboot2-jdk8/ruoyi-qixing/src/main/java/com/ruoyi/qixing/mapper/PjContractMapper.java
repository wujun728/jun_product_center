package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjContract;

/**
 * 业务约定书Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjContractMapper 
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
     * 删除业务约定书
     * 
     * @param id 业务约定书主键
     * @return 结果
     */
    public int deletePjContractById(String id);

    /**
     * 批量删除业务约定书
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjContractByIds(String[] ids);
}
