package com.ruoyi.qixing.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.PjContractMapper;
import com.ruoyi.qixing.domain.PjContract;
import com.ruoyi.qixing.service.IPjContractService;

/**
 * 业务约定书Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class PjContractServiceImpl implements IPjContractService
{
    @Autowired
    private PjContractMapper pjContractMapper;

    /**
     * 查询业务约定书
     *
     * @param id 业务约定书主键
     * @return 业务约定书
     */
    @Override
    public PjContract selectPjContractById(String id)
    {
        return pjContractMapper.selectPjContractById(id);
    }

    /**
     * 查询业务约定书列表
     *
     * @param pjContract 业务约定书
     * @return 业务约定书
     */
    @Override
    public List<PjContract> selectPjContractList(PjContract pjContract)
    {
        return pjContractMapper.selectPjContractList(pjContract);
    }

    /**
     * 新增业务约定书
     *
     * @param pjContract 业务约定书
     * @return 结果
     */
    @Override
    public int insertPjContract(PjContract pjContract)
    {
        pjContract.setCreateTime(DateUtils.getNowDate());
        return pjContractMapper.insertPjContract(pjContract);
    }

    /**
     * 修改业务约定书
     *
     * @param pjContract 业务约定书
     * @return 结果
     */
    @Override
    public int updatePjContract(PjContract pjContract)
    {
        pjContract.setUpdateTime(DateUtils.getNowDate());
        return pjContractMapper.updatePjContract(pjContract);
    }

    /**
     * 批量删除业务约定书
     *
     * @param ids 需要删除的业务约定书主键
     * @return 结果
     */
    @Override
    public int deletePjContractByIds(String[] ids)
    {
        return pjContractMapper.deletePjContractByIds(ids);
    }

    /**
     * 删除业务约定书信息
     *
     * @param id 业务约定书主键
     * @return 结果
     */
    @Override
    public int deletePjContractById(String id)
    {
        return pjContractMapper.deletePjContractById(id);
    }
}
