package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaPomsWorkmarksClaimExpense;

/**
 * 费用报销Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaPomsWorkmarksClaimExpenseMapper 
{
    /**
     * 查询费用报销
     * 
     * @param id 费用报销主键
     * @return 费用报销
     */
    public OaPomsWorkmarksClaimExpense selectOaPomsWorkmarksClaimExpenseById(String id);

    /**
     * 查询费用报销列表
     * 
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 费用报销集合
     */
    public List<OaPomsWorkmarksClaimExpense> selectOaPomsWorkmarksClaimExpenseList(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense);

    /**
     * 新增费用报销
     * 
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 结果
     */
    public int insertOaPomsWorkmarksClaimExpense(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense);

    /**
     * 修改费用报销
     * 
     * @param oaPomsWorkmarksClaimExpense 费用报销
     * @return 结果
     */
    public int updateOaPomsWorkmarksClaimExpense(OaPomsWorkmarksClaimExpense oaPomsWorkmarksClaimExpense);

    /**
     * 删除费用报销
     * 
     * @param id 费用报销主键
     * @return 结果
     */
    public int deleteOaPomsWorkmarksClaimExpenseById(String id);

    /**
     * 批量删除费用报销
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaPomsWorkmarksClaimExpenseByIds(String[] ids);
}
