package com.ruoyi.flowable.controller.task;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.domain.vo.task.*;
import com.ruoyi.flowable.service.task.BpmTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@Api(tags = "管理后台 - 流程任务实例")
@RestController
@RequestMapping("/bpm/task")
@Validated
public class BpmTaskController {

    @Resource
    @Qualifier("bpmTaskService")
    private BpmTaskService bpmTaskService;

    @GetMapping("todo-page")
    @ApiOperation("获取 Todo 待办任务分页")
    @PreAuthorize("@ss.hasPermi('bpm:task:query')")
    public AjaxResult getTodoTaskPage(@Valid BpmTaskTodoPageReqVO pageVO) {
        return AjaxResult.success(bpmTaskService.getTodoTaskPage(SecurityUtils.getUserId(), pageVO));
    }

    @GetMapping("done-page")
    @ApiOperation("获取 Done 已办任务分页")
    @PreAuthorize("@ss.hasPermi('bpm:task:query')")
    public AjaxResult getDoneTaskPage(@Valid BpmTaskDonePageReqVO pageVO) {
        return AjaxResult.success(bpmTaskService.getDoneTaskPage(SecurityUtils.getUserId(), pageVO));
    }

    @GetMapping("/list-by-process-instance-id")
    @ApiOperation(value = "获得指定流程实例的任务列表", notes = "包括完成的、未完成的")
    @ApiImplicitParam(name = "processInstanceId", value = "流程实例的编号", required = true, dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('bpm:task:query')")
    public AjaxResult getTaskListByProcessInstanceId(
        @RequestParam("processInstanceId") String processInstanceId) {
        return AjaxResult.success(bpmTaskService.getTaskListByProcessInstanceId(processInstanceId));
    }

    @PutMapping("/approve")
    @ApiOperation("通过任务")
    @PreAuthorize("@ss.hasPermi('bpm:task:update')")
    public AjaxResult approveTask(@Valid @RequestBody BpmTaskApproveReqVO reqVO) {
        bpmTaskService.approveTask(SecurityUtils.getUserId(), reqVO);
        return AjaxResult.success(true);
    }

    @PutMapping("/reject")
    @ApiOperation("不通过任务")
    @PreAuthorize("@ss.hasPermi('bpm:task:update')")
    public AjaxResult rejectTask(@Valid @RequestBody BpmTaskRejectReqVO reqVO) {
        bpmTaskService.rejectTask(SecurityUtils.getUserId(), reqVO);
        return AjaxResult.success(true);
    }

    @PutMapping("/update-assignee")
    @ApiOperation(value = "更新任务的负责人", notes = "用于【流程详情】的【转派】按钮")
    @PreAuthorize("@ss.hasPermi('bpm:task:update')")
    public AjaxResult updateTaskAssignee(@Valid @RequestBody BpmTaskUpdateAssigneeReqVO reqVO) {
        bpmTaskService.updateTaskAssignee(SecurityUtils.getUserId(), reqVO);
        return AjaxResult.success(true);
    }

    @ApiOperation(value = "获取可驳回节点列表", notes = "获取可驳回节点列表")
    @GetMapping(value = "/getBackNodesByProcessInstanceId/{processInstanceId}/{taskId}")
    public AjaxResult getBackNodesByProcessInstanceId(@PathVariable String processInstanceId,@PathVariable String taskId) {
        return AjaxResult.success(bpmTaskService.getBackNodesByProcessInstanceId(processInstanceId,taskId));
    }


}
