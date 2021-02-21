package com.deer.wms.base.system.service.task;

import com.deer.wms.base.system.model.task.TaskInfoCriteria;
import com.deer.wms.base.system.model.task.TaskInfoDto;
import com.deer.wms.base.system.model.task.TaskInfoWcs;
import com.deer.wms.common.core.service.Service;
import com.deer.wms.base.system.model.task.TaskInfo;

import java.util.List;

/**
 * 任务 服务层
 * 
 * @author guo
 * @date 2019-06-03
 */
public interface
ITaskInfoService extends Service<TaskInfo, Integer>
{


	/**
	 *
	 *	根据出库单id查找出库单的所有任务信息
	 *
	 * @param billId
	 * @return
	 */
	public List<TaskInfo> selectTaskInfoByBillOutMasterId(Integer billId);

	/**
	 *
	 * 封装WCS任务完成时，需要完成的回调
	 *
	 * @param taskId
	 * @param state
	 */
	public void finishTask(String taskId, Integer state);


	/**
	 * 根据任务ID查询任务信息，便于更改状态值
	 *
	 * @param taskId
	 * @return
	 */
	public TaskInfo getTaskInfoByTaskId(String taskId);


	/**
	 *根据billInMasterId查询任务列表
	 *
	 * @param
	 * @return
	 */
	public List<TaskInfo> selectTaskInfoByBillInMasterId(Integer billId);


	/**
	 * 查询状态值为0的任务，(为WCS提供接口)
	 *
	 * @return
	 */
	public List<TaskInfoWcs> selectTaskInfoForWcsByState();


	/**
     * 查询任务信息
     * 
     * @param id 任务ID
     * @return 任务信息
     */
	public TaskInfo selectTaskInfoById(Integer id);
	
	/**
     * 查询任务列表
     * 
     * @param taskInfo 任务信息
     * @return 任务集合
     */
	public List<TaskInfo> selectTaskInfoList(TaskInfo taskInfo);



	
	/**
     * 新增任务
     * 
     * @param taskInfo 任务信息
     * @return 结果
     */
	public int insertTaskInfo(TaskInfo taskInfo);
	
	/**
     * 修改任务
     * 
     * @param taskInfo 任务信息
     * @return 结果
     */
	public int updateTaskInfo(TaskInfo taskInfo);
		
	/**
     * 删除任务信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaskInfoByIds(String ids);

	/**
	 * 根据参数查询数据接口
	 */
	List<TaskInfoDto> findList(TaskInfoCriteria criteria);

	/**
	 * 根据条件查询呆滞物料最后出库日期
	 * @param criteria
	 * @return
	 */
	TaskInfo findByItemCodeAndBatchAndExp(TaskInfoCriteria criteria);

}
