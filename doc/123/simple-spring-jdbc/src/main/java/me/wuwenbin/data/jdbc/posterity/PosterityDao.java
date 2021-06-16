package me.wuwenbin.data.jdbc.posterity;

import me.wuwenbin.data.jdbc.ancestor.AncestorDao;
import me.wuwenbin.data.jdbc.factory.business.DataSourceX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.util.Assert;
import org.springframework.util.NumberUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * the implement of {@link AncestorDao}
 * only for mysql database operations
 * <p>
 * Created by wuwenbin on 2017/1/1.
 *
 * @author wuwenbin
 * @see AncestorDao
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public abstract class PosterityDao implements AncestorDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcCall jdbcCall;
    private SimpleJdbcInsert jdbcInsert;

    public PosterityDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource);
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource);
    }

    @Override
    public void setDataSource(DataSourceX dataSourceX) {
        DataSource dataSource = dataSourceX.getDataSource();
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcInsert = new SimpleJdbcInsert(dataSource);
        this.jdbcCall = new SimpleJdbcCall(dataSource);
    }

    private RowMapper generateRowMapper(Class clazz) {
        return BeanPropertyRowMapper.newInstance(clazz);
    }

    private MapSqlParameterSource generateArraySqlParamSource(String key, Object value) {
        return new MapSqlParameterSource(key, value);
    }

    private MapSqlParameterSource generateMapSqlParamSource(Map<String, ?> m) {
        return new MapSqlParameterSource(m);
    }

    private BeanPropertySqlParameterSource generateBeanSqlParamSource(Object o) {
        return new BeanPropertySqlParameterSource(o);
    }

    private String getTableNameFromInsertSql(String sql) {
        sql = sql.toLowerCase();
        int firstLeft = sql.indexOf("(");
        int into = sql.indexOf("into");
        int values = sql.indexOf("values");
        if (firstLeft > values) {
            return sql.substring(into + 5, values);
        }
        if (firstLeft < values) {
            return sql.substring(into + 5, firstLeft);
        }
        throw new RuntimeException("获取表名失败！");
    }


    protected static String getCountSql(String nativeSQL) {
        final String countSQL = "COUNT(0)";
        Assert.hasText(nativeSQL, "sql is not correct!");
        String sql = nativeSQL.toUpperCase();
        if (sql.contains("DISTINCT(") || sql.contains(" GROUP BY ")) {
            return "SELECT " + countSQL + " FROM (" + nativeSQL + ") TEMP_COUNT_TABLE";
        }
        String[] froms = sql.split(" FROM ");
        String tempSql = "";
        for (int i = 0; i < froms.length; i++) {
            if (i != froms.length - 1) {
                tempSql = tempSql.concat(froms[i] + " FROM ");
            } else {
                tempSql = tempSql.concat(froms[i]);
            }
            int left = tempSql.split("\\(").length;
            int right = tempSql.split("\\)").length;
            if (left == right) {
                break;
            }
        }
        tempSql = " FROM " + nativeSQL.substring(tempSql.length(), sql.length());
        int orderBy = tempSql.toUpperCase().indexOf(" ORDER BY ");
        if (orderBy >= 0) {
            tempSql = tempSql.substring(0, orderBy);
        }
        return "SELECT " + countSQL + " ".concat(tempSql);
    }

    @Override
    public DataSource getCurrentDataSource() {
        return this.dataSource;
    }

    @Override
    public JdbcTemplate getJdbcTemplateObj() {
        return jdbcTemplate;
    }

    @Override
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplateObj() {
        return this.namedParameterJdbcTemplate;
    }

    @Override
    @Deprecated
    public SimpleJdbcInsert getSimpleJdbcInsertObj() {
        return this.jdbcInsert;
    }

    @Override
    public SimpleJdbcCall getSimpleJdbcCall() {
        return this.jdbcCall;
    }

    @Override
    public Connection getConnection() throws Exception {
        return this.dataSource.getConnection();
    }

    @Override
    public void callProcedure(String procedureName) {
        jdbcCall = jdbcCall.withProcedureName(procedureName);
        jdbcCall.execute();
    }

    @Override
    public void callProcedure(String procedureName, Map<String, Object> inParameters) {
        jdbcCall = jdbcCall.withProcedureName(procedureName);
        logger.info("-- SQL参数：[{}]", inParameters);
        jdbcCall.execute(inParameters);
    }

    @Override
    public Map<String, Object> callProcedureQueryOut(String procedureName, Map<String, Object> inParameters) {
        jdbcCall = jdbcCall.withProcedureName(procedureName);
        logger.info("-- SQL参数：[{}]", inParameters);
        return jdbcCall.execute(inParameters);
    }

    @Override
    public List callProcedureQueryListBeans(String procedureName, Map<String, Object> inParameters, Class<?> outBeansType) {
        jdbcCall = jdbcCall.withProcedureName(procedureName);
        if (outBeansType != null) {
            jdbcCall = jdbcCall.returningResultSet("list_beans", generateRowMapper(outBeansType));
        }
        List list = (List) jdbcCall.execute(inParameters).get("list_beans");
        logger.info("-- SQL参数：[{}]", inParameters);
        logger.info("-- 响应条目：[{}]", list.size());
        return list;
    }

    @Override
    @Deprecated
    public long insertBeanGetGeneratedKey(String tableName, String keyName, Object beanParameter) {
        Assert.notNull(tableName, "表名不能为空");
        Assert.notNull(keyName, "自增字段名称不能为空");
        Assert.notNull(beanParameter, "对象bean不能为空");
        jdbcInsert = jdbcInsert.withTableName(tableName).usingGeneratedKeyColumns(keyName);
        Number number = jdbcInsert.executeAndReturnKey(generateBeanSqlParamSource(beanParameter));
        logger.info("生成 Key 为：[{}]：", number);
        return number.longValue();
    }

    @Override
    @Deprecated
    public long insertMapGetGeneratedKey(String tableName, String keyName, Map<String, Object> mapParameter) {
        Assert.notNull(tableName, "表名不能为空!");
        Assert.notNull(keyName, "自增字段名称不能为空!");
        Assert.notNull(mapParameter, "对象map不能为空!");
        jdbcInsert = jdbcInsert.withTableName(tableName).usingGeneratedKeyColumns(keyName);
        Number number = jdbcInsert.executeAndReturnKey(mapParameter);
        logger.info("生成 Key 为：[{}]：", number);
        return number.longValue();
    }

    @Override
    public int insertMapAutoGenKeyReturnAffect(String sql, Map<String, Object> mapParameter) {
        Assert.hasText(sql, "sql语句不正确！");
        Assert.notNull(mapParameter, "对象mapParameter不能为空");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", mapParameter);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sql, generateMapSqlParamSource(mapParameter), keyHolder);
    }

    @Override
    public int insertBeanAutoGenKeyReturnAffect(String sql, Object beanParameter) {
        Assert.hasText(sql, "sql语句不正确！");
        Assert.notNull(beanParameter, "对象beanParameter不能为空");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", beanParameter.toString());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        return namedParameterJdbcTemplate.update(sql, generateBeanSqlParamSource(beanParameter), keyHolder);
    }

    @Override
    public long insertMapAutoGenKeyReturnKey(String sql, Map<String, Object> mapParameter) {
        Assert.hasText(sql, "sql语句不正确！");
        Assert.notNull(mapParameter, "对象mapParameter不能为空");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", mapParameter);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, generateMapSqlParamSource(mapParameter), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public long insertBeanAutoGenKeyReturnKey(String sql, Object beanParameter) {
        Assert.hasText(sql, "sql语句不正确！");
        Assert.notNull(beanParameter, "对象beanParameter不能为空");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", beanParameter);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, generateBeanSqlParamSource(beanParameter), keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Map<String, Object> insertMapAutoGenKeyReturnMap(String insertSQL, Map<String, Object> mapParameter, String pkName) {
        String tableName = getTableNameFromInsertSql(insertSQL);
        long key = insertMapAutoGenKeyReturnKey(insertSQL, mapParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findMapByArray(sql, key);
    }

    @Override
    @Deprecated
    public Map<String, Object> insertMapAutoGenKeyReturnMap(String insertSQL, Map<String, Object> mapParameter, String tableName, String pkName) {
        long key = insertMapAutoGenKeyReturnKey(insertSQL, mapParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findMapByArray(sql, key);
    }

    @Override
    public <T> T insertMapAutoGenKeyReturnBean(String insertSQL, Map<String, Object> mapParameter, Class<T> clazz, String pkName) {
        String tableName = getTableNameFromInsertSql(insertSQL);
        long key = insertMapAutoGenKeyReturnKey(insertSQL, mapParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findBeanByArray(sql, clazz, key);
    }

    @Override
    @Deprecated
    public <T> T insertMapAutoGenKeyReturnBean(String insertSQL, Map<String, Object> mapParameter, Class<T> clazz, String tableName, String pkName) {
        long key = insertMapAutoGenKeyReturnKey(insertSQL, mapParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findBeanByArray(sql, clazz, key);
    }

    @Override
    public <T> T insertBeanAutoGenKeyReturnBean(String insertSQL, Object beaParameter, Class<T> clazz, String pkName) {
        String tableName = getTableNameFromInsertSql(insertSQL);
        long key = insertBeanAutoGenKeyReturnKey(insertSQL, beaParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findBeanByArray(sql, clazz, key);
    }

    @Override
    @Deprecated
    public <T> T insertBeanAutoGenKeyReturnBean(String insertSQL, Object beaParameter, Class<T> clazz, String tableName, String pkName) {
        long key = insertBeanAutoGenKeyReturnKey(insertSQL, beaParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findBeanByArray(sql, clazz, key);
    }

    @Override
    public Map<String, Object> insertBeanAutoGenKeyReturnMap(String insertSQL, Object beanParameter, String pkName) {
        String tableName = getTableNameFromInsertSql(insertSQL);
        long key = insertBeanAutoGenKeyReturnKey(insertSQL, beanParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findMapByArray(sql, key);
    }

    @Override
    @Deprecated
    public Map<String, Object> insertBeanAutoGenKeyReturnMap(String insertSQL, Object beanParameter, String tableName, String pkName) {
        long key = insertBeanAutoGenKeyReturnKey(insertSQL, beanParameter);
        String sql = "SELECT * FROM ".concat(tableName).concat(" WHERE ").concat(pkName).concat(" = ?");
        return findMapByArray(sql, key);
    }

    @Override
    public int executeArray(String sql, Object... arrayParameters) {
        Assert.hasText(sql, "sql语句不正确!");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", arrayParameters);
        int affectCount;
        if (arrayParameters != null && arrayParameters.length > 0) {
            affectCount = jdbcTemplate.update(sql, arrayParameters);
        } else {
            affectCount = jdbcTemplate.update(sql);
        }
        logger.info("-- 响应条目：[{}]", affectCount);
        return affectCount;
    }

    @Override
    public int executeMap(String sql, Map<String, Object> mapParameter) {
        Assert.hasText(sql, "sql语句不正确!");
        int affectCount;
        if (mapParameter != null && mapParameter.size() > 0) {
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", mapParameter);
            affectCount = namedParameterJdbcTemplate.update(sql, mapParameter);
            logger.info("-- 响应条目：[{}]", affectCount);
        } else {
            affectCount = executeArray(sql);
        }
        return affectCount;
    }

    @Override
    public int executeBean(String sql, Object beanParameter) {
        Assert.hasText(sql, "sql语句不正确!");
        int affectCount;
        if (beanParameter != null) {
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", beanParameter.toString());
            affectCount = namedParameterJdbcTemplate.update(sql, generateBeanSqlParamSource(beanParameter));
            logger.info("-- 响应条目：[{}]", affectCount);
        } else {
            affectCount = executeArray(sql);
        }
        return affectCount;
    }

    @SafeVarargs
    @Override
    public final int[] executeBatchByArrayMaps(String sql, Map<String, Object>... mapParameters) {
        Assert.hasText(sql, "sql语句不正确!");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", Arrays.toString(mapParameters));
        if ((mapParameters != null) && (mapParameters.length > 0)) {
            SqlParameterSource[] mapSqlParamSource = SqlParameterSourceUtils.createBatch(mapParameters);
            int[] affects = namedParameterJdbcTemplate.batchUpdate(sql, mapSqlParamSource);
            logger.info("-- 响应条目：[{}]", Arrays.toString(affects));
            return affects;
        }
        return null;
    }

    @Override
    public int[] executeBatchByArrayBeans(String sql, Object... beanParameters) {
        Assert.hasText(sql, "sql语句不正确!");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", Arrays.toString(beanParameters));
        if (beanParameters != null && beanParameters.length > 0) {
            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(beanParameters);
            int[] affects = namedParameterJdbcTemplate.batchUpdate(sql, batch);
            logger.info("-- 响应条目：[{}]", Arrays.toString(affects));
            return affects;
        }
        return null;
    }

    @Override
    public int[] executeBatchByCollectionMaps(String sql, Collection<Map<String, Object>> mapParameters) {
        Assert.hasText(sql, "sql语句不正确!");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", mapParameters);
        if (mapParameters != null && !mapParameters.isEmpty()) {
            Map[] mps = mapParameters.toArray(new Map[mapParameters.size()]);
            int[] affects = namedParameterJdbcTemplate.batchUpdate(sql, mps);
            logger.info("-- 响应条目：[{}]", Arrays.toString(affects));
            return affects;
        }
        return null;
    }

    @Override
    public int[] executeBatchByCollectionBeans(String sql, Collection<?> beanParameters) {
        Assert.hasText(sql, "sql语句不正确!");
        logger.info("SQL:" + sql);
        logger.info("-- SQL参数：[{}]", beanParameters);
        if (beanParameters != null && !beanParameters.isEmpty()) {
            SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(beanParameters.toArray());
            int[] affects = namedParameterJdbcTemplate.batchUpdate(sql, batch);
            logger.info("-- 响应条目：[{}]", Arrays.toString(affects));
            return affects;
        }
        return null;
    }

    @Override
    public Number findNumberByArray(String sql, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            if (arrayParameters != null && arrayParameters.length > 0) {
                return jdbcTemplate.queryForObject(sql, Number.class, arrayParameters);
            } else {
                return jdbcTemplate.queryForObject(sql, Number.class);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return 0;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return 0;
        }
    }

    @Override
    public <T extends Number> T queryNumberByArray(String sql, Class<T> numberClass, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            if (arrayParameters != null && arrayParameters.length > 0) {
                Number n = jdbcTemplate.queryForObject(sql, numberClass, arrayParameters);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                }
                return (T) n;
            } else {
                Number n = jdbcTemplate.queryForObject(sql, numberClass);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                } else {
                    return (T) n;
                }
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return NumberUtils.parseNumber("0", numberClass);
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return NumberUtils.parseNumber("0", numberClass);
        }
    }

    @Override
    public Number findNumberByMap(String sql, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (mapParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                return namedParameterJdbcTemplate.queryForObject(sql, mapParameter, Number.class);
            } else {
                return findNumberByArray(sql);
            }
        } catch (EmptyResultDataAccessException ere) {
            return 0;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return 0;
        }
    }

    @Override
    public <T extends Number> T queryNumberByMap(String sql, Class<T> numberClass, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                Number n = namedParameterJdbcTemplate.queryForObject(sql, mapParameter, numberClass);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                } else {
                    return (T) n;
                }
            } else {
                Number n = queryNumberByArray(sql, numberClass);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                } else {
                    return (T) n;
                }
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return NumberUtils.parseNumber("0", numberClass);
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return NumberUtils.parseNumber("0", numberClass);
        }
    }

    @Override
    public Number findNumberByBean(String sql, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                return namedParameterJdbcTemplate.queryForObject(sql, generateBeanSqlParamSource(beanParameter), Number.class);
            } else {
                return findNumberByArray(sql);
            }
        } catch (EmptyResultDataAccessException ere) {
            return 0;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return 0;
        }
    }

    @Override
    public <T extends Number> T queryNumberByBean(String sql, Class<T> numberClass, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                Number n = namedParameterJdbcTemplate.queryForObject(sql, generateBeanSqlParamSource(beanParameter), numberClass);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                } else {
                    return (T) n;
                }
            } else {
                Number n = queryNumberByArray(sql, numberClass);
                if (n == null) {
                    return NumberUtils.parseNumber("0", numberClass);
                } else {
                    return (T) n;
                }
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return NumberUtils.parseNumber("0", numberClass);
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return NumberUtils.parseNumber("0", numberClass);
        }
    }

    @Override
    public <T> T findPrimitiveByArray(String sql, Class<T> objClass, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            if (arrayParameters != null && arrayParameters.length > 0) {
                return jdbcTemplate.queryForObject(sql, objClass, arrayParameters);
            } else {
                return jdbcTemplate.queryForObject(sql, objClass);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------ " + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public <T> T findPrimitiveByMap(String sql, Class<T> objClass, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                return namedParameterJdbcTemplate.queryForObject(sql, mapParameter, objClass);
            } else {
                return findPrimitiveByArray(sql, objClass);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------ " + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public <T> T findPrimitiveByBean(String sql, Class<T> objClass, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                return namedParameterJdbcTemplate.queryForObject(sql, generateBeanSqlParamSource(beanParameter), objClass);
            } else {
                return findPrimitiveByArray(sql, objClass);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------ " + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public Map<String, Object> findMapByArray(String sql, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            if (arrayParameters != null && arrayParameters.length > 0) {
                return jdbcTemplate.queryForMap(sql, arrayParameters);
            } else {
                return jdbcTemplate.queryForMap(sql);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------ " + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public Map<String, Object> findMapByMap(String sql, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                return namedParameterJdbcTemplate.queryForMap(sql, mapParameter);
            } else {
                return findMapByArray(sql);
            }
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public Map<String, Object> findMapByBean(String sql, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                return namedParameterJdbcTemplate.queryForMap(sql, generateBeanSqlParamSource(beanParameter));
            } else {
                return findMapByArray(sql);
            }
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public <T> T findBeanByArray(String sql, Class<T> clazz, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "类集合中对象类型不能为空!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            if (arrayParameters != null && arrayParameters.length > 0) {
                return (T) jdbcTemplate.queryForObject(sql, generateRowMapper(clazz), arrayParameters);
            } else {
                return (T) jdbcTemplate.queryForObject(sql, generateRowMapper(clazz));
            }
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public <T> T findBeanByMap(String sql, Class<T> clazz, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                return (T) namedParameterJdbcTemplate.queryForObject(sql, mapParameter, generateRowMapper(clazz));
            } else {
                return findBeanByArray(sql, clazz);
            }
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public <T> T findBeanByBean(String sql, Class<T> clazz, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确！");
            Assert.notNull(clazz, "集合中对象类型不能为空！");
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                return (T) namedParameterJdbcTemplate.queryForObject(sql, generateBeanSqlParamSource(beanParameter), generateRowMapper(clazz));
            } else {
                return findBeanByArray(sql, clazz);
            }
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result{}", e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListPrimitiveByArray(String sql, Class<T> objClass, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            List<T> list;
            if (arrayParameters != null && arrayParameters.length > 0) {
                list = jdbcTemplate.queryForList(sql, objClass, arrayParameters);
            } else {
                list = jdbcTemplate.queryForList(sql, objClass);
            }
            logger.info("-- 响应条目：[{}]", list.size());
            return list;
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListPrimitiveByMap(String sql, Class<T> objClass, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            List<T> list;
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                list = namedParameterJdbcTemplate.queryForList(sql, mapParameter, objClass);
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListPrimitiveByArray(sql, objClass);
            }
            return list;
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListPrimitiveByBean(String sql, Class<T> objClass, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            List<T> list;
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                list = namedParameterJdbcTemplate.queryForList(sql, generateBeanSqlParamSource(beanParameter), objClass);
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListPrimitiveByArray(sql, objClass);
            }
            return list;
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findListMapByArray(String sql, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            List<Map<String, Object>> list;
            if (arrayParameters != null && arrayParameters.length > 0) {
                list = jdbcTemplate.queryForList(sql, arrayParameters);
            } else {
                list = jdbcTemplate.queryForList(sql);
            }
            logger.info("-- 响应条目：[{}]", list.size());
            return list;
        } catch (EmptyResultDataAccessException e) {
            logger.info("查询SQL无结果------" + e);
            return null;
        } catch (Exception e) {
            logger.error("查询SQL异常 no result! {}" + e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findListMapByMap(String sql, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            List<Map<String, Object>> list;
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                list = namedParameterJdbcTemplate.queryForList(sql, mapParameter);
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListMapByArray(sql);
            }
            return list;
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> findListMapByBean(String sql, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            List<Map<String, Object>> list;
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                list = namedParameterJdbcTemplate.queryForList(sql, generateBeanSqlParamSource(beanParameter));
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListMapByArray(sql);
            }
            return list;
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListBeanByArray(String sql, Class<T> clazz, Object... arrayParameters) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            logger.info("SQL:" + sql);
            logger.info("-- SQL参数：[{}]", arrayParameters);
            List<T> list;
            if (arrayParameters != null && arrayParameters.length > 0) {
                list = jdbcTemplate.query(sql, generateRowMapper(clazz), arrayParameters);
            } else {
                list = jdbcTemplate.query(sql, generateRowMapper(clazz));
            }
            logger.info("-- 响应条目：[{}]", list.size());
            return list;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListBeanByMap(String sql, Class<T> clazz, Map<String, Object> mapParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            List<T> list;
            if (mapParameter != null && mapParameter.size() > 0) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", mapParameter);
                list = namedParameterJdbcTemplate.query(sql, mapParameter, generateRowMapper(clazz));
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListBeanByArray(sql, clazz);
            }
            return list;
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

    @Override
    public <T> List<T> findListBeanByBean(String sql, Class<T> clazz, Object beanParameter) {
        try {
            Assert.hasText(sql, "sql语句不正确!");
            Assert.notNull(clazz, "集合中对象类型不能为空!");
            List<T> list;
            if (beanParameter != null) {
                logger.info("SQL:" + sql);
                logger.info("-- SQL参数：[{}]", beanParameter);
                list = namedParameterJdbcTemplate.query(sql, generateBeanSqlParamSource(beanParameter), generateRowMapper(clazz));
                logger.info("-- 响应条目：[{}]", list.size());
            } else {
                list = findListBeanByArray(sql, clazz);
            }
            return list;
        } catch (EmptyResultDataAccessException ere) {
            return null;
        } catch (Exception e) {
            logger.error("not result!{}", e);
            return null;
        }
    }

}
