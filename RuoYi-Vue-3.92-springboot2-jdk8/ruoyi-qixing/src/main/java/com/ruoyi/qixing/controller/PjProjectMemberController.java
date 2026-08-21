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
import com.ruoyi.qixing.domain.PjProjectMember;
import com.ruoyi.qixing.service.IPjProjectMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目成员与结算Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/member")
public class PjProjectMemberController extends BaseController
{
    @Autowired
    private IPjProjectMemberService pjProjectMemberService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询项目成员与结算列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(PjProjectMember pjProjectMember)
    {
        startPage();
        List<PjProjectMember> list = pjProjectMemberService.selectPjProjectMemberList(pjProjectMember);
        return getDataTable(list);
    }

    /**
     * 导出项目成员与结算列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, PjProjectMember pjProjectMember)
    {
        List<PjProjectMember> list = pjProjectMemberService.selectPjProjectMemberList(pjProjectMember);
        ExcelUtil<PjProjectMember> util = new ExcelUtil<PjProjectMember>(PjProjectMember.class);
        util.exportExcel(response, list, "项目成员与结算数据");
    }

    /**
     * 获取项目成员与结算详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(pjProjectMemberService.selectPjProjectMemberById(id));
    }

    /**
     * 新增项目成员与结算
     */
    @PreAuthorize("@ss.hasPermi('system:member:add')")
    @PostMapping
    public AjaxResult add(@RequestBody PjProjectMember pjProjectMember)
    {
        return toAjax(pjProjectMemberService.insertPjProjectMember(pjProjectMember));
    }

    /**
     * 修改项目成员与结算
     */
    @PreAuthorize("@ss.hasPermi('system:member:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody PjProjectMember pjProjectMember)
    {
        return toAjax(pjProjectMemberService.updatePjProjectMember(pjProjectMember));
    }

    /**
     * 删除项目成员与结算
     */
    @PreAuthorize("@ss.hasPermi('system:member:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(pjProjectMemberService.deletePjProjectMemberByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<PjProjectMember> list = pjProjectMemberService.selectPjProjectMemberList(new PjProjectMember());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody PjProjectMember entity, String templateId)
    {
        pjProjectMemberService.insertPjProjectMember(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("PjProjectMember-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}