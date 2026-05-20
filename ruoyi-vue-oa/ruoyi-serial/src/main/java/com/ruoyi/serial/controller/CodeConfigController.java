package com.ruoyi.serial.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.serial.api.ICodeGenService;
import com.ruoyi.serial.domain.CodeConfig;
import com.ruoyi.serial.module.CodeConfigDTO;
import com.ruoyi.serial.service.ICodeConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 编号配置Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/serial/config")
public class CodeConfigController extends BaseController {
    @Autowired
    private ICodeConfigService codeConfigService;
    @Autowired
    private ICodeGenService codeGenService;

    /**
     * 查询编号配置列表
     */
    @PreAuthorize("@ss.hasPermi('serial:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(CodeConfig codeConfig) {
        startPage();
        List<CodeConfig> list = codeConfigService.listCodeConfig(codeConfig);
        return getDataTable(list);
    }

    /**
     * 编号选项（所有有效的编号）
     * @return
     */
    @GetMapping("/serialOptions")
    public AjaxResult serialOptions() {
        return AjaxResult.success(codeConfigService.serialOptions());
    }

    /**
     * 获取编号配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('serial:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(codeConfigService.getCodeConfigById(id));
    }

    /**
     * 新增编号配置
     */
    @PreAuthorize("@ss.hasPermi('serial:config:add')")
    @Log(title = "编号配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CodeConfigDTO codeConfig) {
        return toAjax(codeConfigService.saveCodeConfig(codeConfig));
    }

    /**
     * 修改编号配置
     */
    @PreAuthorize("@ss.hasPermi('serial:config:edit')")
    @Log(title = "编号配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CodeConfigDTO codeConfig) {
        return toAjax(codeConfigService.updateCodeConfig(codeConfig));
    }

    /**
     * 启用禁用
     * @param codeConfig
     * @return
     */
    @PutMapping("/changeEnableFlag")
    public AjaxResult changeEnableFlag(@RequestBody CodeConfig codeConfig) {
        return toAjax(codeConfigService.changeEnableFlag(codeConfig));
    }

    /**
     * 删除编号配置
     */
    @PreAuthorize("@ss.hasPermi('serial:config:remove')")
    @Log(title = "编号配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable String id) {
        return toAjax(codeConfigService.deleteCodeConfigById(id));
    }

    /**
     * 获取编号
     *
     * @param confId 规则配置id
     * @return
     */
    @GetMapping("/genSerialNo/{confId}")
    public AjaxResult getCodeNumber(@PathVariable("confId") String confId) {
        return AjaxResult.success("操作成功", codeGenService.getNextCode(confId));
    }
}
