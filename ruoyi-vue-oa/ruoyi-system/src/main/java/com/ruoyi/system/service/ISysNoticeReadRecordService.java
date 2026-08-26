package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SysNoticeReadRecord;

/**
 * 公告已读记录Service接口
 * 
 * @author ruoyi
 * @date 2025-05-13
 */
public interface ISysNoticeReadRecordService {
    /**
     * 查询公告已读记录
     * 
     * @param id 公告已读记录主键
     * @return 公告已读记录
     */
    public SysNoticeReadRecord selectSysNoticeReadRecordById(String id);

    /**
     * 查询公告已读记录列表
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 公告已读记录集合
     */
    public List<SysNoticeReadRecord> selectSysNoticeReadRecordList(SysNoticeReadRecord sysNoticeReadRecord);

    /**
     * 查询用户已读记录
     * @return
     */
    public List<SysNoticeReadRecord> listUserRecords(String userId, List<String> noticeIds);

    /**
     * 新增公告已读记录
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 结果
     */
    public int insertSysNoticeReadRecord(SysNoticeReadRecord sysNoticeReadRecord);

    /**
     * 修改公告已读记录
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 结果
     */
    public int updateSysNoticeReadRecord(SysNoticeReadRecord sysNoticeReadRecord);

    /**
     * 批量删除公告已读记录
     * 
     * @param ids 需要删除的公告已读记录主键集合
     * @return 结果
     */
    public int deleteSysNoticeReadRecordByIds(String[] ids);

    /**
     * 删除公告已读记录信息
     * 
     * @param id 公告已读记录主键
     * @return 结果
     */
    public int deleteSysNoticeReadRecordById(String id);

    /**
     * 已读公告
     * @param noticeId
     */
    public void readNotice(String noticeId);
}
