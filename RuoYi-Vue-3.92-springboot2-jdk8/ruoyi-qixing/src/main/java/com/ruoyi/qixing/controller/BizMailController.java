package com.ruoyi.qixing.controller;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.qixing.domain.BizMail;
import com.ruoyi.qixing.service.IBizMailService;
@RestController
@RequestMapping("/system/mail")
public class BizMailController extends BaseController {
    @Autowired
    private IBizMailService bizMailService;
    @PreAuthorize("@ss.hasPermi('system:mail:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizMail bizMail) {
        startPage();
        List<BizMail> list = bizMailService.selectBizMailList(bizMail);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('system:mail:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizMail bizMail) {
        List<BizMail> list = bizMailService.selectBizMailList(bizMail);
        ExcelUtil<BizMail> util = new ExcelUtil<BizMail>(BizMail.class);
        util.exportExcel(response, list, "邮件数据");
    }
    @PreAuthorize("@ss.hasPermi('system:mail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(bizMailService.selectBizMailById(id));
    }
    @PreAuthorize("@ss.hasPermi('system:mail:add')")
    @PostMapping
    public AjaxResult add(@RequestBody BizMail bizMail) {
        return toAjax(bizMailService.insertBizMail(bizMail));
    }
    @PreAuthorize("@ss.hasPermi('system:mail:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody BizMail bizMail) {
        return toAjax(bizMailService.updateBizMail(bizMail));
    }
    @PreAuthorize("@ss.hasPermi('system:mail:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizMailService.deleteBizMailByIds(ids));
    }
    @GetMapping("/listBySelect")
    public AjaxResult listBySelect(BizMail bizMail) {
        List<BizMail> list = bizMailService.selectBizMailList(bizMail);
        return success(list);
    }
}