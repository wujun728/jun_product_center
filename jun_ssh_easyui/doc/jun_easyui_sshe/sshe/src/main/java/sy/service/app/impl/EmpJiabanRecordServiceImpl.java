package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpAttendRecord;
import sy.model.app.EmpJiabanRecord;
import sy.model.app.EmpJiabanRecordDetail;
import sy.model.base.Syuser;
import sy.service.app.EmpAttendRecordServiceI;
import sy.service.app.EmpJiabanRecordDetailServiceI;
import sy.service.app.EmpJiabanRecordServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户业务逻辑
 * 
 */
@Service
public class EmpJiabanRecordServiceImpl extends
		BaseServiceImpl<EmpJiabanRecord> implements EmpJiabanRecordServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpJiabanRecordServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpJiabanRecord> EmpJiabanRecordDao;

	@Autowired
	private EmpJiabanRecordDetailServiceI empJiabanRecordDetailServiceI;
	@Autowired
	private EmpAttendRecordServiceI empAttendRecordServiceI;

	@Override
	public void updateEmpJiabanRecord(EmpJiabanRecord EmpJiabanRecord,
			EmpJiabanRecordDetail EmpJiabanRecordDetail, String userId) {

		EmpJiabanRecordDetail.setOpOrder(EmpJiabanRecord.getOpOrder());

		if (!DataTypeUtil.isBlank(EmpJiabanRecord.getId())) {
			EmpJiabanRecord t = getById(EmpJiabanRecord.getId());

			EmpJiabanRecordDetail.setStatus(t.getStatus());

			BeanUtils.copyNotNullProperties(EmpJiabanRecord, t,
					new String[] { "createtime" });

			super.update(t);
		}

		empJiabanRecordDetailServiceI.saveEmpJiabanRecordDetail(
				EmpJiabanRecordDetail, userId);

		/**
		 * 如果是审批通过的加班或者请假记录
		 */
		if (EmpJiabanRecord.getStatus() == EmpJiabanRecord.STATUS_ACCESS) {
			// 查询考勤记录
			EmpAttendRecord empAttendRecord = empAttendRecordServiceI
					.findByUserAndDate(EmpJiabanRecord.getSyuser().getId(),
							EmpJiabanRecord.getYyyymmdd());
			if (empAttendRecord != null) {
				empAttendRecord.setIsLeave((byte)EmpJiabanRecord.getType());
				empAttendRecord.setOpTime(EmpJiabanRecord.getRealTime());
				empAttendRecord.setStatus(EmpAttendRecord.STATUS_ACCESS);
				empAttendRecordServiceI.update(empAttendRecord);
			}
		}
	}

	@Override
	public void updateEmpJiabanRecord(EmpJiabanRecord EmpJiabanRecord) {
		if (!DataTypeUtil.isBlank(EmpJiabanRecord.getId())) {
			EmpJiabanRecord t = getById(EmpJiabanRecord.getId());
			BeanUtils.copyNotNullProperties(EmpJiabanRecord, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpJiabanRecord(EmpJiabanRecord EmpJiabanRecord,
			String userId) {
		EmpJiabanRecord.setOpUserId(userId);
		EmpJiabanRecord.setStatus(EmpJiabanRecord.STATUS_NEW);
		String order = getOrder();

		logger.debug("获得流水号 :" + order);
		EmpJiabanRecord.setOpOrder(order);

		save(EmpJiabanRecord);

		// 保存操作的明细记录
		EmpJiabanRecordDetail epJiabanRecordDetail = new EmpJiabanRecordDetail();
		epJiabanRecordDetail.setPhyAddr(EmpJiabanRecord.getPhyAddr());
		epJiabanRecordDetail.setReason("");
		epJiabanRecordDetail.setOpOrder(order);
		epJiabanRecordDetail.setOpUserId(userId);
		epJiabanRecordDetail.setStatus(EmpJiabanRecord.STATUS_NEW);
		epJiabanRecordDetail.setNewStatus(EmpJiabanRecord.STATUS_NEW);

		empJiabanRecordDetailServiceI.saveEmpJiabanRecordDetail(
				epJiabanRecordDetail, userId);

	}

	synchronized public String getOrder() {
		List<Map> list = EmpJiabanRecordDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_jiaban_record')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}
	
	@Override
	public List<EmpJiabanRecord> findEmpJiabanRecordByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpJiabanRecord t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<EmpJiabanRecord> findEmpJiabanRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpJiabanRecord t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countEmpJiabanRecordByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpJiabanRecord t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

}
