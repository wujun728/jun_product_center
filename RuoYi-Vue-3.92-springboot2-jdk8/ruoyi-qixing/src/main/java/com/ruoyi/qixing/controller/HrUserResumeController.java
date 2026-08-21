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
import com.ruoyi.qixing.domain.HrUserResume;
import com.ruoyi.qixing.service.IHrUserResumeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 面试候选人Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/resume")
public class HrUserResumeController extends BaseController
{
    @Autowired
    private IHrUserResumeService hrUserResumeService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询面试候选人列表
     */
    @PreAuthorize("@ss.hasPermi('system:resume:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserResume hrUserResume)
    {
        startPage();
        List<HrUserResume> list = hrUserResumeService.selectHrUserResumeList(hrUserResume);
        return getDataTable(list);
    }

    /**
     * 导出面试候选人列表
     */
    @PreAuthorize("@ss.hasPermi('system:resume:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserResume hrUserResume)
    {
        List<HrUserResume> list = hrUserResumeService.selectHrUserResumeList(hrUserResume);
        ExcelUtil<HrUserResume> util = new ExcelUtil<HrUserResume>(HrUserResume.class);
        util.exportExcel(response, list, "面试候选人数据");
    }

    /**
     * 获取面试候选人详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:resume:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserResumeService.selectHrUserResumeById(id));
    }

    /**
     * 新增面试候选人
     */
    @PreAuthorize("@ss.hasPermi('system:resume:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserResume hrUserResume)
    {
        return toAjax(hrUserResumeService.insertHrUserResume(hrUserResume));
    }

    /**
     * 修改面试候选人
     */
    @PreAuthorize("@ss.hasPermi('system:resume:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserResume hrUserResume)
    {
        return toAjax(hrUserResumeService.updateHrUserResume(hrUserResume));
    }

    /**
     * 删除面试候选人
     */
    @PreAuthorize("@ss.hasPermi('system:resume:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserResumeService.deleteHrUserResumeByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserResume> list = hrUserResumeService.selectHrUserResumeList(new HrUserResume());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserResume entity, String templateId)
    {
        hrUserResumeService.insertHrUserResume(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserResume-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}