package com.deer.wms.base.system.dao.task;


import com.deer.wms.base.system.model.task.TaskInfoCriteria;
import com.deer.wms.base.system.model.task.TaskInfoDto;
import com.deer.wms.base.system.model.task.TaskInfoWcs;
import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.task.TaskInfo;

import java.util.List;

/**
 * 任务 数据层
 * 
 * @author guo
 * @date 2019-06-03
 */
public interface TaskInfoMapper extends Mapper<TaskInfo>
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
     * 删除任务
     * 
     * @param id 任务ID
     * @return 结果
     */
	public int deleteTaskInfoById(Integer id);
	
	/**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaskInfoByIds(String[] ids);

	/**
	 * 根据参数查询数据接口
	 */
	List<TaskInfoDto> findList(TaskInfoCriteria criteria);

	TaskInfo findByItemCodeAndBatchAndExp(TaskInfoCriteria criteria);
	
}