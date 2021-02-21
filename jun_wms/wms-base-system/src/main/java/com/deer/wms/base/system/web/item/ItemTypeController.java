package com.deer.wms.base.system.web.item;

import java.util.List;

import com.deer.wms.common.core.domain.Ztree;
import com.deer.wms.common.utils.StringUtils;
import com.deer.wms.base.system.model.item.ItemType;
import com.deer.wms.base.system.service.item.IItemTypeService;
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
import com.deer.wms.common.core.domain.AjaxResult;
import com.deer.wms.common.utils.poi.ExcelUtil;

/**
 * 物料分类 信息操作处理
 * 
 * @author deer
 * @date 2019-05-08
 */
@Controller
@RequestMapping("/system/itemType")
public class ItemTypeController extends BaseController
{
    private String prefix = "system/itemType";

	@Autowired
	private IItemTypeService itemTypeService;

	@RequiresPermissions("system:itemType:view")
	@GetMapping()
	public String itemType()
	{
	    return prefix + "/itemType";
	}


	/**
	 * 根据父级ID查询物料分类列表（用作删除功能，itemTypeId=parentId）
	 */
	public List<ItemType> selectItemTypeByParentId(Long parentId){

		List<ItemType> list = itemTypeService.selectItemTypeByParentId(parentId);
		return list;
	}





	/**
	 * 查询物料分类列表
	 */
	@RequiresPermissions("system:itemType:list")
	@GetMapping("/list")
	@ResponseBody
	public List<ItemType> list(ItemType itemType)
	{
		startPage();
        List<ItemType> list = itemTypeService.selectItemTypeList(itemType);
		return list;
	}


	/**
	 * 导出物料分类列表
	 */
	@RequiresPermissions("system:itemType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ItemType itemType)
    {
    	List<ItemType> list = itemTypeService.selectItemTypeList(itemType);
        ExcelUtil<ItemType> util = new ExcelUtil<ItemType>(ItemType.class);
        return util.exportExcel(list, "itemType");
    }



	/**
	 * 新增物料分类
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
	{
		ItemType itemType = itemTypeService.selectItemTypeById(parentId);
		if(itemType==null){
			itemType = new ItemType();
			itemType.setItemTypeId(null);
			itemType.setItemTypeName("无");
		}
		mmap.put("itemType", itemType);
		return prefix + "/add";
	}



	/**
	 * 新增保存物料分类
	 */
	@RequiresPermissions("system:itemType:add")
	@Log(title = "物料分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ItemType itemType)
	{
		return toAjax(itemTypeService.insertItemType(itemType));
	}

	/**
	 * 修改物料分类
	 */
	@GetMapping("/edit/{itemTypeId}")
	public String edit(@PathVariable("itemTypeId") Long itemTypeId, ModelMap mmap)
	{
		ItemType itemType = itemTypeService.selectItemTypeById(itemTypeId);
		if (StringUtils.isNotNull(itemType) && 100L == itemTypeId)
		{
			itemType.setParentName("无");
		}
		mmap.put("itemType", itemType);
	    return prefix + "/edit";
	}



	/**
	 * 修改保存物料分类
	 */
	@RequiresPermissions("system:itemType:edit")
	@Log(title = "物料分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ItemType itemType)
	{
		return toAjax(itemTypeService.updateItemType(itemType));
	}





	/**
	 * 删除物料分类
	 */
	@RequiresPermissions("system:itemType:remove")
	@Log(title = "物料分类", businessType = BusinessType.DELETE)
	@GetMapping( "/remove/{itemTypId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable String itemTypId) {


		List<ItemType> list = itemTypeService.selectItemTypeList(new ItemType());

		List<Long> itemTypeIds = itemTypeService.treeMenuList(list,Long.parseLong(itemTypId));

		itemTypeService.deleteListById(itemTypeIds);
		itemTypeService.deleteItemTypeByIds(itemTypId);

		return toAjax(true);
	}




	/**
	 * 加载部门列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Ztree> treeData()
	{
		List<Ztree> ztrees = itemTypeService.selectItemTypeTree(new ItemType());
		return ztrees;
	}

	/**
	 * 选择部门树
	 */
	@GetMapping("/selectItemTypeTree/{itemTypeId}")
	public String selectDeptTree(@PathVariable("itemTypeId") Long itemTypeId, ModelMap mmap)
	{
		ItemType itemType = itemTypeService.selectItemTypeById(itemTypeId);
		if(itemType==null){
			itemType = new ItemType();
			itemType.setItemTypeId(null);
			itemType.setItemTypeName("无");
		}
		mmap.put("itemType", itemType);

		return prefix + "/tree";
	}
	
}
