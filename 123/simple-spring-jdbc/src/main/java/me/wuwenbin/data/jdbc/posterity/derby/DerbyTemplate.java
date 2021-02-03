package me.wuwenbin.data.jdbc.posterity.derby;

import me.wuwenbin.data.jdbc.posterity.PosterityDao;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * AncestorDao的derby实现
 * created by Wuwenbin on 2017/11/29 at 21:45
 *
 * @author wuwenbin
 */
public class DerbyTemplate extends PosterityDao {

    public DerbyTemplate(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfDerby(final String sql, Page page) {
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            String queryLastSql = ") temp_results) final_results WHERE row_number > " + page.getFirst() + " AND row_number <= " + page.getFirst() + page.getPageSize();
            int groupBy = sql.toUpperCase().indexOf("GROUP BY");
            int orderBy = sql.toUpperCase().indexOf("ORDER BY");
            if (orderBy > groupBy) {
                groupBy = sql.length();
            }
            String temp1 = "";
            if (orderBy > 0) {
                temp1 = sql.substring(orderBy, groupBy);
            }
            String queryFirstSql = "SELECT * FROM (SELECT ROW_NUMBER() OVER (" + temp1 + ") row_number,temp_results.* FROM(";
            return queryFirstSql.concat(sql.concat(queryLastSql));
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
        List<Map<String, Object>> list = findListMapByArray(getSqlOfDerby(sql, page), arrayParameters);
        page.setRawResult(list);
        return page;
    }

    @Override
    public Page findPageListMapByMap(String sql, Page page, Map<String, Object> mapParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameter);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByMap(getSqlOfDerby(sql, page), mapParameter);
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
        List<T> list = findListBeanByArray(getSqlOfDerby(sql, page), clazz, arrayParameters);
        page.setRawResult(list);
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
        List<T> list = findListBeanByMap(getSqlOfDerby(sql, page), clazz, mapParameter);
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
        List<T> list = findListBeanByBean(getSqlOfDerby(sql, page), clazz, beanParameter);
        page.setTResult(list);
        return page;
    }
}
