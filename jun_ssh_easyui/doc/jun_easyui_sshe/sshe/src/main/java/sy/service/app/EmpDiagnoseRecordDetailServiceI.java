package sy.service.app;

import java.util.List;

import sy.model.app.EmpDiagnoseRecordDetail;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface EmpDiagnoseRecordDetailServiceI extends
		BaseServiceI<EmpDiagnoseRecordDetail> {

	// /**
	// * 获得客户部门树，或者combotree
	// *
	// * @return
	// */
	// public List<EmpDiagnoseRecordDetail> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得客户部门treeGrid
	// *
	// * @return
	// */
	// public List<EmpDiagnoseRecordDetail> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户
	 */
	public void updateEmpDiagnoseRecordDetail(EmpDiagnoseRecordDetail EmpDiagnoseRecordDetail);

	/**
	 * 查找客户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpDiagnoseRecordDetail> findEmpDiagnoseRecordDetailByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找客户
	 */
	public List<EmpDiagnoseRecordDetail> findEmpDiagnoseRecordDetailByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计客户
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpDiagnoseRecordDetailByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个客户
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpDiagnoseRecordDetail(EmpDiagnoseRecordDetail ustomerInfo, String userId);

	public void saveEmpDiagnoseRecordDetail(
			List<EmpDiagnoseRecordDetail> EmpDiagnoseRecordDetailList, String userId) ;
	
	// public List<EmpDiagnoseRecordDetail> findByFilter(HqlFilter hqlFilter,int
	// userId);
	// public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
