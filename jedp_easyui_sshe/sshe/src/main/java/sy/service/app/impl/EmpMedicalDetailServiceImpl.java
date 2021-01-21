package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpMedicalDetail;
import sy.model.app.MedicalReportAttr;
import sy.model.base.Syuser;
import sy.service.app.EmpMedicalDetailServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpMedicalDetailServiceImpl extends
		BaseServiceImpl<EmpMedicalDetail> implements EmpMedicalDetailServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpMedicalDetailServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpMedicalDetail> EmpMedicalDetailDao;

	@Override
	public void updateEmpMedicalDetail(EmpMedicalDetail EmpMedicalDetail) {
		if (!DataTypeUtil.isBlank(EmpMedicalDetail.getId())) {
			EmpMedicalDetail t = getById(EmpMedicalDetail.getId());
			BeanUtils.copyNotNullProperties(EmpMedicalDetail, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpMedicalDetail(EmpMedicalDetail EmpMedicalDetail,
			String userId) {
		EmpMedicalDetail.setOpUserId(userId);
		EmpMedicalDetail.setStatus(EmpMedicalDetail.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		EmpMedicalDetail.setOpOrder(order);
			
		save(EmpMedicalDetail);
	}

	synchronized public String getOrder() {
		List<Map> list = EmpMedicalDetailDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<EmpMedicalDetail> findEmpMedicalDetailByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpMedicalDetail t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpMedicalDetail> findEmpMedicalDetailByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpMedicalDetail t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}
	
	@Override
	public Long countEmpMedicalDetailByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpMedicalDetail t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	
	@Override
	public List<EmpMedicalDetail> getTreeGrid(HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpMedicalDetail t  ";
		List<EmpMedicalDetail> l = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		return l;
	}

	// @Override
	// public List<EmpMedicalDetail> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from EmpMedicalDetail t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from EmpMedicalDetail t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
