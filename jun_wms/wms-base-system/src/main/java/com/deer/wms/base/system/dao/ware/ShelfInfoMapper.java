package com.deer.wms.base.system.dao.ware;


import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.ware.ShelfInfo;

import java.util.List;

/**
 * 货架设置 数据层
 * 
 * @author deer
 * @date 2019-05-08
 */
public interface ShelfInfoMapper  extends Mapper<ShelfInfo>
{

	/**
	 *	根据货区ID查找所有货架
	 * @param areaId 货区ID
	 * @return
	 */
	public List<ShelfInfo> selectShelfInfoByAreaId(Integer areaId);


	/**
	 * 根据货架名查找货架
	 *
	 * @param shelfName 货架名
	 * @return 货架信息(对象)
	 */
	public ShelfInfo selectShelfInfoByName(String shelfName);

	/**
     * 查询货架设置信息
     * 
     * @param shelfId 货架设置ID
     * @return 货架设置信息
     */
	public ShelfInfo selectShelfInfoById(Integer shelfId);
	
	/**
     * 查询货架设置列表
     * 
     * @param shelfInfo 货架设置信息
     * @return 货架设置集合
     */
	public List<ShelfInfo> selectShelfInfoList(ShelfInfo shelfInfo);
	
	/**
     * 新增货架设置
     * 
     * @param shelfInfo 货架设置信息
     * @return 结果
     */
	public int insertShelfInfo(ShelfInfo shelfInfo);
	
	/**
     * 修改货架设置
     * 
     * @param shelfInfo 货架设置信息
     * @return 结果
     */
	public int updateShelfInfo(ShelfInfo shelfInfo);
	
	/**
     * 删除货架设置
     * 
     * @param shelfId 货架设置ID
     * @return 结果
     */
	public int deleteShelfInfoById(Integer shelfId);
	
	/**
     * 批量删除货架设置
     * 
     * @param shelfIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteShelfInfoByIds(String[] shelfIds);

	List<ShelfInfo> selectShelfInfo(ShelfInfo shelfInfo);
	
}