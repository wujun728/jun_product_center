package sy.service.base.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.base.SysDef;
import sy.model.base.Syuser;
import sy.service.base.SysDefServiceI;
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
public class SysDefServiceImpl extends BaseServiceImpl<SysDef> implements
		SysDefServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	/**
	 * 获得所有的类型数据， 缓存起来
	 * 
	 * @return
	 */
	@Override
 	@Cacheable(value = "SysDefServiceCache", key = "'SysDefList'") 
	public List<SysDef> getAllList() {
		return find();
	}
	@Override
	public List<SysDef> getAllNoCacheList(){
		return find();
	}

	@Override
	public SysDef getSysDefById(int sysCode) {
		return getById(sysCode);
	}

	/**
	 */
	@Override
	@Cacheable(value = "SysDefServiceCache", key = "'SysDefList'")	
	public List<SysDef> getMainMenu(HqlFilter hqlFilter) {
		List<SysDef> l = new ArrayList<SysDef>();
		String hql = "select distinct t from SysDef t";
		List<SysDef> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}

	@Override
	public List<SysDef> sysDefTreeGrid(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysDef t  ";
		List<SysDef> l = find(hql + hqlFilter.getWhereHql(),
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
	private boolean isParentToChild(SysDef t, SysDef pt, SysDef oldParent) {
		if (pt != null && pt.getSysDef() != null) {
			if (pt.getSysDef().getSysCode() == t.getSysCode()) {
				pt.setSysDef(oldParent);
				return true;
			} else {
				return isParentToChild(t, pt.getSysDef(), oldParent);
			}
		}
		return false;
	}

	/**
	 * 由于新添加的资源
	 */
	@Override
	@CacheEvict(value = "SysDefServiceCache", key = "'SysDefList'")
	public void saveSysDef(SysDef sysDef, String userId) {
		sysDef.setUid(userId);
		save(sysDef);
	}
	
	@Override
	@CacheEvict(value = "SysDefServiceCache", key = "'SysDefList'")
	public void updateSysDef(SysDef sysDef) {
		if (sysDef.getSysCode() >= 0) {
			SysDef t = getById(sysDef.getSysCode());
			SysDef oldParent = t.getSysDef();
			BeanUtils.copyNotNullProperties(sysDef, t,
					new String[] { "createTime" });
			if (sysDef.getSysDef() != null) {// 说明要修改的节点选中了上级节点
				SysDef pt = getById(sysDef.getSysDef().getSysCode());// 上级节点
				isParentToChild(t, pt, oldParent);// 说明要将当前节点修改到当前节点的子或者孙子下
				t.setSysDef(pt);
			} else {
				t.setSysDef(null);
			}
		}
	}

}
