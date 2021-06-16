package me.wuwenbin.data.jdbc.posterity.hsql;

import me.wuwenbin.data.jdbc.posterity.PosterityDao;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * AncestorDao的hsql实现
 * created by Wuwenbin on 2017/11/29 at 22:53
 *
 * @author Wuwenbin
 */
public class HsqlTemplate extends PosterityDao {

    public HsqlTemplate(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfHsql(final String sql, Page page) {
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            return "SELECT LIMIT " + page.getFirst() + " " + page.getPageSize() + " " + sql.substring(7, sql.length());
        } else {
            return sql;
        }
    }

    @Override
    public Page findPageListMapByArray(String sql, Page page, Object... arrayParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByArray(getCountSql(sql), Long.class, arrayParameters);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByArray(getSqlOfHsql(sql, page), arrayParameters);
        page.setRawResult(list);
        return page;
    }

    @Override
    public Page findPageListMapByMap(String sql, Page page, Map<String, Object> mapParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameter);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByMap(getSqlOfHsql(sql, page), mapParameter);
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
        List<T> list = findListBeanByArray(getSqlOfHsql(sql, page), clazz, arrayParameters);
        page.setTResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByMap(String sql, Class<T> clazz, Page<T> page, Map<String, Object> mapParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameter);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByMap(getSqlOfHsql(sql, page), clazz, mapParameter);
        page.setResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByBean(String sql, Class<T> clazz, Page<T> page, Object beanParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByBean(getCountSql(sql), Long.class, beanParameter);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByBean(getSqlOfHsql(sql, page), clazz, beanParameter);
        page.setResult(list);
        return page;
    }
}
