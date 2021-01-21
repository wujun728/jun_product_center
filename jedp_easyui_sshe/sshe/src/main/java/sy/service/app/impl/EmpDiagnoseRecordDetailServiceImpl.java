package sy.service.app.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.EmpDiagnoseRecordDetail;
import sy.model.app.MedicalReportData;
import sy.model.base.Syuser;
import sy.service.app.EmpDiagnoseRecordDetailServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class EmpDiagnoseRecordDetailServiceImpl extends
		BaseServiceImpl<EmpDiagnoseRecordDetail> implements EmpDiagnoseRecordDetailServiceI {
	private static final Logger logger = Logger
			.getLogger(EmpDiagnoseRecordDetailServiceImpl.class);

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Autowired
	private BaseDaoI<EmpDiagnoseRecordDetail> EmpDiagnoseRecordDetailDao;

	@Override
	public void updateEmpDiagnoseRecordDetail(EmpDiagnoseRecordDetail EmpDiagnoseRecordDetail) {
		if (!DataTypeUtil.isBlank(EmpDiagnoseRecordDetail.getMedicalId())) {
			EmpDiagnoseRecordDetail t = getById(EmpDiagnoseRecordDetail.getMedicalId());
			BeanUtils.copyNotNullProperties(EmpDiagnoseRecordDetail, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveEmpDiagnoseRecordDetail(EmpDiagnoseRecordDetail EmpDiagnoseRecordDetail,
			String userId) {
		//EmpDiagnoseRecordDetail.set(userId);
		//EmpDiagnoseRecordDetail.setStatus(EmpDiagnoseRecordDetail.STATUS_NEW);
		
		save(EmpDiagnoseRecordDetail);
	}
	public List<EmpDiagnoseRecordDetail> findByCondition(int medicalId, int phyId){
		String hql = "select distinct t from EmpDiagnoseRecordDetail t ";
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#medicalId_I_EQ", medicalId+"");
		hqlFilter.addFilter("QUERY_t#physicalTypeDef#phyId_I_EQ", phyId+"");
		return findByFilter(hqlFilter);
	}
	/**
	 * 如果有就修改，没有数据就新增
	 */
	@Override
	public void saveEmpDiagnoseRecordDetail(
			List<EmpDiagnoseRecordDetail> EmpDiagnoseRecordDetailList, String userId) {
		if (EmpDiagnoseRecordDetailList != null) {
			for (EmpDiagnoseRecordDetail m : EmpDiagnoseRecordDetailList) {
				EmpDiagnoseRecordDetail t = getById(m.getId());
				// 如果dataId 相同，则更新
				if (t != null && !DataTypeUtil.isBlank(m.getId())) {
					BeanUtils.copyNotNullProperties(m, t,
							new String[] { "createtime", "type" });
					
					super.update(t);
				} else {
					// 如果同就诊编号, 且同，则更新
					List<EmpDiagnoseRecordDetail> list = findByCondition(m.getMedicalId(), m.getPhysicalTypeDef().getPhyId());
					if(list.size()>0){
						for(EmpDiagnoseRecordDetail data : list){
							if(data.getMedicalId()>0 &&  data.getPhysicalTypeDef()!= null){
								t = data;
									
								BeanUtils.copyNotNullProperties(m, t,
										new String[] { "createtime","medicalId" ,"id"});
								
								super.update(t);
							}
						}
					}else {
						this.saveEmpDiagnoseRecordDetail(m, userId);	
					}
					
				}
			}
		}
	}
	

	/*synchronized public String getOrder() {
		List<Map> list = EmpDiagnoseRecordDetailDao
				.findBySql("select CONCAT('',DATE_FORMAT(now(),'%Y%m%d'),LPAD((SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='emp_medical_detail')+0,10,'0')) as seqtitle;");
		String order = (String) list.get(0).get("seqtitle");
		return order;
	}*/
	
	@Override
	public List<EmpDiagnoseRecordDetail> findEmpDiagnoseRecordDetailByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from EmpDiagnoseRecordDetail t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}
	
	@Override
	public List<EmpDiagnoseRecordDetail> findEmpDiagnoseRecordDetailByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from EmpDiagnoseRecordDetail t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}
	
	@Override
	public Long countEmpDiagnoseRecordDetailByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from EmpDiagnoseRecordDetail t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<EmpDiagnoseRecordDetail> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from EmpDiagnoseRecordDetail t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from EmpDiagnoseRecordDetail t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
