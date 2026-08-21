package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.OaOfficeCount2;

/**
 * 办公用品申领申购Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IOaOfficeCount2Service 
{
    /**
     * 查询办公用品申领申购
     * 
     * @param id 办公用品申领申购主键
     * @return 办公用品申领申购
     */
    public OaOfficeCount2 selectOaOfficeCount2ById(String id);

    /**
     * 查询办公用品申领申购列表
     * 
     * @param oaOfficeCount2 办公用品申领申购
     * @return 办公用品申领申购集合
     */
    public List<OaOfficeCount2> selectOaOfficeCount2List(OaOfficeCount2 oaOfficeCount2);

    /**
     * 新增办公用品申领申购
     * 
     * @param oaOfficeCount2 办公用品申领申购
     * @return 结果
     */
    public int insertOaOfficeCount2(OaOfficeCount2 oaOfficeCount2);

    /**
     * 修改办公用品申领申购
     * 
     * @param oaOfficeCount2 办公用品申领申购
     * @return 结果
     */
    public int updateOaOfficeCount2(OaOfficeCount2 oaOfficeCount2);

    /**
     * 批量删除办公用品申领申购
     * 
     * @param ids 需要删除的办公用品申领申购主键集合
     * @return 结果
     */
    public int deleteOaOfficeCount2ByIds(String[] ids);

    /**
     * 删除办公用品申领申购信息
     * 
     * @param id 办公用品申领申购主键
     * @return 结果
     */
    public int deleteOaOfficeCount2ById(String id);
}
