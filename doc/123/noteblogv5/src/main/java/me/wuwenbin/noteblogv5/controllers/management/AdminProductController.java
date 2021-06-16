package me.wuwenbin.noteblogv5.controllers.management;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.stuxuhai.jpinyin.PinyinException;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.LayuiTable;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.*;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.HideService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.CommentService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/management/product")
public class AdminProductController extends BaseController {

    private final ArticleService articleService;
    private final DictService dictService;
    private final HideService hideService;
    private final CommentService commentService;

    public AdminProductController(ArticleService articleService,
                                  DictService dictService, HideService hideService,
                                  CommentService commentService) {
        this.articleService = articleService;
        this.dictService = dictService;
        this.hideService = hideService;
        this.commentService = commentService;
    }


    @GetMapping("/add")
    public String publishProductPage(HttpServletRequest request) {
        List<Dict> productDicts = dictService.list(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_ARTICLE_CATE));
        List<Dict> productDicts2 = productDicts.stream().filter(dict -> !dict.getName().equals(Dict.PRODUCT)).collect(Collectors.toList());
        request.setAttribute("cateList", productDicts2);
        request.setAttribute("generateNextArticleId", IdUtil.objectId());
        return "management/product/add";
    }

    @GetMapping("/edit")
    public String edit(Model model, String id, HttpServletRequest request) {
        Article article = articleService.getById(id);
        articleService.handleShowArticle(article, getSessionUser(request));
        List<Dict> productDicts = dictService.list(Wrappers.<Dict>query().eq("`group`", DictGroup.GROUP_ARTICLE_CATE));
        List<Dict> productDicts2 = productDicts.stream().filter(dict -> !dict.getName().equals(Dict.PRODUCT)).collect(Collectors.toList());
        model.addAttribute("cateList", productDicts2);
        model.addAttribute("editArticle", article);
        model.addAttribute("tags", dictService.findTagsByArticleId(id));
        model.addAttribute("cates", dictService.findCatesByArticleId(id));
        return "management/product/edit";
    }

    @GetMapping("/page")
    public String productListPage() {
        return "management/product/list";
    }

    @GetMapping("/list")
    @ResponseBody
    public LayuiTable<Article> productListPage(Page<Article> page, String sort, String order, String title) {
        addPageOrder(page, order, sort);
        Dict productDict = dictService.findProductDict();
        if (productDict != null) {
            IPage<Article> articlePage = articleService.findIndexArticlePage(page, title, String.valueOf(productDict.getId()));
            return new LayuiTable<>(articlePage.getTotal(), articlePage.getRecords());
        } else {
            return new LayuiTable<>(0, new ArrayList<>());
        }
    }


    @PostMapping("/create")
    @ResponseBody
    public ResultBeanObj productCreate(@Valid Article article, BindingResult result, HttpServletRequest request,
                                       @RequestParam(required = false, value = "cateIds[]") List<Integer> cateIds,
                                       @RequestParam(required = false, value = "tagNames[]") List<String> tagNames) {
        String generateNextArticleId = request.getParameter("generateNextArticleId");
        if (StringUtils.isEmpty(generateNextArticleId)) {
            return ResultBeanObj.error("产品唯一标识生成失败，请刷新页面重试！");
        } else {
            int cnt = articleService.count(Wrappers.<Article>query().eq("id", generateNextArticleId));
            if (cnt > 0) {
                return ResultBeanObj.error("产品唯一标识重复，请刷新页面重试！");
            } else {
                article.setId(generateNextArticleId);
            }
        }
        if (result.getErrorCount() == 0) {
            Dict dict = dictService.findProductDict();
            if (cateIds == null) {
                cateIds = new ArrayList<>();
            }
            cateIds.add(dict.getId().intValue());
            if (cateIds.size() > 3) {
                return ResultBeanObj.error("分类选择最多不能超过3个！");
            }
            User su = getSessionUser(request);
            article.setAuthorId(su.getId());
            try {
                int cnt = articleService.createArticle(article, cateIds, tagNames);
                return handle(cnt == 1, "发布成功！", "发布失败！");
            } catch (PinyinException e) {
                return ResultBeanObj.error("自定义文章链接出现非法值，请重新输入！");
            }
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }

    @ResponseBody
    @PostMapping("/update/{field}")
    public ResultBeanObj updateProduct(String id, @PathVariable("field") String field, Boolean status) {
        String appreciable = "appreciable", commented = "commented", top = "top";
        if (top.equalsIgnoreCase(field)) {
            boolean res = articleService.updateTopById(id, status);
            return handle(res, "操作成功！", "操作失败！");
        } else if (appreciable.equalsIgnoreCase(field) || commented.equalsIgnoreCase(field)) {
            boolean res = articleService.update(Wrappers.<Article>update().set(field, status).eq("id", id));
            return handle(res, "修改成功！", "修改失败！");
        } else {
            return ResultBeanObj.error("参数错误！");
        }
    }


    @PostMapping("/update")
    @ResponseBody
    public ResultBeanObj productUpdate(@Valid Article article, BindingResult result, HttpServletRequest request,
                                       @RequestParam(required = false, value = "cateIds[]") List<Integer> cateIds,
                                       @RequestParam(required = false, value = "tagNames[]") List<String> tagNames) {
        if (result.getErrorCount() == 0) {
            Dict dict = dictService.findProductDict();
            if (cateIds == null) {
                cateIds = new ArrayList<>();
            }
            cateIds.removeIf(cateId -> cateId == dict.getId().intValue());
            cateIds.add(dict.getId().intValue());
            if (cateIds.size() > 3) {
                return ResultBeanObj.error("分类选择最多不能超过3个！");
            }
            User su = getSessionUser(request);
            article.setAuthorId(su.getId());
            try {
                int cnt = articleService.updateArticle(article, cateIds, tagNames);
                return handle(cnt > 0, "修改成功！", "修改失败！");
            } catch (PinyinException e) {
                return ResultBeanObj.error("自定义文章链接出现非法值，请重新定义！");
            }
        } else {
            return ajaxJsr303(result.getFieldErrors());
        }
    }


    @PostMapping("/delete")
    @ResponseBody
    public ResultBeanObj delete(String id) {
        boolean res = articleService.removeById(id);
        dictService.deleteArticleRefer(id);
        hideService.deleteArticlePurchaseRefer(id);
        hideService.remove(Wrappers.<Hide>query().eq("article_id", id));
        commentService.remove(Wrappers.<Comment>query().eq("article_id", id));
        if (res) {
            CacheUtils.removeDefaultCache("articleCount");
            CacheUtils.removeDefaultCache("articleWords");
            NbUtils.deleteUploadTempOnArticleDelete(id);
        }
        return handle(res, "删除成功！", "删除失败！");
    }

    @ResponseBody
    @PostMapping("/getNextHideId")
    public ResultBeanObj getNextHideId() {
        return ResultBeanObj.ok("获取成功！", IdUtil.objectId());
    }
}
