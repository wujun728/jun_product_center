package com.deer.wms.base.system.web.ware;

import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.base.system.model.ware.Door;
import com.deer.wms.base.system.model.ware.DoorDto;
import com.deer.wms.base.system.service.ware.IDoorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出入库口 信息操作处理
 * 
 * @author deer
 * @date 2019-05-12
 */
@Controller
@RequestMapping("/system/door")
public class DoorController extends BaseController
{
    private String prefix = "system/door";
	
	@Autowired
	private IDoorService doorService;
	
	@RequiresPermissions("system:door:view")
	@GetMapping()
	public String door()
	{
	    return prefix + "/door";
	}
	
	/**
	 * 查询出入库口列表
	 */
//	@RequiresPermissions("system:door:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Door door)
	{
		startPage();
        List<Door> list = doorService.selectDoorList(door);
		return getDataTable(list);
	}

	/**
	 * 查询出入库口列表(关联查询)
	 */
//	@RequiresPermissions("system:door:findList")
	@PostMapping("/findList")
	@ResponseBody
	public TableDataInfo findList(Door door)
	{
		startPage();
		List<DoorDto> list = doorService.findList(door);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出出入库口列表
	 */
	@RequiresPermissions("system:door:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Door door)
    {
    	List<Door> list = doorService.selectDoorList(door);
        ExcelUtil<Door> util = new ExcelUtil<Door>(Door.class);
        return util.exportExcel(list, "door");
    }
	
	/**
	 * 新增出入库口
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存出入库口
	 */
	@RequiresPermissions("system:door:add")
	@Log(title = "出入库口", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Door door)
	{		
		return toAjax(doorService.insertDoor(door));
	}

	/**
	 * 修改出入库口
	 */
	@RequiresPermissions("system:door:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Door door = doorService.selectDoorById(id);
		mmap.put("door", door);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存出入库口
	 */
	@RequiresPermissions("system:door:edit")
	@Log(title = "出入库口", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Door door)
	{		
		return toAjax(doorService.updateDoor(door));
	}
	
	/**
	 * 删除出入库口
	 */
	@RequiresPermissions("system:door:remove")
	@Log(title = "出入库口", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(doorService.deleteDoorByIds(ids));
	}
	
}
