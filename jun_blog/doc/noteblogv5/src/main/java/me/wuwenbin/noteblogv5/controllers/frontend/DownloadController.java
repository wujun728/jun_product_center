package me.wuwenbin.noteblogv5.controllers.frontend;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.model.entity.Download;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.DownloadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/dl")
public class DownloadController extends BaseController {

    private final DownloadService downloadService;
    private final DictService dictService;

    public DownloadController(DownloadService downloadService, DictService dictService) {
        this.downloadService = downloadService;
        this.dictService = dictService;
    }

    @GetMapping
    public String downloadPage(Model model, Page<Download> page,
                               @RequestParam(required = false, defaultValue = "1") String p, String w,
                               @RequestParam(required = false, defaultValue = "create_time") String o,
                               @RequestParam(required = false, defaultValue = "desc") String s) {
        addPageOrder(page, "desc", "`top`");
        addPageOrder(page, s, o);
        page.setCurrent(!NumberUtil.isNumber(p) || NumberUtil.parseLong(p) <= 0 ? 1 : NumberUtil.parseLong(p));
        IPage<Download> downloadPage = downloadService.page(page,
                Wrappers.<Download>query()
                        .like(StrUtil.isNotEmpty(w), "title", w));

        Map<String, List<Dict>> downloadCatesMap = page.getRecords().stream()
                .collect(toMap(Download::getId, download -> dictService.findCatesByDownloadId(download.getId())));
        model.addAttribute("downloadCatesMap", downloadCatesMap);

        model.addAttribute("downloadPage", downloadPage);
        model.addAttribute("searchWords", w);
        return "frontend/download";
    }

}
