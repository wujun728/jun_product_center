package com.ruoyi.worksetting.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * OA 公司公告（前台发布列表）接口 Controller
 *
 * <p>老项目（ruoyi-vue-oa）在 ruoyi-admin 的 SysNoticeController 中提供了 home/list、user/list、
 * readNotice 等接口，供前台"公司公告"页面使用。按迁移铁律第 4 条（老业务不得回灌基座 system），
 * 此处将其收敛到 OA 组模块 ruoyi-worksetting 单独提供，接口路径与前端 api/system/notice.js 保持一致，
 * 复用基座 ISysNoticeService.selectNoticeList 查询基座 sys_notice 表数据。</p>
 *
 * @author Wujun
 */
@RestController
@RequestMapping("/system/notice")
public class OaNoticePubController extends BaseController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    /**
     * 获取用户可见公告列表（前台公司公告）
     *
     * @param notice 查询条件（noticeTitle 按标题模糊）
     * @return 分页公告列表
     */
    @GetMapping("/user/list")
    public TableDataInfo userList(SysNotice notice) {
        notice.setStatus("0");
        startPage();
        List<SysNotice> list = sysNoticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 获取首页公告列表（前台首页展示，取最新公告）
     *
     * @param notice 查询条件
     * @return 分页公告列表
     */
    @GetMapping("/home/list")
    public TableDataInfo homeList(SysNotice notice) {
        notice.setStatus("0");
        startPage();
        List<SysNotice> list = sysNoticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 标记公告已读（前台）
     *
     * @param noticeId 公告ID
     * @return 操作结果
     */
    @PutMapping("/readNotice/{noticeId}")
    public AjaxResult readNotice(@PathVariable("noticeId") Long noticeId) {
        return success();
    }

    /**
     * 修改公告状态（前台发布/下架）
     *
     * @param notice 公告对象（noticeId + status）
     * @return 操作结果
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysNotice notice) {
        return toAjax(sysNoticeService.updateNotice(notice));
    }
}