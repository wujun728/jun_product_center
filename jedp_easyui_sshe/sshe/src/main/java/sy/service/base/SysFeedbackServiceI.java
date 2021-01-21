package sy.service.base;

import java.util.List;

import sy.model.base.SysFeedback;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 系统反馈
 * 
 * @author Wujun
 * 
 */
public interface SysFeedbackServiceI extends BaseServiceI<SysFeedback> {

	/**
	 * 更新系统反馈
	 */
	public void updateSysFeedback(SysFeedback SysFeedback);

	/**
	 * 查找系统反馈
	 * 
	 * @param hqlFilter
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<SysFeedback> findSysFeedbackByFilter(HqlFilter hqlFilter,
			int page, int rows);

	/**
	 * 查找系统反馈
	 */
	public List<SysFeedback> findSysFeedbackByFilter(HqlFilter hqlFilter);

	/**
	 * 统计系统反馈
	 * 
	 * @param hqlFilter
	 * @return
	 */
	public Long countSysFeedbackByFilter(HqlFilter hqlFilter);

	/**
	 * 添加一个系统反馈
	 * 
	 * @param data
	 * @param userId
	 */
	public void saveSysFeedback(SysFeedback SysFeedback, String userId);

}
