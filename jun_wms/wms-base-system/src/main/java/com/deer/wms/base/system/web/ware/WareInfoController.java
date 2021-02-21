package com.deer.wms.web.controller.ware;

import java.math.BigDecimal;
import java.util.List;

import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.service.item.IItemInfoService;
import com.deer.wms.framework.util.ShiroUtils;
import com.deer.wms.base.system.model.ware.WareInfo;
import com.deer.wms.base.system.service.ware.IWareInfoService;
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
 * 仓库设置 信息操作处理
 * 
 * @author deer
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/system/wareInfo")
public class WareInfoController extends BaseController
{
    private String prefix = "system/wareInfo";
	
	@Autowired
	private IWareInfoService wareInfoService;
	@Autowired
	private IItemInfoService itemInfoService;


	@RequiresPermissions("system:wareInfo:view")
	@GetMapping()
	public String wareInfo()
	{
	    return prefix + "/wareInfo";
	}
	
	/**
	 * 查询仓库设置列表
	 */
	@RequiresPermissions("system:wareInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WareInfo wareInfo)
	{
		startPage();
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
		return getDataTable(list);
	}
	
	/**
	 * 导出仓库设置列表
	 */
	@RequiresPermissions("system:wareInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WareInfo wareInfo)
    {
    	List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        ExcelUtil<WareInfo> util = new ExcelUtil<WareInfo>(WareInfo.class);
        return util.exportExcel(list, "wareInfo");
    }
	
	/**
	 * 新增仓库设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存仓库设置
	 */
	@RequiresPermissions("system:wareInfo:add")
	@Log(title = "仓库设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WareInfo wareInfo)
	{
		if(wareInfo.getWareCode() == null ||wareInfo.getWareCode() == "" || wareInfo.getWareName() == null || wareInfo.getWareName() == ""){
			return error("请填写仓库编码或仓库名称！");
		}
		WareInfo wareInfo1 = new WareInfo();
		wareInfo1.setWareId(wareInfo.getWareId());
		wareInfo1.setWareCode(wareInfo.getWareCode());
		wareInfo1.setWareName(wareInfo.getWareName());
		List<WareInfo> w1 = wareInfoService.findWareInfoList(wareInfo1);
//		if(w1!=null){
		if(w1.size()>0){
			return error("仓库名已存在!");
		}

		wareInfo.setCreateUserId(ShiroUtils.getUserId());
		wareInfo.setCreateUserName(ShiroUtils.getLoginName());
		wareInfoService.save(wareInfo);
		return success();
	}

	/**
	 * 修改仓库设置
	 */
	@GetMapping("/edit/{wareId}")
	public String edit(@PathVariable("wareId") Integer wareId, ModelMap mmap)
	{
		WareInfo wareInfo = wareInfoService.selectWareInfoById(wareId);
		mmap.put("wareInfo", wareInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存仓库设置
	 */
	@RequiresPermissions("system:wareInfo:edit")
	@Log(title = "仓库设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WareInfo wareInfo)
	{
		if(wareInfo.getWareCode() == null ||wareInfo.getWareCode() == "" || wareInfo.getWareName() == null || wareInfo.getWareName() == ""){
			return error("请填写仓库编码或仓库名称！");
		}
//		WareInfo wareInfo1 = new WareInfo();
//		wareInfo1.setWareId(wareInfo.getWareId());
//		wareInfo1.setWareCode(wareInfo.getWareCode());
//		wareInfo1.setWareName(wareInfo.getWareName());
//		List<WareInfo> w1 = wareInfoService.findWareInfoList(wareInfo1);
//		if(w1.size()>0){
//			return error("仓库名已存在!");
//		}
		WareInfo wareInfo2 = wareInfoService.findById(212);
		if(wareInfo.getBoxHeight()<wareInfo2.getBoxHeight()){
			return error("当前高度需大于原有高度!");
		}
		else if(wareInfo.getBoxHeight()>wareInfo2.getBoxHeight()){
			List<ItemInfo> itemInfos = itemInfoService.selectItemInfoList(new ItemInfo());
			if(itemInfos.size()>0){
				for(ItemInfo itemInfo : itemInfos) {
					Integer maxPackQuantity = calcuateStoreQuantity(itemInfo.getThickness(),wareInfo.getBoxHeight());
					itemInfo.setMaxPackQty(maxPackQuantity);
					itemInfoService.update(itemInfo);
				}
			}
		}
		return toAjax(wareInfoService.updateWareInfo(wareInfo));
	}
	
	/**
	 * 删除仓库设置
	 */
	@RequiresPermissions("system:wareInfo:remove")
	@Log(title = "仓库设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(wareInfoService.deleteWareInfoByIds(ids));
	}

	private Integer calcuateStoreQuantity(Double thickness,Integer wareBoxHeight){
		BigDecimal boxHeight = new BigDecimal(wareBoxHeight);
		BigDecimal itemHeight = new BigDecimal(Double.toString(thickness));
		BigDecimal divide = boxHeight.divide(itemHeight, 1, BigDecimal.ROUND_DOWN);
		String replace = Integer.toString(divide.intValue()).substring(0, Integer.toString(divide.intValue()).length() - 1);
		return Integer.parseInt(replace + "0");
	}
}
