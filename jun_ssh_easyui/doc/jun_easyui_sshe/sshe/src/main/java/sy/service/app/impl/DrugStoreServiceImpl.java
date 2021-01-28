package sy.service.app.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugInRecord;
import sy.model.app.DrugInRecordLog;
import sy.model.app.DrugOutRecordLog;
import sy.model.app.DrugPdRecordLog;
import sy.model.app.DrugStore;
import sy.model.base.Syuser;
import sy.service.app.DrugInRecordLogServiceI;
import sy.service.app.DrugOutRecordLogServiceI;
import sy.service.app.DrugPdRecordLogServiceI;
import sy.service.app.DrugStoreServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class DrugStoreServiceImpl extends BaseServiceImpl<DrugStore> implements
		DrugStoreServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugStoreServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugStore> DrugStoreDao;

	@Autowired
	private DrugInRecordLogServiceI drugInRecordLogServiceI;

	@Autowired
	private DrugOutRecordLogServiceI drugOutRecordLogServiceI;

	@Autowired
	private DrugPdRecordLogServiceI drugPdRecordLogServiceI;

	@Override
	public List<DrugStore> getDrugStoreByCond(int specId, String drugLotNo,
			String unit) {
		String hql = "select distinct t from DrugStore t  ";
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#drugSpecInfo#specId_I_EQ", specId + "");
		hqlFilter.addFilter("QUERY_t#drugLotNo_S_EQ", drugLotNo + "");
		hqlFilter.addFilter("QUERY_t#unit_S_EQ", unit + "");
		return findByFilter(hqlFilter);
	}

	/**
	 * 如果库存没有该药品，则返回数量 -1 。
	 * 
	 * 如果有该药品，返回真实的数量
	 * 
	 * @param drugCode
	 * @return
	 */
	@Override
	public int getDrugStoreCount(int specId, String drugTLotNo, String unit) {
		List<DrugStore> list = this
				.getDrugStoreByCond(specId, drugTLotNo, unit);
		int num = 0;
		if (list != null) {
			if (list.size() > 1) {
				logger.warn("貌似该药品["
						+ list.get(0).getDrugSpecInfo().getDrugInfo()
								.getDrugCode()
						+ ","
						+ list.get(0).getDrugSpecInfo().getDrugInfo()
								.getDrugName() + "]的库存数量有些问题,存在多个库存记录!!");
			}
			for (DrugStore drugStore : list) {
				num += drugStore.getNum();
			}

			return num;
		} else {
			return num;
		}
	}

	@Override
	public void saveOrUpdateDrugStore(DrugStore drugStore, String userId,
			byte opType) {
		if (!DataTypeUtil.isBlank(drugStore.getStoreId())) {
			DrugStore t = getById(drugStore.getStoreId());
			BeanUtils.copyNotNullProperties(drugStore, t,
					new String[] { "createtime" });

			update(t);

			if (opType == DrugInRecord.OP_TYPE_IN) {
				DrugInRecordLog drugRecordLog = new DrugInRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("修改药品["
						+ t.getDrugSpecInfo().getDrugInfo().getDrugCode()
						+ "]的库存为[" + t.getNum() + "]");
				drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_OUT) {
				DrugOutRecordLog drugRecordLog = new DrugOutRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("修改药品["
						+ t.getDrugSpecInfo().getDrugInfo().getDrugCode()
						+ "]的库存为[" + t.getNum() + "]");
				drugOutRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_JIUZHG) {
				DrugInRecordLog drugRecordLog = new DrugInRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("修改药品["
						+ t.getDrugSpecInfo().getDrugInfo().getDrugCode()
						+ "]的库存为[" + t.getNum() + "]");
				drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_PANDIAN) {
				/**
				 * 如果是盘点。 则整理库存的盘点数量
				 */
				DrugPdRecordLog drugRecordLog = new DrugPdRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugOutRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());

				drugPdRecordLogServiceI.saveDrugPdRecordLog(drugRecordLog,
						userId);
			} else if (opType == DrugInRecord.OP_TYPE_USE) {
				DrugOutRecordLog drugRecordLog = new DrugOutRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("修改药品["
						+ t.getDrugSpecInfo().getDrugInfo().getDrugCode()
						+ "]的库存为[" + t.getNum() + "]");
				drugOutRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			}

		} else {

			drugStore.setSyuser(new Syuser(userId));
			drugStore.setStatus(DrugStore.STATUS_NEW);
			save(drugStore);

			if (opType == DrugInRecord.OP_TYPE_IN) {
				DrugInRecordLog drugRecordLog = new DrugInRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_ADD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setReason("修改药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode() + "]的库存为[" + drugStore.getNum()
						+ "]");
				drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_OUT) {
				DrugOutRecordLog drugRecordLog = new DrugOutRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("修改药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo().getDrugCode()
						+ "]的库存为[" + drugStore.getNum() + "]");
				drugOutRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_JIUZHG) {
				DrugInRecordLog drugRecordLog = new DrugInRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_ADD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugInRecordLog.STATUS_SUCC);
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());
				drugRecordLog.setReason("修改药品["
						+ drugStore.getDrugSpecInfo().getDrugInfo()
								.getDrugCode() + "]的库存为[" + drugStore.getNum()
						+ "]");
				drugInRecordLogServiceI.saveDrugRecord(drugRecordLog, userId);
			} else if (opType == DrugInRecord.OP_TYPE_PANDIAN) {
				/**
				 * 如果是盘点。 则整理库存的盘点数量
				 */
				DrugPdRecordLog drugRecordLog = new DrugPdRecordLog();
				drugRecordLog.setDrugSpecInfo(drugStore.getDrugSpecInfo());
				drugRecordLog.setNum(drugStore.getNum());
				drugRecordLog.setOpOrder("");
				drugRecordLog.setOpType(drugRecordLog.OP_TYPE_UPD);
				drugRecordLog.setPrice(new BigDecimal(0.0));
				drugRecordLog.setStatus(DrugOutRecordLog.STATUS_SUCC);
				drugRecordLog.setReason("如果是盘点。 则整理库存的盘点数量");
				drugRecordLog.setDrugLotNo(drugStore.getDrugLotNo());

				drugPdRecordLogServiceI.saveDrugPdRecordLog(drugRecordLog,
						userId);
			} else if (opType == DrugInRecord.OP_TYPE_USE) {

			}

		}
	}

	@Override
	public void updateDrugStore(DrugStore drugStore) {
		if (!DataTypeUtil.isBlank(drugStore.getStoreId())) {
			DrugStore t = getById(drugStore.getStoreId());
			BeanUtils.copyNotNullProperties(drugStore, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveDrugStore(DrugStore DrugStore, String userId) {
		DrugStore.setSyuser(new Syuser(userId));
		DrugStore.setStatus(DrugStore.STATUS_NEW);

		save(DrugStore);
	}

	synchronized public String getOrder() {
		List<Map> list = DrugStoreDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<DrugStore> findDrugStoreByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from DrugStore t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugStore> findDrugStoreByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugStore t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugStoreByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugStore t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<DrugStore> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from DrugStore t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from DrugStore t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
