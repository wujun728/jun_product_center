package me.wuwenbin.data.jdbc.posterity.sqlserver;

import me.wuwenbin.data.jdbc.posterity.PosterityDao;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * AncestorDao的SQLServer2000实现
 * created by Wuwenbin on 2017/11/29 at 23:25
 *
 * @author Wuwenbin
 */
public class SqlServer2005Template extends PosterityDao {

    public SqlServer2005Template(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfSs2005(final String sql, Page page, long count) {
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            String queryLastSql = ") temp_results) final_results WHERE row_number > " + page.getFirst() + page.getPageSize();
            int groupBy = sql.toUpperCase().indexOf("GROUP BY");
            int orderBy = sql.toUpperCase().indexOf("ORDER BY");
            if (orderBy > groupBy) {
                groupBy = sql.length();
            }
            String temp1 = "";
            if (orderBy > 0) {
                temp1 = sql.substring(orderBy, groupBy);
            }
            String queryFirstSql = "SELECT TOP " + page.getFirst() + " * FROM (SELECT ROW_NUMBER() OVER (" + temp1 + ") row_number,temp_results.* FROM(";
            return queryFirstSql.concat(sql.concat(queryLastSql));
        } else {
            return sql;
        }
    }

    @Override
    public Page findPageListMapByArray(String sql, Page page, Object... arrayParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByArray(getCountSql(sql), Long.class, arrayParameters);
            page.setTotalCount((int) count);
        }
        List<Map<String, Object>> list = findListMapByArray(getSqlOfSs2005(sql, page, count), arrayParameters);
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
        List<Map<String, Object>> list = findListMapByMap(getSqlOfSs2005(sql, page, count), mapParameter);
        page.setRawResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByArray(String sql, Class<T> clazz, Page<T> page, Object... arrayParameters) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByArray(getCountSql(sql), Long.class, arrayParameters);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByArray(getSqlOfSs2005(sql, page, count), clazz, arrayParameters);
        page.setTResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByMap(String sql, Class<T> clazz, Page<T> page, Map<String, Object> mapParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByMap(getCountSql(sql), Long.class, mapParameter);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByMap(getSqlOfSs2005(sql, page, count), clazz, mapParameter);
        page.setTResult(list);
        return page;
    }

    @Override
    public <T> Page<T> findPageListBeanByBean(String sql, Class<T> clazz, Page<T> page, Object beanParameter) {
        Assert.notNull(page, "分页信息不能为空");
        Assert.hasText(sql, "sql语句不正确!");
        long count = 0;
        if (page.isAutoCount()) {
            count = queryNumberByBean(getCountSql(sql), Long.class, beanParameter);
            page.setTotalCount((int) count);
        }
        List<T> list = findListBeanByBean(getSqlOfSs2005(sql, page, count), clazz, beanParameter);
        page.setTResult(list);
        return page;
    }
}
