package sy.service.base.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustDept;
import sy.model.base.SysSoftVersion;
import sy.model.base.Syuser;
import sy.service.base.SysSoftVersionServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 业务逻辑
 * 
 */
@Service
public class SysSoftVersionServiceImpl extends BaseServiceImpl<SysSoftVersion>
		implements SysSoftVersionServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	@Autowired
	private BaseDaoI<CustDept> custDeptDao;

	@Override
	public void updateSysSoftVersion(SysSoftVersion SysSoftVersion) {
		if (!StringUtils.isBlank(SysSoftVersion.getId())) {
			SysSoftVersion t = getById(SysSoftVersion.getId());
			BeanUtils.copyNotNullProperties(SysSoftVersion, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveSysSoftVersion(SysSoftVersion SysSoftVersion, String userId) {
		save(SysSoftVersion);
	}

	@Override
	public List<SysSoftVersion> findSysSoftVersionByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SysSoftVersion t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SysSoftVersion> findSysSoftVersionByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysSoftVersion t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countSysSoftVersionByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SysSoftVersion t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
