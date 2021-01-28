package sy.service.app.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustDept;
import sy.model.base.Syorganization;
import sy.model.base.Syrole;
import sy.model.base.SysDef;
import sy.model.base.Syuser;
import sy.service.app.CustDeptServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class CustDeptServiceImpl extends BaseServiceImpl<CustDept> implements
		CustDeptServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	/**
	 * 由于角色与客户部门关联，机构也与客户部门关联，所以查询用户能看到的客户部门菜单应该查询两次，最后合并到一起。
	 */
	@Override
	public List<CustDept> getMainMenu(HqlFilter hqlFilter) {
		List<CustDept> l = new ArrayList<CustDept>();
		String hql = "select distinct t from CustDept t";
		List<CustDept> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}

	@Override
	public List<CustDept> deptTreeGrid(HqlFilter hqlFilter) {
		List<CustDept> l = new ArrayList<CustDept>();
		String hql = "select distinct t from CustDept t ";
		List<CustDept> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);
		return l;
	}

	@Override
	public void updateDept(CustDept CustDept) {
		if (!DataTypeUtil.isBlank(CustDept.getDeptId())) {
			CustDept t = getById(CustDept.getDeptId());
			CustDept oldParent = t.getCustDept();
			BeanUtils.copyNotNullProperties(CustDept, t,
					new String[] { "createtime" });
			if (CustDept.getCustDept() != null) {// 说明要修改的节点选中了上级节点
				CustDept pt = getById(CustDept.getCustDept().getDeptId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setCustDept(pt);
			} else {
				t.setCustDept(null);
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
	private boolean isParentToChild(CustDept t, CustDept pt, CustDept oldParent) {
		if (pt != null && pt.getCustDept() != null) {
			if (DataTypeUtil
					.equals(pt.getCustDept().getDeptId(), t.getDeptId())) {
				pt.setCustDept(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getCustDept(), oldParent);
			}
		}
		return false;
	}

	@Override
	public void saveDept(CustDept CustDept, String userId) {
		CustDept.setOpUserId(userId);
		save(CustDept);
	}

	@Override
	public List<CustDept> findDeptByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from CustDept t  ";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
