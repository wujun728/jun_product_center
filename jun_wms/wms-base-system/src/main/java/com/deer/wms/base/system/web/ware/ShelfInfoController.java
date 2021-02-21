package com.deer.wms.base.system.web.ware;

import com.deer.wms.base.system.model.ware.ShelfInfoCriteria;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.ShelfInfo;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.base.system.service.ware.IShelfInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 货架设置 信息操作处理
 * 
 * @author deer
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/system/shelfInfo")
public class ShelfInfoController extends BaseController
{
    private String prefix = "system/shelfInfo";
	
	@Autowired
	private IShelfInfoService shelfInfoService;

	@Autowired
	private ICellInfoService cellInfoService;


	@Autowired
	private BoxInfoService boxInfoService;
	
	@RequiresPermissions("system:shelfInfo:view")
	@GetMapping()
	public String shelfInfo()
	{
	    return prefix + "/shelfInfo";
	}
	
	/**
	 * 查询货架设置列表
	 */
	@RequiresPermissions("system:shelfInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ShelfInfo shelfInfo)
	{

		startPage();
        List<ShelfInfo> list = shelfInfoService.selectShelfInfoList(shelfInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出货架设置列表
	 */
	@RequiresPermissions("system:shelfInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ShelfInfo shelfInfo)
    {
    	List<ShelfInfo> list = shelfInfoService.selectShelfInfoList(shelfInfo);
        ExcelUtil<ShelfInfo> util = new ExcelUtil<ShelfInfo>(ShelfInfo.class);
        return util.exportExcel(list, "shelfInfo");
    }
	
	/**
	 * 新增货架设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存货架设置
	 */
	@RequiresPermissions("system:shelfInfo:add")
	@Log(title = "货架设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@Transactional
	public AjaxResult addSave(ShelfInfo shelfInfo)
	{

		ShelfInfo s1 = shelfInfoService.selectShelfInfoByName(shelfInfo.getShelfName());
		if(s1!=null){

			return error("货架名已存在!");
		}


		shelfInfoService.save(shelfInfo);
		CellInfo cellInfo = new CellInfo();

//		BoxInfo boxInfo = new BoxInfo();

//		List<CellInfo> cellInfos = new ArrayList<CellInfo>();

	//	int cellCount = 0;

		for(int i=1;i<=shelfInfo.getShelfRow();i++){
			for(int j=1;j<=shelfInfo.getShelfColumn();j++){

				cellInfo.setCellId(null);
				cellInfo.setShelfId(shelfInfo.getShelfId());

				cellInfo.setSRow(i);
				cellInfo.setSColumn(j);

//				cellInfos.add(cellInfo);
				cellInfoService.save(cellInfo);

//				boxInfo.setBoxId(null);
//				boxInfo.setBoxCellId(cellInfo.getCellId());
//				boxInfoService.save(boxInfo);

			}
		}


		return  toAjax(true);

	}



	/**
	 * 修改货架设置
	 */
	@GetMapping("/edit/{shelfId}")
	public String edit(@PathVariable("shelfId") Integer shelfId, ModelMap mmap)
	{
		ShelfInfo shelfInfo = shelfInfoService.selectShelfInfoById(shelfId);
		mmap.put("shelfInfo", shelfInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存货架设置
	 */
	@RequiresPermissions("system:shelfInfo:edit")
	@Log(title = "货架设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	@Transactional
	public AjaxResult editSave(ShelfInfo shelfInfo)
	{
		List<ShelfInfo> s1 = shelfInfoService.selectShelfInfo(shelfInfo);
		if(s1.size() > 0){
			return error("货架名已存在!");
		}
		cellInfoService.deleteByShelfId(shelfInfo.getShelfId());
//		shelfInfoService.update(shelfInfo);
		CellInfo cellInfo = new CellInfo();
		cellInfo.setShelfId(shelfInfo.getShelfId());
		for(int i=1;i<=shelfInfo.getShelfRow();i++){
			for(int j=1;j<=shelfInfo.getShelfColumn();j++){
				cellInfo.setCellId(null);
				cellInfo.setSRow(i);
				cellInfo.setSColumn(j);
				cellInfoService.save(cellInfo);
			}
		}
		return toAjax(shelfInfoService.updateShelfInfo(shelfInfo));
	}
	
	/**
	 * 删除货架设置
	 */
	@RequiresPermissions("system:shelfInfo:remove")
	@Log(title = "货架设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(shelfInfoService.deleteShelfInfoByIds(ids));
	}
	
}
