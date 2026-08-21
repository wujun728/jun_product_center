package com.ruoyi.qixing.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.flowable.domain.vo.FlowTaskVo;
import com.ruoyi.workflow.service.IFlowHandleService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.qixing.domain.PjProjectBorrow;
import com.ruoyi.qixing.service.IPjProjectBorrowService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目借阅Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/borrow")
public class PjProjectBorrowController extends BaseController
{
    @Autowired
    private IPjProjectBorrowService pjProjectBorrowService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目借阅列表
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectBorrow pjProjectBorrow)
    {
        startPage();
        List<PjProjectBorrow> list = pjProjectBorrowService.selectPjProjectBorrowList(pjProjectBorrow);
        return getDataTable(list);
    }

    /**
     * 导出项目借阅列表
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectBorrow pjProjectBorrow)
    {
        List<PjProjectBorrow> list = pjProjectBorrowService.selectPjProjectBorrowList(pjProjectBorrow);
        ExcelUtil<PjProjectBorrow> util = new ExcelUtil<PjProjectBorrow>(PjProjectBorrow.class);
        util.exportExcel(response, list, "项目借阅数据");
    }

    /**
     * 获取项目借阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectBorrowService.selectPjProjectBorrowById(id));
    }

    /**
     * 新增项目借阅
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectBorrow pjProjectBorrow)
    {
        return toAjax(pjProjectBorrowService.insertPjProjectBorrow(pjProjectBorrow));
    }

    /**
     * 修改项目借阅
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectBorrow pjProjectBorrow)
    {
        return toAjax(pjProjectBorrowService.updatePjProjectBorrow(pjProjectBorrow));
    }

    /**
     * 删除项目借阅
     */
    @PreAuthorize("@ss.hasPermi('system:borrow:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectBorrowService.deletePjProjectBorrowByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectBorrow> list = pjProjectBorrowService.selectPjProjectBorrowList(new PjProjectBorrow());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectBorrow entity, String templateId)
    {
        pjProjectBorrowService.insertPjProjectBorrow(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectBorrow-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}