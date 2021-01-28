package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpHis;
import sy.model.base.Syuser;
import sy.service.app.EmpHisServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpHisServiceImpl extends
		BaseServiceImpl<EmpHis> implements EmpHisServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpHisServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpHis> EmpHisDao;

	@Override
	public void updateEmpHis(EmpHis EmpHis) {
		if (!DataTypeUtil.isBlank(EmpHis.getHisId())) {
			EmpHis t = getById(EmpHis.getHisId());
			BeanUtils.copyNotNullProperties(EmpHis, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpHis(EmpHis EmpHis,
			String userId) {
		EmpHis.setOpUserId(userId);
		EmpHis.setStatus(EmpHis.STATUS_NEW);
//		String order = getOrder();
//
//		logger.debug("获得流水号 :" + order);
//		EmpHis.setOpOrder(order);
//			
		save(EmpHis);
	}

	synchronized public String getOrder() {
		List<Map> list = EmpHisDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<EmpHis> findEmpHisByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpHis t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpHis> findEmpHisByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpHis t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}
	
	@Override
	public Long countEmpHisByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpHis t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<EmpHis> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from EmpHis t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from EmpHis t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
