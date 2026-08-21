package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaOfficeCount;

/**
 * 办公用品申领申购Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaOfficeCountMapper 
{
    /**
     * 查询办公用品申领申购
     * 
     * @param id 办公用品申领申购主键
     * @return 办公用品申领申购
     */
    public OaOfficeCount selectOaOfficeCountById(String id);

    /**
     * 查询办公用品申领申购列表
     * 
     * @param oaOfficeCount 办公用品申领申购
     * @return 办公用品申领申购集合
     */
    public List<OaOfficeCount> selectOaOfficeCountList(OaOfficeCount oaOfficeCount);

    /**
     * 新增办公用品申领申购
     * 
     * @param oaOfficeCount 办公用品申领申购
     * @return 结果
     */
    public int insertOaOfficeCount(OaOfficeCount oaOfficeCount);

    /**
     * 修改办公用品申领申购
     * 
     * @param oaOfficeCount 办公用品申领申购
     * @return 结果
     */
    public int updateOaOfficeCount(OaOfficeCount oaOfficeCount);

    /**
     * 删除办公用品申领申购
     * 
     * @param id 办公用品申领申购主键
     * @return 结果
     */
    public int deleteOaOfficeCountById(String id);

    /**
     * 批量删除办公用品申领申购
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaOfficeCountByIds(String[] ids);
}
