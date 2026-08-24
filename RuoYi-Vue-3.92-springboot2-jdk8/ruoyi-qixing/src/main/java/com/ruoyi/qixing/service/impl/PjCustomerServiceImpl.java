package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjCustomerMapper;
import com.ruoyi.qixing.domain.PjCustomer;
import com.ruoyi.qixing.service.IPjCustomerService;

/**
 * 客户信息Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjCustomerServiceImpl implements IPjCustomerService
{
    @Autowired
    private PjCustomerMapper pjCustomerMapper;

    /**
     * 查询客户信息
     *
     * @param id 客户信息主键
     * @return 客户信息
     */
    @Override
    public PjCustomer selectPjCustomerById(String id)
    {
        return pjCustomerMapper.selectPjCustomerById(id);
    }

    /**
     * 查询客户信息列表
     *
     * @param pjCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<PjCustomer> selectPjCustomerList(PjCustomer pjCustomer)
    {
        return pjCustomerMapper.selectPjCustomerList(pjCustomer);
    }

    /**
     * 新增客户信息
     *
     * @param pjCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertPjCustomer(PjCustomer pjCustomer)
    {
        // [MIG] 主键为空时生成雪花ID（老项目依赖 MyBatis-Plus 主键生成；迁到经典 MyBatis 后此处补齐）
        if (pjCustomer.getId() == null || pjCustomer.getId().length() == 0)
        {
            pjCustomer.setId(String.valueOf(IdWorker.getId()));
        }
        pjCustomer.setCreateTime(DateUtils.getNowDate());
        return pjCustomerMapper.insertPjCustomer(pjCustomer);
    }

    /**
     * 修改客户信息
     *
     * @param pjCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updatePjCustomer(PjCustomer pjCustomer)
    {
        pjCustomer.setUpdateTime(DateUtils.getNowDate());
        return pjCustomerMapper.updatePjCustomer(pjCustomer);
    }

    /**
     * 批量删除客户信息
     *
     * @param ids 需要删除的客户信息主键
     * @return 结果
     */
    @Override
    public int deletePjCustomerByIds(String[] ids)
    {
        return pjCustomerMapper.deletePjCustomerByIds(ids);
    }

    /**
     * 删除客户信息信息
     *
     * @param id 客户信息主键
     * @return 结果
     */
    @Override
    public int deletePjCustomerById(String id)
    {
        return pjCustomerMapper.deletePjCustomerById(id);
    }
}
