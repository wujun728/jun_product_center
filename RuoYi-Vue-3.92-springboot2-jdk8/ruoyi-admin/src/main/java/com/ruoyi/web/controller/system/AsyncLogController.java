package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mq.domain.AsyncLog;
import com.ruoyi.mq.service.IAsyncLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 异步任务日志记录Controller
 * 
 * @author
 */
@RestController
@RequestMapping("/mq/async")
public class AsyncLogController extends BaseController {
    @Autowired
    private IAsyncLogService asyncLogService;

    /**
     * 查询异步任务日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('mq:async:list')")
    @GetMapping("/list")
    public TableDataInfo list(AsyncLog asyncLog) {
        startPage();
        List<AsyncLog> list = asyncLogService.listAsyncLog(asyncLog);
        return getDataTable(list);
    }

    /**
     * 导出异步任务日志记录列表
     */
    @PreAuthorize("@ss.hasPermi('mq:async:export')")
    @Log(title = "异步任务日志记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AsyncLog asyncLog) {
        List<AsyncLog> list = asyncLogService.listAsyncLog(asyncLog);
        ExcelUtil<AsyncLog> util = new ExcelUtil<AsyncLog>(AsyncLog.class);
        util.exportExcel(response, list, "异步任务日志记录数据");
    }

    /**
     * 获取异步任务日志记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('mq:async:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(asyncLogService.getAsyncLogById(id));
    }

    /**
     * 删除异步任务日志记录
     */
    @PreAuthorize("@ss.hasPermi('mq:async:remove')")
    @Log(title = "异步任务日志记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(asyncLogService.deleteAsyncLogByIds(ids));
    }

    /**
     * 重试异步任务
     */
    @PreAuthorize("@ss.hasPermi('mq:async:retry')")
    @PutMapping(value = "/retry/{id}")
    public AjaxResult retry(@PathVariable("id") String id) {
        asyncLogService.retry(id);
        return AjaxResult.success();
    }
}
