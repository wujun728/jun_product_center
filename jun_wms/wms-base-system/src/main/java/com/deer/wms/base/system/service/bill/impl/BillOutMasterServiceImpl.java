package com.deer.wms.base.system.service.bill.impl;


import com.deer.wms.base.system.model.bill.BillOutMasterCriteria;
import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.common.core.text.Convert;
import com.deer.wms.base.system.dao.bill.BillOutMasterMapper;
import com.deer.wms.base.system.model.bill.BillOutMaster;
import com.deer.wms.base.system.model.bill.BillOutMasterDto;
import com.deer.wms.base.system.service.bill.IBillOutMasterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 入库单 服务层实现
 * 
 * @author cai
 * @date 2019-07-17
 */
@Service
public class BillOutMasterServiceImpl extends AbstractService<BillOutMaster, Integer> implements IBillOutMasterService {

	@Autowired
	private BillOutMasterMapper billOutMasterMapper;

	/**
	 * 根据billId查询出库单信息
	 */
	@Override
	public BillOutMasterDto findBillOutMasterDtoByBillId(Integer billId) {
		return billOutMasterMapper.findBillOutMasterDtoByBillId(billId);
	}

	/**
	 * 删除入库单信息
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	public int deleteBillOutMasterByIds(String ids) {
		return billOutMasterMapper.deleteBillOutMasterByIds(Convert.toStrArray(ids));
	}

	/**
	 * 修改BillOutMaster信息
	 *
	 * @param billOutMaster
	 */
	@Override
	public int updateBillOutMaster(BillOutMaster billOutMaster) {
		return billOutMasterMapper.updateBillOutMaster(billOutMaster);
	}

	/**
	 * 根据billId查找BillOutMaster
	 *
	 * @return
	 */
	@Override
	public BillOutMaster selectBillOutMasterById(Integer billId) {
		return billOutMasterMapper.selectBillOutMasterById(billId);
	}

	/**
	 * 保存BillOutMaster  出库单
	 *
	 * @param billOutMaster
	 */
	@Override
	public void saveBillOutMaster(BillOutMaster billOutMaster) {
		billOutMasterMapper.saveBillOutMaster(billOutMaster);
	}

	/**
	 *
	 * 查询出库单
	 *
	 * @return
	 */
	@Override
	public List<BillOutMasterDto> selectBillOutMasterList() {
		return billOutMasterMapper.selectBillOutMasterList();
	}

	@Override
	public BillOutMaster selectBillOutMasterByBillOutDetailId(@Param("billOutDetailId") Integer billOutDetailId){
		return billOutMasterMapper.selectBillOutMasterByBillOutDetailId(billOutDetailId);
	}

	@Override
	public List<BillOutMasterDto> findList(BillOutMasterCriteria criteria){
		return billOutMasterMapper.findList(criteria);
	}
}


