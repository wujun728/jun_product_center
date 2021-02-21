package com.deer.wms.base.system.service.item.impl;

import java.util.List;

import com.deer.wms.common.constant.UserConstants;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.item.ItemInfo;
import com.deer.wms.base.system.model.item.ItemInfoDto;
import com.deer.wms.base.system.model.item.ItemInfoParam;
import com.deer.wms.base.system.dao.item.ItemInfoMapper;
import com.deer.wms.base.system.service.item.IItemInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deer.wms.common.core.text.Convert;

/**
 * 物料 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class ItemInfoServiceImpl extends AbstractService<ItemInfo, String> implements IItemInfoService
{
	@Autowired
	private ItemInfoMapper itemInfoMapper;


	@Override
	public List<ItemInfoDto> findList(ItemInfoParam param) {
		return itemInfoMapper.findList(param);
	}

	/**
     * 查询物料信息
     * 
     * @param itemId 物料ID
     * @return 物料信息
     */
    @Override
	public ItemInfo selectItemInfoById(Integer itemId)
	{
	    return itemInfoMapper.selectItemInfoById(itemId);
	}
	
	/**
     * 查询物料列表
     * 
     * @param itemInfo 物料信息
     * @return 物料集合
     */
	@Override
	public List<ItemInfo> selectItemInfoList(ItemInfo itemInfo)
	{
	    return itemInfoMapper.selectItemInfoList(itemInfo);
	}
	
    /**
     * 新增物料
     * 
     * @param itemInfo 物料信息
     * @return 结果
     */
	@Override
	public int insertItemInfo(ItemInfo itemInfo)
	{
	    return itemInfoMapper.insertItemInfo(itemInfo);
	}
	
	/**
     * 修改物料
     * 
     * @param itemInfo 物料信息
     * @return 结果
     */
	@Override
	public int updateItemInfo(ItemInfo itemInfo)
	{
	    return itemInfoMapper.updateItemInfo(itemInfo);
	}

	/**
     * 删除物料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteItemInfoByIds(String ids)
	{
		return itemInfoMapper.deleteItemInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public ItemInfo findByItemCode(@Param("itemCode") String itemCode){
		return itemInfoMapper.findByItemCode(itemCode);
	}
	@Override
	public ItemInfo findByItemInfo(ItemInfo itemInfo){
		return itemInfoMapper.findByItemInfo(itemInfo);
	}

	@Override
	public ItemInfo findByInventoryItemId(@Param("inventoryItemId") Integer inventoryItemId){
		return itemInfoMapper.findByInventoryItemId(inventoryItemId);
	}

	@Override
	public String checkItemCode(String itemCode){
		int count = itemInfoMapper.checkItemCode(itemCode);
		if (count > 0)
		{
			return UserConstants.USER_NAME_NOT_UNIQUE;
		}
		return UserConstants.USER_NAME_UNIQUE;
	}
}
