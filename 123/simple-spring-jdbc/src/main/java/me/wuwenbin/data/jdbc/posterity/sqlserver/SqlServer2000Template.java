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
public class SqlServer2000Template extends PosterityDao {

    public SqlServer2000Template(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfSs2000(final String sql, Page page, long count) {
        String finalSql = sql;
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            long i = page.getPageSize();
            long j = page.getFirst() + page.getPageSize();
            if (j >= count) {
                i = i - (j - count);
            }
            String querySqlFirst = "SELECT * FROM ( SELECT TOP " + i + " * FROM ( SELECT TOP " + j + " ";
            String querySqlLastTemp = " ORDER BY id ASC ) tempt1 ORDER BY id DESC ) as tempt2 ORDER BY id ASC";
            String querySqlLast;
            if (!finalSql.toUpperCase().contains("ORDER BY")) {
                querySqlLast = querySqlLastTemp;
            } else {
                querySqlLast = " ORDER BY ";
                int orderBy = finalSql.toUpperCase().indexOf("ORDER BY");
                int groupBy = finalSql.toUpperCase().indexOf("GROUP BY");
                if (orderBy > groupBy) {
                    groupBy = finalSql.length();
                }
                String temp1 = finalSql.substring(finalSql.toUpperCase().indexOf("ORDER BY"), groupBy);
                String temp2 = finalSql.substring(finalSql.toUpperCase().indexOf("ORDER BY") + 8, groupBy);
                finalSql = finalSql.replaceAll(temp1, " ");
                String[] orderBys = temp2.split(",");
                String orderByASC = "";
                String orderByASC2 = "";
                String orderByDESC = "";
                for (int m = 0; m < orderBys.length; m++) {
                    String tempOrderBy = orderBys[m];
                    String dot = ",";
                    if (m == orderBys.length - 1) {
                        dot = "";
                    }
                    String tempOrderByASC = tempOrderBy;
                    String tempOrderByDESC = tempOrderBy;
                    if (tempOrderBy.indexOf(".") > 0) {
                        tempOrderByASC = "tempt2" + tempOrderByASC.substring(tempOrderBy.indexOf("."), tempOrderBy.length());
                        tempOrderByDESC = "tempt1" + tempOrderByDESC.substring(tempOrderBy.indexOf("."), tempOrderBy.length());
                    }
                    if (tempOrderBy.toUpperCase().contains("DESC")) {
                        orderByASC2 = orderByASC2.concat(tempOrderBy + dot);
                        orderByASC = orderByASC.concat(tempOrderByASC + dot);
                        int start = tempOrderByDESC.toUpperCase().indexOf("DESC");
                        tempOrderByDESC = tempOrderByDESC.substring(0, start) + "ASC";
                        orderByDESC = orderByDESC.concat(tempOrderByDESC + dot);
                    } else if (tempOrderBy.toUpperCase().contains("ASC")) {
                        orderByASC2 = orderByASC2.concat(tempOrderBy + dot);
                        orderByASC = orderByASC.concat(tempOrderByASC + dot);
                        int start = tempOrderByDESC.toUpperCase().indexOf("ASC");
                        tempOrderByDESC = tempOrderByDESC.substring(0, start) + "DESC";
                        orderByDESC = orderByDESC.concat(tempOrderByDESC + dot);
                    } else {
                        orderByASC2 = orderByASC2.concat(tempOrderBy + dot);
                        orderByASC = orderByASC.concat(tempOrderByASC + " ASC" + dot);
                        orderByDESC = orderByDESC.concat(tempOrderByDESC + " DESC" + dot);
                    }
                }
                querySqlLast = querySqlLast.concat(orderByASC2 + " ) tempt1 ORDER BY ".concat(orderByDESC) + " ) tempt2 ORDER BY ".concat(orderByASC));
            }
            return querySqlFirst.concat(finalSql.trim().substring(6, finalSql.trim().length()).concat(querySqlLast));
        } else {
            return finalSql;
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
        List<Map<String, Object>> list = findListMapByArray(getSqlOfSs2000(sql, page, count), arrayParameters);
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
        List<Map<String, Object>> list = findListMapByMap(getSqlOfSs2000(sql, page, count), mapParameter);
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
        List<T> list = findListBeanByArray(getSqlOfSs2000(sql, page, count), clazz, arrayParameters);
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
        List<T> list = findListBeanByMap(getSqlOfSs2000(sql, page, count), clazz, mapParameter);
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
        List<T> list = findListBeanByBean(getSqlOfSs2000(sql, page, count), clazz, beanParameter);
        page.setTResult(list);
        return page;
    }
}
