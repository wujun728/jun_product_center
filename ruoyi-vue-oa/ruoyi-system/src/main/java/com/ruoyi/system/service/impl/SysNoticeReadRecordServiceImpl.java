package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.ruoyi.system.mapper.SysNoticeReadRecordMapper;
import com.ruoyi.system.domain.SysNoticeReadRecord;
import com.ruoyi.system.service.ISysNoticeReadRecordService;

/**
 * 公告已读记录Service业务层处理
 * 
 * @author wocurr.com
 */
@Slf4j
@Service
public class SysNoticeReadRecordServiceImpl implements ISysNoticeReadRecordService {
    @Autowired
    private SysNoticeReadRecordMapper sysNoticeReadRecordMapper;

    /**
     * 查询公告已读记录
     * 
     * @param id 公告已读记录主键
     * @return 公告已读记录
     */
    @Override
    public SysNoticeReadRecord selectSysNoticeReadRecordById(String id) {
        return sysNoticeReadRecordMapper.selectSysNoticeReadRecordById(id);
    }

    /**
     * 查询公告已读记录列表
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 公告已读记录
     */
    @Override
    public List<SysNoticeReadRecord> selectSysNoticeReadRecordList(SysNoticeReadRecord sysNoticeReadRecord) {
        return sysNoticeReadRecordMapper.selectSysNoticeReadRecordList(sysNoticeReadRecord);
    }

    /**
     * 批量查询用户已读公告
     *
     * @param userId
     * @param noticeIds
     * @return
     */
    @Override
    public List<SysNoticeReadRecord> listUserRecords(String userId, List<String> noticeIds) {
        return sysNoticeReadRecordMapper.listUserRecords(userId, noticeIds);
    }

    /**
     * 新增公告已读记录
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 结果
     */
    @Override
    public int insertSysNoticeReadRecord(SysNoticeReadRecord sysNoticeReadRecord) {
        sysNoticeReadRecord.setId(IdUtils.fastSimpleUUID());
        sysNoticeReadRecord.setCreateTime(DateUtils.getNowDate());
        return sysNoticeReadRecordMapper.insertSysNoticeReadRecord(sysNoticeReadRecord);
    }

    /**
     * 修改公告已读记录
     * 
     * @param sysNoticeReadRecord 公告已读记录
     * @return 结果
     */
    @Override
    public int updateSysNoticeReadRecord(SysNoticeReadRecord sysNoticeReadRecord) {
        return sysNoticeReadRecordMapper.updateSysNoticeReadRecord(sysNoticeReadRecord);
    }

    /**
     * 批量删除公告已读记录
     * 
     * @param ids 需要删除的公告已读记录主键
     * @return 结果
     */
    @Override
    public int deleteSysNoticeReadRecordByIds(String[] ids) {
        return sysNoticeReadRecordMapper.deleteSysNoticeReadRecordByIds(ids);
    }

    /**
     * 删除公告已读记录信息
     * 
     * @param id 公告已读记录主键
     * @return 结果
     */
    @Override
    public int deleteSysNoticeReadRecordById(String id) {
        return sysNoticeReadRecordMapper.deleteSysNoticeReadRecordById(id);
    }

    /**
     * 阅读公告
     *
     * @param noticeId
     */
    @Override
    public void readNotice(String noticeId) {
        SysNoticeReadRecord sysNoticeReadRecord = new SysNoticeReadRecord();
        sysNoticeReadRecord.setNoticeId(noticeId);
        sysNoticeReadRecord.setUserId(SecurityUtils.getUserId());
        insertSysNoticeReadRecord(sysNoticeReadRecord);
    }
}
