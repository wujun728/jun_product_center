package com.deer.wms.base.system.dao.bill;


import com.deer.wms.base.system.model.bill.BillOutMasterCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.bill.BillOutMasterDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 入库单 数据层
 * 
 * @author cai
 * @date 2019-07-15
 */
public interface BillOutMasterMapper extends Mapper<BillOutMaster>
{

	/**
	 * 根据billId查询出库单信息
	 */
	public BillOutMasterDto findBillOutMasterDtoByBillId(Integer billId);


	/**
	 * 删除入库单
	 *
	 * @param billId 入库单ID
	 * @return 结果
	 */
	public int deleteBillOutMasterById(Integer billId);

	/**
	 * 批量删除入库单
	 *
	 * @param billIds 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteBillOutMasterByIds(String[] billIds);



	/**
	 * 修改BillOutMaster信息
	 *
	 * @param billOutMaster
	 */
	public int updateBillOutMaster(BillOutMaster billOutMaster);


	/**
	 * 根据billId查找BillOutMaster
	 *
	 * @return
	 */
	public BillOutMaster selectBillOutMasterById(Integer billId);


	/**
	 * 保存BillOutMaster  出库单
	 *
	 * @param billOutMaster
	 */
	public void saveBillOutMaster(@Param("billOutMaster") BillOutMaster billOutMaster);

	/**
	 *
	 * 查询出库单
	 *
	 * @return
	 */
	public List<BillOutMasterDto> selectBillOutMasterList();

	BillOutMaster selectBillOutMasterByBillOutDetailId(Integer billOutDetailId);

	List<BillOutMasterDto> findList(BillOutMasterCriteria criteria);
}



















