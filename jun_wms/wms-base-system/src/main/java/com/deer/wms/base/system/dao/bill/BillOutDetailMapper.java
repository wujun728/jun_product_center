package com.deer.wms.base.system.dao.bill;


import com.deer.wms.base.system.model.bill.BillOutDetailCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.bill.BillOutDetail;
import com.deer.wms.base.system.model.bill.BillOutDetailDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 入库单 数据层
 * 
 * @author cai
 * @date 2019-07-15
 */
public interface BillOutDetailMapper extends Mapper<BillOutDetail>
{


	/**
	 * 根据BillOutDetailId删除BillOutDetail
	 *
	 */
	public void deleteBillOutDetailByBillOutDetailId(Integer billOutDetailId);

	/**
	 *  根据billId查询BillOutDetail信息
	 *
	 * @param billId
	 * @return
	 */
	public List<BillOutDetailDto> findListByBillId(Integer billId);


	/**
	 * 保存BillOutDetail  出库单详情
	 *
	 * @param billOutDetail
	 */
	public void saveBillOutDetail(@Param("billOutDetail") BillOutDetail billOutDetail);

	List<BillOutDetail> findList(BillOutDetailCriteria criteria);

}



















