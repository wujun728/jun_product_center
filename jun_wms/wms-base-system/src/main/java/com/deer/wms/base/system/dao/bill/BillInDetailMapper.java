package com.deer.wms.base.system.dao.bill;


import com.deer.wms.base.system.model.bill.BillInDetailCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.bill.BillInDetail;
import com.deer.wms.base.system.model.bill.BillInDetailDto;

import java.util.List;

/**
 * 入库单详情 数据层
 * 
 * @author guo
 * @date 2019-05-13
 */
public interface BillInDetailMapper  extends Mapper<BillInDetail>
{

	List<BillInDetailDto> findList(BillInDetailCriteria billInDetailCriteria);

	/**
     * 查询入库单详情信息
     * 
     * @param billInDetail 入库单详情ID
     * @return 入库单详情信息
     */
	public BillInDetail selectBillInDetailById(Integer billInDetail);
	
	/**
     * 查询入库单详情列表
     * 
     * @param billInDetail 入库单详情信息
     * @return 入库单详情集合
     */
	public List<BillInDetail> selectBillInDetailList(BillInDetail billInDetail);
	
	/**
     * 删除入库单详情
     * 
     * @param billInDetail 入库单详情ID
     * @return 结果
     */
	public int deleteBillInDetailById(Integer billInDetail);
	
	/**
     * 批量删除入库单详情
     * 
     * @param billInDetails 需要删除的数据ID
     * @return 结果
     */
	public int deleteBillInDetailByIds(String[] billInDetails);
	
}