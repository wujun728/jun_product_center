package sy.service.app;

import java.util.List;

import sy.model.app.MedicalReportData;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface MedicalReportDataServiceI extends
		BaseServiceI<MedicalReportData> {

	public List<MedicalReportData> getTreeGrid(HqlFilter hqlFilter);
	// /**
	// * 获得客户部门树，或者combotree
	// *
	// * @return
	// */
	// public List<MedicalReportData> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得客户部门treeGrid
	// *
	// * @return
	// */
	// public List<MedicalReportData> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户
	 */
	public void updateMedicalReportData(MedicalReportData MedicalReportData);

	/**
	 * 查找客户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<MedicalReportData> findMedicalReportDataByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找客户
	 */
	public List<MedicalReportData> findMedicalReportDataByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计客户
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countMedicalReportDataByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveMedicalReportData(MedicalReportData ustomerInfo,
			String userId);

	/**
	 * 添加列表
	 * 
	 * @param medicalReportDataList
	 * @param userId
	 */
	void saveMedicalReportData(List<MedicalReportData> medicalReportDataList,
			String userId);

	// public List<MedicalReportData> findByFilter(HqlFilter hqlFilter,int
	// userId);
	// public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
