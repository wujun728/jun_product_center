package sy.service.app.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.DrugPdRecordLog;
import sy.model.base.Syuser;
import sy.service.app.DrugPdRecordLogServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.HqlFilter;

/**
 * 药品日志业务逻辑
 * 
 */
@Service
public class DrugPdRecordLogServiceImpl extends BaseServiceImpl<DrugPdRecordLog>
		implements DrugPdRecordLogServiceI {
	private static final Logger logger = Logger
			.getLogger(DrugPdRecordLogServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;
	
	@Override
	@Async
	public void saveDrugPdRecordLog(DrugPdRecordLog DrugPdRecordLog, String userId) {
		logger.info("异步处理日志操作 :" + DrugPdRecordLog.toString());
		Syuser syuser = new Syuser(userId);
		DrugPdRecordLog.setSyuser(syuser);
		save(DrugPdRecordLog);
	}

	@Override
	public List<DrugPdRecordLog> findDrugPdRecordLogByFilter(HqlFilter hqlFilter,
			int page, int rows) {
		String hql = "select distinct t from DrugPdRecordLog t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<DrugPdRecordLog> findDrugPdRecordLogByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from DrugPdRecordLog t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countDrugPdRecordLogByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from DrugPdRecordLog t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
