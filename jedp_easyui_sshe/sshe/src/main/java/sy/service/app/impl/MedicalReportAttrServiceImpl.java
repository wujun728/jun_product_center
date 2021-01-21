package sy.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.MedicalReportAttr;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.Syuser;
import sy.service.app.MedicalReportAttrServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class MedicalReportAttrServiceImpl extends
		BaseServiceImpl<MedicalReportAttr> implements MedicalReportAttrServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	/**
	 */
	@Override
	public List<MedicalReportAttr> getMainMenu(HqlFilter hqlFilter) {
		List<MedicalReportAttr> l = new ArrayList<MedicalReportAttr>();
		String hql = "select distinct t from MedicalReportAttr t";
		List<MedicalReportAttr> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}
	
	@Override
	public void updateMedicalReportAttr(MedicalReportAttr MedicalReportAttr) {
		if (!DataTypeUtil.isBlank(MedicalReportAttr.getAttrId())) {
			MedicalReportAttr t = getById(MedicalReportAttr.getAttrId());
			BeanUtils.copyNotNullProperties(MedicalReportAttr, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	/**
	 * 由于新添加的客户部门，当前用户的角色或者机构并没有访问此客户部门的权限，所以这个地方重写save方法，
	 * 将新添加的客户部门放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveMedicalReportAttr(MedicalReportAttr MedicalReportAttr,
			String userId) {
		MedicalReportAttr.setOpUserId(userId);
		save(MedicalReportAttr);
	}

	@Override
	public List<MedicalReportAttr> findMedicalReportAttrByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from MedicalReportAttr t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<MedicalReportAttr> findMedicalReportAttrByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from MedicalReportAttr t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countMedicalReportAttrByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from MedicalReportAttr t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<MedicalReportAttr> getTreeGrid(HqlFilter hqlFilter) {
		String hql = "select distinct t from MedicalReportAttr t  ";
		List<MedicalReportAttr> l = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		return l;
	}

	// @Override
	// public List<MedicalReportAttr> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from MedicalReportAttr t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from MedicalReportAttr t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
