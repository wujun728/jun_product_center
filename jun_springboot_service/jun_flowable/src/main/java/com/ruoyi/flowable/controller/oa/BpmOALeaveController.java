package com.ruoyi.flowable.controller.oa;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.flowable.convert.oa.BpmOALeaveConvert;
import com.ruoyi.flowable.domain.entity.oa.BpmOALeaveDO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeaveCreateReqVO;
import com.ruoyi.flowable.domain.vo.oa.BpmOALeavePageReqVO;
import com.ruoyi.flowable.service.oa.BpmOALeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * OA 请假申请 Controller，用于演示自己存储数据，接入工作流的例子
 *
 * @author jason
 * hasPermi
 */
@Api(tags = "管理后台 - OA 请假申请")
@RestController
@RequestMapping("/bpm/oa/leave")
@Validated
public class BpmOALeaveController {

    @Resource
    private BpmOALeaveService leaveService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermi('bpm:oa-leave:create')")
    @ApiOperation("创建请求申请")
    public AjaxResult createLeave(@Valid @RequestBody BpmOALeaveCreateReqVO createReqVO) {
        return AjaxResult.success(leaveService.createLeave(SecurityUtils.getUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermi('bpm:oa-leave:query')")
    @ApiOperation("获得请假申请")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public AjaxResult getLeave(@RequestParam("id") Long id) {
        BpmOALeaveDO leave = leaveService.getLeave(id);
        return AjaxResult.success(BpmOALeaveConvert.INSTANCE.convert(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermi('bpm:oa-leave:query')")
    @ApiOperation("获得请假申请分页")
    public AjaxResult getLeavePage(@Valid BpmOALeavePageReqVO pageVO) {
        PageResult<BpmOALeaveDO> pageResult = leaveService.getLeavePage(SecurityUtils.getUserId(), pageVO);
        return AjaxResult.success(BpmOALeaveConvert.INSTANCE.convertPage(pageResult));
    }

}
