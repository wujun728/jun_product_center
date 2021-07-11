package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.LayuiTable;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.model.entity.Download;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.DownloadService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management/download")
public class AdminDownloadController extends BaseController {

    private final DownloadService downloadService;
    private final DictService dictService;
    private final JdbcTemplate jdbcTemplate;

    public AdminDownloadController(DownloadService downloadService, DictService dictService, JdbcTemplate jdbcTemplate) {
        this.downloadService = downloadService;
        this.dictService = dictService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/add")
    public String addDownloadPage(Model model) {
        model.addAttribute("downloadCateList", dictService.list(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_DOWNLOAD_CATE)));
        return "management/download/add";
    }

    @GetMapping("/page")
    public String notePage() {
        return "management/download/list";
    }

    @RequestMapping("/edit")
    public String editDownloadPage(Model model, String id) {
        Download download = downloadService.getById(id);
        model.addAttribute("editDownload", download);
        model.addAttribute("downloadCateList", dictService.list(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_DOWNLOAD_CATE)));
        model.addAttribute("downloads", dictService.findCatesByDownloadId(id));
        return "management/download/edit";
    }

    @GetMapping("/list")
    @ResponseBody
    public LayuiTable<Download> downloadListPage(Page<Download> page, String sort, String order, String title) {
        addPageOrder(page, order, sort);
        IPage<Download> downloadPage = downloadService.page(page,
                Wrappers.<Download>query().like(StrUtil.isNotEmpty(title), "title", title));
        return new LayuiTable<>(downloadPage.getTotal(), downloadPage.getRecords());
    }

    @PostMapping("/create")
    @ResponseBody
    public ResultBeanObj createDownload(@Valid Download download, HttpServletRequest request,
                                        @RequestParam(required = false, value = "downloadCateIds[]") List<Integer> downloadCateIds,
                                        BindingResult result) {
        if (result.getErrorCount() == 0) {
            if (downloadCateIds == null || downloadCateIds.size() == 0) {
                return ResultBeanObj.error("请至少选择一个分类");
            }
            if (downloadCateIds.size() > 3) {
                return ResultBeanObj.error("分类选择最多不能超过3个！");
            }
            download.setId(IdUtil.objectId());
            download.setCreateUserId(getSessionUser(request).getId());
            boolean res = downloadService.save(download);
            if (res) {
                String downloadId = download.getId();
                downloadCateIds.forEach(downloadCateId -> jdbcTemplate.update("insert into refer_download_cate(download_id, cate_id) values (?,?)", downloadId, downloadCateId));
            }
            return handle(res, "保存成功！", "保存失败！");
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultBeanObj updateDownload(@Valid Download download,
                                        @RequestParam(required = false, value = "downloadCateIds[]") List<Integer> downloadCateIds,
                                        BindingResult result) {
        if (result.getErrorCount() == 0) {
            download.setCreateTime(null);
            download.setUpdateTime(new Date());
            boolean res = downloadService.updateById(download);
            if (res) {
                String downloadId = download.getId();
                jdbcTemplate.update("delete from refer_download_cate where download_id = ?", downloadId);
                downloadCateIds.forEach(downloadCateId -> jdbcTemplate.update("insert into refer_download_cate(download_id, cate_id) values (?,?)", downloadId, downloadCateId));
            }
            return handle(res, "修改成功！", "修改失败！");
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }

    @PostMapping("/update/top")
    @ResponseBody
    public ResultBeanObj top(Long id, Boolean status) {
        boolean res = downloadService.update(
                Wrappers.<Download>update().set("top", status).eq("id", id));
        return handle(res, "修改成功！", "修改失败！");
    }


    @PostMapping("/delete")
    @ResponseBody
    public ResultBeanObj delete(Long id) {
        boolean res = downloadService.removeById(id);
        return handle(res, "删除成功！", "删除失败！");
    }
}
