package com.erp.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.GridModel;
import com.erp.model.Item;
import com.erp.service.ItemService;
import com.erp.util.Constants;
import com.erp.util.PageUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/item")
@Slf4j
public class ItemAction extends BaseAction
{	
	private static final long serialVersionUID = -5649548096030365832L;
	@Autowired
	private ItemService itemService;
	private Integer suplierId;
	
	@ModelAttribute
	public void setSuplierId(Integer suplierId )
	{
		this.suplierId = suplierId;
	}

	@Autowired
	public void setItemService(ItemService itemService )
	{
		this.itemService = itemService;
	}
	
	
	/**  
	* 函数功能说明
	* Administrator修改者名字
	* 2013-7-2修改日期
	* 修改内容
	* @Title: findItemByMyid 
	* @Description: TODO:根据myid查询商品
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findItemByMyid")
	public String findItemByMyid(Item item) throws Exception
	{
		OutputJson(itemService.findItemByMyid(item.getMyid(),suplierId));
		return null;
	}
	
	/**  
	* 函数功能说明
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: findBrandList 
	* @Description: TODO:查询品牌
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findBrandList")
	public String findBrandList() throws Exception{
		OutputJson(itemService.findBrandList());
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: addBrands 
	* @Description: TODO:增加品牌
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/addBrands")
	public String addBrands(Item item) throws Exception
	{
		OutputJson(getMessage(itemService.addBrands(item.getName())));
		return null;
	}
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: findItemList 
	* @Description: TODO:查询商品列表
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/findItemList")
	public String findItemList() throws Exception
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil=new PageUtil(page, rows, searchAnds, searchColumnNames, searchConditions, searchVals);
		GridModel gridModel=new GridModel();
		gridModel.setRows(itemService.findItemList(map, pageUtil));
		gridModel.setTotal(itemService.getCount(map,pageUtil));
		OutputJson(gridModel);
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: persistenceItem 
	* @Description: TODO:持久化item
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/persistenceItem")
	public String persistenceItem(Item item) throws Exception
	{
		OutputJson(getMessage(itemService.persistenceItem(item)),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	/**  
	* 函数功能说明 
	* Administrator修改者名字
	* 2013-6-27修改日期
	* 修改内容
	* @Title: delItem 
	* @Description: TODO:删除ITem
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	@ResponseBody
	@RequestMapping(value = "/delItem")
	public String delItem(Item item) throws Exception
	{
		OutputJson(getMessage(itemService.delItem(item.getItemId())));
		return null;
	}
	
}
