package sy.service.app.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugOutRecordLog;
import sy.model.base.Syuser;
import sy.service.app.DrugOutRecordLogServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.HqlFilter;

/**
 * 药品日志业务逻辑
 * 
 */
@Service
public class DrugOutRecordLogServiceImpl extends BaseServiceImpl<DrugOutRecordLog>
		implements DrugOutRecordLogServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugOutRecordLogServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;
	
	@Override
	@Async
	public void saveDrugRecord(DrugOutRecordLog DrugOutRecordLog, String userId) {
		logger.info("异步处理日志操作 :" + DrugOutRecordLog.toString());
		Syuser syuser = new Syuser(userId);
		DrugOutRecordLog.setSyuser(syuser);
		save(DrugOutRecordLog);
	}

	@Override
	public List<DrugOutRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugOutRecordLog t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugOutRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugOutRecordLog t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugOutRecordLog t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
