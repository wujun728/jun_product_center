package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugInRecord;
import sy.model.app.DrugInRecordLog;
import sy.model.app.DrugInfo;
import sy.model.app.DrugSpecInfo;
import sy.model.app.DrugStore;
import sy.model.app.DrugTimes;
import sy.model.base.Syuser;
import sy.service.app.DrugInRecordServiceI;
import sy.service.app.DrugInRecordLogServiceI;
import sy.service.app.DrugSpecInfoServiceI;
import sy.service.app.DrugStoreServiceI;
import sy.service.app.DrugTimesServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class DrugInRecordServiceImpl extends BaseServiceImpl<DrugInRecord>
		implements DrugInRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugInRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugInRecord> drugInRecord;

	@Autowired
	private DrugStoreServiceI drugStoreServiceI;

	@Autowired
	private DrugSpecInfoServiceI drugSpecInfoServiceI;

	@Autowired
	private DrugInRecordLogServiceI drugInRecordLogServiceI;

	@Autowired
	private DrugTimesServiceI drugTimesServiceI;

	@Override
	public void updateDrugRecord(DrugInRecord drugRecord) {
		if (!DataTypeUtil.isBlank(drugRecord.getId())) {
			DrugInRecord t = getById(drugRecord.getId());

			BeanUtils.copyNotNullProperties(drugRecord, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	/**
	 * 药品出入库操作， 放到一个事务中进行
	 * 
	 * @param drugRecord
	 * @param userId
	 */
	@Override
	public void saveDrugRecordAndRelStore(DrugInRecord drugRecord, String userId) {
		Syuser syuser = new Syuser(userId);
		drugRecord.setSyuser(syuser);
		drugRecord.setStatus(DrugInRecord.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		drugRecord.setOpOrder(order);
		save(drugRecord);
		String reason = "";
		byte opResult = DrugInRecordLog.STATUS_SUCC;

		if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_PANDIAN) {

			/**
			 * 修改库存的盘点数据
			 */
			List<DrugStore> drugStoreList = drugStoreServiceI
					.getDrugStoreByCond(drugRecord.getDrugSpecInfo().getSpecId(),
							drugRecord.getDrugLotNo(),drugRecord.getUnit());
			if (drugStoreList != null && drugStoreList.size() > 0) {
				if (drugStoreList.size() > 1) {
					reason = "貌似有重复的药品数据,请检查数据正确性 !!";
					logger.error(reason);
					opResult = DrugInRecordLog.STATUS_FAILD;
				} else {
					DrugStore drugStore = drugStoreList.get(0);
					// 增加数量; 支持负数 !!
					drugStore
							.setNum2(drugStore.getNum2() + drugRecord.getNum());
					if (drugRecord.getNum() < 0) {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]减少了["
								+ drugRecord.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					} else {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]增加了["
								+ drugRecord.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					}
					
					drugStore.setCustomerInfo(drugRecord.getCustomerInfo());
					drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugRecord.getOpType());
					logger.info(reason);
					opResult = DrugInRecordLog.STATUS_SUCC;
				}
			} else {
				logger.warn("库存中找不到该药品和批次。");
				opResult = DrugInRecordLog.STATUS_FAILD;
			}

			/**
			 * 如果是盘点。 则整理库存的盘点数量
			 */
			DrugInRecordLog drugRecordLog = new DrugInRecordLog();
			drugRecordLog.setDrugSpecInfo(drugRecord.getDrugSpecInfo());
			drugRecordLog.setNum(drugRecord.getNum());
			drugRecordLog.setOpOrder(order);
			drugRecordLog.setOpType(drugRecord.getOpType());
			drugRecordLog.setPrice(drugRecord.getPrice());
			drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
			drugRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
			drugRecordLog.setDrugLotNo(drugRecord.getDrugLotNo());
			drugRecordLog.setCustomerInfo(drugRecord.getCustomerInfo());

			drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			return;
		} else if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_JIUZHG) {
			// 纠正操作， 强制修改库存数据为盘点数据

			/**
			 * 修改库存的库存数据
			 */
			List<DrugStore> drugStoreList = drugStoreServiceI
					.getDrugStoreByCond(drugRecord.getDrugSpecInfo().getSpecId(),
							drugRecord.getDrugLotNo(), drugRecord.getUnit());
			if (drugStoreList != null && drugStoreList.size() > 0) {
				if (drugStoreList.size() > 1) {
					reason = "貌似有重复的药品数据,请检查数据正确性 !!";
					logger.error(reason);
					opResult = DrugInRecordLog.STATUS_FAILD;
				} else {
					DrugStore drugStore = drugStoreList.get(0);
					// 增加数量; 支持负数 !!
					drugStore.setNum(drugStore.getNum2());
					if (drugRecord.getNum() < 0) {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]减少了["
								+ drugRecord.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					} else {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]增加了["
								+ drugRecord.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					}
					drugStore.setCustomerInfo(drugRecord.getCustomerInfo());
					drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugRecord.getOpType());
					logger.info(reason);
					opResult = DrugInRecordLog.STATUS_SUCC;
				}
			} else {
				logger.warn("库存中找不到该药品和批次。");
				opResult = DrugInRecordLog.STATUS_FAILD;
			}

			/**
			 * 如果是盘点。 则整理库存的盘点数量
			 */
			DrugInRecordLog drugRecordLog = new DrugInRecordLog();
			drugRecordLog.setDrugSpecInfo(drugRecord.getDrugSpecInfo());
			drugRecordLog.setNum(drugRecord.getNum());
			drugRecordLog.setOpOrder(order);
			drugRecordLog.setOpType(drugRecord.getOpType());
			drugRecordLog.setPrice(drugRecord.getPrice());
			drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
			drugRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
			drugRecordLog.setDrugLotNo(drugRecord.getDrugLotNo());
			drugRecordLog.setCustomerInfo(drugRecord.getCustomerInfo());
			
			drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			return;
		}
		/**
		 * 修改库存的数据
		 */
		List<DrugStore> drugStoreList = drugStoreServiceI.getDrugStoreByCond(
				drugRecord.getDrugSpecInfo().getSpecId(),
				drugRecord.getDrugLotNo(),drugRecord.getUnit());
		if (drugStoreList != null && drugStoreList.size() > 0) {
			if (drugStoreList.size() > 1) {
				reason = "貌似有重复的药品数据,请检查数据正确性 !!";
				logger.error(reason);
				opResult = DrugInRecordLog.STATUS_FAILD;
			} else {
				DrugStore drugStore = drugStoreList.get(0);
				// 增加数量; 支持负数 !!
				drugStore.setNum(drugStore.getNum() + drugRecord.getNum());
				if (drugRecord.getNum() < 0) {
					reason = "药品["
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugCode()
							+ ","
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugName() + "]减少了["
							+ drugRecord.getNum() + "],库存量["
							+ drugStore.getNum() + "]";
					logger.info(reason);
				} else {
					reason = "药品["
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugCode()
							+ ","
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugName() + "]增加了["
							+ drugRecord.getNum() + "],库存量["
							+ drugStore.getNum() + "]";
					logger.info(reason);
				}
				drugStore.setCustomerInfo(drugRecord.getCustomerInfo());
				drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugRecord.getOpType());
				logger.info(reason);
				opResult = DrugInRecordLog.STATUS_SUCC;
			}
		} else {

			// 没有找到库存中有这个药品， 那么新增一个吧
			DrugStore drugStore = new DrugStore();
			DrugSpecInfo drugSpecInfo = null;
			if (drugRecord.getDrugSpecInfo() == null) {
				drugSpecInfo = drugSpecInfoServiceI.getById(drugRecord
						.getDrugSpecInfo().getSpecId());
				if (drugSpecInfo == null) {
					reason = "根本就没有这个药品，你无法入库,请先增加该药品的信息!!";
					logger.error(reason);
					opResult = DrugInRecordLog.STATUS_FAILD;

					DrugInRecordLog drugRecordLog = new DrugInRecordLog();
					drugRecordLog.setDrugSpecInfo(drugRecord.getDrugSpecInfo());
					drugRecordLog.setNum(drugRecord.getNum());
					drugRecordLog.setOpOrder(order);
					drugRecordLog.setOpType(drugRecord.getOpType());
					drugRecordLog.setPrice(drugRecord.getPrice());
					drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
					drugRecordLog.setReason(reason);
					drugRecordLog.setDrugLotNo(drugRecord.getDrugLotNo());
					drugRecordLog.setCustomerInfo(drugRecord.getCustomerInfo());
					drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
					return;
				}
			} else {
				drugSpecInfo = drugRecord.getDrugSpecInfo();
			}
			drugStore.setDrugSpecInfo(drugSpecInfo);
			// 增加数量; 支持负数 !!
			drugStore.setNum(drugStore.getNum() + drugRecord.getNum());
			drugStore.setUnit(drugRecord.getUnit());
			if (drugRecord.getNum() < 0) {
				reason = "药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode()
						+ ","
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugName() + "]减少了[" + drugRecord.getNum()
						+ "],库存量[" + drugStore.getNum() + "]";
				logger.info(reason);
			} else {
				reason = "药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode()
						+ ","
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugName() + "]增加了[" + drugRecord.getNum()
						+ "],库存量[" + drugStore.getNum() + "]";
				logger.info(reason);
			}
			
			drugStore.setSpecification(drugRecord.getDrugSpecInfo()
					.getSpecification());
			drugStore.setPrice(drugRecord.getPrice());
			drugStore.setDrugLotNo(drugRecord.getDrugLotNo());
			drugStore.setCustomerInfo(drugRecord.getCustomerInfo());
			drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugRecord.getOpType());

			logger.info(reason);
			opResult = DrugInRecordLog.STATUS_SUCC;
		}
		/**
		 * 增加批次表
		 */
		DrugTimes drugTimes = null;
		drugTimes = drugTimesServiceI.getDrugTimesByCond(drugRecord
				.getDrugSpecInfo().getSpecId(),
				drugRecord.getDrugLotNo(), drugRecord.getUnit());
		
		if (drugTimes != null) {
			// 修改操作
			drugTimes.setPrice(drugRecord.getPrice());
			drugTimes.setContact(drugRecord.getContact());
			drugTimes.setSpecification(drugRecord.getDrugSpecInfo()
					.getSpecification());
			drugTimes.setUnit(drugRecord.getUnit());
			 if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_IN){
				 drugTimes.setTotal(drugTimes.getTotal() + drugRecord.getNum());	
				 drugTimes.setRest(drugTimes.getRest() +  drugRecord.getNum());	
			 }else  if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_OUT){
				 drugTimes.setRest(drugTimes.getRest() + drugRecord.getNum());
			 }else  if (drugRecord.getOpType() == DrugInRecord.OP_TYPE_USE){
				 drugTimes.setRest(drugTimes.getRest() + drugRecord.getNum());
			 }	
			 
			drugTimes.setExpireTime(drugRecord.getExpireTime());
			drugTimes.setProduceTime(drugRecord.getProduceTime());

			drugTimesServiceI.updateDrugTimes(drugTimes);
			
			logger.info("修改药品的批次信息 !!");
			
		} else {
			// 新增操作
			drugTimes = new DrugTimes();
			BeanUtils.copyProperties(drugRecord, drugTimes);

			DrugSpecInfo drugSpecInfo = null;
			if (drugRecord.getDrugSpecInfo() == null) {
				drugSpecInfo = drugSpecInfoServiceI.getById(drugRecord
						.getDrugSpecInfo().getSpecId());
				if (drugSpecInfo == null) {
					reason = "根本就没有这个药品，你无法入库,请先增加该药品的信息!!";
					logger.error(reason);
					opResult = DrugInRecordLog.STATUS_FAILD;
					return;
				}
				drugTimes.setContact(drugRecord.getContact());
				drugTimes.setTotal(drugRecord.getNum());
				drugTimes.setRest(drugRecord.getNum());
				drugTimes.setSpecification(drugRecord.getDrugSpecInfo()
						.getSpecification());
				drugTimes.setExpireTime(drugRecord.getExpireTime());
				drugTimes.setProduceTime(drugRecord.getProduceTime());
			} else {
				drugSpecInfo = drugRecord.getDrugSpecInfo();
				drugTimes.setContact(drugRecord.getContact());
				drugTimes.setTotal(drugRecord.getNum());
				drugTimes.setRest(drugRecord.getNum());
				drugTimes.setSpecification(drugRecord.getDrugSpecInfo()
						.getSpecification());
				drugTimes.setExpireTime(drugRecord.getExpireTime());
				drugTimes.setProduceTime(drugRecord.getProduceTime());
			}

			drugTimesServiceI.saveDrugTimes(drugTimes, userId);

			logger.info("新增药品的批次信息 !!");
		}

		DrugInRecordLog drugRecordLog = new DrugInRecordLog();
		drugRecordLog.setDrugSpecInfo(drugRecord.getDrugSpecInfo());
		drugRecordLog.setNum(drugRecord.getNum());
		drugRecordLog.setOpOrder(order);
		drugRecordLog.setOpType(drugRecord.getOpType());
		drugRecordLog.setPrice(drugRecord.getPrice());
		drugRecordLog.setStatus(opResult);
		drugRecordLog.setReason(reason);
		drugRecordLog.setDrugLotNo(drugRecord.getDrugLotNo());
		drugRecordLog.setPrice(drugRecord.getPrice());
		drugRecordLog.setUnit(drugRecord.getUnit());
		drugRecordLog.setCustomerInfo(drugRecord.getCustomerInfo());
		
		drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
	}

	@Override
	public void saveDrugRecord(DrugInRecord drugRecord, String userId) {
		Syuser syuser = new Syuser(userId);
		drugRecord.setSyuser(syuser);
		drugRecord.setStatus(DrugInRecord.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		drugRecord.setOpOrder(order);
		save(drugRecord);
	}
	
	synchronized public String getOrder() {
		List<Map> list = drugInRecord
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='drug_in_record')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}
	
	@Override
	public List<DrugInRecord> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugInRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugInRecord> findDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugInRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugInRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
