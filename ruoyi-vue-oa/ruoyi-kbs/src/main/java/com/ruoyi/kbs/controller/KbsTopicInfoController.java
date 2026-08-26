package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsTopicInfo;
import com.ruoyi.kbs.domain.qo.KbsTopicInfoQo;
import com.ruoyi.kbs.domain.vo.KbsTopicInfoVo;
import com.ruoyi.kbs.service.IKbsTopicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库主题Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/topic/info")
public class KbsTopicInfoController extends BaseController {
    @Autowired
    private IKbsTopicInfoService kbsTopicInfoService;

    /**
     * 查询知识库主题列表
     */
    @PreAuthorize("@ss.hasPermi('topic:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(KbsTopicInfo kbsTopicInfo) {
        startPage();
        List<KbsTopicInfoVo> list = kbsTopicInfoService.listKbsTopicInfo(kbsTopicInfo);
        return getDataTable(list);
    }

    /**
     * 获取知识库主题详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsTopicInfoService.getKbsTopicInfoVoById(id));
    }

    /**
     * 新增知识库主题
     */
    @Log(title = "知识库主题", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsTopicInfoVo kbsTopicInfoVo) {
        return toAjax(kbsTopicInfoService.saveKbsTopicInfo(kbsTopicInfoVo));
    }

    /**
     * 修改知识库主题
     */
    @Log(title = "知识库主题", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KbsTopicInfoVo kbsTopicInfoVo) {
        return toAjax(kbsTopicInfoService.updateKbsTopicInfoVo(kbsTopicInfoVo));
    }

    /**
     * 删除知识库主题
     */
    @Log(title = "知识库主题", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsTopicInfoService.softDeleteKbsTopicInfoByIds(ids));
    }

    /**
     * 获取知识库主题列表，按类别进行分组展示
     */
    @GetMapping(value = "/listByCategory")
    public AjaxResult listTopicGroupByCategory(KbsTopicInfoQo qo) {
        return success(kbsTopicInfoService.listTopicGroupByCategory(qo));
    }

    /**
     * 获取知识库主题所有详细信息
     */
    @GetMapping(value = "/allInfo/{id}")
    public AjaxResult getAllInfo(@PathVariable("id") String id) {
        return success(kbsTopicInfoService.getAllTopicInfo(id));
    }
}
