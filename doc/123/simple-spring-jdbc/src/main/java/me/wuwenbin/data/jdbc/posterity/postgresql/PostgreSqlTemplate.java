package me.wuwenbin.data.jdbc.posterity.postgresql;

import me.wuwenbin.data.jdbc.posterity.PosterityDao;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


/**
 * the implements of postgresql
 * Created by wuwenbin on 2017/4/13.
 */
public final class PostgreSqlTemplate extends PosterityDao {

    public PostgreSqlTemplate(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfPostgresql(final String sql, Page page) {
        String querySql = sql;
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            querySql = querySql.concat(" LIMIT " + page.getPageSize() + " OFFSET " + page.getFirst());
        }
        return querySql;
    }


    @Override
    public Page findPageListMapByArray(final String sql, Page page, Object... arrayParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByArray(getCountSql(sql), Long.class, arrayParameters);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByArray(getSqlOfPostgresql(sql, page), arrayParameters);
        page.setRawResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByArray(String sql, Class<T> clazz, Page<T> page, Object... arrayParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByArray(getCountSql(sql), Long.class, arrayParameters);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByArray(getSqlOfPostgresql(sql, page), clazz, arrayParameters);
        page.setResult(list);
        return page;
    }


    @Override
    public <T> Page<T> findPageListBeanByBean(String sql, Class<T> clazz, Page<T> page, Object beanParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByBean(getCountSql(sql), Long.class, beanParameters);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByBean(getSqlOfPostgresql(sql, page), clazz, beanParameters);
        page.setTResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByMap(String sql, Class<T> clazz, Page<T> page, Map<String, Object> mapParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameters);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByMap(getSqlOfPostgresql(sql, page), clazz, mapParameters);
        page.setTResult(list);
        return page;
    }

    @Override
    public Page findPageListMapByMap(String sql, Page page, Map<String, Object> mapParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameters);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByMap(getSqlOfPostgresql(sql, page), mapParameters);
        page.setRawResult(list);
        return page;
    }


}
