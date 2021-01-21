package sy.service.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugTimes;
import sy.model.app.MyDrugTimes;
import sy.model.base.Syuser;
import sy.service.app.DrugTimesServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class DrugTimesServiceImpl extends BaseServiceImpl<DrugTimes> implements
		DrugTimesServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugTimesServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<DrugTimes> DrugTimesDao;

	@Override
	public void updateDrugTimes(DrugTimes DrugTimes) {
		if (!DataTypeUtil.isBlank(DrugTimes.getId())) {
			DrugTimes t = getById(DrugTimes.getId());
			BeanUtils.copyNotNullProperties(DrugTimes, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public DrugTimes getDrugTimesByCond(int specId, String drugLotNo,
			String unit) {
		String hql = "select distinct t from DrugTimes t  where t.drugSpecInfo.specId = :specId and t.drugLotNo = :drugLotNo and t.unit = :unit";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("specId", specId);
		map.put("drugLotNo", drugLotNo);
		map.put("unit", unit);

		List<DrugTimes> list = super.find(hql, map);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	@Override
	public void saveDrugTimes(DrugTimes DrugTimes, String userId) {
		Syuser syuser = new Syuser(userId);
		DrugTimes.setSyuser(syuser);
		DrugTimes.setStatus(DrugTimes.STATUS_NEW);

		save(DrugTimes);
	}

	@Override
	public List<DrugTimes> findDrugTimesByFilter(HqlFilter hqlFilter, int page,
			int rows) {
		String hql = "select distinct t from DrugTimes t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugTimes> findDrugTimesByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugTimes t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugTimesByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugTimes t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public Long countMyDrugTimesByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugTimes t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	@Override
	public List<MyDrugTimes> findMyDrugTimesByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugTimes t ";
		List<DrugTimes> list = find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);

		List<MyDrugTimes> myList = new ArrayList<MyDrugTimes>();

		for (DrugTimes drugTimes : list) {
			MyDrugTimes my = new MyDrugTimes();
			org.springframework.beans.BeanUtils.copyProperties(drugTimes, my);
			
			my.setTotal(drugTimes.getTotal());
			my.setRest(drugTimes.getRest());
			my.setDrugLotNo(drugTimes.getDrugLotNo());
			my.setExpireTime(drugTimes.getExpireTime());
			my.setDrugName(drugTimes.getDrugSpecInfo().getDrugInfo()
					.getDrugName());
			my.setDrugDesc(drugTimes.getDrugSpecInfo().getDrugInfo()
					.getDrugDesc());
			
			myList.add(my);
		}

		return myList;
	}
}
