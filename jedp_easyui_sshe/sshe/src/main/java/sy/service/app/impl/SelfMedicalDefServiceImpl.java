package sy.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.SelfMedicalDef;
import sy.model.base.Syuser;
import sy.service.app.SelfMedicalDefServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 
 */
@Service
public class SelfMedicalDefServiceImpl extends BaseServiceImpl<SelfMedicalDef>
		implements SelfMedicalDefServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Override
	public void updateSelfMedicalDef(SelfMedicalDef customerInfo) {
		if (!DataTypeUtil.isBlank(customerInfo.getDefId())) {
			SelfMedicalDef t = getById(customerInfo.getDefId());
			BeanUtils.copyNotNullProperties(customerInfo, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveSelfMedicalDef(SelfMedicalDef SelfMedicalDef, String userId) {
		SelfMedicalDef.setOpUserId(userId);
		save(SelfMedicalDef);
	}

	@Override
	public List<SelfMedicalDef> findSelfMedicalDefByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SelfMedicalDef t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SelfMedicalDef> findSelfMedicalDefByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SelfMedicalDef t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countSelfMedicalDefByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SelfMedicalDef t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
