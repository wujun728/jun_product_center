package com.ruoyi.flowable.service;

import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import org.flowable.bpmn.model.UserTask;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 流程任务服务
 *
 * @author wocurr.com
 */
public interface IFlowTaskService {

    /**
     * 审批任务
     *
     * @param task 请求实体参数
     */
    void complete(FlowTaskVo task);

    /**
     * 驳回任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void taskReject(FlowTaskVo flowTaskVo);


    /**
     * 退回任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void taskReturn(FlowTaskVo flowTaskVo);

    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo 请求实体参数
     * @return List<UserTask>
     */
    List<UserTask> findReturnTaskList(FlowTaskVo flowTaskVo);

    /**
     * 删除任务
     *
     * @param procInsId 流程实例ID
     * @param taskIds   任务列表
     */
    void deleteTask(String procInsId, List<String> taskIds);

    /**
     * 认领/签收任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void claim(FlowTaskVo flowTaskVo);

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void unClaim(FlowTaskVo flowTaskVo);

    /**
     * 委派任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void delegateTask(FlowTaskVo flowTaskVo);

    /**
     * 转办任务
     *
     * @param flowTaskVo 请求实体参数
     */
    void assignTask(FlowTaskVo flowTaskVo);


    /**
     * 多实例加签
     *
     * @param flowTaskVo 请求实体参数
     */
    void addMultiInstanceExecution(FlowTaskVo flowTaskVo);

    /**
     * 多实例减签
     *
     * @param flowTaskVo 请求实体参数
     */
    void deleteMultiInstanceExecution(FlowTaskVo flowTaskVo);

    /**
     * 撤回流程
     *
     * @param flowTaskVo 请求实体参数
     * @param isAdmin    是否为管理员
     */
    void revokeProcess(FlowTaskVo flowTaskVo, Boolean isAdmin);

    /**
     * 终止流程
     *
     * @param flowTaskVo 请求实体参数
     */
    void terminateProcess(FlowTaskVo flowTaskVo);

    /**
     * 流程历史流转记录
     *
     * @param procInsId 流程实例Id
     * @return Map<String, Object>
     */
    Map<String, Object> flowHistoryRecord(String procInsId, String actId, int pageNum, int pageSize);

    /**
     * 审批意见列表
     *
     * @param procInsId 流程实例ID
     * @param actId     流程环节ID
     * @param pageNum   页码
     * @param pageSize  每页条数
     * @return Map<String, Object>
     */
    Map<String, Object> flowCmts(String procInsId, String actId, int pageNum, int pageSize);

    /**
     * 获取流程过程图
     *
     * @param processId 流程定义ID
     * @return InputStream
     */
    InputStream diagram(String processId);

    /**
     * 获取流程变量
     *
     * @param taskId 任务ID
     * @return Map<String, Object>
     */
    Map<String, Object> processVariables(String taskId);

    /**
     * 获取下一个节点
     *
     * @param flowTaskVo 请求参数
     * @return FlowNextDto
     */
    List<FlowNextDto> getNextFlowNode(FlowTaskVo flowTaskVo);

    /**
     * 流程节点信息
     *
     * @param procInsId 流程实例ID
     * @param deployId  部署ID
     * @return Map<String, Object>
     */
    Map<String, Object> flowXmlAndNode(String procInsId, String deployId);

    /**
     * 获取任务
     *
     * @param taskId 任务ID
     * @return FlowTaskDto
     */
    FlowTaskDto getFlowTask(String taskId);

    /**
     * 获取历史任务
     *
     * @param taskId 任务ID
     * @return FlowTaskDto
     */
    FlowTaskDto getHistoryFlowTask(String taskId);

    /**
     * 获取当前任务的扩展属性
     *
     * @param taskId 任务ID
     * @return Map<String, Object>
     */
    Map<String, Object> getTaskVariables(String taskId);

    /**
     * 校验当前用户是否为流程创建者
     *
     * @param procInsId 流程实例ID
     * @return boolean
     */
    boolean checkIsFlowCreator(String procInsId);

    /**
     * 更新任务审批意见
     *
     * @param flowTaskVo 请求实体参数
     */
    void updateTaskComment(FlowTaskVo flowTaskVo);

    /**
     * 校验完成提交条件
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    Boolean checkCompleteCondition(FlowTaskVo flowTaskVo);

    /**
     * 校验退回
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    Boolean checkReturnCondition(FlowTaskVo flowTaskVo);

    /**
     * 校验驳回
     *
     * @param flowTaskVo 请求实体参数
     * @return
     */
    Boolean checkRejectCondition(FlowTaskVo flowTaskVo);

    /**
     * 指定处理人
     *
     * @param flowTaskVo 请求实体参数
     * @param assigneeId 处理人ID
     */
    void setAssignee(FlowTaskVo flowTaskVo, String assigneeId);

    /**
     * 获取任务列表
     *
     * @param procInsId 流程实例ID
     * @param taskIds   任务ID集合
     * @return
     */
    List<FlowTaskDto> getTaskList(String procInsId, List<String> taskIds);

    /**
     * 处理委托，特别处理下个环节处理人为固定人员的情况
     *
     * @param flowTaskVo 流程参数
     * @return
     */
    void handleFixedAssigneeByEntrust(FlowTaskVo flowTaskVo);

    /**
     * 校验挂起条件
     *
     * @param taskId 请求实体参数
     * @return
     */
    void checkTaskSuspend(String taskId);

    /**
     * 删除退回/撤回并行网关后的任务
     *
     * @param flowTaskVo 流程参数
     */
    void deleteTaskByParallelGateway(FlowTaskVo flowTaskVo);
}
