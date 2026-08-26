package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.HolidayWorkSetting;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.HolidaySetting;
import com.ruoyi.system.service.IHolidaySettingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 假期设置Controller
 * 
 * @author wocurr.com
 * @date 2025-05-22
 */
@RestController
@RequestMapping("/system/holiday/setting")
public class HolidaySettingController extends BaseController {
    @Autowired
    private IHolidaySettingService holidaySettingService;

    /**
     * 查询假期设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:setting:list')")
    @GetMapping("/list")
    public TableDataInfo list(HolidaySetting holidaySetting) {
        startPage();
        List<HolidaySetting> list = holidaySettingService.listHolidaySetting(holidaySetting);
        return getDataTable(list);
    }

    /**
     * 获取指定月份的假日信息
     * @param holidaySetting
     * @return
     */
    @GetMapping("/month/holiday")
    public AjaxResult getMonthHoliday(HolidaySetting holidaySetting) {
        return success(holidaySettingService.getMonthHoliday(holidaySetting));
    }

    /**
     * 获取年度假日配置
     * @param year
     * @return
     */
    @GetMapping("/year/holiday/{year}")
    public AjaxResult getYearHoliday(@PathVariable("year") String year) {
        return success(holidaySettingService.getYearHoliday(year));
    }

    /**
     * 导出假期设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:setting:export')")
    @Log(title = "假期设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HolidaySetting holidaySetting) {
        List<HolidaySetting> list = holidaySettingService.listHolidaySetting(holidaySetting);
        ExcelUtil<HolidaySetting> util = new ExcelUtil<HolidaySetting>(HolidaySetting.class);
        util.exportExcel(response, list, "假期设置数据");
    }

    /**
     * 获取假期设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:setting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(holidaySettingService.getHolidaySettingById(id));
    }

    /**
     * 新增假期设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:add')")
    @Log(title = "假期设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HolidaySetting holidaySetting) {
        return toAjax(holidaySettingService.saveHolidaySetting(holidaySetting));
    }

    /**
     * 修改假期设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:edit')")
    @Log(title = "假期设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HolidaySetting holidaySetting) {
        return toAjax(holidaySettingService.updateHolidaySetting(holidaySetting));
    }

    /**
     * 删除假期设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:remove')")
    @Log(title = "假期设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(holidaySettingService.deleteHolidaySettingByIds(ids));
    }
}
