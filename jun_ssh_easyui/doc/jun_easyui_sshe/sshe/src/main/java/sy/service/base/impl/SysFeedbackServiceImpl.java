package sy.service.base.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustDept;
import sy.model.base.SysFeedback;
import sy.model.base.Syuser;
import sy.service.base.SysFeedbackServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 业务逻辑
 * 
 */
@Service
public class SysFeedbackServiceImpl extends BaseServiceImpl<SysFeedback>
		implements SysFeedbackServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	@Autowired
	private BaseDaoI<CustDept> custDeptDao;

	@Override
	public void updateSysFeedback(SysFeedback SysFeedback) {
		if (!StringUtils.isBlank(SysFeedback.getId())) {
			SysFeedback t = getById(SysFeedback.getId());
			BeanUtils.copyNotNullProperties(SysFeedback, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveSysFeedback(SysFeedback SysFeedback, String userId) {
		Syuser syuser = new Syuser(userId);
		SysFeedback.setSyuser(syuser);
		
		save(SysFeedback);
	}

	@Override
	public List<SysFeedback> findSysFeedbackByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SysFeedback t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SysFeedback> findSysFeedbackByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysFeedback t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countSysFeedbackByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SysFeedback t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
