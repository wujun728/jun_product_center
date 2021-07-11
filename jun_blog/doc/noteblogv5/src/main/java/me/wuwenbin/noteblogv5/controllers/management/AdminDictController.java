package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management/dict")
public class AdminDictController extends BaseController {

    private final HttpServletRequest request;
    private final DictService dictService;


    public AdminDictController(HttpServletRequest request, DictService dictService) {
        this.request = request;
        this.dictService = dictService;
    }

    @GetMapping("/catetag")
    public String cateTagPage(String cateName, String tagName,String downloadCateName) {
        request.setAttribute("cateList",
                dictService.list(Wrappers.<Dict>query()
                        .eq("`group`", DictGroup.GROUP_ARTICLE_CATE)
                        .like(StrUtil.isNotEmpty(cateName), "name", cateName)));
        request.setAttribute("searchCate", cateName);
        request.setAttribute("tagList",
                dictService.list(Wrappers.<Dict>query()
                        .eq("`group`", DictGroup.GROUP_TAG)
                        .like(StrUtil.isNotEmpty(tagName), "name", tagName)));
        request.setAttribute("searchTag", tagName);
        request.setAttribute("downloadCateList",
                dictService.list(Wrappers.<Dict>query()
                        .eq("`group`", DictGroup.GROUP_DOWNLOAD_CATE)
                        .like(StrUtil.isNotEmpty(downloadCateName), "name", downloadCateName)));
        request.setAttribute("searchDownloadCateName", downloadCateName);
        return "management/dict/cates_tags";
    }

    @GetMapping("/keyword")
    public String keywordPage(String keyword) {
        request.setAttribute("keywords",
                dictService.list(Wrappers.<Dict>query()
                        .eq("`group`", DictGroup.GROUP_KEYWORD)
                        .like(StrUtil.isNotEmpty(keyword), "name", keyword)));
        request.setAttribute("searchKeyword", keyword);
        return "management/dict/keyword";
    }

    @GetMapping("/link")
    public String linkPage(String name) {
        request.setAttribute("links",
                dictService.list(Wrappers.<Dict>query()
                        .eq("`group`", DictGroup.GROUP_LINK)
                        .like(StrUtil.isNotEmpty(name), "name", StrUtil.format("{},", name))));
        request.setAttribute("searchLinkName", name);
        return "management/dict/link";
    }

    @GetMapping("/cate/list")
    @ResponseBody
    public ResultBeanObj cateList() {
        List<Dict> dictList = dictService.findList(DictGroup.GROUP_ARTICLE_CATE);
        List<Map<String, Object>> cates = new ArrayList<>(dictList.size());
        for (Dict cate : dictList) {
            Map<String, Object> c = new HashMap<>(4);
            c.put("name", cate.getName());
            c.put("value", cate.getId());
            c.put("selected", "");
            c.put("disabled", "");
            cates.add(c);
        }
        return ResultBeanObj.ok("获取成功", cates);
    }

    @GetMapping("/downloadCate/list")
    @ResponseBody
    public ResultBeanObj downloadCateList() {
        List<Dict> dictList = dictService.findList(DictGroup.GROUP_DOWNLOAD_CATE);
        List<Map<String, Object>> cates = new ArrayList<>(dictList.size());
        for (Dict cate : dictList) {
            Map<String, Object> c = new HashMap<>(4);
            c.put("name", cate.getName());
            c.put("value", cate.getId());
            c.put("selected", "");
            c.put("disabled", "");
            cates.add(c);
        }
        return ResultBeanObj.ok("获取成功", cates);
    }

    @PostMapping("/cate/add")
    @ResponseBody
    public ResultBeanObj addCate(String cateName) {
        Dict c = dictService.getOne(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_ARTICLE_CATE).eq("name", cateName));
        if (c == null) {
            boolean res = dictService.save(Dict.builder().name(cateName).group(DictGroup.GROUP_ARTICLE_CATE).build());
            if (res) {
                CacheUtils.removeDefaultCache("cateGroupList");
            }
            return handle(res, "添加成功！", "添加失败！");
        } else {
            return ResultBeanObj.error("已经有此分类，不能重复添加！");
        }
    }

    @PostMapping("/downloadCate/add")
    @ResponseBody
    public ResultBeanObj addDownloadCate(String downloadCateName) {
        Dict c = dictService.getOne(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_DOWNLOAD_CATE).eq("name", downloadCateName));
        if (c == null) {
            boolean res = dictService.save(Dict.builder().name(downloadCateName).group(DictGroup.GROUP_DOWNLOAD_CATE).build());
            return handle(res, "添加成功！", "添加失败！");
        } else {
            return ResultBeanObj.error("已经有此下载分类，不能重复添加！");
        }
    }


    @PostMapping("/tag/add")
    @ResponseBody
    public ResultBeanObj addTag(String tagName) {
        Dict t = dictService.getOne(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_TAG).eq("name", tagName));
        if (t == null) {
            boolean res = dictService.save(Dict.builder().name(tagName).group(DictGroup.GROUP_TAG).build());
            if (res) {
                CacheUtils.removeDefaultCache("cateGroupList");
            }
            return handle(res, "添加成功！", "添加失败！");
        } else {
            return ResultBeanObj.error("已经有此标签，不能重复添加！");
        }
    }

    @PostMapping("/keyword/add")
    @ResponseBody
    public ResultBeanObj addKeyword(String keyword) {
        Dict t = dictService.getOne(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_KEYWORD).eq("name", keyword));
        if (t == null) {
            boolean res = dictService.save(Dict.builder().name(keyword).group(DictGroup.GROUP_KEYWORD).build());
            return handle(res, "添加成功！", "添加失败！");
        } else {
            return ResultBeanObj.error("已经有此关键字，不能重复添加！");
        }
    }

    @PostMapping("/link/add")
    @ResponseBody
    public ResultBeanObj addLink(String linkName, String linkHref) {
        Dict t = dictService.getOne(Wrappers.<Dict>query()
                .eq("`group`", DictGroup.GROUP_LINK)
                .likeRight("name", StrUtil.format("{},", linkName)));
        if (t == null) {
            boolean res = dictService.save(
                    Dict.builder().name(StrUtil.format("{}，{}", linkName, linkHref)).group(DictGroup.GROUP_LINK).build());
            return handle(res, "添加成功！", "添加失败！");
        } else {
            return ResultBeanObj.error("已存在，请检查是否重复添加！");
        }
    }

    @PostMapping("/cate/delete")
    @ResponseBody
    public ResultBeanObj deleteCate(Long id) {
        int c = dictService.articleCateReferCnt(id);
        if (c > 0) {
            return ResultBeanObj.error("请删除所有相关的文章再做操作！");
        } else {
            boolean res = dictService.removeById(id);
            if (res) {
                CacheUtils.removeDefaultCache("cateGroupList");
            }
            return handle(res, "删除成功！", "删除失败！");
        }
    }

    @PostMapping("/downloadCate/delete")
    @ResponseBody
    public ResultBeanObj deleteDownloadCate(Long id) {
        int c = dictService.downloadCateReferCnt(id);
        if (c > 0) {
            return ResultBeanObj.error("请删除所有相关的下载再做操作！");
        } else {
            boolean res = dictService.removeById(id);
            return handle(res, "删除成功！", "删除失败！");
        }
    }

    @PostMapping("/tag/delete")
    @ResponseBody
    public ResultBeanObj deleteTag(Long id) {
        int c = dictService.articleTagReferCnt(id);
        if (c > 0) {
            return ResultBeanObj.error("请删除所有相关的文章再做操作！");
        } else {
            boolean res = dictService.removeById(id);
            return handle(res, "删除成功！", "删除失败！");
        }
    }

    @PostMapping("/keyword/delete")
    @ResponseBody
    public ResultBeanObj deleteKeyword(Long id) {
        boolean res = dictService.removeById(id);
        return handle(res, "删除成功！", "删除失败！");
    }

    @PostMapping("/link/delete")
    @ResponseBody
    public ResultBeanObj deleteLink(Long id) {
        boolean res = dictService.removeById(id);
        return handle(res, "删除成功！", "删除失败！");
    }

}
