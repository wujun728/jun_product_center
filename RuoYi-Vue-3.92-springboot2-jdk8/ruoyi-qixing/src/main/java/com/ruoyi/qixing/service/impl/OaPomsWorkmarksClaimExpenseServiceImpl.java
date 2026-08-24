package com.ruoyi.qixing.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.qixing.mapper.OaPomsWorkmarksClaimExpenseMapper;
import com.ruoyi.qixing.domain.OaPomsWorkmarksClaimExpense;
import com.ruoyi.qixing.service.IOaPomsWorkmarksClaimExpenseService;

/**
 * 费用报销Service业务层处理
 *
 * @author template
 * @date 2026-06-11
 */
@Service
public class OaPomsWorkmarksClaimExpenseServiceImpl implements IOaPomsWorkmarksClaimExpenseService
{
    @Autowired
    private OaPomsWorkmarksClaimExpenseMapper oaPomsWorkmarksClaimExpenseMapper;

    /**
     * 查询费用报销
     *
     * @param id 费用报销主键
     * @return 费用报销
     */
    @Override
    public OaPomsWorkmarksClaimExpense selectOaPomsWorkmarksClaimExpenseById(String id)
    {
        return oaPomsWorkmarksClaimExpenseMapper.selectOaPomsWorkmarksClaimExpenseById(id);
    }

    /**
     * 查询费用报销列表
     *
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 费用报销
     */
    @Override
    public List<OaPomsWorkmarksClaimExpense> selectOaPomsWorkmarksClaimExpenseList(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        return oaPomsWorkmarksClaimExpenseMapper.selectOaPomsWorkmarksClaimExpenseList(oaPomsWorkmarksClaimExpense);
    }

    /**
     * 新增费用报销
     *
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 结果
     */
    @Override
    public int insertOaPomsWorkmarksClaimExpense(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {if (oaPomsWorkmarksClaimExpense.getId() == null || oaPomsWorkmarksClaimExpense.getId().length() == 0)
        {
            oaPomsWorkmarksClaimExpense.setId(String.valueOf(IdWorker.getId()));
        }

        oaPomsWorkmarksClaimExpense.setCreateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksClaimExpenseMapper.insertOaPomsWorkmarksClaimExpense(oaPomsWorkmarksClaimExpense);
    }

    /**
     * 修改费用报销
     *
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 结果
     */
    @Override
    public int updateOaPomsWorkmarksClaimExpense(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense)
    {
        oaPomsWorkmarksClaimExpense.setUpdateTime(DateUtils.getNowDate());
        return oaPomsWorkmarksClaimExpenseMapper.updateOaPomsWorkmarksClaimExpense(oaPomsWorkmarksClaimExpense);
    }

    /**
     * 批量删除费用报销
     *
     * @param ids 需要删除的费用报销主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksClaimExpenseByIds(String[] ids)
    {
        return oaPomsWorkmarksClaimExpenseMapper.deleteOaPomsWorkmarksClaimExpenseByIds(ids);
    }

    /**
     * 删除费用报销信息
     *
     * @param id 费用报销主键
     * @return 结果
     */
    @Override
    public int deleteOaPomsWorkmarksClaimExpenseById(String id)
    {
        return oaPomsWorkmarksClaimExpenseMapper.deleteOaPomsWorkmarksClaimExpenseById(id);
    }
}
