package com.deer.wms.base.system.service.ware.impl;

import java.util.List;

import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.ware.ShelfInfo;
import com.deer.wms.base.system.dao.ware.ShelfInfoMapper;
import com.deer.wms.base.system.service.ware.IShelfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deer.wms.common.core.text.Convert;

/**
 * 货架设置 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class ShelfInfoServiceImpl  extends AbstractService<ShelfInfo, String> implements IShelfInfoService
{
	@Autowired
	private ShelfInfoMapper shelfInfoMapper;

	/**
	 *	根据货区ID查找所有货架
	 * @param areaId 货区ID
	 * @return
	 */
	@Override
	public List<ShelfInfo> selectShelfInfoByAreaId(Integer areaId) {


		return shelfInfoMapper.selectShelfInfoByAreaId(areaId);
	}

	/**
	 * 根据货架名查找货架
	 *
	 * @param shelfName 货架名
	 * @return 货架信息(对象)
	 */
	@Override
	public ShelfInfo selectShelfInfoByName(String shelfName) {

		return shelfInfoMapper.selectShelfInfoByName(shelfName);
	}

	/**
     * 查询货架设置信息
     * 
     * @param shelfId 货架设置ID
     * @return 货架设置信息
     */
    @Override
	public ShelfInfo selectShelfInfoById(Integer shelfId)
	{
	    return shelfInfoMapper.selectShelfInfoById(shelfId);
	}
	
	/**
     * 查询货架设置列表
     * 
     * @param shelfInfo 货架设置信息
     * @return 货架设置集合
     */
	@Override
	public List<ShelfInfo> selectShelfInfoList(ShelfInfo shelfInfo)
	{
	    return shelfInfoMapper.selectShelfInfoList(shelfInfo);
	}
	
    /**
     * 新增货架设置
     * 
     * @param shelfInfo 货架设置信息
     * @return 结果
     */
	@Override
	public int insertShelfInfo(ShelfInfo shelfInfo)
	{
	    return shelfInfoMapper.insertShelfInfo(shelfInfo);
	}
	
	/**
     * 修改货架设置
     * 
     * @param shelfInfo 货架设置信息
     * @return 结果
     */
	@Override
	public int updateShelfInfo(ShelfInfo shelfInfo)
	{
	    return shelfInfoMapper.updateShelfInfo(shelfInfo);
	}

	/**
     * 删除货架设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShelfInfoByIds(String ids)
	{
		return shelfInfoMapper.deleteShelfInfoByIds(Convert.toStrArray(ids));
	}

	/**
	 *
	 * @param ids 需要删除的数据ID
	 * @return
	 */
	@Override
	public List<ShelfInfo> selectShelfInfo(ShelfInfo shelfInfo)
	{
		return shelfInfoMapper.selectShelfInfo(shelfInfo);
	}
}
