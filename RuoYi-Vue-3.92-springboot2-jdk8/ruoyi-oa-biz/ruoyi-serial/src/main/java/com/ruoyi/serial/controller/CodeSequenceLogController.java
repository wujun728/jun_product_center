package com.ruoyi.serial.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.serial.domain.CodeSequenceLog;
import com.ruoyi.serial.service.ICodeSequenceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 编号生成日志Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/serial/log")
public class CodeSequenceLogController extends BaseController {
    @Autowired
    private ICodeSequenceLogService codeSequenceLogService;

    /**
     * 查询编号生成日志列表
     */
    @PreAuthorize("@ss.hasPermi('serial:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodeSequenceLog codeSequenceLog) {
        startPage();
        List<CodeSequenceLog> list = codeSequenceLogService.listCodeSequenceLog(codeSequenceLog);
        return getDataTable(list);
    }

    /**
     * 获取编号生成日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('serial:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(codeSequenceLogService.getCodeSequenceLogById(id));
    }

}
