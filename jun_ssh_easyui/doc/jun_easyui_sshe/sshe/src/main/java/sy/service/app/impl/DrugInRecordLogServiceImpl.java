package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugInRecord;
import sy.model.app.DrugInRecordLog;
import sy.model.base.Syuser;
import sy.service.app.DrugInfoServiceI;
import sy.service.app.DrugInRecordLogServiceI;
import sy.service.app.DrugStoreServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.HqlFilter;

/**
 * 药品日志业务逻辑
 * 
 */
@Service
public class DrugInRecordLogServiceImpl extends BaseServiceImpl<DrugInRecordLog>
		implements DrugInRecordLogServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugInRecordLogServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;
	
	@Override
//	@Async
	public void saveDrugRecord(DrugInRecordLog drugRecordLog, String userId) {
		logger.info("异步处理日志操作 :" + drugRecordLog.toString());
		Syuser syuser = new Syuser(userId);
		drugRecordLog.setSyuser(syuser);
		save(drugRecordLog);
	}

	@Override
	public List<DrugInRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugInRecordLog t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugInRecordLog> findDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugInRecordLog t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugInRecordLog t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
