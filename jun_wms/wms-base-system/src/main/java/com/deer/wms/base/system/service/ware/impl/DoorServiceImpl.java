package com.deer.wms.base.system.service.ware.impl;

import java.util.List;

import com.deer.wms.base.system.model.ware.Door;
import com.deer.wms.base.system.model.ware.DoorDto;
import com.deer.wms.base.system.dao.ware.DoorMapper;
import com.deer.wms.base.system.service.ware.IDoorService;
import com.deer.wms.common.core.text.Convert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出入库口 服务层实现
 * 
 * @author deer
 * @date 2019-05-12
 */
@Service
public class DoorServiceImpl implements IDoorService
{
	@Autowired
	private DoorMapper doorMapper;

	/**
     * 查询出入库口信息
     * 
     * @param id 出入库口ID
     * @return 出入库口信息
     */
    @Override
	public Door selectDoorById(@Param("id") Integer id)
	{
	    return doorMapper.selectDoorById(id);
	}
	
	/**
     * 查询出入库口列表
     * 
     * @param door 出入库口信息
     * @return 出入库口集合
     */
	@Override
	public List<Door> selectDoorList(Door door)
	{
	    return doorMapper.selectDoorList(door);
	}

	@Override
	public List<DoorDto> findList(Door door) {
		return doorMapper.findList(door);
	}

	/**
     * 新增出入库口
     * 
     * @param door 出入库口信息
     * @return 结果
     */
	@Override
	public int insertDoor(Door door)
	{
	    return doorMapper.insertDoor(door);
	}
	
	/**
     * 修改出入库口
     * 
     * @param door 出入库口信息
     * @return 结果
     */
	@Override
	public int updateDoor(Door door)
	{
	    return doorMapper.updateDoor(door);
	}

	/**
     * 删除出入库口对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDoorByIds(String ids)
	{
		return doorMapper.deleteDoorByIds(Convert.toStrArray(ids));
	}
	
}
