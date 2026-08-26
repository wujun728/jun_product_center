package com.ruoyi.kbs.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.kbs.domain.KbsRecycle;
import com.ruoyi.kbs.service.IKbsRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库文档回收站Controller
 * 
 * @author wocurr.com
 */
@RestController
@RequestMapping("/recycle/recycle")
public class KbsRecycleController extends BaseController {
    @Autowired
    private IKbsRecycleService kbsRecycleService;

    /**
     * 查询知识库文档回收站列表
     */
    @GetMapping("/list")
    public TableDataInfo list(KbsRecycle kbsRecycle) {
        startPage();
        List<KbsRecycle> list = kbsRecycleService.listKbsRecycle(kbsRecycle);
        return getDataTable(list);
    }

    /**
     * 彻底删除知识库文档回收站
     */
    @Log(title = "知识库文档回收站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(kbsRecycleService.deleteKbsRecycleByIds(ids));
    }

    /**
     * 恢复回收站对象
     */
    @Log(title = "知识库文档回收站", businessType = BusinessType.OTHER)
    @PutMapping("/{ids}")
    public AjaxResult recover(@PathVariable String[] ids) {
        return toAjax(kbsRecycleService.recover(ids));
    }
}
