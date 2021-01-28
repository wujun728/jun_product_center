package sy.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.base.BaseDaoI;
import sy.model.app.MedicalReportAttr;
import sy.model.app.MedicalReportData;
import sy.model.base.SessionInfo;
import sy.model.base.Syuser;
import sy.service.app.MedicalReportDataServiceI;
import sy.service.impl.BaseServiceImpl;
import sy.util.base.BeanUtils;
import sy.util.base.ConfigUtil;
import sy.util.base.DataTypeUtil;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
@Service
public class MedicalReportDataServiceImpl extends
		BaseServiceImpl<MedicalReportData> implements MedicalReportDataServiceI {

	@Autowired
	private BaseDaoI<Syuser> userDao;

	@Override
	public List<MedicalReportData> getTreeGrid(HqlFilter hqlFilter) {
		String hql = "select distinct t from MedicalReportData t  ";
		List<MedicalReportData> l = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		return l;
	}

	@Override
	public void updateMedicalReportData(MedicalReportData MedicalReportData) {
		if (!DataTypeUtil.isBlank(MedicalReportData.getDataId())) {
			MedicalReportData t = getById(MedicalReportData.getDataId());
			BeanUtils.copyNotNullProperties(MedicalReportData, t,
					new String[] { "createtime" });

			super.update(t);
		}
	}

	@Override
	public void saveMedicalReportData(MedicalReportData MedicalReportData,
			String userId) {
		MedicalReportData.setOpUserId(userId);
		save(MedicalReportData);
	}
	
	public List<MedicalReportData> getByParam(String opOrder, int attrId) {
		String hql = "select distinct t from MedicalReportData t ";
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#opOrder_S_EQ", opOrder + "");
		hqlFilter.addFilter("QUERY_t#attrId_I_EQ", attrId + "");
		
		List<MedicalReportData> l = find(hql + hqlFilter.getWhereHql(),
				hqlFilter.getParams());
		
		return l;
	}

	/**
	 * 如果有就修改，没有数据就新增
	 */
	@Override
	public void saveMedicalReportData(
			List<MedicalReportData> medicalReportDataList, String userId) {
		if (medicalReportDataList != null) {
			for (MedicalReportData m : medicalReportDataList) {
				MedicalReportData t = getById(m.getDataId());
				// 如果dataId 相同，则更新
				if (t != null && !DataTypeUtil.isBlank(m.getDataId())) {
					BeanUtils.copyNotNullProperties(m, t,
							new String[] { "createtime" });
				
					super.update(t);
				} else {
					// 如果同流水号, 且同属性ID，则更新
					
					List<MedicalReportData> list = findByCondition(m.getOpOrder(), m.getAttrId());
					if(list.size()>0){
						for(MedicalReportData data : list){
							if(data.getOpOrder()!=null && !data.getOpOrder().isEmpty()){
								t = data;
									
								BeanUtils.copyNotNullProperties(m, t,
										new String[] { "createtime","attrId" ,"dataId"});
								
								
								super.update(t);
							}
						}
					}else {
						this.saveMedicalReportData(m, userId);	
					}
					
				}
			}
		}
	}

	public List<MedicalReportData> findByCondition(String opOrder, int attrId){
		String hql = "select distinct t from MedicalReportData t ";
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#opOrder_S_EQ", opOrder);
		hqlFilter.addFilter("QUERY_t#attrId_I_EQ", attrId+"");
		return findByFilter(hqlFilter);
	}
	
	@Override
	public List<MedicalReportData> findMedicalReportDataByFilter(
			HqlFilter hqlFilter, int page, int rows) {
		String hql = "select distinct t from MedicalReportData t ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams(), page, rows);
	}

	@Override
	public List<MedicalReportData> findMedicalReportDataByFilter(
			HqlFilter hqlFilter) {
		String hql = "select distinct t from MedicalReportData t  ";
		return find(hql + hqlFilter.getWhereAndOrderHql(),
				hqlFilter.getParams());
	}

	@Override
	public Long countMedicalReportDataByFilter(HqlFilter hqlFilter) {
		String hql = "select count(distinct t) from MedicalReportData t  ";
		return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	}

	// @Override
	// public List<MedicalReportData> findByFilter(HqlFilter hqlFilter,int
	// userId) {
	// Syuser user = userDao.getById(Syuser.class, userId);
	// int cId = user.getCustomerId();
	//
	// String hql = "select distinct t from MedicalReportData t ";
	// return find(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }
	// @Override
	// public Long countByFilter(HqlFilter hqlFilter,int userId) {
	// String hql = "select count(distinct t) from MedicalReportData t";
	// return count(hql + hqlFilter.getWhereHql(), hqlFilter.getParams());
	// }

}
