package com.ruoyi.flowable.controller.definition;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.IoUtils;
import com.ruoyi.flowable.convert.definition.BpmModelConvert;
import com.ruoyi.flowable.domain.vo.model.*;
import com.ruoyi.flowable.service.definition.BpmModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;

@Api(tags = "管理后台 - 流程模型")
@RestController
@RequestMapping("/bpm/model")
@Validated
public class BpmModelController {

    @Resource
    private BpmModelService modelService;

    @GetMapping("/page")
    @ApiOperation(value = "获得模型分页")
    public AjaxResult getModelPage(BpmModelPageReqVO pageVO) {
        return AjaxResult.success(modelService.getModelPage(pageVO));
    }

    @GetMapping("/get")
    @ApiOperation("获得模型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('bpm:model:query')")
    public AjaxResult getModel(@RequestParam("id") String id) {
        BpmModelRespVO model = modelService.getModel(id);
        return AjaxResult.success(model);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新建模型")
    @PreAuthorize("@ss.hasPermi('bpm:model:create')")
    public AjaxResult createModel(@Valid @RequestBody BpmModelCreateReqVO createRetVO) {
        return AjaxResult.success(modelService.createModel(createRetVO, null));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改模型")
    @PreAuthorize("@ss.hasPermi('bpm:model:update')")
    public AjaxResult updateModel(@Valid @RequestBody BpmModelUpdateReqVO modelVO) {
        modelService.updateModel(modelVO);
        return AjaxResult.success(true);
    }

    @PostMapping("/import")
    @ApiOperation(value = "导入模型")
    @PreAuthorize("@ss.hasPermi('bpm:model:import')")
    public AjaxResult importModel(@Valid BpmModeImportReqVO importReqVO) throws IOException {
        BpmModelCreateReqVO createReqVO = BpmModelConvert.INSTANCE.convert(importReqVO);
        // 读取文件
        String bpmnXml = IoUtils.readUtf8(importReqVO.getBpmnFile().getInputStream(), false);
        return AjaxResult.success(modelService.createModel(createReqVO, bpmnXml));
    }

    @PostMapping("/deploy")
    @ApiOperation(value = "部署模型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('bpm:model:deploy')")
    public AjaxResult deployModel(@RequestParam("id") String id) {
        modelService.deployModel(id);
        return AjaxResult.success(true);
    }

    @PutMapping("/update-state")
    @ApiOperation(value = "修改模型的状态", notes = "实际更新的部署的流程定义的状态")
    @PreAuthorize("@ss.hasPermi('bpm:model:update')")
    public AjaxResult updateModelState(@Valid @RequestBody BpmModelUpdateStateReqVO reqVO) {
        modelService.updateModelState(reqVO.getId(), reqVO.getState());
        return AjaxResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除模型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = String.class)
    @PreAuthorize("@ss.hasPermi('bpm:model:delete')")
    public AjaxResult deleteModel(@RequestParam("id") String id) {
        modelService.deleteModel(id);
        return AjaxResult.success(true);
    }
}
