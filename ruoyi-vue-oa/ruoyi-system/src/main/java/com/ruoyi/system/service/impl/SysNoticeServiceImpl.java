package com.ruoyi.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.system.domain.SysNoticeReadRecord;
import com.ruoyi.system.domain.dto.SysNoticeDTO;
import com.ruoyi.system.mapper.SystemCopyMapper;
import com.ruoyi.system.service.ISysNoticeReadRecordService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.mapper.SysNoticeMapper;
import com.ruoyi.system.service.ISysNoticeService;

/**
 * 公告 服务层实现
 *
 * @author ruoyi
 */
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {
    @Autowired
    private SysNoticeMapper noticeMapper;
    @Autowired
    private ISysNoticeReadRecordService noticeReadRecordService;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(String noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public List<SysNoticeDTO> selectUserHomeNoticeList(SysNotice notice) {
        notice.setCreateId(SecurityUtils.getUserId());
        notice.setDeptId(SecurityUtils.getDeptId());
        Map<String, Object> params = new HashMap<>();
        params.put("now", DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD));
        notice.setParams(params);
        List<SysNotice> list = noticeMapper.selectUserHomeNoticeList(notice);
        // 已读未读状态
        List<SysNoticeDTO> data = SystemCopyMapper.INSTANCE.listSysNoticeDTO(list);
        if (CollectionUtils.isNotEmpty(data)) {
            List<String> noticeIds = data.stream().map(SysNoticeDTO::getNoticeId).collect(Collectors.toList());
            List<SysNoticeReadRecord> readList = noticeReadRecordService.listUserRecords(SecurityUtils.getUserId(), noticeIds);
            Set<String> readNoticeIds = Optional.ofNullable(readList).orElse(Collections.emptyList()).stream()
                    .map(SysNoticeReadRecord::getNoticeId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            data.forEach(n -> {
                String noticeId = n.getNoticeId();
                n.setReadFlag(readNoticeIds.contains(noticeId) ? Constants.YES_VALUE : Constants.NO_VALUE);
            });
        }
        return data;
    }

    @Override
    public List<SysNotice> selectUserNoticeList(SysNotice notice) {
        notice.setCreateId(SecurityUtils.getUserId());
        notice.setDeptId(notice.getDeptId() == null ? SecurityUtils.getDeptId() : notice.getDeptId());
        Map<String, Object> params = new HashMap<>();
        params.put("now", DateUtils.dateTimeNow(DateUtils.YYYY_MM_DD));
        notice.setParams(params);
        return noticeMapper.selectUserNoticeList(notice);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice) {
        notice.setNoticeId(IdUtils.fastSimpleUUID());
        notice.setCreateId(SecurityUtils.getUserId());
        notice.setDeptId(notice.getDeptId() == null ? SecurityUtils.getDeptId() : notice.getDeptId());
        notice.setCreateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice) {
        notice.setUpdateId(SecurityUtils.getUserId());
        notice.setUpdateTime(DateUtils.getNowDate());
        notice.setUpdateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(String noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(String[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    @Override
    public int changeStatus(SysNotice notice) {
        return noticeMapper.changeStatus(notice);
    }

    @Override
    public void readNotice(String noticeId) {
        if (StringUtils.isBlank(noticeId)) {
            return;
        }
        noticeReadRecordService.readNotice(noticeId);
    }
}
