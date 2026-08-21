package com.ruoyi.qixing.service;

import java.util.List;
import com.ruoyi.qixing.domain.OaLearnInfo;

/**
 * 培训学习Service接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface IOaLearnInfoService 
{
    /**
     * 查询培训学习
     * 
     * @param id 培训学习主键
     * @return 培训学习
     */
    public OaLearnInfo selectOaLearnInfoById(String id);

    /**
     * 查询培训学习列表
     * 
     * @param oaLearnInfo 培训学习
     * @return 培训学习集合
     */
    public List<OaLearnInfo> selectOaLearnInfoList(OaLearnInfo oaLearnInfo);

    /**
     * 新增培训学习
     * 
     * @param oaLearnInfo 培训学习
     * @return 结果
     */
    public int insertOaLearnInfo(OaLearnInfo oaLearnInfo);

    /**
     * 修改培训学习
     * 
     * @param oaLearnInfo 培训学习
     * @return 结果
     */
    public int updateOaLearnInfo(OaLearnInfo oaLearnInfo);

    /**
     * 批量删除培训学习
     * 
     * @param ids 需要删除的培训学习主键集合
     * @return 结果
     */
    public int deleteOaLearnInfoByIds(String[] ids);

    /**
     * 删除培训学习信息
     * 
     * @param id 培训学习主键
     * @return 结果
     */
    public int deleteOaLearnInfoById(String id);
}
