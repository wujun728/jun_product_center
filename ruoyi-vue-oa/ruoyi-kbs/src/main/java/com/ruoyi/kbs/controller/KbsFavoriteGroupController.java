package com.ruoyi.kbs.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.kbs.domain.KbsFavoriteGroup;
import com.ruoyi.kbs.service.IKbsFavoriteGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库收藏组Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/favorite/group")
public class KbsFavoriteGroupController extends BaseController {
    @Autowired
    private IKbsFavoriteGroupService kbsFavoriteGroupService;

    /**
     * 查询知识库收藏组列表
     */
    @GetMapping("/list")
    public TableDataInfo list(KbsFavoriteGroup kbsFavoriteGroup) {
        startPage();
        List<KbsFavoriteGroup> list = kbsFavoriteGroupService.listKbsFavoriteGroup(kbsFavoriteGroup);
        return getDataTable(list);
    }

    /**
     * 获取知识库收藏组详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsFavoriteGroupService.getKbsFavoriteGroupById(id));
    }

    /**
     * 新增知识库收藏组
     */
    @PostMapping
    public AjaxResult add(@RequestBody KbsFavoriteGroup kbsFavoriteGroup) {
        return toAjax(kbsFavoriteGroupService.saveKbsFavoriteGroup(kbsFavoriteGroup));
    }

    /**
     * 修改知识库收藏组
     */
    @PutMapping
    public AjaxResult edit(@RequestBody KbsFavoriteGroup kbsFavoriteGroup) {
        return toAjax(kbsFavoriteGroupService.updateKbsFavoriteGroup(kbsFavoriteGroup));
    }

    /**
     * 删除知识库收藏组
     */
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsFavoriteGroupService.deleteKbsFavoriteGroupByIds(ids));
    }

    /**
     * 获取下拉模板列表
     */
    @GetMapping(value = "/select")
    public AjaxResult getGroupSelectList() {
        return success(kbsFavoriteGroupService.getGroupSelectList());
    }
}
