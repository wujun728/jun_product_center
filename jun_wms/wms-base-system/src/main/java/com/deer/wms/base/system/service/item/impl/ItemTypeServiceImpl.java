package com.deer.wms.base.system.service.item.impl;

import java.util.ArrayList;
import java.util.List;

import com.deer.wms.common.core.domain.Ztree;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.item.ItemType;
import com.deer.wms.base.system.dao.item.ItemTypeMapper;
import com.deer.wms.base.system.service.item.IItemTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deer.wms.common.core.text.Convert;

/**
 * 物料分类 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class ItemTypeServiceImpl extends AbstractService<ItemType, String> implements IItemTypeService
{
	@Autowired
	private ItemTypeMapper itemTypeMapper;

	/**
	 * 根据整张表个数据，和需要删除的父节点id，查询出所有子节点id
	 * @param list
	 * @param parentId
	 * @return 所有需要删除的节点的list
	 */
	public  List<Long> treeMenuList( List<ItemType> list, Long parentId){
		List<Long> newList = new ArrayList<Long>();
		for(ItemType itemType: list){
			//遍历出父id等于参数的id，add进子节点集合
			Long iParentId = itemType.getParentId();
			if(iParentId==null){
				continue;
			}
			if(iParentId.equals(parentId)){
				//递归遍历下一级
				// list.remove(itemType);
				treeMenuList(list,itemType.getItemTypeId());
				newList.add(itemType.getItemTypeId());
			}
		}
		return newList;
	}

	public void deleteListById(List<Long> list){

		for(Long itemTtypeId : list){

			itemTypeMapper.deleteItemTypeById(itemTtypeId);
		}
	}



	/**
	 * 根据物料ID查询物料分类列表
	 *
	 * @param parentId 物料分类ID
	 * @return 物料分类信息
	 */
	@Override
	public List<ItemType> selectItemTypeByParentId(Long parentId) {

		return itemTypeMapper.selectItemTypeByParentId(parentId);
	}

	/**
     * 查询物料分类信息
     * 
     * @param itemTypeId 物料分类ID
     * @return 物料分类信息
     */
    @Override
	public ItemType selectItemTypeById(Long itemTypeId)
	{
	    return itemTypeMapper.selectItemTypeById(itemTypeId);
	}
	
	/**
     * 查询物料分类列表
     * 
     * @param itemType 物料分类信息
     * @return 物料分类集合
     */
	@Override
	public List<ItemType> selectItemTypeList(ItemType itemType)
	{
	    return itemTypeMapper.selectItemTypeList(itemType);
	}
	
    /**
     * 新增物料分类
     * 
     * @param itemType 物料分类信息
     * @return 结果
     */
	@Override
	public int insertItemType(ItemType itemType)
	{
	    return itemTypeMapper.insertItemType(itemType);
	}
	
	/**
     * 修改物料分类
     * 
     * @param itemType 物料分类信息
     * @return 结果
     */
	@Override
	public int updateItemType(ItemType itemType)
	{
	    return itemTypeMapper.updateItemType(itemType);
	}

	/**
     * 删除物料分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteItemTypeByIds(String ids)
	{
		return itemTypeMapper.deleteItemTypeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 对象转分类树
	 *

	 * @return 树结构列表
	 */
	public List<Ztree> initZtree(List<ItemType> itemTypeList )
	{

		List<Ztree> ztrees = new ArrayList<Ztree>();

		for (ItemType itemType : itemTypeList)
		{

			Ztree ztree = new Ztree();
			ztree.setId(itemType.getItemTypeId());
			ztree.setpId(itemType.getParentId());
			ztree.setName(itemType.getItemTypeName());
			ztree.setTitle(itemType.getItemTypeName());

			ztrees.add(ztree);

		}
		return ztrees;
	}

	@Override
	public List<Ztree> selectItemTypeTree(ItemType itemType) {
		List<ItemType> itemTypeList = itemTypeMapper.selectItemTypeList(itemType);
		List<Ztree> ztrees = initZtree(itemTypeList);
		return ztrees;
	}


}
