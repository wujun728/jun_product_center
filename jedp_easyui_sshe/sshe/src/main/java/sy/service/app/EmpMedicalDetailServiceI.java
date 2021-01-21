package sy.service.app;

import java.util.List;

import sy.model.app.EmpMedicalDetail;
import sy.model.app.MedicalReportAttr;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 客户部门业务逻辑
 * 
 */
public interface EmpMedicalDetailServiceI extends
		BaseServiceI<EmpMedicalDetail> {

	// /**
	// * 获得客户部门树，或者combotree
	// *
	// * @return
	// */
	// public List<EmpMedicalDetail> getMainMenu(HqlFilter hqlFilter);
	//
	// /**
	// * 获得客户部门treeGrid
	// *
	// * @return
	// */
	// public List<EmpMedicalDetail> deptTreeGrid(HqlFilter hqlFilter);

	/**
	 * 更新客户
	 */
	public void updateEmpMedicalDetail(EmpMedicalDetail EmpMedicalDetail);

	/**
	 * 查找客户
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<EmpMedicalDetail> findEmpMedicalDetailByFilter(
			HqlFilter hqlFilter, int page, int rows);

	/**
	 * 查找客户
	 */
	public List<EmpMedicalDetail> findEmpMedicalDetailByFilter(
			HqlFilter hqlFilter);

	/**
	 * 统计客户
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countEmpMedicalDetailByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个客户
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveEmpMedicalDetail(EmpMedicalDetail ustomerInfo, String userId);
	
	public List<EmpMedicalDetail> getTreeGrid(HqlFilter hqlFilter);

	// public List<EmpMedicalDetail> findByFilter(HqlFilter hqlFilter,int
	// userId);
	// public Long countByFilter(HqlFilter hqlFilter,int userId) ;
}
