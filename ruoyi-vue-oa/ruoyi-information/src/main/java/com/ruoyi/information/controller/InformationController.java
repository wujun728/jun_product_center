package com.ruoyi.information.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.information.domain.Information;
import com.ruoyi.information.service.IInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 新闻资讯Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/information/news")
public class InformationController extends BaseController {
    @Autowired
    private IInformationService informationService;

    /**
     * 查询新闻资讯列表
     */
    @PreAuthorize("@ss.hasPermi('information:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(Information information) {
        startPage();
        List<Information> list = informationService.listInformation(information);
        return getDataTable(list);
    }

    /**
     * 查询已发布的新闻资讯
     * @param information
     * @return
     */
    @GetMapping("/pub/list")
    public TableDataInfo pubList(Information information) {
        startPage();
        List<Information> list = informationService.listPub(information);
        return getDataTable(list);
    }

    /**
     * 获取新闻资讯详细信息
     */
    @PreAuthorize("@ss.hasPermi('information:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(informationService.getInformationById(id));
    }

    /**
     * 新增新闻资讯
     */
    @PreAuthorize("@ss.hasPermi('information:information:add')")
    @Log(title = "新闻资讯", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Information information) {
        return toAjax(informationService.saveInformation(information));
    }

    /**
     * 修改新闻资讯
     */
    @PreAuthorize("@ss.hasPermi('information:information:edit')")
    @Log(title = "新闻资讯", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Information information) {
        return toAjax(informationService.updateInformation(information));
    }

    /**
     * 删除新闻资讯
     */
    @PreAuthorize("@ss.hasPermi('information:information:remove')")
    @Log(title = "新闻资讯", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(informationService.deleteInformationByIds(ids));
    }

    /**
     * 置顶
     * @param information
     * @return
     */
    @PutMapping("/toTop")
    public AjaxResult toTop(@RequestBody Information information) {
        informationService.toTop(information);
        return toAjax(1);
    }

    /**
     * 变更状态
     * @param information
     * @return
     */
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody Information information) {
        return toAjax(informationService.changeStatus(information));
    }

    /**
     * 增加阅读数
     * @param id
     * @return
     */
    @PutMapping("/addReadNum/{id}")
    public AjaxResult addReadNum(@PathVariable("id") String id) {
        informationService.addReadNum(id);
        return toAjax(1);
    }
}
