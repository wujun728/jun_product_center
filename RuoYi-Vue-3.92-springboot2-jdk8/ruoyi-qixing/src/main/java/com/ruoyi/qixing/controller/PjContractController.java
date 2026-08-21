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
import com.ruoyi.qixing.domain.PjContract;
import com.ruoyi.qixing.service.IPjContractService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务约定书Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/contract")
public class PjContractController extends BaseController
{
    @Autowired
    private IPjContractService pjContractService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询业务约定书列表
     */
    @PreAuthorize("@ss.hasPermi('system:contract:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjContract pjContract)
    {
        startPage();
        List<PjContract> list = pjContractService.selectPjContractList(pjContract);
        return getDataTable(list);
    }

    /**
     * 导出业务约定书列表
     */
    @PreAuthorize("@ss.hasPermi('system:contract:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjContract pjContract)
    {
        List<PjContract> list = pjContractService.selectPjContractList(pjContract);
        ExcelUtil<PjContract> util = new ExcelUtil<PjContract>(PjContract.class);
        util.exportExcel(response, list, "业务约定书数据");
    }

    /**
     * 获取业务约定书详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:contract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjContractService.selectPjContractById(id));
    }

    /**
     * 新增业务约定书
     */
    @PreAuthorize("@ss.hasPermi('system:contract:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjContract pjContract)
    {
        return toAjax(pjContractService.insertPjContract(pjContract));
    }

    /**
     * 修改业务约定书
     */
    @PreAuthorize("@ss.hasPermi('system:contract:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjContract pjContract)
    {
        return toAjax(pjContractService.updatePjContract(pjContract));
    }

    /**
     * 删除业务约定书
     */
    @PreAuthorize("@ss.hasPermi('system:contract:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjContractService.deletePjContractByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjContract> list = pjContractService.selectPjContractList(new PjContract());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjContract entity, String templateId)
    {
        pjContractService.insertPjContract(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjContract-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}