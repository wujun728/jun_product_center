package com.deer.wms.base.system.service.item;


import com.deer.wms.common.core.domain.Ztree;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.item.ItemType;

import java.util.List;

/**
 * 物料分类 服务层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface IItemTypeService extends Service<ItemType, String>
{



	/**
	 * 根据整张表个数据，和需要删除的父节点id，查询出所有子节点id
	 * @param list
	 * @param parentId
	 * @return
	 */
	public List<Long> treeMenuList(List<ItemType> list, Long parentId);

	public void deleteListById(List<Long> list);


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
     * 删除物料分类信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteItemTypeByIds(String ids);


	/**
	 * 对象转分类树
	 *
	 * @param itemTypeList 部门列表

	 * @return 树结构列表
	 */
	  List<Ztree> initZtree(List<ItemType> itemTypeList );


	/**
	 * 查询 树
	 *
	 * @param
	 * @return
	 */
	public List<Ztree> selectItemTypeTree(ItemType itemType);




	
}
