package sy.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.MedicalReportDef;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.Syuser;
import sy.service.app.MedicalReportDefServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class MedicalReportDefServiceImpl extends
		BaseServiceImpl<MedicalReportDef> implements MedicalReportDefServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Override
	public void updateMedicalReportDef(MedicalReportDef MedicalReportDef) {
		if (!DataTypeUtil.isBlank(MedicalReportDef.getTemplateId())) {
			MedicalReportDef t = getById(MedicalReportDef.getTemplateId());
			BeanUtils.copyNotNullProperties(MedicalReportDef, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	/**
	 * 由于新添加的客户部门，当前用户的角色或者机构并没有访问此客户部门的权限，所以这个地方重写save方法，
	 * 将新添加的客户部门放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveMedicalReportDef(MedicalReportDef MedicalReportDef,
			String userId) {
		MedicalReportDef.setOpUserId(userId);
		save(MedicalReportDef);
	}

	@Override
	public List<MedicalReportDef> findMedicalReportDefByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from MedicalReportDef t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<MedicalReportDef> findMedicalReportDefByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from MedicalReportDef t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countMedicalReportDefByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from MedicalReportDef t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<MedicalReportDef> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from MedicalReportDef t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from MedicalReportDef t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	
	@Override
	public List<MedicalReportDef> getMainMenu(HqlFilter hqlFilter) {
		List<MedicalReportDef> l = new ArrayList<MedicalReportDef>();
		String hql = "select distinct t from MedicalReportDef t";
		List<MedicalReportDef> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}
}
