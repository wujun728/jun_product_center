package sy.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.ImpCustUserData;
import sy.model.base.Syuser;
import sy.service.app.ImpCustUserDataServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 导入员工数据业务逻辑
 * 
 */
@Service
public class ImpCustUserDataServiceImpl extends
		BaseServiceImpl<ImpCustUserData> implements ImpCustUserDataServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;
	
	@Override
	public List<ImpCustUserData> getMainMenu(HqlFilter hqlFilter) {
		List<ImpCustUserData> l = new ArrayList<ImpCustUserData>();
		String hql = "select distinct t from ImpCustUserData t";
		List<ImpCustUserData> list = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		l.addAll(list);

		return l;
	}

	@Override
	public void updateImpCustUserData(ImpCustUserData impCustUserData) {
		if (!DataTypeUtil.isBlank(impCustUserData.getId())) {
			ImpCustUserData t = getById(impCustUserData.getId());
			BeanUtils.copyNotNullProperties(impCustUserData, t,
					new String[] { "createtime" });

			update(t);
		}
	}

	@Override
	public void saveImpCustUserData(ImpCustUserData impCustUserData,
			String userId) {

		Syuser syuser = new Syuser();
		syuser.setId(userId);
		impCustUserData.setSyuser(syuser);
		save(impCustUserData);

	}

	@Override
	public List<ImpCustUserData> findByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from ImpCustUserData t  ";
		return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
