package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpJiabanRecordDetail;
import sy.model.base.Syuser;
import sy.service.app.EmpJiabanRecordDetailServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 加班请假操作明细业务逻辑
 * 
 */
@Service
public class EmpJiabanRecordDetailServiceImpl extends
		BaseServiceImpl<EmpJiabanRecordDetail> implements EmpJiabanRecordDetailServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpJiabanRecordDetailServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpJiabanRecordDetail> EmpJiabanRecordDetailDao;

	@Override
	public void updateEmpJiabanRecordDetail(EmpJiabanRecordDetail EmpJiabanRecordDetail) {
		if (!DataTypeUtil.isBlank(EmpJiabanRecordDetail.getId())) {
			EmpJiabanRecordDetail t = getById(EmpJiabanRecordDetail.getId());
			BeanUtils.copyNotNullProperties(EmpJiabanRecordDetail, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}
	
	@Override
	public void saveEmpJiabanRecordDetail(EmpJiabanRecordDetail EmpJiabanRecordDetail,
			String userId) {
		EmpJiabanRecordDetail.setOpUserId(userId);
		//EmpJiabanRecordDetail.setStatus(EmpJiabanRecordDetail.STATUS_NEW);
		
		save(EmpJiabanRecordDetail);
	}
	
	@Override
	public List<EmpJiabanRecordDetail> findEmpJiabanRecordDetailByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpJiabanRecordDetail t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpJiabanRecordDetail> findEmpJiabanRecordDetailByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpJiabanRecordDetail t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}
	
	@Override
	public Long countEmpJiabanRecordDetailByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpJiabanRecordDetail t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
