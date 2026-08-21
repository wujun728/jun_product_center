package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsTopicCategory;
import com.ruoyi.kbs.service.IKbsTopicCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库主题类别Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/topic/category")
public class KbsTopicCategoryController extends BaseController {
    @Autowired
    private IKbsTopicCategoryService kbsTopicCategoryService;

    /**
     * 查询知识库主题类别列表
     */
    @GetMapping("/list")
    public TableDataInfo list(KbsTopicCategory kbsTopicCategory) {
        startPage();
        List<KbsTopicCategory> list = kbsTopicCategoryService.listKbsTopicCategory(kbsTopicCategory);
        return getDataTable(list);
    }

    /**
     * 获取知识库主题类别详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsTopicCategoryService.getKbsTopicCategoryById(id));
    }

    /**
     * 新增知识库主题类别
     */
    @Log(title = "知识库主题类别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KbsTopicCategory kbsTopicCategory) {
        return toAjax(kbsTopicCategoryService.saveKbsTopicCategory(kbsTopicCategory));
    }

    /**
     * 修改知识库主题类别
     */
    @Log(title = "知识库主题类别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KbsTopicCategory kbsTopicCategory) {
        return toAjax(kbsTopicCategoryService.updateKbsTopicCategory(kbsTopicCategory));
    }

    /**
     * 删除知识库主题类别
     */
    @Log(title = "知识库主题类别", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsTopicCategoryService.deleteKbsTopicCategoryByIds(ids));
    }

    /**
     * 获取下拉模板列表
     */
    @GetMapping(value = "/select")
    public AjaxResult getCategorySelectList() {
        return success(kbsTopicCategoryService.getCategorySelectList());
    }
}
