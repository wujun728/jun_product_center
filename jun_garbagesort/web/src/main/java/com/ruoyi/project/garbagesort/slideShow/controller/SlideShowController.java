package com.ruoyi.project.garbagesort.slideShow.controller;

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
import com.ruoyi.project.garbagesort.slideShow.domain.SlideShow;
import com.ruoyi.project.garbagesort.slideShow.service.ISlideShowService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 幻灯片播放Controller
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Controller
@RequestMapping("/garbagesort/slideShow")
public class SlideShowController extends BaseController
{
    private String prefix = "garbagesort/slideShow";

    @Autowired
    private ISlideShowService slideShowService;

    @RequiresPermissions("garbagesort:slideShow:view")
    @GetMapping()
    public String slideShow()
    {
        return prefix + "/slideShow";
    }

    /**
     * 查询幻灯片播放列表
     */
    @RequiresPermissions("garbagesort:slideShow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SlideShow slideShow)
    {
        startPage();
        List<SlideShow> list = slideShowService.selectSlideShowList(slideShow);
        return getDataTable(list);
    }

    /**
     * 导出幻灯片播放列表
     */
    @RequiresPermissions("garbagesort:slideShow:export")
    @Log(title = "幻灯片播放", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SlideShow slideShow)
    {
        List<SlideShow> list = slideShowService.selectSlideShowList(slideShow);
        ExcelUtil<SlideShow> util = new ExcelUtil<SlideShow>(SlideShow.class);
        return util.exportExcel(list, "幻灯片播放数据");
    }

    /**
     * 新增幻灯片播放
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存幻灯片播放
     */
    @RequiresPermissions("garbagesort:slideShow:add")
    @Log(title = "幻灯片播放", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SlideShow slideShow)
    {
        return toAjax(slideShowService.insertSlideShow(slideShow));
    }

    /**
     * 修改幻灯片播放
     */
    @GetMapping("/edit/{slide}")
    public String edit(@PathVariable("slide") Long slide, ModelMap mmap)
    {
        SlideShow slideShow = slideShowService.selectSlideShowById(slide);
        mmap.put("slideShow", slideShow);
        return prefix + "/edit";
    }

    /**
     * 修改保存幻灯片播放
     */
    @RequiresPermissions("garbagesort:slideShow:edit")
    @Log(title = "幻灯片播放", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SlideShow slideShow)
    {
        return toAjax(slideShowService.updateSlideShow(slideShow));
    }

    /**
     * 删除幻灯片播放
     */
    @RequiresPermissions("garbagesort:slideShow:remove")
    @Log(title = "幻灯片播放", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(slideShowService.deleteSlideShowByIds(ids));
    }
}
