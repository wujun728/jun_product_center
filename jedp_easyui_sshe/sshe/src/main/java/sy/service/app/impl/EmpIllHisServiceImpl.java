package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpIllHis;
import sy.model.base.Syuser;
import sy.service.app.EmpIllHisServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpIllHisServiceImpl extends BaseServiceImpl<EmpIllHis> implements
		EmpIllHisServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpIllHisServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpIllHis> EmpIllHisDao;

	@Override
	public void updateEmpIllHis(EmpIllHis EmpIllHis) {
		if (!DataTypeUtil.isBlank(EmpIllHis.getId())) {
			EmpIllHis t = getById(EmpIllHis.getId());
			BeanUtils.copyNotNullProperties(EmpIllHis, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpIllHis(EmpIllHis EmpIllHis, String userId) {
		EmpIllHis.setOpUserId(userId);
		EmpIllHis.setStatus(EmpIllHis.STATUS_NEW);

		save(EmpIllHis);
	}

	synchronized public String getOrder() {
		List<Map> list = EmpIllHisDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}

	@Override
	public List<EmpIllHis> findEmpIllHisByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from EmpIllHis t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpIllHis> findEmpIllHisByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpIllHis t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpIllHisByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpIllHis t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
