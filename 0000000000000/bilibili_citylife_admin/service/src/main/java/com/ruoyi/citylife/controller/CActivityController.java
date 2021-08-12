package com.ruoyi.citylife.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.citylife.domain.CActivity;
import com.ruoyi.citylife.service.ICActivityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 活动Controller
 *
 * @author ruoyi
 * @date 2020-11-17
 */
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/citylife/activity" )
public class CActivityController extends BaseController {

    private final ICActivityService iCActivityService;

    /**
     * 查询活动列表
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(CActivity cActivity)
    {
        startPage();
        LambdaQueryWrapper<CActivity> lqw = new LambdaQueryWrapper<CActivity>();
        if (StringUtils.isNotBlank(cActivity.getPbid())){
            lqw.eq(CActivity::getPbid ,cActivity.getPbid());
        }
        if (StringUtils.isNotBlank(cActivity.getTitle())){
            lqw.eq(CActivity::getTitle ,cActivity.getTitle());
        }
        if (cActivity.getTime() != null){
            lqw.eq(CActivity::getTime ,cActivity.getTime());
        }
        if (StringUtils.isNotBlank(cActivity.getPosition())){
            lqw.eq(CActivity::getPosition ,cActivity.getPosition());
        }
        if (StringUtils.isNotBlank(cActivity.getLabel())){
            lqw.eq(CActivity::getLabel ,cActivity.getLabel());
        }
        if (StringUtils.isNotBlank(cActivity.getImg())){
            lqw.eq(CActivity::getImg ,cActivity.getImg());
        }
        if (StringUtils.isNotBlank(cActivity.getDetail())){
            lqw.eq(CActivity::getDetail ,cActivity.getDetail());
        }
        if (cActivity.getViews() != null){
            lqw.eq(CActivity::getViews ,cActivity.getViews());
        }
        if (cActivity.getCollections() != null){
            lqw.eq(CActivity::getCollections ,cActivity.getCollections());
        }
        if (cActivity.getLikes() != null){
            lqw.eq(CActivity::getLikes ,cActivity.getLikes());
        }
        if (cActivity.getUnlikes() != null){
            lqw.eq(CActivity::getUnlikes ,cActivity.getUnlikes());
        }
        if (cActivity.getComments() != null){
            lqw.eq(CActivity::getComments ,cActivity.getComments());
        }
        if (cActivity.getChecked() != null){
            lqw.eq(CActivity::getChecked ,cActivity.getChecked());
        }
        if (cActivity.getDeleted() != null){
            lqw.eq(CActivity::getDeleted ,cActivity.getDeleted());
        }
        List<CActivity> list = iCActivityService.list(lqw);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:export')" )
    @Log(title = "活动" , businessType = BusinessType.EXPORT)
    @GetMapping("/export" )
    public R export(CActivity cActivity) {
        LambdaQueryWrapper<CActivity> lqw = new LambdaQueryWrapper<CActivity>(cActivity);
        List<CActivity> list = iCActivityService.list(lqw);
        ExcelUtil<CActivity> util = new ExcelUtil<CActivity>(CActivity. class);
        return util.exportExcel(list, "activity" );
    }

    /**
     * 获取活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:query')" )
    @GetMapping(value = "/{id}" )
    public R getInfo(@PathVariable("id" ) String id) {
        return R.success(iCActivityService.getById(id));
    }

    /**
     * 新增活动
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:add')" )
    @Log(title = "活动" , businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody CActivity cActivity) {
        return toAjax(iCActivityService.save(cActivity) ? 1 : 0);
    }

    /**
     * 修改活动
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:edit')" )
    @Log(title = "活动" , businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody CActivity cActivity) {
        return toAjax(iCActivityService.updateById(cActivity) ? 1 : 0);
    }

    /**
     * 删除活动
     */
    @PreAuthorize("@ss.hasPermi('citylife:activity:remove')" )
    @Log(title = "活动" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}" )
    public R remove(@PathVariable String[] ids) {
        return toAjax(iCActivityService.removeByIds(Arrays.asList(ids)) ? 1 : 0);
    }
}
