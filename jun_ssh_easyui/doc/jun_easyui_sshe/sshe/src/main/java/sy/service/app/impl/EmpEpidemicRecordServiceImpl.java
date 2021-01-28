package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpEpidemicRecord;
import sy.model.base.Syuser;
import sy.service.app.EmpEpidemicRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 流行病业务逻辑
 * 
 */
@Service
public class EmpEpidemicRecordServiceImpl extends
		BaseServiceImpl<EmpEpidemicRecord> implements EmpEpidemicRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpEpidemicRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpEpidemicRecord> EmpEpidemicRecordDao;

	@Override
	public void updateEmpEpidemicRecord(EmpEpidemicRecord EmpEpidemicRecord) {
		if (!DataTypeUtil.isBlank(EmpEpidemicRecord.getId())) {
			EmpEpidemicRecord t = getById(EmpEpidemicRecord.getId());
			BeanUtils.copyNotNullProperties(EmpEpidemicRecord, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpEpidemicRecord(EmpEpidemicRecord EmpEpidemicRecord,
			String userId) {
		EmpEpidemicRecord.setOpUserId(userId);
		EmpEpidemicRecord.setStatus(EmpEpidemicRecord.STATUS_NEW);

		save(EmpEpidemicRecord);
	}
	
	@Override
	public List<EmpEpidemicRecord> findEmpEpidemicRecordByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpEpidemicRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpEpidemicRecord> findEmpEpidemicRecordByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpEpidemicRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpEpidemicRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpEpidemicRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
