package sy.service.base.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.base.Syorganization;
import sy.model.base.Syresource;
import sy.model.base.Syrole;
import sy.model.base.Syuser;
import sy.service.base.SyresourceServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 * @author Wujun
 * 
 */
@Service
public class SyresourceServiceImpl extends BaseServiceImpl<Syresource> implements SyresourceServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	/**
	 * 由于角色与资源关联，机构也与资源关联，所以查询用户能看到的资源菜单应该查询两次，最后合并到一起。
	 */
	@Override
	public List<Syresource> getMainMenu(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Syresource t join t.syorganizations organization join organization.syusers user";
		List<Syresource> resource_organization = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Syresource>(new HashSet<Syresource>(l));// 去重
		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public List<Syresource> resourceTreeGrid(HqlFilter hqlFilter) {
		List<Syresource> l = new ArrayList<Syresource>();
		String hql = "select distinct t from Syresource t join t.syroles role join role.syusers user";
		List<Syresource> resource_role = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_role);
		hql = "select distinct t from Syresource t join t.syorganizations organization join organization.syusers user";
		List<Syresource> resource_organization = find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
		l.addAll(resource_organization);
		l = new ArrayList<Syresource>(new HashSet<Syresource>(l));// 去重
		Collections.sort(l, new Comparator<Syresource>() {// 排序
					@Override
					public int compare(Syresource o1, Syresource o2) {
						if (o1.getSeq() == null) {
							o1.setSeq(1000);
						}
						if (o2.getSeq() == null) {
							o2.setSeq(1000);
						}
						return o1.getSeq().compareTo(o2.getSeq());
					}
				});
		return l;
	}

	@Override
	public void updateResource(Syresource syresource) {
		if (syresource.getId() != null) {
			Syresource t = getById(syresource.getId());
			Syresource oldParent = t.getSyresource();
			BeanUtils.copyNotNullProperties(syresource, t, new String[] { "createdatetime" });
			if (syresource.getSyresource() != null) {// 说明要修改的节点选中了上级节点
				Syresource pt = getById(syresource.getSyresource().getId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSyresource(pt);
			} else {
				t.setSyresource(null);
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
	private boolean isParentToChild(Syresource t, Syresource pt, Syresource oldParent) {
		if (pt != null && pt.getSyresource() != null) {
			if (pt.getSyresource().getId().equals(t.getId())) {
				pt.setSyresource(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getSyresource(), oldParent);
			}
		}
		return false;
	}

	/**
	 * 由于新添加的资源，当前用户的角色或者机构并没有访问此资源的权限，所以这个地方重写save方法，将新添加的资源放到用户的第一个角色里面或者第一个机构里面
	 */
	@Override
	public void saveResource(Syresource syresource, String userId) {
		save(syresource);

		Syuser user = userDao.getById(Syuser.class, userId);
		Set<Syrole> roles = user.getSyroles();
		if (roles != null && !roles.isEmpty()) {// 如果用户有角色，就将新资源放到用户的第一个角色里面
			List<Syrole> l = new ArrayList<Syrole>();
			l.addAll(roles);
			Collections.sort(l, new Comparator<Syrole>() {
				@Override
				public int compare(Syrole o1, Syrole o2) {
					if (o1.getCreatedatetime().getTime() > o2.getCreatedatetime().getTime()) {
						return 1;
					}
					if (o1.getCreatedatetime().getTime() < o2.getCreatedatetime().getTime()) {
						return -1;
					}
					return 0;
				}
			});
			l.get(0).getSyresources().add(syresource);
		} else {// 如果用户没有角色
			Set<Syorganization> organizations = user.getSyorganizations();
			if (organizations != null && !organizations.isEmpty()) {// 如果用户没有角色，但是有机构，那就将新资源放到第一个机构里面
				List<Syorganization> l = new ArrayList<Syorganization>();
				l.addAll(organizations);
				Collections.sort(l, new Comparator<Syorganization>() {
					@Override
					public int compare(Syorganization o1, Syorganization o2) {
						if (o1.getCreatedatetime().getTime() > o2.getCreatedatetime().getTime()) {
							return 1;
						}
						if (o1.getCreatedatetime().getTime() < o2.getCreatedatetime().getTime()) {
							return -1;
						}
						return 0;
					}
				});
				l.get(0).getSyresources().add(syresource);
			}
		}
	}

	@Override
	public List<Syresource> findResourceByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from Syresource t left join t.syroles role left join t.syorganizations organization";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
