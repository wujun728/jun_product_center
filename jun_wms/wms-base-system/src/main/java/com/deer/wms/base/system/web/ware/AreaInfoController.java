package com.deer.wms.web.controller.ware;

import java.util.List;

import com.deer.wms.base.system.model.ware.AreaInfo;
import com.deer.wms.base.system.model.ware.AreaInfoDto;
import com.deer.wms.base.system.service.ware.IAreaInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.utils.poi.ExcelUtil;

/**
 * 货区设置 信息操作处理
 * 
 * @author deer
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/system/areaInfo")
public class AreaInfoController extends BaseController
{
    private String prefix = "system/areaInfo";
	
	@Autowired
	private IAreaInfoService areaInfoService;
	
	@RequiresPermissions("system:areaInfo:view")
	@GetMapping()
	public String areaInfo()
	{
	    return prefix + "/areaInfo";
	}
	
	/**
	 * 查询货区设置列表
	 */
	@ApiOperation("zxv")
	@RequiresPermissions("system:areaInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AreaInfo areaInfo)
	{
		startPage();
        List<AreaInfoDto> list = areaInfoService.selectAreaInfoList(areaInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出货区设置列表
	 */
	@RequiresPermissions("system:areaInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AreaInfo areaInfo)
    {
    	List<AreaInfoDto> list = areaInfoService.selectAreaInfoList(areaInfo);
        ExcelUtil<AreaInfoDto> util = new ExcelUtil<AreaInfoDto>(AreaInfoDto.class);
        return util.exportExcel(list, "areaInfo");
    }
	
	/**
	 * 新增货区设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存货区设置
	 */
	@RequiresPermissions("system:areaInfo:add")
	@Log(title = "货区设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AreaInfo areaInfo)
	{

		AreaInfo a1 = areaInfoService.selectAreaInfoByName(areaInfo.getAreaName());
		if(a1!=null){

			return error("货区名已存在!");
		}


		return toAjax(areaInfoService.insertAreaInfo(areaInfo));
	}

	/**
	 * 修改货区设置
	 */
	@GetMapping("/edit/{areaId}")
	public String edit(@PathVariable("areaId") Integer areaId, ModelMap mmap)
	{
		AreaInfo areaInfo = areaInfoService.selectAreaInfoById(areaId);
		mmap.put("areaInfo", areaInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存货区设置
	 */
	@RequiresPermissions("system:areaInfo:edit")
	@Log(title = "货区设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AreaInfo areaInfo)
	{

		AreaInfo a1 = areaInfoService.selectAreaInfoByName(areaInfo.getAreaName());
		if(a1!=null){

			return error("货区名已存在!");
		}

		return toAjax(areaInfoService.updateAreaInfo(areaInfo));
	}
	
	/**
	 * 删除货区设置
	 */
	@RequiresPermissions("system:areaInfo:remove")
	@Log(title = "货区设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(areaInfoService.deleteAreaInfoByIds(ids));
	}
	
}
