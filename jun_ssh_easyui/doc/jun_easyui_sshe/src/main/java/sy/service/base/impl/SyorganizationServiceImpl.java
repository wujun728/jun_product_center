package sy.service.base.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.service.base.SyorganizationServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 机构业务
 * 
 * @author Wujun
 * 
 */
@Service
public class SyorganizationServiceImpl extends BaseServiceImpl<Syorganization> implements SyorganizationServiceI {

	@Autowired
	private BaseDaoI<Syresource> resourceDao;

	@Override
	public void updateOrganization(Syorganization syorganization) {
		if (syorganization.getId() != null) {
			Syorganization t = getById(syorganization.getId());
			Syorganization oldParent = t.getSyorganization();
			BeanUtils.copyNotNullProperties(syorganization, t, new String[] { "createdatetime" });
			if (syorganization.getSyorganization() != null) {// 说明要修改的节点选中了上级节点
				Syorganization pt = getById(syorganization.getSyorganization().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSyorganization(pt);
			} else {
				t.setSyorganization(null);
			}
		}
	}

	/**
	 * 判断是否是将当前节点修改到当前节点的子节点下
	 * 
	 * @param t
	 *            当前节点
	 * @param pt
	 *            要修改到的节点
	 * 
	 * @param oldParent
	 *            原上级节点
	 * @return
	 */
	private boolean isParentToChild(Syorganization t, Syorganization pt, Syorganization oldParent) {
		if (pt != null && pt.getSyorganization() != null) {
			if (pt.getSyorganization().getId().equals(t.getId())) {
				pt.setSyorganization(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getSyorganization(), oldParent);
			}
		}
		return false;
	}

	@Override
	public void grant(String id, String resourceIds) {
		Syorganization organization = getById(id);
		if (organization != null) {
			organization.setSyresources(new HashSet<Syresource>());
			for (String resourceId : resourceIds.split(",")) {
				if (resourceId != null && !resourceId.equals("")) {
					Syresource resource = resourceDao.getById(Syresource.class, resourceId);
					if (resource != null) {
						organization.getSyresources().add(resource);
					}
				}
			}
		}
	}

	@Override
	public List<Syorganization> findOrganizationByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syorganization t join t.syusers user";
		return find(hql + hqlFilter.getWhereAndOrderHql(), hqlFilter.getParams());
	}

}
