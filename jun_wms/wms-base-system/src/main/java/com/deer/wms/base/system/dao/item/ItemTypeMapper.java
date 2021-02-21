package com.deer.wms.base.system.dao.item;


import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.item.ItemType;

import java.util.List;

/**
 * 物料分类 数据层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface ItemTypeMapper extends Mapper<ItemType>
{


	/**
	 * 根据物料ID查询物料分类列表
	 *
	 * @param parentId 物料分类ID
	 * @return 物料分类信息
	 */
	public List<ItemType> selectItemTypeByParentId(Long parentId);


	/**
     * 查询物料分类信息
     * 
     * @param itemTypeId 物料分类ID
     * @return 物料分类信息
     */
	public ItemType selectItemTypeById(Long itemTypeId);
	
	/**
     * 查询物料分类列表
     * 
     * @param itemType 物料分类信息
     * @return 物料分类集合
     */
	public List<ItemType> selectItemTypeList(ItemType itemType);
	
	/**
     * 新增物料分类
     * 
     * @param itemType 物料分类信息
     * @return 结果
     */
	public int insertItemType(ItemType itemType);
	
	/**
     * 修改物料分类
     * 
     * @param itemType 物料分类信息
     * @return 结果
     */
	public int updateItemType(ItemType itemType);
	
	/**
     * 删除物料分类
     * 
     * @param itemTypeId 物料分类ID
     * @return 结果
     */
	public int deleteItemTypeById(Long itemTypeId);
	
	/**
     * 批量删除物料分类
     * 
     * @param itemTypeIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteItemTypeByIds(String[] itemTypeIds);
	
}