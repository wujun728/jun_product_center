package com.ruoyi.workflow.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.todo.domain.Done;
import com.ruoyi.todo.service.IDoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 已办Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/workflow/done")
public class DoneController extends BaseController {
    @Autowired
    private IDoneService doneService;

    /**
     * 查询已办列表
     */
    @PreAuthorize("@ss.hasPermi('workflow:done:list')")
    @GetMapping("/list")
    public TableDataInfo list(Done done) {
        startPage();
        List<Done> list = doneService.listDone(done);
        return getDataTable(list);
    }

    /**
     * 获取已办详细信息
     */
    @PreAuthorize("@ss.hasPermi('workflow:done:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(doneService.getDoneById(id));
    }

    /**
     * 催办
     * @param done
     * @return
     */
    @PostMapping(value = "/urge")
    public AjaxResult urge(@RequestBody Done done) {
        return toAjax(doneService.urgeAll(done));
    }

}
