package sy.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugSpecInfo;
import sy.model.app.MyDrugSpecInfo;
import sy.model.base.Syuser;
import sy.service.app.DrugSpecInfoServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class DrugSpecInfoServiceImpl extends BaseServiceImpl<DrugSpecInfo>
		implements DrugSpecInfoServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugSpecInfoServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugSpecInfo> DrugSpecInfoDao;

	@Override
	public void updateDrugSpecInfo(DrugSpecInfo DrugSpecInfo) {
		if (!DataTypeUtil.isBlank(DrugSpecInfo.getSpecId())) {
			DrugSpecInfo t = getById(DrugSpecInfo.getSpecId());
			BeanUtils.copyNotNullProperties(DrugSpecInfo, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveDrugSpecInfo(DrugSpecInfo DrugSpecInfo, String userId) {
		DrugSpecInfo.setOpUserId(userId);
		DrugSpecInfo.setStatus(DrugSpecInfo.STATUS_NEW);

		save(DrugSpecInfo);
	}

	@Override
	public List<DrugSpecInfo> findDrugSpecInfoByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugSpecInfo t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugSpecInfo> findDrugSpecInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugSpecInfo t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugSpecInfoByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugSpecInfo t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public Long countMyDrugTimesByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugSpecInfo t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}
	
	@Override
	public List<MyDrugSpecInfo> findMyDrugTimesByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugSpecInfo t ";
		List<DrugSpecInfo> list = find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);

		List<MyDrugSpecInfo> myList = new ArrayList<MyDrugSpecInfo>();
		
		for (DrugSpecInfo drugSpecInfo : list) {
			MyDrugSpecInfo my = new MyDrugSpecInfo();
			org.springframework.beans.BeanUtils.copyProperties(drugSpecInfo, my);
			my.setUnit(drugSpecInfo.getUnit());
			my.setDrugName(drugSpecInfo.getDrugInfo()
					.getDrugName());
			my.setDrugDesc(drugSpecInfo.getDrugInfo()
					.getDrugDesc());	
			my.setDrugCode(drugSpecInfo.getDrugInfo().getDrugCode());
			
			my.setCustomerId(drugSpecInfo.getDrugInfo().getCustomerInfo().getCustomerId());
			my.setCustomerName(drugSpecInfo.getDrugInfo().getCustomerInfo().getCustomerName());
			
			myList.add(my);
		}
		
		return myList;
	}
}
