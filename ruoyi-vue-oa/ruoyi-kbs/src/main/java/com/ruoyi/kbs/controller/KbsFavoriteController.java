package com.ruoyi.kbs.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.kbs.domain.KbsFavorite;
import com.ruoyi.kbs.service.IKbsFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 知识库收藏Controller
 *
 * @author wocurr.com
 */
@RestController
@RequestMapping("/favorite/favorite")
public class KbsFavoriteController extends BaseController {
    @Autowired
    private IKbsFavoriteService kbsFavoriteService;

    /**
     * 查询知识库收藏列表
     */
    @GetMapping("/list")
    public AjaxResult list(KbsFavorite kbsFavorite) {
        return success(kbsFavoriteService.listKbsFavorite(kbsFavorite));
    }

    /**
     * 新增知识库收藏
     */
    @PostMapping
    public AjaxResult add(@RequestBody KbsFavorite kbsFavorite) {
        return toAjax(kbsFavoriteService.saveKbsFavorite(kbsFavorite));
    }

    /**
     * 统计文档的收藏数量
     */
    @GetMapping(value = "/stat/{docId}")
    public AjaxResult statDocumentFavoriteNum(@PathVariable("docId") String docId) {
        return success(kbsFavoriteService.statDocumentFavoriteNum(docId));
    }

    /**
     * 获取知识库收藏详细信息
     */
    @GetMapping(value = "/infoByUserId/{docId}")
    public AjaxResult getInfoByFavoriteId(@PathVariable("docId") String docId) {
        return success(kbsFavoriteService.getKbsFavoriteByUserId(docId));
    }

    /**
     * 取消知识库收藏
     */
    @DeleteMapping("/cancel/{docId}")
    public AjaxResult cancelFavoriteByDocUser(@PathVariable("docId") String docId) {
        return toAjax(kbsFavoriteService.cancelFavoriteByDocUser(docId));
    }
}
