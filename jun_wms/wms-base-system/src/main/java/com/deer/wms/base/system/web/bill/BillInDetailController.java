package com.deer.wms.base.system.web.bill;

import com.deer.wms.base.system.model.bill.BillInDetail;
import com.deer.wms.base.system.model.bill.BillInDetailCriteria;
import com.deer.wms.base.system.model.bill.BillInDetailDto;
import com.deer.wms.base.system.service.bill.IBillInDetailService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库单详情 信息操作处理
 * 
 * @author guo
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/in/billInDetail")
public class BillInDetailController extends BaseController
{
    private String prefix = "in/billInDetail";
	
	@Autowired
	private IBillInDetailService billInDetailService;





	
	@RequiresPermissions("in:billInDetail:view")
	@GetMapping()
	public String billInDetail()
	{
	    return prefix + "/billInDetail";
	}
	
	/**
	 * 查询入库单详情列表
	 */
	@RequiresPermissions("in:billInDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BillInDetail billInDetail)
	{
		startPage();
        List<BillInDetail> list = billInDetailService.selectBillInDetailList(billInDetail);
		return getDataTable(list);
	}

	/**
	 * 查询入库单详情列表
	 */
	@RequiresPermissions("in:billInDetail:list")
	@PostMapping("/findList")
	@ResponseBody
	public TableDataInfo findList(BillInDetailCriteria billInDetailCriteria)
	{
		startPage();
		List<BillInDetailDto> list = billInDetailService.findList(billInDetailCriteria);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出入库单详情列表
	 */
	@RequiresPermissions("in:billInDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BillInDetail billInDetail)
    {
    	List<BillInDetail> list = billInDetailService.selectBillInDetailList(billInDetail);
        ExcelUtil<BillInDetail> util = new ExcelUtil<BillInDetail>(BillInDetail.class);
        return util.exportExcel(list, "billInDetail");
    }
	
	/**
	 * 新增入库单详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存入库单详情
	 */
	@RequiresPermissions("in:billInDetail:add")
	@Log(title = "入库单详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BillInDetail billInDetail)
	{
		billInDetailService.save(billInDetail);
		return success();
	}

	/**
	 * 修改入库单详情
	 */
	@GetMapping("/edit/{billInDetail}")
	public String edit(@PathVariable("billInDetail") Integer billInDetailId, ModelMap mmap)
	{
		BillInDetail billInDetail = billInDetailService.selectBillInDetailById(billInDetailId);
		mmap.put("billInDetail", billInDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存入库单详情
	 */
	@RequiresPermissions("in:billInDetail:edit")
	@Log(title = "入库单详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BillInDetail billInDetail)
	{
		billInDetailService.update(billInDetail);
		return success();
	}
	
	/**
	 * 删除入库单详情
	 */
	@RequiresPermissions("in:billInDetail:remove")
	@Log(title = "入库单详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(billInDetailService.deleteBillInDetailByIds(ids));
	}


	/**
	 * 删除入库单详情
	 */
	@RequiresPermissions("in:billInDetail:remove")
	@Log(title = "入库单详情", businessType = BusinessType.DELETE)
	@GetMapping( "/delete")
	@ResponseBody
	public Result delete(String id)
	{
		return ResultGenerator.genSuccessResult(billInDetailService.deleteBillInDetailByIds(id));
	}
	
}


























