package com.deer.wms.base.system.service.ware.impl;

import java.util.List;

import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.ware.AreaInfo;
import com.deer.wms.base.system.model.ware.AreaInfoDto;
import com.deer.wms.base.system.dao.ware.AreaInfoMapper;
import com.deer.wms.base.system.service.ware.IAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deer.wms.common.core.text.Convert;

/**
 * 货区设置 服务层实现
 * 
 * @author deer
 * @date 2019-05-08
 */
@Service
public class AreaInfoServiceImpl extends AbstractService<AreaInfo, String> implements IAreaInfoService
{
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	/**
	 *
	 * 根据货区名查找货区
	 */
	@Override
	public AreaInfo selectAreaInfoByName(String areaName) {

		return areaInfoMapper.selectAreaInfoByName(areaName);
	}

	/**

	/**
     * 查询货区设置信息
     * 
     * @param areaId 货区设置ID
     * @return 货区设置信息
     */
    @Override
	public AreaInfo selectAreaInfoById(Integer areaId)
	{
	    return areaInfoMapper.selectAreaInfoById(areaId);
	}
	
	/**
     * 查询货区设置列表
     * 
     * @param areaInfo 货区设置信息
     * @return 货区设置集合
     */
	@Override
	public List<AreaInfoDto> selectAreaInfoList(AreaInfo areaInfo)
	{
	    return areaInfoMapper.selectAreaInfoList(areaInfo);
	}
	
    /**
     * 新增货区设置
     * 
     * @param areaInfo 货区设置信息
     * @return 结果
     */
	@Override
	public int insertAreaInfo(AreaInfo areaInfo)
	{
	    return areaInfoMapper.insertAreaInfo(areaInfo);
	}
	
	/**
     * 修改货区设置
     * 
     * @param areaInfo 货区设置信息
     * @return 结果
     */
	@Override
	public int updateAreaInfo(AreaInfo areaInfo)
	{
	    return areaInfoMapper.updateAreaInfo(areaInfo);
	}

	/**
     * 删除货区设置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAreaInfoByIds(String ids)
	{
		return areaInfoMapper.deleteAreaInfoByIds(Convert.toStrArray(ids));
	}
	
}
