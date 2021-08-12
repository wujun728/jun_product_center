package cn.iocoder.yudao.adminserver.modules.infra.controller.logger;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExcelVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExportReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import cn.iocoder.yudao.adminserver.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import cn.iocoder.yudao.adminserver.modules.infra.convert.logger.InfApiErrorLogConvert;
import cn.iocoder.yudao.adminserver.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import cn.iocoder.yudao.adminserver.modules.infra.service.logger.InfApiErrorLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Api(tags = "API 错误日志")
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class InfApiErrorLogController {

    @Resource
    private InfApiErrorLogService apiErrorLogService;

    @PutMapping("/update-status")
    @ApiOperation("更新 API 错误日志的状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "processStatus", value = "处理状态", required = true, example = "1", dataTypeClass = Integer.class)
    })
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:update-status')")
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id,
                                                          @RequestParam("processStatus") Integer processStatus) {
        apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
        return success(true);
    }

    @GetMapping("/page")
    @ApiOperation("获得 API 错误日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<PageResult<InfApiErrorLogRespVO>> getApiErrorLogPage(@Valid InfApiErrorLogPageReqVO pageVO) {
        PageResult<InfApiErrorLogDO> pageResult = apiErrorLogService.getApiErrorLogPage(pageVO);
        return success(InfApiErrorLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出 API 错误日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:export')")
    @OperateLog(type = EXPORT)
    public void exportApiErrorLogExcel(@Valid InfApiErrorLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<InfApiErrorLogDO> list = apiErrorLogService.getApiErrorLogList(exportReqVO);
        // 导出 Excel
        List<InfApiErrorLogExcelVO> datas = InfApiErrorLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "API 错误日志.xls", "数据", InfApiErrorLogExcelVO.class, datas);
    }

}
