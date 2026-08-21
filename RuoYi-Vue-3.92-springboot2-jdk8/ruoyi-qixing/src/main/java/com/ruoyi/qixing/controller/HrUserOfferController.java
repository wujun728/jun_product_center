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
import com.ruoyi.qixing.domain.HrUserOffer;
import com.ruoyi.qixing.service.IHrUserOfferService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Offer发放Controller
 *
 * @author template
 * @date 2026-06-11
 */
@RestController
@RequestMapping("/system/offer")
public class HrUserOfferController extends BaseController
{
    @Autowired
    private IHrUserOfferService hrUserOfferService;

    @Autowired
    private IFlowHandleService flowHandleService;

    /**
     * 查询Offer发放列表
     */
    @PreAuthorize("@ss.hasPermi('system:offer:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrUserOffer hrUserOffer)
    {
        startPage();
        List<HrUserOffer> list = hrUserOfferService.selectHrUserOfferList(hrUserOffer);
        return getDataTable(list);
    }

    /**
     * 导出Offer发放列表
     */
    @PreAuthorize("@ss.hasPermi('system:offer:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrUserOffer hrUserOffer)
    {
        List<HrUserOffer> list = hrUserOfferService.selectHrUserOfferList(hrUserOffer);
        ExcelUtil<HrUserOffer> util = new ExcelUtil<HrUserOffer>(HrUserOffer.class);
        util.exportExcel(response, list, "Offer发放数据");
    }

    /**
     * 获取Offer发放详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:offer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(hrUserOfferService.selectHrUserOfferById(id));
    }

    /**
     * 新增Offer发放
     */
    @PreAuthorize("@ss.hasPermi('system:offer:add')")
    @PostMapping
    public AjaxResult add(@RequestBody HrUserOffer hrUserOffer)
    {
        return toAjax(hrUserOfferService.insertHrUserOffer(hrUserOffer));
    }

    /**
     * 修改Offer发放
     */
    @PreAuthorize("@ss.hasPermi('system:offer:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody HrUserOffer hrUserOffer)
    {
        return toAjax(hrUserOfferService.updateHrUserOffer(hrUserOffer));
    }

    /**
     * 删除Offer发放
     */
    @PreAuthorize("@ss.hasPermi('system:offer:remove')")
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(hrUserOfferService.deleteHrUserOfferByIds(ids));
    }

    @GetMapping("/listBySelect")
    public AjaxResult listBySelect()
    {
        List<HrUserOffer> list = hrUserOfferService.selectHrUserOfferList(new HrUserOffer());
        return success(list);
    }

    @PostMapping("/submitFlow")
    public AjaxResult submitFlow(@RequestBody HrUserOffer entity, String templateId)
    {
        hrUserOfferService.insertHrUserOffer(entity);

        FlowTaskVo flowTaskVo = new FlowTaskVo();
        flowTaskVo.setBusinessId(entity.getId());
        flowTaskVo.setTemplateId(templateId);
        flowTaskVo.setTitle("HrUserOffer-" + entity.getId());
        flowTaskVo.getVariables().put("businessId", entity.getId());

        return success(flowHandleService.startFlow(flowTaskVo));
    }

}