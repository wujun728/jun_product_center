package sy.service.app.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugPdRecordLog;
import sy.model.app.DrugSpecInfo;
import sy.model.app.DrugStore;
import sy.model.app.DrugStoreCheck;
import sy.model.app.DrugTimes;
import sy.model.base.Syuser;
import sy.service.app.DrugPdRecordLogServiceI;
import sy.service.app.DrugSpecInfoServiceI;
import sy.service.app.DrugStoreCheckRecordServiceI;
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
public class DrugStoreCheckRecordServiceImpl extends
		BaseServiceImpl<DrugStoreCheck> implements DrugStoreCheckRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugStoreCheckRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugStoreCheck> drugStoreCheckDao;

	@Autowired
	private DrugStoreServiceI drugStoreServiceI;

	@Autowired
	private DrugSpecInfoServiceI drugSpecInfoServiceI;

	@Autowired
	private DrugPdRecordLogServiceI drugPdRecordLogServiceI;

	@Autowired
	private DrugTimesServiceI drugTimesServiceI;

	@Override
	public void updateDrugStoreCheck(DrugStoreCheck drugStoreCheck) {
		if (!DataTypeUtil.isBlank(drugStoreCheck.getStoreId())) {
			DrugStoreCheck t = getById(drugStoreCheck.getStoreId());

			BeanUtils.copyNotNullProperties(drugStoreCheck, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	/**
	 * 药品出入库操作， 放到一个事务中进行
	 * 
	 * @param DrugStoreCheck
	 * @param userId
	 */
	@Override
	public void saveDrugStoreCheckAndRelStore(DrugStoreCheck drugStoreCheck,
			String userId) {
		Syuser syuser = new Syuser(userId);
		drugStoreCheck.setSyuser(syuser);
		drugStoreCheck.setStatus(DrugStoreCheck.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		drugStoreCheck.setOpOrder(order);
		
		save(drugStoreCheck);
		String reason = "";
		byte opResult = DrugPdRecordLog.STATUS_SUCC;

		if (drugStoreCheck.getOpType() == DrugStoreCheck.OP_TYPE_PANDIAN) {

			/**
			 * 修改库存的盘点数据
			 */
			List<DrugStore> drugStoreList = drugStoreServiceI
					.getDrugStoreByCond(drugStoreCheck.getDrugSpecInfo()
							.getSpecId(), drugStoreCheck.getDrugLotNo(),
							drugStoreCheck.getUnit());
			if (drugStoreList != null && drugStoreList.size() > 0) {
				if (drugStoreList.size() > 1) {
					reason = "貌似有重复的药品数据,请检查数据正确性 !!";
					logger.error(reason);
					opResult = DrugPdRecordLog.STATUS_FAILD;
				} else {
					DrugStore drugStore = drugStoreList.get(0);
					// 盘点强制修改数量; 支持负数 !!
					drugStore.setNum2(drugStoreCheck.getNum2());
					if (drugStoreCheck.getNum() < 0) {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]减少了["
								+ drugStoreCheck.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					} else {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]增加了["
								+ drugStoreCheck.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					}
					drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugStoreCheck.getOpType());
					logger.info(reason);
					opResult = DrugPdRecordLog.STATUS_SUCC;
				}
			} else {
				logger.warn("库存中找不到该药品和批次。");
				opResult = DrugPdRecordLog.STATUS_FAILD;
			}

			/**
			 * 如果是盘点。 则整理库存的盘点数量
			 */
			DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
			drugPdRecordLog.setDrugSpecInfo(drugStoreCheck.getDrugSpecInfo());
			drugPdRecordLog.setNum(drugStoreCheck.getNum2());
			drugPdRecordLog.setOpOrder(order);
			drugPdRecordLog.setOpType(drugStoreCheck.getOpType());
			drugPdRecordLog.setPrice(drugStoreCheck.getPrice());
			drugPdRecordLog.setStatus(DrugPdRecordLog.STATUS_SUCC);
			drugPdRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
			drugPdRecordLog.setUnit(drugStoreCheck.getUnit());
			drugPdRecordLog.setDrugLotNo(drugStoreCheck.getDrugLotNo());
			drugPdRecordLogServiceI
					.saveDrugPdRecordLog(drugPdRecordLog, userId);
			return;
		} else if (drugStoreCheck.getOpType() == DrugStoreCheck.OP_TYPE_JIUZHG) {
			// 纠正操作， 强制修改库存数据为盘点数据
			/**
			 * 修改库存的库存数据
			 */
			List<DrugStore> drugStoreList = drugStoreServiceI
					.getDrugStoreByCond(drugStoreCheck.getDrugSpecInfo()
							.getSpecId(), drugStoreCheck.getDrugLotNo(),
							drugStoreCheck.getUnit());
			if (drugStoreList != null && drugStoreList.size() > 0) {
				if (drugStoreList.size() > 1) {
					reason = "貌似有重复的药品数据,请检查数据正确性 !!";
					logger.error(reason);
					opResult = DrugPdRecordLog.STATUS_FAILD;
				} else {
					DrugStore drugStore = drugStoreList.get(0);
					// 增加数量; 支持负数 !!
					drugStore.setNum(drugStore.getNum2());
					if (drugStoreCheck.getNum() < 0) {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]减少了["
								+ drugStoreCheck.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					} else {
						reason = "药品["
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugCode()
								+ ","
								+ drugStore.getDrugSpecInfo().getDrugInfo()
										.getDrugName() + "]增加了["
								+ drugStoreCheck.getNum() + "],盘点的库存量["
								+ drugStore.getNum2() + "]";
						logger.info(reason);
					}
					drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugStoreCheck.getOpType());
					logger.info(reason);
					opResult = DrugPdRecordLog.STATUS_SUCC;
				}
			} else {
				logger.warn("库存中找不到该药品和批次。");
				opResult = DrugPdRecordLog.STATUS_FAILD;
			}

			/**
			 * 如果是盘点。 则整理库存的盘点数量
			 */
			DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
			drugPdRecordLog.setDrugSpecInfo(drugStoreCheck.getDrugSpecInfo());
			drugPdRecordLog.setNum(drugStoreCheck.getNum());
			drugPdRecordLog.setOpOrder(order);
			drugPdRecordLog.setOpType(drugStoreCheck.getOpType());
			drugPdRecordLog.setPrice(drugStoreCheck.getPrice());
			drugPdRecordLog.setStatus(DrugPdRecordLog.STATUS_SUCC);
			drugPdRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
			drugPdRecordLog.setUnit(drugStoreCheck.getUnit());
			drugPdRecordLog.setDrugLotNo(drugStoreCheck.getDrugLotNo());

			drugPdRecordLogServiceI
					.saveDrugPdRecordLog(drugPdRecordLog, userId);
			return;
		}
		/**
		 * 修改库存的数据
		 */
		List<DrugStore> drugStoreList = drugStoreServiceI.getDrugStoreByCond(
				drugStoreCheck.getDrugSpecInfo().getSpecId(),
				drugStoreCheck.getDrugLotNo(), drugStoreCheck.getUnit());
		if (drugStoreList != null && drugStoreList.size() > 0) {
			if (drugStoreList.size() > 1) {
				reason = "貌似有重复的药品数据,请检查数据正确性 !!";
				logger.error(reason);
				opResult = DrugPdRecordLog.STATUS_FAILD;
			} else {
				DrugStore drugStore = drugStoreList.get(0);
				// 增加数量; 支持负数 !!
				drugStore.setNum(drugStore.getNum() + drugStoreCheck.getNum());
				if (drugStoreCheck.getNum() < 0) {
					reason = "药品["
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugCode()
							+ ","
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugName() + "]减少了["
							+ drugStoreCheck.getNum() + "],库存量["
							+ drugStore.getNum() + "]";
					logger.info(reason);
				} else {
					reason = "药品["
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugCode()
							+ ","
							+ drugStore.getDrugSpecInfo().getDrugInfo()
									.getDrugName() + "]增加了["
							+ drugStoreCheck.getNum() + "],库存量["
							+ drugStore.getNum() + "]";
					logger.info(reason);
				}
				drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugStoreCheck.getOpType());
				logger.info(reason);
				opResult = DrugPdRecordLog.STATUS_SUCC;
			}
		} else {

			// 没有找到库存中有这个药品， 那么新增一个吧
			DrugStore drugStore = new DrugStore();
			DrugSpecInfo drugSpecInfo = null;
			if (drugStoreCheck.getDrugSpecInfo() == null) {
				drugSpecInfo = drugSpecInfoServiceI.getById(drugStoreCheck
						.getDrugSpecInfo().getSpecId());
				if (drugSpecInfo == null) {
					reason = "根本就没有这个药品，你无法入库,请先增加该药品的信息!!";
					logger.error(reason);
					opResult = DrugPdRecordLog.STATUS_FAILD;

					DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
					drugPdRecordLog.setDrugSpecInfo(drugStoreCheck
							.getDrugSpecInfo());
					drugPdRecordLog.setNum(drugStoreCheck.getNum());
					drugPdRecordLog.setOpOrder(order);
					drugPdRecordLog.setOpType(drugStoreCheck.getOpType());
					drugPdRecordLog.setPrice(drugStoreCheck.getPrice());
					drugPdRecordLog.setStatus(DrugPdRecordLog.STATUS_SUCC);
					drugPdRecordLog.setReason(reason);
					drugPdRecordLog.setUnit(drugStoreCheck.getUnit());
					drugPdRecordLog.setDrugLotNo(drugStoreCheck.getDrugLotNo());

					drugPdRecordLogServiceI.saveDrugPdRecordLog(
							drugPdRecordLog, userId);
					return;
				}
			} else {
				drugSpecInfo = drugStoreCheck.getDrugSpecInfo();
			}
			drugStore.setDrugSpecInfo(drugSpecInfo);
			// 增加数量; 支持负数 !!
			drugStore.setNum(drugStore.getNum() + drugStoreCheck.getNum());
			drugStore.setUnit(drugStoreCheck.getUnit());
			if (drugStoreCheck.getNum() < 0) {
				reason = "药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode()
						+ ","
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugName() + "]减少了["
						+ drugStoreCheck.getNum() + "],库存量["
						+ drugStore.getNum() + "]";
				logger.info(reason);
			} else {
				reason = "药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode()
						+ ","
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugName() + "]增加了["
						+ drugStoreCheck.getNum() + "],库存量["
						+ drugStore.getNum() + "]";
				logger.info(reason);
			}

			drugStore.setSpecification(drugStoreCheck.getDrugSpecInfo()
					.getSpecification());
			drugStore.setPrice(drugStoreCheck.getPrice());
			drugStore.setDrugLotNo(drugStoreCheck.getDrugLotNo());
			drugStoreServiceI.saveOrUpdateDrugStore(drugStore, userId,drugStoreCheck.getOpType());

			logger.info(reason);
			opResult = DrugPdRecordLog.STATUS_SUCC;
		}
		/**
		 * 增加批次表
		 */
		DrugTimes drugTimes = null;
		drugTimes = drugTimesServiceI.getDrugTimesByCond(drugStoreCheck
				.getDrugSpecInfo().getSpecId(), drugStoreCheck.getDrugLotNo(),
				drugStoreCheck.getUnit());

		if (drugTimes != null) {
			// 修改操作
			drugTimes.setPrice(drugStoreCheck.getPrice());

			drugTimes.setSpecification(drugStoreCheck.getDrugSpecInfo()
					.getSpecification());
			// drugTimes.setExpireTime(DrugStoreCheck.getExpireTime());
			// drugTimes.setProduceTime(DrugStoreCheck.getProduceTime());

			drugTimesServiceI.updateDrugTimes(drugTimes);

			logger.info("修改药品的批次信息 !!");

		} else {
			// 新增操作
			drugTimes = new DrugTimes();
			BeanUtils.copyProperties(drugStoreCheck, drugTimes);

			DrugSpecInfo drugSpecInfo = null;
			if (drugStoreCheck.getDrugSpecInfo() == null) {
				drugSpecInfo = drugSpecInfoServiceI.getById(drugStoreCheck
						.getDrugSpecInfo().getSpecId());
				if (drugSpecInfo == null) {
					reason = "根本就没有这个药品，你无法入库,请先增加该药品的信息!!";
					logger.error(reason);
					opResult = DrugPdRecordLog.STATUS_FAILD;
					return;
				}

				drugTimes.setSpecification(drugStoreCheck.getDrugSpecInfo()
						.getSpecification());
				// drugTimes.setExpireTime(DrugStoreCheck.getExpireTime());
				// drugTimes.setProduceTime(DrugStoreCheck.getProduceTime());
			} else {
				drugSpecInfo = drugStoreCheck.getDrugSpecInfo();
				drugTimes.setSpecification(drugStoreCheck.getDrugSpecInfo()
						.getSpecification());
				// drugTimes.setExpireTime(DrugStoreCheck.getExpireTime());
				// drugTimes.setProduceTime(DrugStoreCheck.getProduceTime());
			}

			drugTimesServiceI.saveDrugTimes(drugTimes, userId);

			logger.info("新增药品的批次信息 !!");
		}

		DrugPdRecordLog drugPdRecordLog = new DrugPdRecordLog();
		drugPdRecordLog.setDrugSpecInfo(drugStoreCheck.getDrugSpecInfo());
		drugPdRecordLog.setNum(drugStoreCheck.getNum());
		drugPdRecordLog.setOpOrder(order);
		drugPdRecordLog.setOpType(drugStoreCheck.getOpType());
		drugPdRecordLog.setPrice(drugStoreCheck.getPrice());
		drugPdRecordLog.setStatus(opResult);
		drugPdRecordLog.setReason(reason);
		drugPdRecordLog.setDrugLotNo(drugStoreCheck.getDrugLotNo());
		drugPdRecordLog.setPrice(drugStoreCheck.getPrice());
		drugPdRecordLog.setUnit(drugStoreCheck.getUnit());

		drugPdRecordLogServiceI.saveDrugPdRecordLog(drugPdRecordLog, userId);
	}

	@Override
	public void saveDrugStoreCheck(DrugStoreCheck DrugStoreCheck, String userId) {
		Syuser syuser = new Syuser(userId);
		DrugStoreCheck.setSyuser(syuser);
		DrugStoreCheck.setStatus(DrugStoreCheck.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		DrugStoreCheck.setOpOrder(order);
		save(DrugStoreCheck);
	}

	synchronized public String getOrder() {
		List<Map> list = drugStoreCheckDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='drug_store_check')+0,10,'0')) as seqtitle;");
		String s="";
		try {
			s = new String((byte[]) list.get(0).get("seqtitle"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String order = s;
		return order;
	}

	@Override
	public List<DrugStoreCheck> findDrugStoreCheckByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugStoreCheck t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugStoreCheck> findDrugStoreCheckByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugStoreCheck t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugStoreCheckByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugStoreCheck t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
