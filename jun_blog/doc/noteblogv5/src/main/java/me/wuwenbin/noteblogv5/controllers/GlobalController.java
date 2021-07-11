package me.wuwenbin.noteblogv5.controllers;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.model.entity.Param;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.service.CommentService;
import me.wuwenbin.noteblogv5.service.ParamService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * created by Wuwenbin on 2018/9/7 at 9:35
 *
 * @author wuwenbin
 */
@ControllerAdvice(basePackages = "me.wuwenbin.noteblogv5.controllers")
public class GlobalController {

    private final ParamService paramService;
    private final DictService dictService;
    private final ArticleService articleService;
    private final CommentService commentService;

    public GlobalController(ParamService paramService, DictService dictService,
                            ArticleService articleService, CommentService commentService) {
        this.paramService = paramService;
        this.dictService = dictService;
        this.articleService = articleService;
        this.commentService = commentService;
    }

    @ModelAttribute("settings")
    public void addSettings(Model model, HttpServletRequest request) {
        String genParams = "gen_params";
        Map settingsMap = CacheUtils.getParamCache().get(genParams, Map.class);
        if (settingsMap != null && settingsMap.size() > 0) {
            //noinspection unchecked
            Object rechargeUrl = settingsMap.getOrDefault("cash_recharge_url", "{\"name\":\"\",\"url\":\"\"}");
            JSONArray jsonArray = JSONUtil.parseArray(rechargeUrl);
            //noinspection unchecked
            settingsMap.put("recharges", jsonArray);
            model.addAttribute("settings", settingsMap);
        } else {
            List<Param> params = paramService.list(Wrappers.<Param>query().ge("`group`", 0));
            settingsMap = params.stream().collect(Collectors.toMap(Param::getName, p -> p.getValue() == null ? "" : p.getValue()));
            //noinspection unchecked
            Object rechargeUrl = settingsMap.getOrDefault("cash_recharge_url", "{\"name\":\"\",\"url\":\"\"}");
            JSONArray jsonArray = JSONUtil.parseArray(rechargeUrl);
            //noinspection unchecked
            settingsMap.put("recharges", jsonArray);
            CacheUtils.getParamCache().put(genParams, settingsMap);
            model.addAttribute("settings", settingsMap);
        }
        if (!request.getRequestURL().toString().contains("/management/")) {
            List cateGroupList = CacheUtils.fetchFromDefaultCache("cateGroupList", List.class);
            if (cateGroupList == null) {
                cateGroupList = dictService.findList(DictGroup.GROUP_ARTICLE_CATE);
                CacheUtils.putIntoDefaultCache("cateGroupList", cateGroupList);
            }

            Integer articleCount = CacheUtils.fetchFromDefaultCache("articleCount", Integer.class);
            if (articleCount == null) {
                articleCount = articleService.count();
                CacheUtils.putIntoDefaultCache("articleCount", articleCount);
            }

            Long articleWords = CacheUtils.fetchFromDefaultCache("articleWords", Long.class);
            if (articleWords == null) {
                articleWords = articleService.sumArticleWords();
                CacheUtils.putIntoDefaultCache("articleWords", articleWords);
            }

            Integer commentCount = CacheUtils.fetchFromDefaultCache("commentCount", Integer.class);
            if (commentCount == null) {
                commentCount = commentService.count();
                CacheUtils.putIntoDefaultCache("commentCount", commentCount);
            }
            model.addAttribute("cateList", cateGroupList);
            model.addAttribute("blogCount", articleCount);
            model.addAttribute("blogWords", articleWords);
            model.addAttribute("runningDays", paramService.calcRunningDays());
            model.addAttribute("commentCount", commentCount);
        }
    }


}
