package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.PjCustomer;

/**
 * 客户信息Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface PjCustomerMapper 
{
    /**
     * 查询客户信息
     * 
     * @param id 客户信息主键
     * @return 客户信息
     */
    public PjCustomer selectPjCustomerById(String id);

    /**
     * 查询客户信息列表
     * 
     * @param pjCustomer 客户信息
     * @return 客户信息集合
     */
    public List<PjCustomer> selectPjCustomerList(PjCustomer pjCustomer);

    /**
     * 新增客户信息
     * 
     * @param pjCustomer 客户信息
     * @return 结果
     */
    public int insertPjCustomer(PjCustomer pjCustomer);

    /**
     * 修改客户信息
     * 
     * @param pjCustomer 客户信息
     * @return 结果
     */
    public int updatePjCustomer(PjCustomer pjCustomer);

    /**
     * 删除客户信息
     * 
     * @param id 客户信息主键
     * @return 结果
     */
    public int deletePjCustomerById(String id);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePjCustomerByIds(String[] ids);
}
