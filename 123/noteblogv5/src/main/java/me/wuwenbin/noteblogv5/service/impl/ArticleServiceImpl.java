package me.wuwenbin.noteblogv5.service.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import me.wuwenbin.noteblogv5.exception.AppRunningException;
import me.wuwenbin.noteblogv5.exception.ArticleCreateException;
import me.wuwenbin.noteblogv5.exception.ArticleUpdateException;
import me.wuwenbin.noteblogv5.mapper.ArticleMapper;
import me.wuwenbin.noteblogv5.mapper.DictMapper;
import me.wuwenbin.noteblogv5.model.entity.Article;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.service.ArticleService;
import me.wuwenbin.noteblogv5.service.DictService;
import me.wuwenbin.noteblogv5.utils.CacheUtils;
import me.wuwenbin.noteblogv5.utils.NbUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;
    private final DictMapper dictMapper;
    private final JdbcTemplate jdbcTemplate;

    public ArticleServiceImpl(ArticleMapper articleMapper, JdbcTemplate jdbcTemplate, DictMapper dictMapper) {
        this.articleMapper = articleMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.dictMapper = dictMapper;
    }

    @Override
    public int createArticle(Article article, List<Integer> cateIds, List<String> tagNames) throws PinyinException {
        if (CollectionUtils.isEmpty(cateIds)) {
            throw new AppRunningException("文章至少得有一个分类归属！");
        }
        if (StrUtil.isNotEmpty(article.getUrlSeq())) {
            article.setUrlSeq(PinyinHelper.convertToPinyinString(article.getUrlSeq(), "", PinyinFormat.WITHOUT_TONE));
            int cnt = articleMapper.selectCount(Wrappers.<Article>query().eq("url_seq", article.getUrlSeq()));
            boolean isExistUrl = cnt > 0;
            if (isExistUrl) {
                throw new AppRunningException("已存在 url：" + article.getUrlSeq());
            }
        }
        handleHideArticle(article);
        setArticleSummaryAndTxt(article);
        decorateArticle(article, true);
        int affect = articleMapper.insert(article);
        if (affect == 1) {
            String articleId = article.getId();
            cateIds.forEach(cateId -> jdbcTemplate.update("insert into refer_article_cate values (?,?)", articleId, cateId));
            if (!CollectionUtils.isEmpty(tagNames)) {
                saveArticleTags(articleId, tagNames);
            }
            CacheUtils.removeDefaultCache("articleCount");
            CacheUtils.removeDefaultCache("articleWords");
            NbUtils.deleteUploadTempWhenArticleNotSave();
        } else {
            throw new ArticleCreateException("出现错误，请重试！");
        }
        return affect;
    }

    @Override
    public int updateArticle(Article article, List<Integer> cateIds, List<String> tagNames) throws PinyinException {
        if (CollectionUtils.isEmpty(cateIds)) {
            throw new AppRunningException("文章至少得有一个分类归属！");
        }
        if (StringUtils.isEmpty(article.getId())) {
            throw new AppRunningException("未指定修改文章的ID");
        }
        if (StrUtil.isNotEmpty(article.getUrlSeq())) {
            article.setUrlSeq(PinyinHelper.convertToPinyinString(article.getUrlSeq(), "", PinyinFormat.WITHOUT_TONE));
            int cnt = articleMapper.selectCount(Wrappers.<Article>query().eq("url_seq", article.getUrlSeq()).ne("id", article.getId()));
            if (cnt > 0) {
                throw new AppRunningException("已存在 url：" + article.getUrlSeq());
            }
        }
        handleHideArticle(article);
        setArticleSummaryAndTxt(article);
        decorateArticle(article, false);
        int affect = articleMapper.updateById(article);
        if (affect == 1) {
            String articleId = article.getId();
            jdbcTemplate.update("delete from refer_article_cate where article_id = ?", articleId);
            cateIds.forEach(cateId -> jdbcTemplate.update("insert into refer_article_cate values (?,?)", articleId, cateId));
            if (!CollectionUtils.isEmpty(tagNames)) {
                jdbcTemplate.update("delete from refer_article_tag where article_id = ?", articleId);
                saveArticleTags(articleId, tagNames);
            }
            CacheUtils.removeDefaultCache("articleWords");
            NbUtils.deleteUploadTempOnArticleUpdate(articleId);
            updateArticleHide(article);
        } else {
            throw new ArticleUpdateException("更新文章出错！");
        }

        return affect;
    }


    @Override
    public IPage<Article> findIndexArticlePage(Page<Article> page, String title, String cateIds) {
        if (StringUtils.isEmpty(cateIds)) {
            return articleMapper.selectPage(page, Wrappers.<Article>query().ne("draft", 1).like(StrUtil.isNotEmpty(title), "title", title));
        } else {
            String[] cateIdArr = cateIds.split("_");
            List<String> cateIdList = Arrays.stream(cateIdArr).filter(NumberUtil::isInteger).collect(Collectors.toList());
            if (cateIdList.size() == 0) {
                return articleMapper.selectPage(page, Wrappers.<Article>query().ne("draft", 1).like(StrUtil.isNotEmpty(title), "title", title));
            }
            return articleMapper.findIndexArticlePage(page, title, cateIdList);
        }
    }

    @Override
    public IPage<Article> findAdminArticlePage(Page<Article> page, String title) {
        Dict productDict = dictMapper.findProductDict();
        return articleMapper.findAdminArticlePage(page, title, productDict.getId());
    }

    @Override
    public long sumArticleWords() {
        int cnt = articleMapper.selectCount(Wrappers.emptyWrapper());
        return cnt == 0 ? cnt : articleMapper.sumArticleWords();
    }

    @Override
    public List<Article> findRandomArticles(int limit) {
        return articleMapper.findRandomArticles(limit);
    }

    /**
     * 计算商品总数
     *
     * @return
     */
    @Override
    public int countProduct() {
        Dict dict = NbUtils.getBean(DictService.class).findProductDict();
        if (dict == null) {
            return 0;
        } else {
            String sql1 = "select * from refer_article_cate where cate_id = ?";
            List<Dict> ds = jdbcTemplate.query(sql1, BeanPropertyRowMapper.newInstance(Dict.class), dict.getId());
            if (ds == null || ds.size() == 0) {
                return 0;
            } else {
                String sql = "select count(1) from refer_article_cate where cate_id = ? group by cate_id";
                Integer c = jdbcTemplate.queryForObject(sql, Integer.class, dict.getId());
                return Integer.parseInt(String.valueOf(c));
            }
        }
    }

    @Override
    public boolean updateTopById(String articleId, boolean top) {
        if (top) {
            int maxTop = articleMapper.selectMaxTop();
            return update(Wrappers.<Article>update().set("top", maxTop + 1).eq("id", articleId));
        } else {
            int currentTop = articleMapper.selectById(articleId).getTop();
            articleMapper.updateTopsByTop(currentTop);
            return update(Wrappers.<Article>update().set("top", 0).eq("id", articleId));
        }
    }

    @Override
    public void updateViewsById(String articleId) {
        articleMapper.updateViewsById(articleId);
    }

    @Override
    public int updateApproveCntById(String articleId) {
        return articleMapper.updateApproveCntById(articleId);
    }

    private void updateArticleHide(Article article) {
        JdbcTemplate jdbcTemplate = NbUtils.getBean(JdbcTemplate.class);
        String sql = "delete from nb_hide where locate(id,?)=0 and article_id = ?";
        jdbcTemplate.update(sql, article.getContent(), article.getId());
    }
}
