package me.wuwenbin.noteblogv5.controllers.frontend;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.Comment;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.CommentService;
import me.wuwenbin.noteblogv5.service.ParamService;
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
@RequestMapping("/product")
public class ProductController extends BaseController {

    private final DictService dictService;
    private final ParamService paramService;
    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;

    public ProductController(DictService dictService, ParamService paramService,
                             ArticleService articleService, UserService userService,
                             CommentService commentService) {
        this.dictService = dictService;
        this.paramService = paramService;
        this.articleService = articleService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/index")
    public String index(Model model, Page<Article> page,
                        @RequestParam(required = false, defaultValue = "1") String p) {
        setPageNoSize(page, !NumberUtil.isNumber(p) || NumberUtil.parseInt(p) <= 0 ? 1 : NumberUtil.parseInt(p));
        setPageOrder(page, OrderItem.desc("top"), OrderItem.desc("post"));

        Dict productDict = dictService.findProductDict();
        if (productDict != null) {
            IPage<Article> articlePage = articleService.findIndexArticlePage(page, null, String.valueOf(productDict.getId()));
            model.addAttribute("indexPage", articlePage);

        } else {
            model.addAttribute("indexPage", new Page<>());
        }

        Map<String, String> articleAuthorNames = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> userService.getById(article.getAuthorId()).getNickname()));
        model.addAttribute("articleAuthors", articleAuthorNames);

        Map<String, List<Dict>> articleCatesMap = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> dictService.findCatesByArticleId(article.getId())));
        model.addAttribute("articleCatesMap", articleCatesMap);

        Map<String, Integer> commentCounts = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> commentService.count(Wrappers.<Comment>query().eq("article_id", article.getId()))));
        model.addAttribute("articleCommentCountMap", commentCounts);

        Map<String, List<Dict>> articleTagsMap = page.getRecords().stream()
                .collect(toMap(Article::getId, article -> dictService.findTagsByArticleId(article.getId())));
        model.addAttribute("articleTagsMap", articleTagsMap);

        model.addAttribute("productCount", articleService.countProduct());

        return "frontend/product";
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
