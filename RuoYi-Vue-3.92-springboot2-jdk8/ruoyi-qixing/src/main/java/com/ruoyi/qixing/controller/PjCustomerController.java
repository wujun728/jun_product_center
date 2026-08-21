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
import com.ruoyi.qixing.domain.PjCustomer;
import com.ruoyi.qixing.service.IPjCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/customer")
public class PjCustomerController extends BaseController
{
    @Autowired
    private IPjCustomerService pjCustomerService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjCustomer pjCustomer)
    {
        startPage();
        List<PjCustomer> list = pjCustomerService.selectPjCustomerList(pjCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:customer:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjCustomer pjCustomer)
    {
        List<PjCustomer> list = pjCustomerService.selectPjCustomerList(pjCustomer);
        ExcelUtil<PjCustomer> util = new ExcelUtil<PjCustomer>(PjCustomer.class);
        util.exportExcel(response, list, "客户信息数据");
    }

    /**
     * 获取客户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjCustomerService.selectPjCustomerById(id));
    }

    /**
     * 新增客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjCustomer pjCustomer)
    {
        return toAjax(pjCustomerService.insertPjCustomer(pjCustomer));
    }

    /**
     * 修改客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjCustomer pjCustomer)
    {
        return toAjax(pjCustomerService.updatePjCustomer(pjCustomer));
    }

    /**
     * 删除客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:customer:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjCustomerService.deletePjCustomerByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjCustomer> list = pjCustomerService.selectPjCustomerList(new PjCustomer());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjCustomer entity, String templateId)
    {
        pjCustomerService.insertPjCustomer(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjCustomer-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}