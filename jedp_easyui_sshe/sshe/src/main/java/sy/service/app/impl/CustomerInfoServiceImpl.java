package sy.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustomerInfo;
import sy.model.base.Syresource;
import sy.model.base.Syuser;
import sy.service.app.CustomerInfoServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class CustomerInfoServiceImpl extends BaseServiceImpl<CustomerInfo>
		implements CustomerInfoServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Override
	public void updateCustomerInfo(CustomerInfo customerInfo) {
		if (!DataTypeUtil.isBlank(customerInfo.getCustomerId())) {
			CustomerInfo t = getById(customerInfo.getCustomerId());
			BeanUtils.copyNotNullProperties(customerInfo, t,
					new String[] { "createtime" });
				
			super.update(t);
		}
	}

	/**
	 * 由于新添加的客户部门，当前用户的角色或者机构并没有访问此客户部门的权限，所以这个地方重写save方法，
	 * 将新添加的客户部门放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveCustomerInfo(CustomerInfo CustomerInfo, String userId) {
		CustomerInfo.setOpUserId(userId);
		save(CustomerInfo);
		
		Syuser user = userDao.getById(Syuser.class, userId);
		user.getSycustomerInfos().add(CustomerInfo);// 把新添加的客户与当前用户关联
	}

	@Override
	public List<CustomerInfo> findCustomerInfoWithoutUserCondByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from CustomerInfo t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<CustomerInfo> findCustomerInfoByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from CustomerInfo t join t.syusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<CustomerInfo> findCustomerInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from CustomerInfo t  join t.syusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countCustomerInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from CustomerInfo t join t.syusers user ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public Long countCustomerInfoWithoutUserCondByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from CustomerInfo t   ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	
//	@Override
//	public List<CustomerInfo> findByFilter(HqlFilter hqlFilter,int userId) {
//		Syuser user = userDao.getById(Syuser.class, userId);
//		int cId = user.getCustomerId();
//		
//		String hql = "select distinct t from CustomerInfo t ";
//		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
//	}
//	@Override
//	public Long countByFilter(HqlFilter hqlFilter,int userId) {
//	 	String hql = "select count(distinct t) from CustomerInfo t";
//		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
//	}

}
