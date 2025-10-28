package com.ruoyi.flowable.controller.definition;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.flowable.domain.vo.process.BpmProcessDefinitionListReqVO;
import com.ruoyi.flowable.domain.vo.process.BpmProcessDefinitionPageReqVO;
import com.ruoyi.flowable.service.definition.BpmProcessDefinitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(tags = "管理后台 - 流程定义")
@RestController
@RequestMapping("/bpm/process-definition")
@Validated
public class BpmProcessDefinitionController {

    @Resource
    private BpmProcessDefinitionService bpmDefinitionService;

    @GetMapping("/page")
    @ApiOperation(value = "获得流程定义分页")
    @PreAuthorize("@ss.hasPermi('bpm:process-definition:query')")
    public AjaxResult getProcessDefinitionPage(
            BpmProcessDefinitionPageReqVO pageReqVO) {
        return AjaxResult.success(bpmDefinitionService.getProcessDefinitionPage(pageReqVO));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获得流程定义列表")
    @PreAuthorize("@ss.hasPermi('bpm:process-definition:query')")
    public AjaxResult getProcessDefinitionList(
            BpmProcessDefinitionListReqVO listReqVO) {
        return AjaxResult.success(bpmDefinitionService.getProcessDefinitionList(listReqVO));
    }

    @GetMapping("/get-bpmn-xml")
    @ApiOperation(value = "获得流程定义的 BPMN XML")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('bpm:process-definition:query')")
    public AjaxResult getProcessDefinitionBpmnXML(@RequestParam("id") String id) {
        String bpmnXML = bpmDefinitionService.getProcessDefinitionBpmnXML(id);
        return AjaxResult.success(bpmnXML);
    }
}
