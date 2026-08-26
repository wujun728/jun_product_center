package com.ruoyi.file.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.beust.jcommander.Parameter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.file.business.domain.FileStorage;
import com.ruoyi.file.business.service.IFileStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件存储Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/file/storage")
public class FileStorageController extends BaseController {
    @Autowired
    private IFileStorageService fileStorageService;

    /**
     * 查询文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('file:storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(FileStorage fileStorage) {
        startPage();
        List<FileStorage> list = fileStorageService.listFileStorage(fileStorage);
        return getDataTable(list);
    }

    /**
     * 导出文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('file:storage:export')")
    @Log(title = "文件存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FileStorage fileStorage) {
        List<FileStorage> list = fileStorageService.listFileStorage(fileStorage);
        ExcelUtil<FileStorage> util = new ExcelUtil<FileStorage>(FileStorage.class);
        util.exportExcel(response, list, "文件存储数据");
    }

    /**
     * 获取文件存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('file:storage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(fileStorageService.getFileStorageById(id));
    }

    /**
     * 新增文件存储
     */
    @PreAuthorize("@ss.hasPermi('file:storage:add')")
    @Log(title = "文件存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FileStorage fileStorage) {
        return toAjax(fileStorageService.saveFileStorage(fileStorage));
    }

    /**
     * 修改文件存储
     */
    @PreAuthorize("@ss.hasPermi('file:storage:edit')")
    @Log(title = "文件存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FileStorage fileStorage) {
        return toAjax(fileStorageService.updateFileStorage(fileStorage));
    }

    /**
     * 删除文件存储
     */
    @PreAuthorize("@ss.hasPermi('file:storage:remove')")
    @Log(title = "文件存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(fileStorageService.deleteFileStorageByIds(ids));
    }
}
