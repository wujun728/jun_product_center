package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wuwen
 */
@MybatisMapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 处理改动 所有 top 对应值
     *
     * @param currentTop
     */
    void updateTopsByTop(int currentTop);

    /**
     * 查找最大的top
     *
     * @return
     */
    int selectMaxTop();

    /**
     * 统计文章总字数
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
     * 更新浏览量
     *
     * @param articleId
     */
    void updateViewsById(String articleId);

    /**
     * 更新文章点赞数
     *
     * @param articleId
     * @return
     */
    int updateApproveCntById(String articleId);

    /**
     * 带有分类查找首页文章
     *
     * @param page
     * @param title
     * @param cateIds
     * @return
     */
    IPage<Article> findIndexArticlePage(IPage<Article> page, @Param("title") String title, @Param("cateIds") List<String> cateIds);

    /**
     * 管理文章列表数据
     *
     * @param page
     * @param title
     * @param productCateId
     * @return
     */
    IPage<Article> findAdminArticlePage(Page<Article> page, @Param("title") String title, @Param("productCateId") long productCateId);
}
