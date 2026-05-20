package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysNoticeReadRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 公告已读记录Mapper接口
 * 
 * @author wocurr.com
 */
public interface SysNoticeReadRecordMapper {
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

    public List<SysNoticeReadRecord> listUserRecords(@Param("userId")String userId, @Param("noticeIds") List<String> noticeIds);

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
     * 删除公告已读记录
     * 
     * @param id 公告已读记录主键
     * @return 结果
     */
    public int deleteSysNoticeReadRecordById(String id);

    /**
     * 批量删除公告已读记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysNoticeReadRecordByIds(String[] ids);
}
