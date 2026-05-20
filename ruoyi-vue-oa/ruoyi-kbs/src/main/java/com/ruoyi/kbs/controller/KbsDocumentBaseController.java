package com.ruoyi.kbs.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.kbs.domain.KbsDocumentBase;
import com.ruoyi.kbs.domain.qo.KbsDocumentBaseQo;
import com.ruoyi.kbs.domain.vo.KbsDocumentInfoVo;
import com.ruoyi.kbs.service.IKbsDocumentBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 知识库文档基本Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/document/base")
public class KbsDocumentBaseController extends BaseController {
    @Autowired
    private IKbsDocumentBaseService kbsDocumentBaseService;

    /**
     * 查询知识库文档基本列表
     */
    @GetMapping("/list")
    public TableDataInfo list(KbsDocumentBase kbsDocumentBase) {
        startPage();
        List<KbsDocumentBase> list = kbsDocumentBaseService.listKbsDocumentBase(kbsDocumentBase);
        return getDataTable(list);
    }

    /**
     * 获取知识库文档基本详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(kbsDocumentBaseService.getKbsDocumentInfoById(id));
    }

    /**
     * 新增知识库文档基本
     */

    @PostMapping
    public AjaxResult add(@RequestBody KbsDocumentInfoVo kbsDocumentInfoVo) {
        return AjaxResult.success("操作成功", kbsDocumentBaseService.saveKbsDocumentBase(kbsDocumentInfoVo));
    }

    /**
     * 修改知识库文档基本
     */
    @PutMapping
    public AjaxResult edit(@RequestBody KbsDocumentInfoVo kbsDocumentInfoVo) {
        return toAjax(kbsDocumentBaseService.updateKbsDocumentBase(kbsDocumentInfoVo));
    }

    /**
     * 更新文档名
     *
     * @param kbsDocumentBase
     * @return
     */
    @PutMapping(value = "/resetName")
    public AjaxResult resetName(@RequestBody KbsDocumentBase kbsDocumentBase) {
        return toAjax(kbsDocumentBaseService.resetName(kbsDocumentBase));
    }

    /**
     * 删除知识库文档基本
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable List<String> ids) {
        return toAjax(kbsDocumentBaseService.softDeleteKbsDocumentBaseByIds(ids));
    }

    /**
     * 获取知识库文档树
     */
    @GetMapping(value = "/listByTopic")
    public AjaxResult listDocumentByTopic(KbsDocumentBaseQo qo) {
        return success(kbsDocumentBaseService.listDocumentByTopic(qo));
    }

    /**
     * 重新排序
     *
     * @param qo
     * @return
     */
    @PutMapping(value = "/reSort")
    public AjaxResult reSort(@RequestBody KbsDocumentBaseQo qo) {
        return toAjax(kbsDocumentBaseService.reSort(qo));
    }
}
