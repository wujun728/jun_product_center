package com.ruoyi.flowable.controller.definition;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.flowable.convert.definition.BpmFormConvert;
import com.ruoyi.flowable.domain.entity.definition.BpmFormDO;
import com.ruoyi.flowable.domain.vo.form.BpmFormCreateReqVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormPageReqVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormRespVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormUpdateReqVO;
import com.ruoyi.flowable.service.definition.BpmFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "管理后台 - 动态表单")
@RestController
@RequestMapping("/bpm/form")
@Validated
public class BpmFormController {

//    @Autowired
//    @Qualifier("bpmFormService")
    @Resource
    private BpmFormService bpmFormService;

    @PostMapping("/create")
    @ApiOperation("创建动态表单")
    @PreAuthorize("@ss.hasPermi('bpm:form:create')")
    public AjaxResult createForm(@Valid @RequestBody BpmFormCreateReqVO createReqVO) {
        return AjaxResult.success(bpmFormService.createForm(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新动态表单")
    @PreAuthorize("@ss.hasPermi('bpm:form:update')")
    public AjaxResult updateForm(@Valid @RequestBody BpmFormUpdateReqVO updateReqVO) {
        bpmFormService.updateForm(updateReqVO);
        return AjaxResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除动态表单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('bpm:form:delete')")
    public AjaxResult deleteForm(@RequestParam("id") Long id) {
        bpmFormService.deleteForm(id);
        return AjaxResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得动态表单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermi('bpm:form:query')")
    public AjaxResult getForm(@RequestParam("id") Long id) {
        BpmFormDO form = bpmFormService.getForm(id);
        return AjaxResult.success(BpmFormConvert.INSTANCE.convert(form));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获得动态表单的精简列表", notes = "用于表单下拉框")
    public AjaxResult getSimpleForms() {
        List<BpmFormDO> list = bpmFormService.getFormList();
        return AjaxResult.success(BpmFormConvert.INSTANCE.convertList2(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得动态表单分页")
    @PreAuthorize("@ss.hasPermi('bpm:form:query')")
    public AjaxResult getFormPage(@Valid BpmFormPageReqVO pageVO) {
        PageResult<BpmFormDO> pageResult = bpmFormService.getFormPage(pageVO);
        PageResult<BpmFormRespVO> bpmFormRespVOPageResult = BpmFormConvert.INSTANCE.convertPage(pageResult);
        return AjaxResult.success(bpmFormRespVOPageResult);
    }

}
