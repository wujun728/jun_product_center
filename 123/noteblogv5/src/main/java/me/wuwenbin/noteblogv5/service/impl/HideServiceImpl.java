package me.wuwenbin.noteblogv5.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.wuwenbin.noteblogv5.mapper.HideMapper;
import me.wuwenbin.noteblogv5.model.bo.HideBo;
import me.wuwenbin.noteblogv5.model.entity.Hide;
import me.wuwenbin.noteblogv5.service.HideService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wuwen
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HideServiceImpl extends ServiceImpl<HideMapper, Hide> implements HideService {

    private final JdbcTemplate jdbcTemplate;
    private final HideMapper hideMapper;

    public HideServiceImpl(JdbcTemplate jdbcTemplate, HideMapper hideMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.hideMapper = hideMapper;
    }

    @Override
    public void deleteArticlePurchaseRefer(String articleId) {
        String sql = "delete from refer_hide_user_purchase where article_id = ?";
        jdbcTemplate.update(sql, articleId);
    }

    @Override
    public boolean userIsBought(String articleId, long userId, String hideId) {
        String sql = "select count(1) from refer_hide_user_purchase where article_id = ? and user_id = ? and hide_id = ?";
        String c = jdbcTemplate.queryForObject(sql, String.class, articleId, userId, hideId);
        return c != null && Integer.parseInt(c) == 1;
    }

    @Override
    public int purchaseArticleHideContent(String articleId, String hideId, Long userId) {
        String sql = "insert into refer_hide_user_purchase(article_id,hide_id,user_id,purchase_time) values (?,?,?,now())";
        return jdbcTemplate.update(sql, articleId, hideId, userId);
    }

    @Override
    public IPage<HideBo> findMyPurchases(IPage<HideBo> page, Long userId) {
        return hideMapper.findMyPurchases(page, userId);
    }
}
