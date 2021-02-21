package com.deer.wms.base.system.dao.item;


import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.model.item.ItemInfoDto;
import com.deer.wms.base.system.model.item.ItemInfoParam;

import java.util.List;

/**
 * 物料 数据层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface ItemInfoMapper extends Mapper<ItemInfo>
{

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
     * 删除物料
     * 
     * @param itemId 物料ID
     * @return 结果
     */
	public int deleteItemInfoById(Integer itemId);
	
	/**
     * 批量删除物料
     * 
     * @param itemIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteItemInfoByIds(String[] itemIds);

	ItemInfo findByItemCode(String itemCode);

	ItemInfo findByItemInfo(ItemInfo itemInfo);

	ItemInfo findByInventoryItemId(Integer inventoryItemId);

	int checkItemCode(String itemCode);
}