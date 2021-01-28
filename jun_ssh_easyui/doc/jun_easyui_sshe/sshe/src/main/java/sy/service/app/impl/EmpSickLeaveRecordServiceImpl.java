package sy.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpSickLeaveRecord;
import sy.model.base.Syuser;
import sy.service.app.EmpSickLeaveRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 病假管理业务逻辑
 * 
 */
@Service
public class EmpSickLeaveRecordServiceImpl extends BaseServiceImpl<EmpSickLeaveRecord>
		implements EmpSickLeaveRecordServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Override
	public void updateEmpSickLeaveRecord(EmpSickLeaveRecord EmpSickLeaveRecord) {
		if (!DataTypeUtil.isBlank(EmpSickLeaveRecord.getId())) {
			EmpSickLeaveRecord t = getById(EmpSickLeaveRecord.getId());
			BeanUtils.copyNotNullProperties(EmpSickLeaveRecord, t,
					new String[] { "createtime" });
				
			super.update(t);
		}
	}
	
	@Override
	public void saveEmpSickLeaveRecord(EmpSickLeaveRecord EmpSickLeaveRecord, String userId) {
		EmpSickLeaveRecord.setOpUserId(userId);
		save(EmpSickLeaveRecord);
	}
	
	@Override
	public List<EmpSickLeaveRecord> findEmpSickLeaveRecordByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from EmpSickLeaveRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpSickLeaveRecord> findEmpSickLeaveRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpSickLeaveRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpSickLeaveRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpSickLeaveRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	
	
}
