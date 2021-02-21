package com.deer.wms.base.system.dao.ware;


import com.deer.wms.base.system.model.ware.Door;
import com.deer.wms.base.system.model.ware.DoorDto;

import java.util.List;

/**
 * 出入库口 数据层
 * 
 * @author deer
 * @date 2019-05-12
 */
public interface DoorMapper 
{
	/**
     * 查询出入库口信息
     * 
     * @param id 出入库口ID
     * @return 出入库口信息
     */
	public Door selectDoorById(Integer id);

	/**
	 * 查询出入库口列表(关联查询)
	 *

	 */
	public List<DoorDto> findList(Door door);
	
	/**
     * 查询出入库口列表
     * 
     * @param door 出入库口信息
     * @return 出入库口集合
     */
	public List<Door> selectDoorList(Door door);
	
	/**
     * 新增出入库口
     * 
     * @param door 出入库口信息
     * @return 结果
     */
	public int insertDoor(Door door);
	
	/**
     * 修改出入库口
     * 
     * @param door 出入库口信息
     * @return 结果
     */
	public int updateDoor(Door door);
	
	/**
     * 删除出入库口
     * 
     * @param id 出入库口ID
     * @return 结果
     */
	public int deleteDoorById(Integer id);
	
	/**
     * 批量删除出入库口
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDoorByIds(String[] ids);
	
}