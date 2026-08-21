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
import com.ruoyi.qixing.domain.BizTest;
import com.ruoyi.qixing.service.IBizTestService;
@RestController
@RequestMapping("/system/test")
public class BizTestController extends BaseController {
    @Autowired
    private IBizTestService bizTestService;
    @PreAuthorize("@ss.hasPermi('system:test:list')")
    @GetMapping("/list")
    public TableDataInfo list(BizTest bizTest) {
        startPage();
        List<BizTest> list = bizTestService.selectBizTestList(bizTest);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('system:test:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, BizTest bizTest) {
        List<BizTest> list = bizTestService.selectBizTestList(bizTest);
        ExcelUtil<BizTest> util = new ExcelUtil<BizTest>(BizTest.class);
        util.exportExcel(response, list, "测试数据");
    }
    @PreAuthorize("@ss.hasPermi('system:test:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(bizTestService.selectBizTestById(id));
    }
    @PreAuthorize("@ss.hasPermi('system:test:add')")
    @PostMapping
    public AjaxResult add(@RequestBody BizTest bizTest) {
        return toAjax(bizTestService.insertBizTest(bizTest));
    }
    @PreAuthorize("@ss.hasPermi('system:test:edit')")
    @PutMapping
    public AjaxResult edit(@RequestBody BizTest bizTest) {
        return toAjax(bizTestService.updateBizTest(bizTest));
    }
    @PreAuthorize("@ss.hasPermi('system:test:remove')")
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(bizTestService.deleteBizTestByIds(ids));
    }
    @GetMapping("/listBySelect")
    public AjaxResult listBySelect(BizTest bizTest) {
        List<BizTest> list = bizTestService.selectBizTestList(bizTest);
        return success(list);
    }
}