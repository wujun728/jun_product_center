package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaLawInfo;

/**
 * 政策法规Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaLawInfoMapper 
{
    /**
     * 查询政策法规
     * 
     * @param id 政策法规主键
     * @return 政策法规
     */
    public OaLawInfo selectOaLawInfoById(String id);

    /**
     * 查询政策法规列表
     * 
     * @param oaLawInfo 政策法规
     * @return 政策法规集合
     */
    public List<OaLawInfo> selectOaLawInfoList(OaLawInfo oaLawInfo);

    /**
     * 新增政策法规
     * 
     * @param oaLawInfo 政策法规
     * @return 结果
     */
    public int insertOaLawInfo(OaLawInfo oaLawInfo);

    /**
     * 修改政策法规
     * 
     * @param oaLawInfo 政策法规
     * @return 结果
     */
    public int updateOaLawInfo(OaLawInfo oaLawInfo);

    /**
     * 删除政策法规
     * 
     * @param id 政策法规主键
     * @return 结果
     */
    public int deleteOaLawInfoById(String id);

    /**
     * 批量删除政策法规
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaLawInfoByIds(String[] ids);
}
