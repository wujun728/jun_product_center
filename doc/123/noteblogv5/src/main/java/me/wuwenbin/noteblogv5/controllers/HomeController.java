package me.wuwenbin.noteblogv5.controllers;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.Comment;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * @author wuwen
 */
@Controller
public class HomeController extends BaseController {

    private final DictService dictService;
    private final ParamService paramService;
    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;

    public HomeController(DictService dictService, ParamService paramService,
                          ArticleService articleService, UserService userService,
                          CommentService commentService) {
        this.dictService = dictService;
        this.paramService = paramService;
        this.articleService = articleService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping(value = {"/index", "/"})
    public String index(Model model, Page<Article> page, String cateIds,
                        @RequestParam(required = false, defaultValue = "1") String p,
                        @ModelAttribute("settings") Map settingsMap) {
        setPageNoSize(page, !NumberUtil.isNumber(p) || NumberUtil.parseInt(p) <= 0 ? 1 : NumberUtil.parseInt(p));
        setPageOrder(page, OrderItem.desc("top"), OrderItem.desc("post"));
        model.addAttribute("indexPage", articleService.findIndexArticlePage(page, null, cateIds));

        String typesetting = MapUtil.getStr(settingsMap, "typesetting");

        Map<String, String> articleAuthorNames = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> userService.getById(article.getAuthorId()).getNickname()));
        model.addAttribute("articleAuthors", articleAuthorNames);

        Map<String, List<Dict>> articleCatesMap = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> dictService.findCatesByArticleId(article.getId())));
        model.addAttribute("articleCatesMap", articleCatesMap);

        Map<String, Integer> commentCounts = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> commentService.count(Wrappers.<Comment>query().eq("article_id", article.getId()))));
        model.addAttribute("articleCommentCountMap", commentCounts);

        if ("card_media".equalsIgnoreCase(typesetting) || "long_card".equalsIgnoreCase(typesetting)) {
            Map<String, List<Dict>> articleTagsMap = page.getRecords().stream()
                    .collect(toMap(Article::getId, article -> dictService.findTagsByArticleId(article.getId())));
            model.addAttribute("articleTagsMap", articleTagsMap);
        }

        if ("left3right9".equalsIgnoreCase(typesetting) || "left9right3".equalsIgnoreCase(typesetting)) {
            model.addAttribute("tagList", dictService.findTagList30());
            model.addAttribute("linkList", dictService.findList(DictGroup.GROUP_LINK));
            model.addAttribute("randomArticles", articleService.findRandomArticles(8));
        }

        return "frontend/index";
    }

    @GetMapping("/management/index")
    public String mIndex() {
        return "management/admin_index";
    }


    @PostMapping("/checkUpdate")
    @ResponseBody
    public ResultBeanObj checkUpdate(String clientVersion) {
        String serverVersion = NBV5.VERSION;
        if (StrUtil.isNotEmpty(clientVersion) && !serverVersion.equals(clientVersion)) {
            return ResultBeanObj.ok("检测到更新，最新版本【" + serverVersion + "】请前往<a href='https://wuwenbin.me/article/u/noteblogv5_upadtes' target='_blank'>【此处】</a>更新");
        } else {
            return ResultBeanObj.ok("您用的版本为最新版本！");
        }
    }

    private void setPageNoSize(Page<Article> page, int p) {
        page.setCurrent(p <= 0 ? 1 : p);
        int pageSize = Integer.parseInt(paramService.findByName("article_page_size").getValue());
        page.setSize(pageSize);
    }

    private void setPageOrder(Page<Article> page, OrderItem... ois) {
        page.addOrder(ois);
    }

}
