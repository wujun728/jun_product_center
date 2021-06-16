package me.wuwenbin.noteblogv5.controllers.frontend;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.vdurmont.emoji.EmojiParser;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.constant.NBV5;
import me.wuwenbin.noteblogv5.controllers.BaseController;
import me.wuwenbin.noteblogv5.model.json.ResultBeanObj;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.Comment;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.MailService;
import me.wuwenbin.noteblogv5.service.CommentService;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by Wuwenbin on 2018/2/8 at 18:54
 *
 * @author wuwenbin
 */
@Controller
@RequestMapping("/token/comment")
public class CommentController extends BaseController {

    private final ParamService paramService;
    private final ArticleService articleService;
    private final DictService dictService;
    private final CommentService commentService;
    private final MailService mailService;

    public CommentController(ParamService paramService,
                             ArticleService articleService, DictService dictService,
                             CommentService commentService, MailService mailService) {
        this.paramService = paramService;
        this.articleService = articleService;
        this.dictService = dictService;
        this.commentService = commentService;
        this.mailService = mailService;
    }

    @PostMapping("/sub")
    @ResponseBody
    public ResultBeanObj sub(@Valid Comment comment, BindingResult bindingResult, HttpServletRequest request) {
        Param globalCommentOnOff = paramService.findByName("global_comment_onoff");
        Article article = articleService.getById(comment.getArticleId());
        if ("1".equals(globalCommentOnOff.getValue()) && article.getCommented()) {
            if (!bindingResult.hasErrors()) {
                comment.setIpAddr(NbUtils.getRemoteAddress(request));
                boolean develop = NbUtils.getBean(Environment.class).getProperty("app.develop", Boolean.class, true);
                if (!develop) {
                    comment.setIpInfo(NbUtils.getIpInfo(comment.getIpAddr()).getAddress());
                } else {
                    comment.setIpInfo("本地/未知");
                }
                comment.setUserAgent(request.getHeader("user-agent"));
                comment.setComment(
                        HtmlUtil.removeHtmlTag(NbUtils.stripSqlXss(comment.getComment()),
                                false, "style", "link", "meta", "script"));
                comment.setComment(EmojiParser.parseToHtmlDecimal(comment.getComment()));
                comment.setPost(LocalDateTime.now());
                comment.setClearComment(HtmlUtil.cleanHtmlTag(comment.getComment()));
                int floor = commentService.findMaxFloorByArticleId(comment.getArticleId());
                comment.setFloor(floor);
                List<Dict> keywords = dictService.findList(DictGroup.GROUP_KEYWORD);
                keywords.forEach(
                        kw -> comment.setComment(comment.getComment().replace(kw.getName(), StrUtil.repeat("*", kw.getName().length()))));
                if (StringUtils.isEmpty(comment.getComment())) {
                    return ResultBeanObj.error("评论正确填写评论内容！");
                }
                if (commentService.save(comment)) {
                    if ("1".equals(paramService.findByName(NBV5.COMMENT_MAIL_NOTICE_ONOFF).getValue())) {
                        mailService.sendNoticeMail(basePath(request), articleService.getById(comment.getArticleId()), comment.getComment());
                    }
                    CacheUtils.removeDefaultCache("commentCount");
                    return ResultBeanObj.ok("发表评论成功");
                } else {
                    return ResultBeanObj.error("发表评论失败");
                }
            } else {
                return ajaxJsr303(bindingResult.getFieldErrors());
            }
        } else {
            return ResultBeanObj.error("未开放评论！");
        }
    }
}
