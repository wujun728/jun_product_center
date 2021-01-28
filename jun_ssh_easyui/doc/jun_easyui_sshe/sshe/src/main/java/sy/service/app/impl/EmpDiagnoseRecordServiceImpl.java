package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpDiagnoseRecord;
import sy.model.base.Syuser;
import sy.service.app.EmpDiagnoseRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpDiagnoseRecordServiceImpl extends
		BaseServiceImpl<EmpDiagnoseRecord> implements EmpDiagnoseRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpDiagnoseRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpDiagnoseRecord> EmpDiagnoseRecordDao;

	@Override
	public void updateEmpDiagnoseRecord(EmpDiagnoseRecord EmpDiagnoseRecord) {
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecord.getMedicalId())) {
			EmpDiagnoseRecord t = getById(EmpDiagnoseRecord.getMedicalId());
			BeanUtils.copyNotNullProperties(EmpDiagnoseRecord, t,
					new String[] { "createtime" });
			
			super.update(t);
		}
	}
	
	@Override
	public void saveEmpDiagnoseRecord(EmpDiagnoseRecord EmpDiagnoseRecord,
			String userId) {
		EmpDiagnoseRecord.setOpUserId(userId);
		EmpDiagnoseRecord.setStatus(EmpDiagnoseRecord.STATUS_NEW);
//		String order = getOrder();
//
//		logger.debug("获得流水号 :" + order);
//		EmpDiagnoseRecord.setOpOrder(order);
			
		save(EmpDiagnoseRecord);
	}

	synchronized public String getOrder() {
		List<Map> list = EmpDiagnoseRecordDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<EmpDiagnoseRecord> findEmpDiagnoseRecordByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpDiagnoseRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpDiagnoseRecord> findEmpDiagnoseRecordByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpDiagnoseRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}
	
	@Override
	public Long countEmpDiagnoseRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpDiagnoseRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<EmpDiagnoseRecord> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from EmpDiagnoseRecord t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from EmpDiagnoseRecord t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
