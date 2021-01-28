package sy.service.base.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.CustDept;
import sy.model.base.SysMessage;
import sy.model.base.Syuser;
import sy.service.base.SysMessageServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.HqlFilter;

/**
 * 业务逻辑
 * 
 */
@Service
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessage>
		implements SysMessageServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	@Autowired
	private BaseDaoI<CustDept> custDeptDao;
	
	@Override
	public void updateSysMessage(SysMessage SysMessage) {
		if (!StringUtils.isBlank(SysMessage.getUserId())) {
			SysMessage t = getById(SysMessage.getId());
			BeanUtils.copyNotNullProperties(SysMessage, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveSysMessage(SysMessage SysMessage, String userId) {
		save(SysMessage);
	}

	@Override
	public List<SysMessage> findSysMessageByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from SysMessage t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<SysMessage> findSysMessageByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from SysMessage t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countSysMessageByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from SysMessage t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
