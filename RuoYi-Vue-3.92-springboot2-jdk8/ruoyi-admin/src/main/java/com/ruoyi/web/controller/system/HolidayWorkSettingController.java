package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.HolidayWorkSetting;
import com.ruoyi.system.service.IHolidayWorkSettingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 节假日补班设置Controller
 * 
 * @author wocurr.com
 * @date 2025-05-22
 */
@RestController
@RequestMapping("/system/holiday/worksetting")
public class HolidayWorkSettingController extends BaseController {
    @Autowired
    private IHolidayWorkSettingService holidayWorkSettingService;

    /**
     * 查询节假日补班设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:setting:list')")
    @GetMapping("/list")
    public TableDataInfo list(HolidayWorkSetting holidayWorkSetting) {
        startPage();
        List<HolidayWorkSetting> list = holidayWorkSettingService.listHolidayWorkSetting(holidayWorkSetting);
        return getDataTable(list);
    }

    /**
     * 获取指定月份的补班信息
     * @param holidayWorkSetting
     * @return
     */
    @GetMapping("/month/work")
    public AjaxResult getMonthHoliday(HolidayWorkSetting holidayWorkSetting) {
        return success(holidayWorkSettingService.getMonthWorkHoliday(holidayWorkSetting));
    }

    /**
     * 导出节假日补班设置列表
     */
    @PreAuthorize("@ss.hasPermi('system:setting:export')")
    @Log(title = "节假日补班设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HolidayWorkSetting holidayWorkSetting) {
        List<HolidayWorkSetting> list = holidayWorkSettingService.listHolidayWorkSetting(holidayWorkSetting);
        ExcelUtil<HolidayWorkSetting> util = new ExcelUtil<HolidayWorkSetting>(HolidayWorkSetting.class);
        util.exportExcel(response, list, "节假日补班设置数据");
    }

    /**
     * 获取节假日补班设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:setting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(holidayWorkSettingService.getHolidayWorkSettingById(id));
    }

    /**
     * 新增节假日补班设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:add')")
    @Log(title = "节假日补班设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HolidayWorkSetting holidayWorkSetting) {
        return toAjax(holidayWorkSettingService.saveHolidayWorkSetting(holidayWorkSetting));
    }

    /**
     * 修改节假日补班设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:edit')")
    @Log(title = "节假日补班设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HolidayWorkSetting holidayWorkSetting) {
        return toAjax(holidayWorkSettingService.updateHolidayWorkSetting(holidayWorkSetting));
    }

    /**
     * 删除节假日补班设置
     */
    @PreAuthorize("@ss.hasPermi('system:setting:remove')")
    @Log(title = "节假日补班设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(holidayWorkSettingService.deleteHolidayWorkSettingByIds(ids));
    }
}
