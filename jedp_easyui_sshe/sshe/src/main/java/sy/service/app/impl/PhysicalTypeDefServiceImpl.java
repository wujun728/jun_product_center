package sy.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.PhysicalTypeDef;
import sy.model.base.Syuser;
import sy.service.app.PhysicalTypeDefServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 资源业务逻辑
 * 
 */
@Service
public class PhysicalTypeDefServiceImpl extends
		BaseServiceImpl<PhysicalTypeDef> implements PhysicalTypeDefServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	/**
	 * 获得所有的类型数据， 缓存起来
	 * 
	 * @return
	 */
	@Override
//	@Cacheable(value = "PhysicalTypeDefServiceCache", key = "'PhysicalTypeDefList'")
	public List<PhysicalTypeDef> getAllList() {
		return find();
	}

	@Override
	public PhysicalTypeDef getPhysicalTypeDefById(int sysCode) {
		return getById(sysCode);
	}

	/**
	 */
	@Override
	public List<PhysicalTypeDef> getMainMenu(HqlFilter hqlFilter) {
		List<PhysicalTypeDef> l = new ArrayList<PhysicalTypeDef>();
		String hql = "select distinct t from PhysicalTypeDef t";
		List<PhysicalTypeDef> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}

	@Override
	public List<PhysicalTypeDef> PhysicalTypeDefTreeGrid(HqlFilter hqlFilter) {
		String hql = "select distinct t from PhysicalTypeDef t  ";
		List<PhysicalTypeDef> l = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());

		return l;
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
	@SuppressWarnings("unused")
	private boolean isParentToChild(PhysicalTypeDef t, PhysicalTypeDef pt,
			PhysicalTypeDef oldParent) {
		if (pt != null && pt.getPhysicalTypeDef() != null) {
			if (pt.getPhysicalTypeDef().getPhyId() == t.getPhyId()) {
				pt.setPhysicalTypeDef(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getPhysicalTypeDef(), oldParent);
			}
		}
		return false;
	}
	
	/**
	 * 由于新添加的资源
	 */
	@Override
	public void savePhysicalTypeDef(PhysicalTypeDef PhysicalTypeDef, String userId) {
		PhysicalTypeDef.setUid(userId);
		save(PhysicalTypeDef);
	}

	@Override
	public void updatePhysicalTypeDef(PhysicalTypeDef PhysicalTypeDef) {
		if (PhysicalTypeDef.getPhyId() >= 0) {
			PhysicalTypeDef t = getById(PhysicalTypeDef.getPhyId());
			PhysicalTypeDef oldParent = t.getPhysicalTypeDef();
			BeanUtils.copyNotNullProperties(PhysicalTypeDef, t,
					new String[] { "createtime" });
			if (PhysicalTypeDef.getPhysicalTypeDef() != null) {// 说明要修改的节点选中了上级节点
				PhysicalTypeDef pt = getById(PhysicalTypeDef
						.getPhysicalTypeDef().getPhyId());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setPhysicalTypeDef(pt);
			} else {
				t.setPhysicalTypeDef(null);
			}
		}
	}
	
	@Override
	public List<PhysicalTypeDef> getAllNoCacheList(){
		return find();
	}
}
