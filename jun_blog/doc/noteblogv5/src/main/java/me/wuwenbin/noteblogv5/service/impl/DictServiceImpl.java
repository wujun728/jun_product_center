package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.mapper.DictMapper;
import me.wuwenbin.noteblogv5.model.entity.Dict;
import me.wuwenbin.noteblogv5.service.DictService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;

import java.util.List;
import java.util.Map;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private final JdbcTemplate jdbcTemplate;
    private final DictMapper dictMapper;

    public DictServiceImpl(JdbcTemplate jdbcTemplate, DictMapper dictMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.dictMapper = dictMapper;
    }

    @Override
    public int articleCateReferCnt(Long cateId) {
        String sql = "select count(*) as cnt from refer_article_cate where cate_id = ?";
        Integer c = jdbcTemplate.queryForObject(sql, Integer.class, cateId);
        return NumberUtils.parseNumber(String.valueOf(c), Integer.class);
    }

    /**
     * 查询关联下载的分类数量有多少
     *
     * @param cateId
     * @return
     */
    @Override
    public int downloadCateReferCnt(Long cateId) {
        String sql = "select count(*) as cnt from refer_download_cate where cate_id = ?";
        Integer c = jdbcTemplate.queryForObject(sql, Integer.class, cateId);
        return NumberUtils.parseNumber(String.valueOf(c), Integer.class);
    }

    @Override
    public List<Dict> findCatesByArticleId(String articleId) {
        String sql = "select * from nb_dict where `group` = ? and id in (select cate_id from refer_article_cate where article_id = ?)";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dict.class), DictGroup.GROUP_ARTICLE_CATE, articleId);
    }

    /**
     * 根据文章查找对应的标签
     *
     * @param downloadId
     * @return
     */
    @Override
    public List<Dict> findCatesByDownloadId(String downloadId) {
        String sql = "select * from nb_dict where `group` = ? and id in (select cate_id from refer_download_cate where download_id = ?)";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dict.class), DictGroup.GROUP_DOWNLOAD_CATE, downloadId);
    }

    @Override
    public int articleTagReferCnt(Long tagId) {
        String sql = "select count(*) as cnt from refer_article_tag where tag_id = ?";
        Integer c = jdbcTemplate.queryForObject(sql, Integer.class, tagId);
        return NumberUtils.parseNumber(String.valueOf(c), Integer.class);
    }

    @Override
    public List<Dict> findTagsByArticleId(String articleId) {
        String sql = "select * from nb_dict where `group`=? and  id in (select tag_id from refer_article_tag where article_id = ?)";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dict.class), DictGroup.GROUP_TAG, articleId);
    }

    @Override
    public List<Dict> findList(String groupName) {
        return this.list(Wrappers.<Dict>query().eq("`group`", groupName));
    }

    @Override
    public List<Map<String, Object>> findTagList30() {
        return dictMapper.findTagsTab();
    }

    @Override
    public void deleteArticleRefer(String articleId) {
        String sql1 = "delete from refer_article_cate where article_id = ?";
        jdbcTemplate.update(sql1, articleId);
        String sql2 = "delete from refer_article_tag where article_id = ?";
        jdbcTemplate.update(sql2, articleId);
    }

    @Override
    public Dict findProductDict() {
        Dict product = dictMapper.findProductDict();
        if (product == null) {
            product = Dict.builder().group(DictGroup.GROUP_ARTICLE_CATE).name(Dict.PRODUCT).build();
            dictMapper.insert(product);
        }
        return product;
    }
}
