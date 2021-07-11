package me.wuwenbin.noteblogv5.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.wuwenbin.noteblogv5.constant.DictGroup;
import me.wuwenbin.noteblogv5.model.bo.SearchArticleBo;
import me.wuwenbin.noteblogv5.model.bo.SearchBo;
import me.wuwenbin.noteblogv5.service.SearchService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SearchServiceImpl implements SearchService {

    private final JdbcTemplate jdbcTemplate;

    public SearchServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<SearchBo> searchWithWords(String words, int pageNo) {
        String baseSql =
                "SELECT a.id,'article' AS type, a.title, a.summary AS  res_content, a.post " +
                        "FROM nb_article a WHERE a.title LIKE  CONCAT('%',?,'%') OR LOCATE(?,a.text_content)>0 " +
                        "UNION " +
                        "SELECT n.id, 'note' AS type, n.title, n.clear_content AS content, n.post " +
                        "FROM nb_note n WHERE n.title LIKE  CONCAT('%',?,'%') OR LOCATE(?,n.clear_content)>0 " +
                        "ORDER BY post DESC ";
        String countSql = StrUtil.format("select count(1) from ({}) a", baseSql);
        String querySql = baseSql.concat(" LIMIT " + getFirst(pageNo) + "," + 10);
        Integer cnt = jdbcTemplate.queryForObject(countSql, Integer.class, words, words, words, words);
        cnt = cnt != null ? cnt : 0;
        List<SearchBo> searchBoList = jdbcTemplate.query(querySql, BeanPropertyRowMapper.newInstance(SearchBo.class), words, words, words, words);
        Page<SearchBo> page = new Page<>(pageNo, 10, cnt);
        page.setRecords(searchBoList);
        return page;
    }

    @Override
    public Page<SearchArticleBo> searchWithDict(String dictGroup, String dictName, int pageNo) {
        String baseSql =
                "SELECT a.*,'article' AS type,a.text_content AS res_content FROM nb_article a WHERE a.id IN " +
                        "(SELECT {} FROM {} WHERE {} IN " +
                        "(SELECT d.id FROM nb_dict d WHERE d.name LIKE CONCAT('%',?,'%') AND `group` = '{}') " +
                        ")";
        if (dictGroup.equalsIgnoreCase(DictGroup.GROUP_ARTICLE_CATE)) {
            baseSql = StrUtil.format(baseSql,
                    "rac.article_id", "refer_article_cate rac", "rac.cate_id", DictGroup.GROUP_ARTICLE_CATE);
        } else {
            baseSql = StrUtil.format(baseSql,
                    "rat.article_id", "refer_article_tag rat", "rat.tag_id", DictGroup.GROUP_TAG);
        }
        String countSql = StrUtil.format("select count(1) from ({}) temp", baseSql);
        String querySql = baseSql.concat(" LIMIT " + getFirst(pageNo) + "," + 10);
        Integer cnt = jdbcTemplate.queryForObject(countSql, Integer.class, dictName);
        cnt = cnt != null ? cnt : 0;
        List<SearchArticleBo> dictList = jdbcTemplate.query(querySql, BeanPropertyRowMapper.newInstance(SearchArticleBo.class), dictName);
        Page<SearchArticleBo> page = new Page<>(pageNo, 10, cnt);
        page.setRecords(dictList);
        return page;
    }

    /**
     * @return 通过挡圈的页码和页面数据量大小计算当前页面的数据的第一条在数据库中的顺序
     */
    private int getFirst(int pageNo) {
        return pageNo < 1 ? -1 : (pageNo - 1) * 10;
    }
}
