package com.ruoyi.project.garbagesort.imageClassify.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.garbagesort.imageClassify.domain.ImageClassify;
import com.ruoyi.project.garbagesort.imageClassify.service.IImageClassifyService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 图像识别记录Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/imageClassify")
public class ImageClassifyController extends BaseController
{
    private String prefix = "garbagesort/imageClassify";

    @Autowired
    private IImageClassifyService imageClassifyService;

    @RequiresPermissions("garbagesort:imageClassify:view")
    @GetMapping()
    public String imageClassify()
    {
        return prefix + "/imageClassify";
    }

    /**
     * 查询图像识别记录列表
     */
    @RequiresPermissions("garbagesort:imageClassify:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImageClassify imageClassify)
    {
        startPage();
        List<ImageClassify> list = imageClassifyService.selectImageClassifyList(imageClassify);
        return getDataTable(list);
    }

    /**
     * 导出图像识别记录列表
     */
    @RequiresPermissions("garbagesort:imageClassify:export")
    @Log(title = "图像识别记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ImageClassify imageClassify)
    {
        List<ImageClassify> list = imageClassifyService.selectImageClassifyList(imageClassify);
        ExcelUtil<ImageClassify> util = new ExcelUtil<ImageClassify>(ImageClassify.class);
        return util.exportExcel(list, "图像识别记录数据");
    }

    /**
     * 新增图像识别记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图像识别记录
     */
    @RequiresPermissions("garbagesort:imageClassify:add")
    @Log(title = "图像识别记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ImageClassify imageClassify)
    {
        return toAjax(imageClassifyService.insertImageClassify(imageClassify));
    }

    /**
     * 修改图像识别记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ImageClassify imageClassify = imageClassifyService.selectImageClassifyById(id);
        mmap.put("imageClassify", imageClassify);
        return prefix + "/edit";
    }

    /**
     * 修改保存图像识别记录
     */
    @RequiresPermissions("garbagesort:imageClassify:edit")
    @Log(title = "图像识别记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ImageClassify imageClassify)
    {
        return toAjax(imageClassifyService.updateImageClassify(imageClassify));
    }

    /**
     * 删除图像识别记录
     */
    @RequiresPermissions("garbagesort:imageClassify:remove")
    @Log(title = "图像识别记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(imageClassifyService.deleteImageClassifyByIds(ids));
    }
}
