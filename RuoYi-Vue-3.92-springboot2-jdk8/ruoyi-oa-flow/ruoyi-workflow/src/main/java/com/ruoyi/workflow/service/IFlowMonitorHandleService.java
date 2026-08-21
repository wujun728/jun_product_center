package com.ruoyi.workflow.service;

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
public interface IFlowMonitorHandleService {

    /**
     * 查询所有正在运行的流程实例列表
     *
     * @param businessKey
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
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
     * 查询流程活动历史
     *
     * @param processInstanceId
     * @param pageSize
     * @param pageNum
     * @return
     */
    TableDataInfo getHistoryList(String processInstanceId, Integer pageSize, Integer pageNum);

    /**
     * 挂起流程实例
     *
     * @param processInstanceId
     * @return
     */
    void suspend(String processInstanceId);

    /**
     * 唤醒流程实例
     *
     * @param processInstanceId
     * @return
     */
    void reRun( String processInstanceId);

    /**
     * 查询跳转环节列表
     *
     * @param flowActivityVo
     * @return
     */
    List<UserTask> getJumpActivityList(FlowActivityVo flowActivityVo);

    /**
     * 环节跳转
     *
     * @param flowTaskVo
     * @return
     */
    void jumpActivity(FlowTaskVo flowTaskVo);

    /**
     * 查询跳转环节
     *
     * @param flowActivityVo
     * @return
     */
    List<FlowNextDto> getJumpActivityNode(FlowActivityVo flowActivityVo);

    /**
     * 获取流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    List<FlowTaskDto> getFlowNodeTasks(FlowTaskVo flowTaskVo);

    /**
     * 获取已完成流程环节任务列表
     *
     * @param flowTaskVo
     * @return
     */
    List<FlowTaskDto> getFinishFlowNodeTasks(FlowTaskVo flowTaskVo);
}
