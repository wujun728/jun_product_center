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
import com.ruoyi.qixing.domain.HrUserBecomeMember;
import com.ruoyi.qixing.service.IHrUserBecomeMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转正Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/becomeMember")
public class HrUserBecomeMemberController extends BaseController
{
    @Autowired
    private IHrUserBecomeMemberService hrUserBecomeMemberService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询转正列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserBecomeMember hrUserBecomeMember)
    {
        startPage();
        List<HrUserBecomeMember> list = hrUserBecomeMemberService.selectHrUserBecomeMemberList(hrUserBecomeMember);
        return getDataTable(list);
    }

    /**
     * 导出转正列表
     */
    @PreAuthorize("@ss.hasPermi('system:member:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserBecomeMember hrUserBecomeMember)
    {
        List<HrUserBecomeMember> list = hrUserBecomeMemberService.selectHrUserBecomeMemberList(hrUserBecomeMember);
        ExcelUtil<HrUserBecomeMember> util = new ExcelUtil<HrUserBecomeMember>(HrUserBecomeMember.class);
        util.exportExcel(response, list, "转正数据");
    }

    /**
     * 获取转正详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserBecomeMemberService.selectHrUserBecomeMemberById(id));
    }

    /**
     * 新增转正
     */
    @PreAuthorize("@ss.hasPermi('system:member:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserBecomeMember hrUserBecomeMember)
    {
        return toAjax(hrUserBecomeMemberService.insertHrUserBecomeMember(hrUserBecomeMember));
    }

    /**
     * 修改转正
     */
    @PreAuthorize("@ss.hasPermi('system:member:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserBecomeMember hrUserBecomeMember)
    {
        return toAjax(hrUserBecomeMemberService.updateHrUserBecomeMember(hrUserBecomeMember));
    }

    /**
     * 删除转正
     */
    @PreAuthorize("@ss.hasPermi('system:member:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserBecomeMemberService.deleteHrUserBecomeMemberByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserBecomeMember> list = hrUserBecomeMemberService.selectHrUserBecomeMemberList(new HrUserBecomeMember());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserBecomeMember entity, String templateId)
    {
        hrUserBecomeMemberService.insertHrUserBecomeMember(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserBecomeMember-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}