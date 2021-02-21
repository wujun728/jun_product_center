package com.deer.wms.base.system.service.item;


import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.model.item.ItemInfoDto;
import com.deer.wms.base.system.model.item.ItemInfoParam;

import java.util.List;

/**
 * 物料 服务层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface IItemInfoService  extends Service<ItemInfo, String>
{
	/**
	 * 条件查询
	 *
	 * @param
	 * @return 物料信息
	 */
	public List<ItemInfoDto> findList(ItemInfoParam param);

	/**
     * 查询物料信息
     * 
     * @param itemId 物料ID
     * @return 物料信息
     */
	public ItemInfo selectItemInfoById(Integer itemId);
	
	/**
     * 查询物料列表
     * 
     * @param itemInfo 物料信息
     * @return 物料集合
     */
	public List<ItemInfo> selectItemInfoList(ItemInfo itemInfo);
	
	/**
     * 新增物料
     * 
     * @param itemInfo 物料信息
     * @return 结果
     */
	public int insertItemInfo(ItemInfo itemInfo);
	
	/**
     * 修改物料
     * 
     * @param itemInfo 物料信息
     * @return 结果
     */
	public int updateItemInfo(ItemInfo itemInfo);
		
	/**
     * 删除物料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteItemInfoByIds(String ids);

	/**
	 * 根据物料编码查询物料信息
	 */
	ItemInfo findByItemCode(String itemCode);
	/**
	 *	修改时判断是否有重复的编码
	 */
	ItemInfo findByItemInfo(ItemInfo itemInfo);

	ItemInfo findByInventoryItemId(Integer inventoryItemId);

	String checkItemCode(String itemCode);
}
