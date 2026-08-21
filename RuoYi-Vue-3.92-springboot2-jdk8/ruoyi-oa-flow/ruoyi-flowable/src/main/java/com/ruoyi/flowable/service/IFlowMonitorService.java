package com.ruoyi.flowable.service;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowActivityVo;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import org.flowable.bpmn.model.UserTask;

import java.util.List;

/**
 * <p> 流程监控服务接口 </p>
 *
 * @Author wocurr.com
 */
public interface IFlowMonitorService {

    /**
     * 查询所有正在运行的流程实例列表
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @param pageSize     页大小
     * @param pageNum      页码
     * @return TableDataInfo
     */
    TableDataInfo getProcessInstanceList(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum);

    /**
     * 查询所有流程实例列表-包含在运行和已结束
     *
     * @param businessKey
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    TableDataInfo getHistoryListProcess(String procInstId, String businessKey, String name, Integer pageSize, Integer pageNum);

    /**
     * 查询所有流程实例列表-包含在运行和已结束
     *
     * @param procInstId   流程实例ID
     * @param businessKey  业务主键
     * @param name         流程定义名称
     * @param pageSize     页大小
     * @param pageNum      页码
     * @return TableDataInfo
     */
    TableDataInfo getHistoryList(String processInstanceId, Integer pageSize, Integer pageNum);

    /**
     * 挂起流程实例
     *
     * @param processInstanceId 流程实例ID
     */
    void suspend(String processInstanceId);

    /**
     * 激活流程实例
     *
     * @param processInstanceId 流程实例ID
     */
    void reRun( String processInstanceId);

    /**
     * 查询跳转环节列表
     *
     * @param flowActivityVo 流程活动信息
     * @return
     */
    List<UserTask> getJumpActivityList(FlowActivityVo flowActivityVo);

    /**
     * 跳转流程环节
     *
     * @param flowTaskVo 流程任务信息
     */
    void jumpActivity(FlowTaskVo flowTaskVo);

    /**
     * 获取跳转节点信息
     *
     * @param flowActivityVo 流程活动信息
     * @return List<FlowNextDto>
     */
    List<FlowNextDto> getJumpActivityNode(FlowActivityVo flowActivityVo);

    /**
     * 获取流程环节任务列表
     *
     * @param flowTaskVo 流程任务信息
     * @return
     */
    List<FlowTaskDto> getFlowNodeTasks(FlowTaskVo flowTaskVo);

    /**
     * 获取完成流程环节任务列表
     *
     * @param flowTaskVo 流程任务信息
     * @return
     */
    List<FlowTaskDto> getFinishFlowNodeTasks(FlowTaskVo flowTaskVo);
}
