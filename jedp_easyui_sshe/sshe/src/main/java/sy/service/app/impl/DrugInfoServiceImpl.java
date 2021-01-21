package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugInfo;
import sy.model.base.Syuser;
import sy.service.app.DrugInfoServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class DrugInfoServiceImpl extends BaseServiceImpl<DrugInfo> implements
		DrugInfoServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugInfoServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugInfo> DrugInfoDao;

	@Override
	public void updateDrugInfo(DrugInfo drugInfo) {
		if (!DataTypeUtil.isBlank(drugInfo.getDrugCode())) {
			DrugInfo t = getById(drugInfo.getDrugCode());
			BeanUtils.copyNotNullProperties(drugInfo, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveDrugInfo(DrugInfo DrugInfo, String userId) {
		DrugInfo.setOpUserId(userId);
		DrugInfo.setStatus(DrugInfo.STATUS_NEW);
		
		save(DrugInfo);
	}

	synchronized public String getOrder() {
		List<Map> list = DrugInfoDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<DrugInfo> findDrugInfoByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from DrugInfo t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugInfo> findDrugInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugInfo t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugInfo t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<DrugInfo> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from DrugInfo t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from DrugInfo t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
