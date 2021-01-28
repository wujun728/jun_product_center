package sy.service.app.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustDept;
import sy.model.app.CustUser;
import sy.model.base.Syorganization;
import sy.model.base.Syuser;
import sy.service.app.CustUserServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户员工业务逻辑
 * 
 */
@Service
public class CustUserServiceImpl extends BaseServiceImpl<CustUser> implements
		CustUserServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	@Autowired
	private BaseDaoI<CustDept> custDeptDao;
	
	/**
	 * 暂时只支持一个员工一个部门, 不支持一个员工多个部门
	 */
	@Override
	public void grantOrganization(String id, String organizationIds) {
		CustUser custUser = getById(id);
		if (custUser != null) {

			for (String organizationId : organizationIds.split(",")) {
				if (!StringUtils.isBlank(organizationId)) {
					CustDept custDept = custDeptDao.getById(CustDept.class,
							organizationId);
					if (custDept != null) {
						custUser.setCustDept(custDept);
					}
				}
			}
		}
	}

	@Override
	public void updateCustUser(CustUser CustUser) {
		if (!DataTypeUtil.isBlank(CustUser.getUserId())) {
			CustUser t = getById(CustUser.getUserId());
			BeanUtils.copyNotNullProperties(CustUser, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	/**
	 * 由于新添加的客户员工，当前用户的角色或者机构并没有访问此客户员工的权限，所以这个地方重写save方法，
	 * 将新添加的客户员工放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveCustUser(CustUser custUser, String userId) {
		custUser.setOpUserId(userId);
		save(custUser);
	}

	@Override
	public List<CustUser> findCustUserByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from CustUser t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<CustUser> findCustUserByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from CustUser t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countCustUserByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from CustUser t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
