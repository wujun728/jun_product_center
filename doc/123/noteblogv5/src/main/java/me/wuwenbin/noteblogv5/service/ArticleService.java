package me.wuwenbin.noteblogv5.service;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.stuxuhai.jpinyin.PinyinException;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.enums.RoleEnum;
import me.wuwenbin.noteblogv5.exception.ArticleException;
import me.wuwenbin.noteblogv5.mapper.CommentMapper;
import me.wuwenbin.noteblogv5.mapper.DictMapper;
import me.wuwenbin.noteblogv5.mapper.UploadMapper;
import me.wuwenbin.noteblogv5.model.entity.*;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.util.RandomUtil.randomInt;

/**
 * @author wuwen
 */
public interface ArticleService extends IService<Article> {

    String HIDE_COMMENT = "comment";
    String HIDE_PURCHASE = "purchase";
    String HIDE_LOGIN = "login";

    String HIDE_COMMENT_REPLACEMENT = "<blockquote data-htype=\"{hideType}\" data-hid=\"{hideId}\" class=\"layui-elem-quote\">此处内容回复可见，" +
            "<a class=\"layui-text\" href=\"#comment-list\">点我回复</a></blockquote>";
    String HIDE_PURCHASE_REPLACEMENT = "<blockquote data-htype=\"{hideType}\" data-hid=\"{hideId}\" class=\"layui-elem-quote\">此处内容需购买（{price}硬币），" +
            "<a class=\"layui-text\" onclick=\"purchaseContent('{articleId}','{hideId}');\">点我购买</a></blockquote>";
    String HIDE_LOGIN_REPLACEMENT = "<blockquote data-htype=\"{hideType}\" data-hid=\"{hideId}\" class=\"layui-elem-quote\">此处内容登录之后可见，" +
            "<a class=\"layui-text\" href=\"/login?t={currentTimes}\" target=\"_blank\">点我登录</a></blockquote>";


    /**
     * 创建文章
     *
     * @param article
     * @param cateIds
     * @param tagNames
     * @return
     * @throws PinyinException
     */
    int createArticle(Article article, List<Integer> cateIds, List<String> tagNames) throws PinyinException;

    /**
     * 修改一篇文章
     *
     * @param article
     * @param cateIds
     * @param tagNames
     * @return
     * @throws PinyinException
     */
    int updateArticle(Article article, List<Integer> cateIds, List<String> tagNames) throws PinyinException;

    /**
     * 首页page
     *
     * @param page
     * @param title
     * @param cateIds
     * @return
     */
    IPage<Article> findIndexArticlePage(Page<Article> page, String title, String cateIds);

    /**
     * 管理文章列表数据
     *
     * @param page
     * @param title
     * @return
     */
    IPage<Article> findAdminArticlePage(Page<Article> page, String title);

    /**
     * 统计素有文章的总字数
     *
     * @return
     */
    long sumArticleWords();

    /**
     * 随机n篇文章
     *
     * @param limit
     * @return
     */
    List<Article> findRandomArticles(int limit);

    /**
     * 计算商品总数
     *
     * @return
     */
    int countProduct();

    /**
     * 根据文章内容生成文章摘要
     *
     * @param article
     */
    default void setArticleSummaryAndTxt(Article article) {
        ParamService paramService = NbUtils.getBean(ParamService.class);
        int summaryLength = Integer.parseInt(paramService.findByName("summary_length").getValue());
        String clearContent = HtmlUtil.cleanHtmlTag(StrUtil.trim(article.getContent()));
        clearContent = StringUtils.trimAllWhitespace(clearContent);
        clearContent = clearContent.substring(0, Math.min(clearContent.length(), summaryLength));
        int allStandardLength = clearContent.length();
        int fullAngelLength = NbUtils.fullAngelWords(clearContent);
        int finalLength = allStandardLength - fullAngelLength / 2;
        if (StringUtils.isEmpty(article.getSummary())) {
            article.setSummary(clearContent.substring(0, Math.min(finalLength, summaryLength)));
        }
        article.setTextContent(clearContent);
    }

    /**
     * 装饰article的一些空值为默认值
     *
     * @param article
     * @param isNew
     */
    default void decorateArticle(Article article, boolean isNew) {
        String cover = article.getCover();
        if (StrUtil.isNotEmpty(cover)) {
            UploadMapper uploadMapper = NbUtils.getBean(UploadMapper.class);
            Upload upload = uploadMapper.selectOne(Wrappers.<Upload>query().eq("virtual_path", cover));
            String imgSrc = upload.getDiskPath();
            File f = new File(imgSrc);
            ImgUtil.scale(f, f, 500, 312, new Color(238, 238, 238));
        }
        if (isNew) {
            Date d = new Date();
            article.setPost(d);
            article.setModify(d);
        } else {
            article.setModify(new Date());
        }
        article.setViews(randomInt(666, 1609));
        article.setApproveCnt(randomInt(6, 169));
        if (StringUtils.isEmpty(article.getAppreciable())) {
            article.setAppreciable(false);
        }
        if (StringUtils.isEmpty(article.getCommented())) {
            article.setCommented(false);
        }
        if (StringUtils.isEmpty(article.getReprinted())) {
            article.setReprinted(false);
        }
        if (StringUtils.isEmpty(article.getTop())) {
            article.setTop(0);
        }
    }

    /**
     * 处理文章内容的隐藏
     *
     * @param article
     */
    default void handleHideArticle(Article article) {
        HideService hideService = NbUtils.getBean(HideService.class);
        String contentHtml = article.getContent();
        contentHtml = contentHtml.replace(" />", ">");
        Document document = Jsoup.parse(contentHtml);
        document.outputSettings().prettyPrint(false);
        JXDocument doc = JXDocument.create(document);

        //处理回复可见
        List<JXNode> hide4Comments = doc.selN(StrUtil.format("//div[@data-hide='{}']", HIDE_COMMENT));
        for (JXNode comment : hide4Comments) {
            String html = comment.asElement().outerHtml();
            String hideId = comment.asElement().attr("data-hid");
            if (StrUtil.isEmpty(hideId)) {
                throw new ArticleException("未获取到隐藏内容的token，请刷新重试！");
            }
            Map<String, Object> hideMap = new HashMap<>(4);
            hideMap.put("hideId", hideId);
            hideMap.put("hideType", HIDE_COMMENT);
            String replacement = StrUtil.format(HIDE_COMMENT_REPLACEMENT, hideMap);
            contentHtml = contentHtml.replace(html, replacement);
            Hide existHideInTable = hideService.getOne(Wrappers.<Hide>query().eq("id", hideId).eq("hide_type", HIDE_COMMENT).eq("article_id", article.getId()));
            if (existHideInTable == null) {
                Hide hide = Hide.builder()
                        .id(hideId)
                        .articleId(article.getId())
                        .hideType(HIDE_COMMENT)
                        .hideHtml(html).build();
                hideService.save(hide);
            } else {
                hideService.update(
                        Wrappers.<Hide>update()
                                .set("hide_html", html)
                                .eq("id", hideId).eq("article_id", article.getId()).eq("hide_type", HIDE_COMMENT));
            }
            article.setContent(contentHtml);
        }

        //处理购买可见
        List<JXNode> hidePurchases = doc.selN(StrUtil.format("//div[@data-hide='{}']", HIDE_PURCHASE));
        for (JXNode purchase : hidePurchases) {
            String html = purchase.asElement().outerHtml();
            String hideId = purchase.asElement().attr("data-hid");
            String hidePrice = purchase.asElement().attr("data-price");
            if (StrUtil.isEmpty(hideId)) {
                throw new ArticleException("未获取到隐藏内容的token，请刷新重试！");
            }
            Map<String, Object> hideMap = new HashMap<>(4);
            hideMap.put("hideId", hideId);
            hideMap.put("hideType", HIDE_PURCHASE);
            hideMap.put("articleId", article.getId());
            hideMap.put("price", hidePrice);
            String replacement = StrUtil.format(HIDE_PURCHASE_REPLACEMENT, hideMap);
            contentHtml = contentHtml.replace(html, replacement);
            Hide existHideInTable = hideService.getOne(Wrappers.<Hide>query().eq("id", hideId).eq("hide_type", HIDE_PURCHASE).eq("article_id", article.getId()));
            if (existHideInTable == null) {
                if (StrUtil.isEmpty(hidePrice)) {
                    throw new RuntimeException("隐藏的购买内容，必须购买硬币的个数！");
                }
                Hide hide = Hide.builder()
                        .id(hideId)
                        .articleId(article.getId())
                        .hideType(HIDE_PURCHASE)
                        .hidePrice(Integer.valueOf(hidePrice))
                        .hideHtml(html).build();
                hideService.save(hide);
            } else {
                hideService.update(
                        Wrappers.<Hide>update()
                                .set("hide_html", html)
                                .eq("id", hideId).eq("article_id", article.getId()).eq("hide_type", HIDE_PURCHASE));
            }
            article.setContent(contentHtml);
        }

        //处理登录可见
        List<JXNode> hideLogins = doc.selN(StrUtil.format("//div[@data-hide='{}']", HIDE_LOGIN));
        for (JXNode login : hideLogins) {
            String html = login.asElement().outerHtml();
            String hideId = login.asElement().attr("data-hid");
            if (StrUtil.isEmpty(hideId)) {
                throw new ArticleException("未获取到隐藏内容的token，请刷新重试！");
            }
            Map<String, Object> hideMap = new HashMap<>(4);
            hideMap.put("hideId", hideId);
            hideMap.put("hideType", HIDE_LOGIN);
            hideMap.put("currentTimes", System.currentTimeMillis());
            String replacement = StrUtil.format(HIDE_LOGIN_REPLACEMENT, hideMap);
            contentHtml = contentHtml.replace(html, replacement);
            Hide existHideInTable = hideService.getOne(Wrappers.<Hide>query().eq("id", hideId).eq("hide_type", HIDE_LOGIN).eq("article_id", article.getId()));
            if (existHideInTable == null) {
                Hide hide = Hide.builder()
                        .id(hideId)
                        .articleId(article.getId())
                        .hideType(HIDE_LOGIN)
                        .hideHtml(html).build();
                hideService.save(hide);
            } else {
                hideService.update(
                        Wrappers.<Hide>update()
                                .set("hide_html", html)
                                .eq("id", hideId).eq("article_id", article.getId()).eq("hide_type", HIDE_LOGIN));
            }
            article.setContent(contentHtml);
        }

        CacheUtils.removeDefaultCache("hideCommentTemp");
        CacheUtils.removeDefaultCache("hidePurchaseTemp");
        CacheUtils.removeDefaultCache("hideLoginTemp");

    }

    /**
     * 处理文章内容的显示
     *
     * @param article
     * @param visitingUser
     */
    default void handleShowArticle(Article article, User visitingUser) {
        String contentHtml = article.getContent();
        contentHtml = contentHtml.replace(" />", ">");
        Document document = Jsoup.parse(contentHtml);
        document.outputSettings().prettyPrint(false);
        JXDocument doc = JXDocument.create(document);

        //处理回复可见
        if (visitingUser != null) {
            if (visitingUser.getRole() == RoleEnum.ADMIN) {
                contentHtml = handleShow(doc, contentHtml, HIDE_COMMENT);
            } else {
                long userId = visitingUser.getId();
                String articleId = article.getId();
                CommentMapper commentMapper = NbUtils.getBean(CommentMapper.class);
                int cnt = commentMapper.selectCount(Wrappers.<Comment>query().eq("article_id", articleId).eq("user_id", userId));
                if (cnt > 0) {
                    contentHtml = handleShow(doc, contentHtml, HIDE_COMMENT);
                }
            }
        }

        //处理购买可见
        if (visitingUser != null) {
            if (visitingUser.getRole() == RoleEnum.ADMIN) {
                contentHtml = handleShow(doc, contentHtml, HIDE_PURCHASE);
            } else {
                long userId = visitingUser.getId();
                String articleId = article.getId();
                HideService hideService = NbUtils.getBean(HideService.class);
                List<JXNode> hides = doc.selN(StrUtil.format("//blockquote[@data-htype='{}']", HIDE_PURCHASE));
                for (JXNode hideNode : hides) {
                    String hideId = hideNode.asElement().attr("data-hid");
                    if (hideService.userIsBought(articleId, userId, hideId)) {
                        Hide hide = hideService.getById(hideId);
                        contentHtml = contentHtml.replace(hideNode.asElement().outerHtml(), hide.getHideHtml());
                    }
                }
            }
        }

        //处理登录可见
        if (visitingUser != null) {
            contentHtml = handleShow(doc, contentHtml, HIDE_LOGIN);
        }

        article.setContent(contentHtml);
    }

    /**
     * 处理隐藏内容
     *
     * @param doc
     * @param contentHtml
     * @param hideType
     * @return
     */
    default String handleShow(JXDocument doc, String contentHtml, String hideType) {
        HideService hideService = NbUtils.getBean(HideService.class);
        List<JXNode> hides = doc.selN(StrUtil.format("//blockquote[@data-htype='{}']", hideType));
        for (JXNode hideNode : hides) {
            String hideId = hideNode.asElement().attr("data-hid");
            Hide hide = hideService.getById(hideId);
            contentHtml = contentHtml.replace(hideNode.asElement().outerHtml(), hide.getHideHtml());
        }
        return contentHtml;
    }


    /**
     * 修改文章的 top 值
     *
     * @param articleId
     * @param top
     * @return
     * @throws Exception
     */
    boolean updateTopById(String articleId, boolean top);

    /**
     * 更新浏览量
     *
     * @param articleId
     */
    void updateViewsById(String articleId);

    /**
     * 更新点赞数
     *
     * @param articleId
     * @return
     */
    int updateApproveCntById(String articleId);

    /**
     * 保存文章的 tags
     *
     * @param articleId
     * @param tagNames
     */
    default void saveArticleTags(String articleId, List<String> tagNames) {
        DictMapper dictMapper = NbUtils.getBean(DictMapper.class);
        JdbcTemplate jdbcTemplate = NbUtils.getBean(JdbcTemplate.class);
        for (String tagName : tagNames) {
            boolean isExist = dictMapper.selectCount(Wrappers.<Dict>query().eq("name", tagName).eq("`group`", DictGroup.GROUP_TAG)) > 0;
            long tagId;
            if (isExist) {
                Dict oldTag = dictMapper.selectOne(Wrappers.<Dict>query().eq("name", tagName).eq("`group`", DictGroup.GROUP_TAG));
                tagId = oldTag.getId();
            } else {
                Dict newTag = Dict.builder().name(tagName).group(DictGroup.GROUP_TAG).build();
                dictMapper.insert(newTag);
                tagId = newTag.getId();
            }
            jdbcTemplate.update("insert into refer_article_tag (article_id, tag_id) values (?,?);", articleId, tagId);
        }
    }
}
