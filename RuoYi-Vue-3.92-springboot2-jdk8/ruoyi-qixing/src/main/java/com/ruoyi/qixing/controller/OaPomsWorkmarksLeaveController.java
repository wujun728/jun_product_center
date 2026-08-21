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
import com.ruoyi.qixing.domain.OaPomsWorkmarksLeave;
import com.ruoyi.qixing.service.IOaPomsWorkmarksLeaveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 员工请假Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/leave")
public class OaPomsWorkmarksLeaveController extends BaseController
{
    @Autowired
    private IOaPomsWorkmarksLeaveService oaPomsWorkmarksLeaveService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询员工请假列表
     */
    @PreAuthorize("@ss.hasPermi('system:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        startPage();
        List<OaPomsWorkmarksLeave> list = oaPomsWorkmarksLeaveService.selectOaPomsWorkmarksLeaveList(oaPomsWorkmarksLeave);
        return getDataTable(list);
    }

    /**
     * 导出员工请假列表
     */
    @PreAuthorize("@ss.hasPermi('system:leave:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        List<OaPomsWorkmarksLeave> list = oaPomsWorkmarksLeaveService.selectOaPomsWorkmarksLeaveList(oaPomsWorkmarksLeave);
        ExcelUtil<OaPomsWorkmarksLeave> util = new ExcelUtil<OaPomsWorkmarksLeave>(OaPomsWorkmarksLeave.class);
        util.exportExcel(response, list, "员工请假数据");
    }

    /**
     * 获取员工请假详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(oaPomsWorkmarksLeaveService.selectOaPomsWorkmarksLeaveById(id));
    }

    /**
     * 新增员工请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:add')")
    @PostMapping
    public AjaxResult add(@RequestBody OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        return toAjax(oaPomsWorkmarksLeaveService.insertOaPomsWorkmarksLeave(oaPomsWorkmarksLeave));
    }

    /**
     * 修改员工请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody OaPomsWorkmarksLeave oaPomsWorkmarksLeave)
    {
        return toAjax(oaPomsWorkmarksLeaveService.updateOaPomsWorkmarksLeave(oaPomsWorkmarksLeave));
    }

    /**
     * 删除员工请假
     */
    @PreAuthorize("@ss.hasPermi('system:leave:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(oaPomsWorkmarksLeaveService.deleteOaPomsWorkmarksLeaveByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<OaPomsWorkmarksLeave> list = oaPomsWorkmarksLeaveService.selectOaPomsWorkmarksLeaveList(new OaPomsWorkmarksLeave());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody OaPomsWorkmarksLeave entity, String templateId)
    {
        oaPomsWorkmarksLeaveService.insertOaPomsWorkmarksLeave(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("OaPomsWorkmarksLeave-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}