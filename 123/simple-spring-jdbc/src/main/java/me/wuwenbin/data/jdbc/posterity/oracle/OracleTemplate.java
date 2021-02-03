package me.wuwenbin.data.jdbc.posterity.oracle;

import me.wuwenbin.data.jdbc.posterity.PosterityDao;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * the implement of oracle
 * <p>
 *
 * @author wuwenbin
 * @date 2017/3/27
 */
public class OracleTemplate extends PosterityDao {
    public OracleTemplate(DataSource dataSource) {
        super(dataSource);
    }

    private static String getSqlOfOracle(final String sql, Page page) {
        if (page.isFirstSetted() && page.isPageSizeSetted()) {
            String querySqlFirst = "SELECT * FROM ( SELECT tempt.*,ROWNUM rn FROM ( ";
            String querySqlLast = " ) tempt WHERE ROWNUM<=" + page.getFirst() + page.getPageSize() + " ) WHERE rn>" + page.getFirst();
            return querySqlFirst.concat(sql.concat(querySqlLast));
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
        List<Map<String, Object>> list = findListMapByArray(getSqlOfOracle(sql, page), arrayParameters);
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
        List<Map<String, Object>> list = findListMapByMap(getSqlOfOracle(sql, page), mapParameter);
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
        List<T> list = findListBeanByArray(getSqlOfOracle(sql, page), clazz, arrayParameters);
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
        List<T> list = findListBeanByMap(getSqlOfOracle(sql, page), clazz, mapParameter);
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
        List<T> list = findListBeanByBean(getSqlOfOracle(sql, page), clazz, beanParameter);
        page.setTResult(list);
        return page;
    }
}
