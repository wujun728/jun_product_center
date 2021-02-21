package com.deer.wms.base.system.web.item;

import com.alibaba.fastjson.JSONArray;
import com.deer.wms.base.system.model.*;
import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.model.item.ItemInfoDto;
import com.deer.wms.base.system.model.item.ItemInfoParam;
import com.deer.wms.base.system.model.ware.WareInfo;
import com.deer.wms.base.system.service.OperatorService;
import com.deer.wms.base.system.service.ServerVisitAddressService;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.base.system.service.ware.IWareInfoService;
import com.deer.wms.common.annotation.Log;
import com.deer.wms.common.core.controller.BaseController;
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.core.page.TableDataInfo;
import com.deer.wms.common.core.result.Result;
import com.deer.wms.common.core.result.ResultGenerator;
import com.deer.wms.common.enums.BusinessType;
import com.deer.wms.common.utils.poi.ExcelUtil;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物料 信息操作处理
 * 
 * @author deer
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/system/itemInfo")
public class ItemInfoController extends BaseController
{
    private String prefix = "system/itemInfo";
	
	@Autowired
	private IItemInfoService itemInfoService;
	@Autowired
	private IWareInfoService wareInfoService;
	@Autowired
	private ServerVisitAddressService serverVisitAddressService;
	@Autowired
	private OperatorService operatorService;
	
	@RequiresPermissions("system:itemInfo:view")
	@GetMapping()
	public String itemInfo()
	{

		String result = prefix + "/itemInfo";

		return result;
	}
	
	/**
	 * 查询物料列表
	 */
	@RequiresPermissions("system:itemInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ItemInfo itemInfo)
	{
		startPage();
        List<ItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
		return getDataTable(list);
	}


	/**
	 * 查询物料列表
	 */
	@RequiresPermissions("system:itemInfo:list")
	@PostMapping("/findList")
	@ResponseBody
	public TableDataInfo findList(ItemInfoParam param)
	{
		startPage();
		List<ItemInfoDto> list = itemInfoService.findList(param);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出物料列表
	 */
	@RequiresPermissions("system:itemInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ItemInfo itemInfo)
    {
    	List<ItemInfo> list = itemInfoService.selectItemInfoList(itemInfo);
        ExcelUtil<ItemInfo> util = new ExcelUtil<ItemInfo>(ItemInfo.class);
        return util.exportExcel(list, "itemInfo");
    }
	
	/**
	 * 新增物料
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}




	/**
	 * 新增保存物料
	 */
	@RequiresPermissions("system:itemInfo:add")
	@Log(title = "物料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@Transactional
	public synchronized AjaxResult addSave(ItemInfo itemInfo)
	{
		if(itemInfoService.findByItemCode(itemInfo.getItemCode())!=null){
			return error("此编码已存在!");
		}else {
			if(itemInfo.getThickness()<=0 || itemInfo.getThickness() == null) {
				return error("请填写正确的厚度!");
			}
			else{
				Integer maxStoreQuantity = calcuateStoreQuantity(itemInfo.getThickness());
				if(maxStoreQuantity==0){
					return error("请优先维护单箱厚度!");
				}
				else {
					itemInfo.setUnit("张");
					itemInfo.setMaxPackQty(maxStoreQuantity);
					itemInfoService.save(itemInfo);
					return toAjax(true);
				}
			}
		}
	}

	/**
	 * 修改物料
	 */
	@GetMapping("/edit/{itemId}")
	public String edit(@PathVariable("itemId") Integer itemId, ModelMap mmap)
	{
		ItemInfo itemInfo = itemInfoService.selectItemInfoById(itemId);
		itemInfo.setCardNoOne("");
		itemInfo.setCardNoTwo("");
		mmap.put("itemInfo", itemInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存物料
	 */
	@RequiresPermissions("system:itemInfo:edit")
	@Log(title = "物料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public synchronized AjaxResult editSave(ItemInfo itemInfo)
	{
		if(itemInfoService.findByItemInfo(itemInfo)!=null){
			return error("此编码已存在!");
		}else {
			if(itemInfo.getThickness()<=0 || itemInfo.getThickness() == null) {
				return error("请填写正确的厚度!");
			}
			else {
				Integer maxStoreQuantity = calcuateStoreQuantity(itemInfo.getThickness());
				if(maxStoreQuantity==0){
					return error("请优先维护单箱厚度!");
				}
				else {
					if((itemInfo.getCardNoOne() == "" || itemInfo.getCardNoTwo() == "")){
						return error("请刷卡录入卡号！");
					}
					if(itemInfo.getCardNoTwo().equals(itemInfo.getCardNoOne())){
						return error("请录入不同卡号！");
					}
					Operator operatorOne = operatorService.findByCard(itemInfo.getCardNoOne());
					Operator operatorTwo = operatorService.findByCard(itemInfo.getCardNoTwo());
					if(operatorOne == null){
						return error("卡号一未录入，请联系管理员录入");
					}
					if(operatorTwo == null){
						return error("卡号二未录入，请联系管理员录入");
					}
					itemInfo.setMaxPackQty(maxStoreQuantity);
					itemInfoService.update(itemInfo);
					return success();
				}
			}
		}
	}
	
	/**
	 * 删除物料
	 */
	@RequiresPermissions("system:itemInfo:remove")
	@Log(title = "物料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(itemInfoService.deleteItemInfoByIds(ids));
	}

	@GetMapping( "/findMaxPacQuantity")
	@ResponseBody
	public Result findMaxPacQuantity(@Param("itemCode") String itemCode)
	{
		if(!itemCode.equals("")) {
			ItemInfo itemInfo = itemInfoService.findByItemCode(itemCode);
			if (itemInfo != null) {
				if (itemInfo.getMaxPackQty() != null) {
					return ResultGenerator.genSuccessResult(itemInfo.getMaxPackQty());
				} else {
					return ResultGenerator.genSuccessResult();
				}
			} else {
				return ResultGenerator.genSuccessResult();
			}
		}else{
			return ResultGenerator.genSuccessResult();
		}
	}

	private Integer calcuateStoreQuantity(Double thickness){
		WareInfo wareInfo = wareInfoService.findById(212);
		if(wareInfo.getBoxHeight() == null) {
			return 0;
		}
		else{
			BigDecimal boxHeight = new BigDecimal(Integer.toString(wareInfo.getBoxHeight()));
			BigDecimal itemHeight = new BigDecimal(Double.toString(thickness));
			BigDecimal divide = boxHeight.divide(itemHeight, 1, BigDecimal.ROUND_DOWN);
			String replace = Integer.toString(divide.intValue()).substring(0, Integer.toString(divide.intValue()).length() - 1);
			return Integer.parseInt(replace + "0");
		}
	}

	/**
	 * 校验物料编码是否存在
	 */
	@PostMapping("/checkItemInfo")
	@ResponseBody
	public String checkItemInfo(ItemInfo itemInfo)
	{
		return itemInfoService.checkItemCode(itemInfo.getItemCode());
	}
}
