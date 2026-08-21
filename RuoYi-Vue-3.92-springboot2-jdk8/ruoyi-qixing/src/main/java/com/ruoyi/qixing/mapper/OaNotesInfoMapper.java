package com.ruoyi.qixing.mapper;

import java.util.List;
import com.ruoyi.qixing.domain.OaNotesInfo;

/**
 * 公告通知Mapper接口
 * 
 * @author template
 * @date 2026-06-11
 */
public interface OaNotesInfoMapper 
{
    /**
     * 查询公告通知
     * 
     * @param id 公告通知主键
     * @return 公告通知
     */
    public OaNotesInfo selectOaNotesInfoById(String id);

    /**
     * 查询公告通知列表
     * 
     * @param oaNotesInfo 公告通知
     * @return 公告通知集合
     */
    public List<OaNotesInfo> selectOaNotesInfoList(OaNotesInfo oaNotesInfo);

    /**
     * 新增公告通知
     * 
     * @param oaNotesInfo 公告通知
     * @return 结果
     */
    public int insertOaNotesInfo(OaNotesInfo oaNotesInfo);

    /**
     * 修改公告通知
     * 
     * @param oaNotesInfo 公告通知
     * @return 结果
     */
    public int updateOaNotesInfo(OaNotesInfo oaNotesInfo);

    /**
     * 删除公告通知
     * 
     * @param id 公告通知主键
     * @return 结果
     */
    public int deleteOaNotesInfoById(String id);

    /**
     * 批量删除公告通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOaNotesInfoByIds(String[] ids);
}
