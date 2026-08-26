package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.dto.SysNoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    /**
     * 获取通知公告列表
     */
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 获取首页公告
     */
    @GetMapping("/home/list")
    public TableDataInfo HomeList(SysNotice notice)
    {
        startPage();
        List<SysNoticeDTO> list = noticeService.selectUserHomeNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 获取用户公告
     */
    @GetMapping("/user/list")
    public TableDataInfo userList(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectUserNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable String noticeId)
    {
        return success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice)
    {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable String[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }

    /**
     * 修改公告状态
     * @param notice
     * @return
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysNotice notice) {
        return toAjax(noticeService.changeStatus(notice));
    }

    /**
     * 已读公告
     * @param noticeId
     * @return
     */
    @PutMapping("/readNotice/{noticeId}")
    public AjaxResult readNotice(@PathVariable("noticeId") String noticeId) {
        noticeService.readNotice(noticeId);
        return toAjax(1);
    }

}
