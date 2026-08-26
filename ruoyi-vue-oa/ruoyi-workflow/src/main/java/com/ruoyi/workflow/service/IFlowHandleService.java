package com.ruoyi.workflow.service;

import com.ruoyi.flowable.domain.dto.FlowNextDto;
import com.ruoyi.flowable.domain.dto.FlowTaskDto;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.module.FlowRecordParam;
import org.flowable.bpmn.model.UserTask;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 流程处理Service接口
 * 
 * @author wocurr.com
 */
public interface IFlowHandleService {

    /**
     * 启动流程
     *
     * @param flowTaskVo 流程对象
     * @return FlowTaskDto
     */
    FlowTaskDto startFlow(FlowTaskVo flowTaskVo);

    /**
     * 撤回流程
     *
     * @param flowTaskVo 流程对象
     */
    void revokeProcess(FlowTaskVo flowTaskVo);

    /**
     * 驳回任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人ID
     */
    void taskReject(FlowTaskVo flowTaskVo, String createId);

    /**
     * 退回任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人ID
     */
    void taskReturn(FlowTaskVo flowTaskVo, String createId);

    /**
     * 终止流程
     *
     * @param flowTaskVo 流程对象
     */
    void terminateProcess(FlowTaskVo flowTaskVo);

    /**
     * 跳转流程
     *
     * @param flowTaskVo 流程对象
     */
    void jumpActivity(FlowTaskVo flowTaskVo);

    /**
     * 获取所有可回退的节点
     *
     * @param flowTaskVo 流程对象
     * @return List<UserTask>
     */
    List<UserTask> findReturnTaskList(FlowTaskVo flowTaskVo);

    /**
     * 认领/签收任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人
     */
    void claim(FlowTaskVo flowTaskVo, String createId);

    /**
     * 取消认领/签收任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人
     */
    void unClaim(FlowTaskVo flowTaskVo, String createId);

    /**
     * 委派任务
     *
     * @param flowTaskVo 流程对象
     */
    void delegateTask(FlowTaskVo flowTaskVo);

    /**
     * 转办任务
     *
     * @param flowTaskVo 流程对象
     */
    void assignTask(FlowTaskVo flowTaskVo);

    /**
     * 多实例加签
     *
     * @param flowTaskVo 流程对象
     */
    void addMultiInstanceExecution(FlowTaskVo flowTaskVo);

    /**
     * 多实例减签
     *
     * @param flowTaskVo 流程对象
     */
    void deleteMultiInstanceExecution(FlowTaskVo flowTaskVo);

    /**
     * 获取下一个节点
     *
     * @param flowTaskVo 流程对象
     * @return FlowNextDto
     */
    List<FlowNextDto> getNextFlowNode(FlowTaskVo flowTaskVo);

    /**
     * 获取流程变量
     *
     * @param taskId 任务ID
     * @return Map<String, Object>
     */
    Map<String, Object> processVariables(String taskId);

    /**
     * 生成流程图
     *
     * @param processId 流程定义ID
     * @param response 响应
     */
    void diagram(String processId, HttpServletResponse response);

    /**
     * 流程节点信息
     *
     * @param procInsId 流程实例ID
     * @param deployId  部署ID
     * @return Map<String, Object>
     */
    Map<String, Object> flowXmlAndNode(String procInsId, String deployId);

    /**
     * 流程任务
     *
     * @param taskId 任务ID
     * @return FlowTaskDto
     */
    FlowTaskDto getFlowTask(String taskId);

    /**
     * 历史流程任务
     *
     * @param taskId 任务ID
     * @return FlowTaskDto
     */
    FlowTaskDto getHistoryFlowTask(String taskId);

    /**
     * 流程历史流转记录
     *
     * @return Map<String, Object>
     */
    Map<String, Object> flowRecord(FlowRecordParam param);

    /**
     * 审批意见记录
     * @param param
     * @return
     */
    Map<String, Object> flowCmts(FlowRecordParam param);

    /**
     * 完成流程任务
     *
     * @param flowTaskVo 流程对象
     * @param createId 创建人ID
     */
    void completeTask(FlowTaskVo flowTaskVo, String createId);

    /**
     * 催办
     *
     * @param flowTaskVo 流程对象
     */
    int urge(FlowTaskVo flowTaskVo);

    /**
     * 管理员终止流程
     *
     * @param flowTaskVo 流程对象
     */
    void terminateProcessByAdmin(FlowTaskVo flowTaskVo);

    /**
     * 管理员取回任务
     *
     * @param flowTaskVo 流程对象
     */
    void returnFinishTaskByAdmin(FlowTaskVo flowTaskVo);

    /**
     * 取回提交
     *
     * @param flowTaskVo 流程对象
     */
    void returnSubmit(FlowTaskVo flowTaskVo);

    /**
     * 校验完成提交条件
     *
     * @param flowTaskVo 流程对象
     * @return 是否检验通过
     */
    Boolean checkCompleteCondition(FlowTaskVo flowTaskVo);

    /**
     * 校验退回
     *
     * @param flowTaskVo 流程对象
     * @return 是否检验通过
     */
    Boolean checkReturnCondition(FlowTaskVo flowTaskVo);

    /**
     * 校验驳回
     *
     * @param flowTaskVo 流程对象
     * @return 是否检验通过
     */
    Boolean checkRejectCondition(FlowTaskVo flowTaskVo);

    /**
     * 抄送
     *
     * @param flowTaskVo 流程对象
     */
    void copyTask(FlowTaskVo flowTaskVo);

    /**
     * 设置抄送阅办完成
     *
     * @param id 阅办ID
     */
    void readCopyTodo(String id);

    /**
     * 获取减签的任务列表
     *
     * @param flowTaskVo
     * @return
     */
    List<FlowTaskDto> getDeleteMultiTasks(FlowTaskVo flowTaskVo);
}
