package com.deer.wms.base.system.web.box;

import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.box.BoxInfo;
import com.deer.wms.base.system.model.box.BoxItem;
import com.deer.wms.base.system.model.box.BoxItemCriteria;
import com.deer.wms.base.system.model.box.BoxItemDto;
import com.deer.wms.base.system.model.task.TaskInfo;
import com.deer.wms.base.system.model.ware.CellInfo;
import com.deer.wms.base.system.model.ware.CellInfoDto;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.base.system.service.SubinventoryTransferRecordService;
import com.deer.wms.base.system.service.box.BoxInfoService;
import com.deer.wms.base.system.service.box.IBoxItemService;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.base.system.service.task.ITaskInfoService;
import com.deer.wms.base.system.service.ware.ICellInfoService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.CommonCode;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.exception.ServiceException;
import com.deer.wms.common.utils.DateUtils;
import com.deer.wms.common.utils.GuidUtils;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.deer.wms.framework.util.MyUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.omg.SendingContext.RunTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组盘 信息操作处理
 * 
 * @author guo
 * @date 2019-06-03
 */
@Controller
@RequestMapping("/in/boxItem")
public class BoxItemController extends BaseController
{
    private String prefix = "in/boxItem";
	
	@Autowired
	private IBoxItemService boxItemService;
	@Autowired
	private SubinventoryTransferRecordService subinventoryTransferRecordService;
	@Autowired
	private ServerVisitAddressService serverVisitAddressService;
	@Autowired
	private BoxInfoService boxInfoService;
	@Autowired
	private ITaskInfoService taskInfoService;
	@Autowired
	private ICellInfoService cellInfoService;
	@Autowired
	private IItemInfoService itemInfoService;
	
	@RequiresPermissions("in:boxItem:view")
	@GetMapping("/page")
	public String boxItem()
	{
	    return prefix + "/boxItem";
	}

	@RequiresPermissions("in:inventoryManage:view")
	@GetMapping("/inventoryManagePage")
	public String inventoryManage()
	{
		return prefix + "/inventoryManage";
	}
	/**
	 *
	 * 关联查询托盘信息
	 *
	 *
	 */

	@PostMapping("/findList")
	@ResponseBody
	public TableDataInfo findList(BoxItemCriteria boxItemCriteria)
	{
		startPage();
		List<BoxItemDto> list = boxItemService.selectBoxItemDtoList(boxItemCriteria);
		return getDataTable(list);
	}

	/**
	 * 查询组盘列表
	 */
	@RequiresPermissions("in:boxItem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BoxItem boxItem)
	{
		startPage();
        List<BoxItem> list = boxItemService.selectBoxItemList(boxItem);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出组盘列表
	 */
	@RequiresPermissions("in:boxItem:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BoxItem boxItem)
    {
    	List<BoxItem> list = boxItemService.selectBoxItemList(boxItem);
        ExcelUtil<BoxItem> util = new ExcelUtil<BoxItem>(BoxItem.class);
        return util.exportExcel(list, "boxItem");
    }
	
	/**
	 * 新增组盘
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存组盘
	 */
	@RequiresPermissions("in:boxItem:add")
	@Log(title = "组盘", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BoxItem boxItem)
	{		
		return toAjax(boxItemService.insertBoxItem(boxItem));
	}

	/**
	 * 修改组盘
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		BoxItem boxItem = boxItemService.selectBoxItemById(id);
		mmap.put("boxItem", boxItem);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存组盘
	 */
	@RequiresPermissions("in:boxItem:edit")
	@Log(title = "组盘", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BoxItem boxItem)
	{		
		return toAjax(boxItemService.updateBoxItem(boxItem));
	}
	
	/**
	 * 删除组盘
	 */
	@RequiresPermissions("in:boxItem:remove")
	@Log(title = "组盘", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(boxItemService.deleteBoxItemByIds(ids));
	}


	/**
	 * 品质异常检验
	 */
//	@RequiresPermissions("in:boxItem:checkOut")
	@Log(title = "检验", businessType = BusinessType.OTHER)
	@PostMapping( "/qualityAbnormalCheck")
	@ResponseBody
	@Transactional
	public Result qualityAbnormalCheck(@RequestBody BoxItemCriteria boxItemCriteria)
	{
		String error = "";
		int count = 0;
		try {
			List<BoxItemDto> boxItemDtos = boxItemService.findList(boxItemCriteria);
			String bool = cellInfoService.judgeBoxItemState(boxItemDtos);
			if (!bool.equals("success")) {
				error = bool;
				throw new RuntimeException();
			}
			for (BoxItemDto boxItemDto : boxItemDtos) {
				cellInfoService.updateCellStateAndBoxStateAndSendTaskInfo(boxItemDto, null, boxItemCriteria.getLoginPersonCardNo());
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(CommonCode.GENERAL_WARING_CODE,error);
		}
		return ResultGenerator.genSuccessResult(count);
	}

	//检验入库后
	@PostMapping( "/billInAfterQualityAbnormalCheck")
	@ResponseBody
	@Transactional
	public Result billInAfterQualityAbnormalCheck(@RequestBody BoxItemCriteria boxItemCriteria)
	{
		CellInfoDto cellInfoDto = cellInfoService.getBestCell();
		cellInfoService.updateCellInfoState(cellInfoDto,2);
		BoxInfo boxInfo = boxInfoService.getBoxInfoByBoxCode(boxItemCriteria.getBoxCode());
		boxInfo.setBoxState(2);
		boxInfoService.update(boxInfo);
		BoxItem boxItem = boxItemService.getBoxItemByBoxCode(boxItemCriteria.getBoxCode());
		TaskInfo taskInfo = new TaskInfo(new GuidUtils().toString(), "105",
				MyUtils.connectShelfNameAndRowAndColumn(cellInfoDto.getShelfName(),cellInfoDto.getSColumn(),cellInfoDto.getSRow()),
				TaskTypeConstant.IN_AVAILABLE_BOX,0,boxItem.getQuantity(),boxItem.getBoxCode(), "0",boxItem.getBillInDetailId()
		);
		taskInfo.setBarCode(MyUtils.connectPrintString(boxItem.getItemCode(),boxItem.getQuantity(),boxItem.getExp(),boxItem.getBatch(),itemInfoService.findByItemCode(boxItem.getItemCode()).getItemName()));
		taskInfo.setCardNo(boxItemCriteria.getLoginPersonCardNo());
		taskInfo.setTaskStartTime(DateUtils.getTime());
		taskInfoService.save(taskInfo);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * 库存管理请求账户别名与子库
	 */
	@PostMapping( "/boxItemManage")
	@ResponseBody
	public Result boxItemManage()
	{

		return ResultGenerator.genSuccessResult();
	}
}
