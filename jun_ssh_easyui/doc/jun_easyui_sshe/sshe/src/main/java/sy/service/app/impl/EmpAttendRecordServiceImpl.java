package sy.service.app.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpAttendRecord;
import sy.model.base.Syuser;
import sy.service.app.EmpAttendRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 员工考勤记录逻辑
 * 
 */
@Service
public class EmpAttendRecordServiceImpl extends
		BaseServiceImpl<EmpAttendRecord> implements EmpAttendRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpAttendRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpAttendRecord> empAttendRecordDao;
	
	@Override
	public EmpAttendRecord findByUserAndDate(String userId, String yyyymmdd){
		String hql = "select distinct t from EmpAttendRecord t  where t.syuser.id = :userId and t.yyyymmdd = :yyyymmdd";
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("yyyymmdd", yyyymmdd);
		
		List<EmpAttendRecord> list = super.find(hql, map);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		
		return null;
	}
	@Override
	public void updateEmpAttendRecord(EmpAttendRecord EmpAttendRecord) {
		if (!DataTypeUtil.isBlank(EmpAttendRecord.getId())) {
			EmpAttendRecord t = getById(EmpAttendRecord.getId());
			BeanUtils.copyNotNullProperties(EmpAttendRecord, t,
					new String[] { "createtime","yyyymmdd"});
			
			super.update(t);
		}
	}
	
	@Override
	public void saveEmpAttendRecord(EmpAttendRecord EmpAttendRecord,
			String userId) {
		EmpAttendRecord.setOpUserId(userId);
		
		save(EmpAttendRecord);
	}
	
	@Override
	public List<EmpAttendRecord> findEmpAttendRecordByFilterNoDept(
			HqlFilter hqlFilter, int page, int rows) {
		
		String hql = "select distinct t from EmpAttendRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpAttendRecord> findEmpAttendRecordByFilterNoDept(HqlFilter hqlFilter) {
		
		String hql = "select distinct t from EmpAttendRecord t";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpAttendRecordByFilterNoDept(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpAttendRecord t     ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	
	@Override
	public List<EmpAttendRecord> findEmpAttendRecordByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		hqlFilter.setNoWhere(true);
		String hql = "select distinct t from EmpAttendRecord t  , Syorganization o where 1=1 and t.syuser in elements(o.syusers)";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpAttendRecord> findEmpAttendRecordByFilter(HqlFilter hqlFilter) {
		hqlFilter.setNoWhere(true);
		String hql = "select distinct t from EmpAttendRecord t , Syorganization o where 1=1 and t.syuser in elements(o.syusers) ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpAttendRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpAttendRecord t , Syorganization o where 1=1 and t.syuser in elements(o.syusers)  ";
		return count(hql + hqlFilter.getWhereHql(true), hqlFilter.getParams());
	}

}
