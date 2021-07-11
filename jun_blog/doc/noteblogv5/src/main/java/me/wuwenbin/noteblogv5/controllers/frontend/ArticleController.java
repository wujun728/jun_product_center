package me.wuwenbin.noteblogv5.controllers.frontend;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.enums.OperateType;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.bo.CommentBo;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.User;
import me.wuwenbin.noteblogv5.model.entity.UserCoinRecord;
import me.wuwenbin.noteblogv5.service.UserCoinRecordService;
import me.wuwenbin.noteblogv5.service.UserService;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.HideService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author wuwen
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private final ArticleService articleService;
    private final DictService dictService;
    private final UserService userService;
    private final CommentService commentService;
    private final HideService hideService;
    private final UserCoinRecordService userCoinRecordService;

    public ArticleController(ArticleService articleService, DictService dictService,
                             UserService userService, CommentService commentService,
                             HideService hideService, UserCoinRecordService userCoinRecordService) {
        this.articleService = articleService;
        this.dictService = dictService;
        this.userService = userService;
        this.commentService = commentService;
        this.hideService = hideService;
        this.userCoinRecordService = userCoinRecordService;
    }

    @GetMapping("/{aId}")
    public String article(@PathVariable("aId") String aId, Model model,
                          @ModelAttribute("settings") Map settingsMap,
                          Page<CommentBo> commentPage,
                          HttpServletRequest request) {
        Article article = articleService.getById(aId);
        if (article.getDraft()) {
            //noinspection SpringMVCViewInspection
            return "redirect:/";
        }
        articleService.updateViewsById(aId);

        model.addAttribute("author", userService.getById(article.getAuthorId()).getNickname());

        model.addAttribute("cateList", dictService.findList(DictGroup.GROUP_ARTICLE_CATE));
        model.addAttribute("tags", dictService.findTagsByArticleId(aId));

        String articlePageStyle = MapUtil.getStr(settingsMap, "article_page_style");
        if ("-1".equalsIgnoreCase(articlePageStyle) || "1".equalsIgnoreCase(articlePageStyle)) {
            model.addAttribute("tagList", dictService.findTagList30());
            model.addAttribute("linkList", dictService.findList(DictGroup.GROUP_LINK));
            model.addAttribute("randomArticles", articleService.findRandomArticles(8));
        }
        OrderItem oi = OrderItem.desc("post");
        commentPage.addOrder(oi);
        model.addAttribute("comments",
                commentService.findCommentPage(
                        commentPage, null, null, Collections.singletonList(aId), null, true));

        //处理隐藏标签
        articleService.handleShowArticle(article, getSessionUser(request));
        model.addAttribute("article", article);
        return "frontend/article";
    }

    @GetMapping("/u/{urlSeq}")
    public ModelAndView articleUrl(@PathVariable("urlSeq") String urlSeq) {
        urlSeq = StrUtil.isNotEmpty(urlSeq) ? urlSeq : "";
        Article article = articleService.getOne(Wrappers.<Article>query().eq("url_seq", "/" + urlSeq));
        return new ModelAndView(new RedirectView("/article/" + article.getId()));
    }

    @PostMapping("/comments")
    @ResponseBody
    public IPage<CommentBo> comments(Page<CommentBo> page, String articleId) {
        OrderItem oi = OrderItem.desc("post");
        page.addOrder(oi);
        return commentService
                .findCommentPage(page, null, null, Collections.singletonList(articleId), null, true);
    }


    @PostMapping("/approve")
    @ResponseBody
    public ResultBeanObj approve(@RequestParam String articleId) {
        boolean res = articleService.updateApproveCntById(articleId) == 1;
        return handle(res, "感谢您的点赞！", "请稍后再试！");
    }

    @PostMapping("/token/purchase")
    @ResponseBody
    public ResultBeanObj purchaseHide(@RequestParam String articleId,
                                      @RequestParam String hideId, HttpServletRequest request) {
        User purchaseUser = userService.getById(getSessionUser(request).getId());
        if (purchaseUser == null) {
            return ResultBeanObj.custom(ResultBeanObj.LOGIN_INVALID, "请先登录再进行购买操作！").put("base", basePath(request));
        } else {
            long userId = purchaseUser.getId();
            int remainCoin = purchaseUser.getRemainCoin();
            int hidePrice = hideService.getById(hideId).getHidePrice();
            if (remainCoin >= hidePrice) {
                boolean r = userService.update(Wrappers.<User>update().set("remain_coin", remainCoin - hidePrice).eq("id", userId));
                if (r) {
                    int cnt = hideService.purchaseArticleHideContent(articleId, hideId, userId);
                    if (cnt == 1) {
                        UserCoinRecord record = UserCoinRecord.builder()
                                .operateTime(new Date()).remainCoin(remainCoin - hidePrice).userId(userId).operateType(OperateType.PURCHASE_MINUS)
                                .operateValue(hidePrice).remark(OperateType.PURCHASE_MINUS.getDesc()).build();
                        userCoinRecordService.save(record);
                        updateSessionUser(request, userService.getById(userId));
                    }
                    return handle(cnt == 1, "购买成功！", "购买失败！");
                } else {
                    return ResultBeanObj.error("购买失败，请稍后再试！");
                }
            } else {
                return ResultBeanObj.error("您的硬币不足，请充值之后再购买！");
            }
        }
    }
}
