package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.terracotta.statistics.derived.OperationResultFilter;

import sy.action.OpResult;
import sy.dao.base.BaseDaoI;
import sy.model.app.DrugInRecord;
import sy.model.app.DrugOutRecord;
import sy.model.app.DrugTimes;
import sy.model.app.EmpDrugUseRecord;
import sy.model.base.Syuser;
import sy.service.app.DrugInRecordServiceI;
import sy.service.app.DrugOutRecordLogServiceI;
import sy.service.app.DrugOutRecordServiceI;
import sy.service.app.DrugStoreServiceI;
import sy.service.app.DrugTimesServiceI;
import sy.service.app.EmpDrugUseRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpDrugUseRecordServiceImpl extends
		BaseServiceImpl<EmpDrugUseRecord> implements EmpDrugUseRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpDrugUseRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugTimes> drugTimesDao;

	@Autowired
	private BaseDaoI<EmpDrugUseRecord> EmpDrugUseRecordDao;

	@Autowired
	private DrugStoreServiceI drugStoreServiceI;

	@Autowired
	private DrugOutRecordLogServiceI drugRecordLogServiceI;

	@Autowired
	private DrugTimesServiceI drugTimesServiceI;
	@Autowired
	private DrugOutRecordServiceI drugRecordServiceI;

	@Override
	public void updateEmpDrugUseRecord(EmpDrugUseRecord EmpDrugUseRecord) {
		if (!DataTypeUtil.isBlank(EmpDrugUseRecord.getId())) {
			EmpDrugUseRecord t = getById(EmpDrugUseRecord.getId());
			BeanUtils.copyNotNullProperties(EmpDrugUseRecord, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpDrugUseRecord(EmpDrugUseRecord EmpDrugUseRecord,
			String userId) {
		Syuser syuser = new Syuser(userId);
		EmpDrugUseRecord.setSyuser(syuser);

		EmpDrugUseRecord.setStatus(EmpDrugUseRecord.STATUS_NEW);
		// String order = getOrder();
		//
		// logger.debug("获得流水号 :" + order);
		// EmpDrugUseRecord.setOpOrder(order);
		//
		save(EmpDrugUseRecord);
	}

	synchronized public String getOrder() {
		List<Map> list = EmpDrugUseRecordDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	public List<EmpDrugUseRecord> findByCondition(int medicalId, int drugTimes) {
		String hql = "select distinct t from EmpDrugUseRecord t ";
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#medicalId_I_EQ", medicalId + "");
		hqlFilter.addFilter("QUERY_t#drugTimes#id_I_EQ", drugTimes + "");
		return findByFilter(hqlFilter);
	}

	private DrugOutRecord saveDrugRecord(EmpDrugUseRecord t, String userId) {
		if (t == null)
			return null;

		DrugOutRecord data = new DrugOutRecord();

		/**
		 * 消耗并且出库
		 * 
		 */
		DrugTimes drugTimes = drugTimesServiceI.getById(t.getDrugTimes()
				.getId());
		if (drugTimes == null) {
			return null;
		}

		int num = drugStoreServiceI.getDrugStoreCount(drugTimes
				.getDrugSpecInfo().getSpecId(), drugTimes.getDrugLotNo(),
				drugTimes.getDrugSpecInfo().getUnit());

		if (num <= 0) {
			logger.warn("出库失败！库存中没有该药品 !!");

			// DrugRecordLog drugRecordLog = new DrugRecordLog();
			// drugRecordLog.setDrugInfo(drugTimes.getDrugInfo());
			// drugRecordLog.setNum(t.getNumber());
			// drugRecordLog.setOpOrder(order);
			// drugRecordLog.setOpType(data.getOpType());
			// drugRecordLog.setPrice(data.getPrice());
			// drugRecordLog.setStatus(DrugRecordLog.STATUS_SUCC);
			// drugRecordLog.setReason("出库失败！库存中没有该药品 !!");
			//
			// drugRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);

			return null;
		} else if (t.getNumber() > num) {
			logger.warn("出库失败！库存不足 !!");

			// DrugRecordLog drugRecordLog = new DrugRecordLog();
			// drugRecordLog.setDrugInfo(data.getDrugInfo());
			// drugRecordLog.setNum(data.getNum());
			// drugRecordLog.setOpOrder(order);
			// drugRecordLog.setOpType(data.getOpType());
			// drugRecordLog.setPrice(data.getPrice());
			// drugRecordLog.setStatus(DrugRecordLog.STATUS_SUCC);
			// drugRecordLog.setReason("出库失败！库存不足 ["+data.getNum()+"] !!");
			//
			// drugRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			return null;
		} else {
			data.setNum(t.getNumber() * -1);
		}

		data.setOpType(DrugInRecord.OP_TYPE_OUT);
		data.setDrugLotNo(drugTimes.getDrugLotNo());
		data.setDrugSpecInfo(drugTimes.getDrugSpecInfo());
		// data.setExpireTime(drugTimes.getExpireTime());
		data.setUnit(drugTimes.getUnit());
		data.setPrice(t.getPrice());
		// data.setStatus(DrugInRecord.STATUS_NEW);

		// ~~~
		return data;
	}

	private boolean checkEmpDrugUseRecord(
			List<EmpDrugUseRecord> empDrugUseRecordList, String userId) {
		if (empDrugUseRecordList == null) {
			return false;
		}
		boolean check = true;

		for (EmpDrugUseRecord m : empDrugUseRecordList) {
			EmpDrugUseRecord t = super.getById(m.getId());
			// 如果dataId 相同，则更新
			if (t != null && !DataTypeUtil.isBlank(m.getId())) {

				// BeanUtils.copyNotNullProperties(m, t,
				// new String[] { "createtime" });

				/**
				 * 同时去更新库存中的记录
				 */
				DrugOutRecord drugRecord = saveDrugRecord(t, userId);
				if (drugRecord == null) {
					check = false;
					break;
				}

			} else {
				// 如果同就诊编号, 且同drugCode，则更新
				List<EmpDrugUseRecord> list = findByCondition(m.getMedicalId(),
						m.getDrugTimes().getId());
				if (list.size() > 0) {
					for (EmpDrugUseRecord data : list) {
						if (data.getMedicalId() > 0
								&& data.getDrugTimes() != null) {
							t = data;

							// BeanUtils.copyNotNullProperties(m, t, new
							// String[] {
							// "createtime", "medicalId", "id" });

							DrugOutRecord drugRecord = saveDrugRecord(t, userId);
							if (drugRecord == null) {
								check = false;
								break;
							}
						}
					}
				} else {
					/**
					 * 同时去更新库存中的记录
					 */
					DrugOutRecord drugRecord = saveDrugRecord(t, userId);
					if (drugRecord == null) {
						check = true;
						break;
					}
				}
			}

		}

		if (!check) {
			logger.warn("出库失败！库存不足后者没有找到这个批次的药品，请核查 !!");
			return false;
		}

		return true;
	}

	/**
	 * 如果有就修改，没有数据就新增
	 */
	@Override
	public void saveEmpDrugUseRecord(
			List<EmpDrugUseRecord> empDrugUseRecordList, String userId) {

		if (!checkEmpDrugUseRecord(empDrugUseRecordList, userId)) {
			return;
		}
		for (EmpDrugUseRecord m : empDrugUseRecordList) {
			EmpDrugUseRecord t = super.getById(m.getId());
			// 如果dataId 相同，则更新
			if (t != null && !DataTypeUtil.isBlank(m.getId())) {

				/**
				 * 同时去更新库存中的记录
				 */
				DrugOutRecord data = saveDrugRecord(t, userId);
				if (data != null) {
					((DrugOutRecordServiceI) drugRecordServiceI)
							.saveDrugRecordAndRelStore(data, userId);
				}
				BeanUtils.copyNotNullProperties(m, t,
						new String[] { "createtime" });
				super.update(t);

			} else {
				// 如果同就诊编号, 且同drugCode，则更新
				List<EmpDrugUseRecord> list = findByCondition(m.getMedicalId(),
						m.getDrugTimes().getId());
				if (list.size() > 0) {
					for (EmpDrugUseRecord data : list) {
						if (data.getMedicalId() > 0
								&& data.getDrugTimes() != null) {
							t = data;

							/**
							 * 同时去更新库存中的记录
							 */
							DrugOutRecord drugRecord = saveDrugRecord(t, userId);
							if (data != null) {
								((DrugOutRecordServiceI) drugRecordServiceI)
										.saveDrugRecordAndRelStore(drugRecord,
												userId);
							}

							BeanUtils.copyNotNullProperties(m, t, new String[] {
									"createtime", "medicalId", "id" });
							super.update(t);
						}
					}
				} else {
					this.saveEmpDrugUseRecord(m, userId);
					/**
					 * 同时去更新库存中的记录
					 */
					DrugOutRecord data = saveDrugRecord(m, userId);
					if (data != null) {
						((DrugOutRecordServiceI) drugRecordServiceI)
								.saveDrugRecordAndRelStore(data, userId);
					}
				}
			}

		}

		// /**
		// * 修改批次表中的剩余记录
		// */
		// DrugTimes drugTimes = null;
		// drugTimes = drugTimesServiceI.getDrugTimesByCond(drugRecord
		// .getDrugSpecInfo().getSpecId(),
		// drugRecord.getDrugLotNo(), drugRecord.getUnit());
		//
		// if (drugTimes != null) {
		// // 修改操作
		// drugTimes.setPrice(drugRecord.getPrice());
		// drugTimes.setContact(drugRecord.getContact());
		// drugTimes.setSpecification(drugRecord.getDrugSpecInfo()
		// .getSpecification());
		// drugTimes.setUnit(drugRecord.getUnit());
		// if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_IN){
		// drugTimes.setTotal(drugTimes.getTotal() + drugRecord.getNum());
		// drugTimes.setRest(drugTimes.getRest() + drugRecord.getNum());
		// }else if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_OUT){
		// drugTimes.setRest(drugTimes.getRest() + drugRecord.getNum());
		// }else if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_USE){
		// drugTimes.setRest(drugTimes.getRest() + drugRecord.getNum());
		// }
		//
		// drugTimes.setExpireTime(drugRecord.getExpireTime());
		// drugTimes.setProduceTime(drugRecord.getProduceTime());
		//
		// drugTimesServiceI.updateDrugTimes(drugTimes);
		//
		// logger.info("修改药品的批次信息 !!");
		//
		// }
	}

	@Override
	public List<EmpDrugUseRecord> findEmpDrugUseRecordByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpDrugUseRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpDrugUseRecord> findEmpDrugUseRecordByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpDrugUseRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpDrugUseRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpDrugUseRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public OpResult saveEmpDrugUseRecord2(
			List<EmpDrugUseRecord> empDrugUseRecordList, String userId) {
		if (!checkEmpDrugUseRecord(empDrugUseRecordList, userId)) {
			return new OpResult(false, "库存不足，请检查 !!");
		}
		
		saveEmpDrugUseRecord(empDrugUseRecordList, userId);

		return new OpResult(true, "");
	}

	// @Override
	// public List<EmpDrugUseRecord> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from EmpDrugUseRecord t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from EmpDrugUseRecord t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
