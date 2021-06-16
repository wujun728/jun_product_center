package me.wuwenbin.data.jdbc.ancestor;

import me.wuwenbin.data.jdbc.factory.business.DataSourceX;
import me.wuwenbin.data.jdbc.support.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Interface of some jdbc operations
 * For more detail explanation or Chinese document,
 * please check url at http://template-dao.mydoc.io/
 * <p>
 * Created by wuwenbin on 2016/12/11.
 *
 * @author Wuwenbin
 * @since 1.0.0
 */
public interface AncestorDao {

    /**
     * transfer current dataSource
     *
     * @return {@link DataSource}
     */
    DataSource getCurrentDataSource();

    /**
     * 重新设置当前数据源
     *
     * @param dataSource
     */
    void setDataSource(DataSource dataSource);

    /**
     * 重新设置当前数据源
     *
     * @param dataSource
     */
    void setDataSource(DataSourceX dataSource);

    /**
     * transfer object of current JdbcTemplate
     *
     * @return {@link org.springframework.jdbc.core.JdbcTemplate}
     */
    JdbcTemplate getJdbcTemplateObj();

    /**
     * transfer object of current namedParameterJdbcTemplate
     *
     * @return {@link org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate}
     */
    NamedParameterJdbcTemplate getNamedParameterJdbcTemplateObj();

    /**
     * transfer object of current simpleJdbcInsert
     * the property is not recommended,it could result in some problems that is not expected
     * if you want to user generatedKey,we recommend to user {@link #getJdbcTemplateObj()}
     *
     * @return {@link org.springframework.jdbc.core.simple.SimpleJdbcInsert}
     */
    @Deprecated
    SimpleJdbcInsert getSimpleJdbcInsertObj();

    /**
     * transfer object of current simpleJdbcCall
     *
     * @return {@link org.springframework.jdbc.core.simple.SimpleJdbcCall}
     */
    SimpleJdbcCall getSimpleJdbcCall();

    /**
     * transfer the connection of current datasource.
     * it can do some work of customizable jdbc operation.
     *
     * @return {@link Connection}
     * @throws Exception error of opening database
     */
    Connection getConnection() throws Exception;

    /**
     * execute stored procedure
     *
     * @param procedureName the name of procedure
     */
    void callProcedure(String procedureName);

    /**
     * execute stored procedure
     *
     * @param procedureName the name of procedure
     * @param inParameters  the param of execution(key:the name of param,value:the value of param)
     */
    void callProcedure(String procedureName, Map<String, Object> inParameters);

    /**
     * execute procedure with out-returned value
     * for detail example,please check Chinese Document.
     *
     * @param procedureName the name of procedure
     * @param inParameters  the collection of param(key:the name of param,value:the value of param)
     * @return {@link Map}  the collection of returned value(key:the out-param name,value:the out-value param)
     */
    Map<String, Object> callProcedureQueryOut(String procedureName, Map<String, Object> inParameters);

    /**
     * execute procedure with out-returned value or included SELECT collection's returned value
     *
     * @param procedureName the name of procedure
     * @param inParameters  the collection of param(key:the name of param,value:the value of param)
     * @param outBeansType  the returned beanList type
     * @return {@link List} beanList collection
     */
    List callProcedureQueryListBeans(String procedureName, Map<String, Object> inParameters, Class<?> outBeansType);

    /**
     * <tt>INSERT</tt> values with returned <tt>PK</tt> value
     * use {@link #insertBeanAutoGenKeyReturnKey(String, Object)} to instead of this
     *
     * @param tableName     the name of table
     * @param keyName       the name of column increase automatically
     * @param beanParameter the object of bean(eg:the property:<tt>userName</tt> should correspond to <tt>user_name</tt>, for we are not suggest to use upper case)
     * @return {@link Long} the column which increase automatically
     * @throws Exception the message of insert exception
     * @see #insertBeanAutoGenKeyReturnKey(String, Object)
     */
    @Deprecated
    long insertBeanGetGeneratedKey(String tableName, String keyName, Object beanParameter) throws Exception;

    /**
     * <tt>INSERT</tt> values with returned <tt>PK</tt> value
     * use {@link #insertBeanAutoGenKeyReturnKey(String, Object)} to instead
     *
     * @param tableName    the name of table
     * @param keyName      the name of column increase automatically
     * @param mapParameter the data map,key refer column,suggesting lower case
     * @return {@link Long} the column which increase automatically
     * @throws Exception the message of insert exception
     */
    @Deprecated
    long insertMapGetGeneratedKey(String tableName, String keyName, Map<String, Object> mapParameter) throws Exception;

    /**
     * 根据所给语句以及map级的sql类型参数，生成key
     *
     * @param sql          sql语句
     * @param mapParameter map类型的参数
     * @return 插入影响的条数
     * @throws Exception 插入的异常信息
     */
    int insertMapAutoGenKeyReturnAffect(String sql, Map<String, Object> mapParameter) throws Exception;

    /**
     * 插入bean返回插入的影响条数，自动生成key
     *
     * @param sql           插入sql语句
     * @param beanParameter bean类型参数
     * @return 插入影响的条数
     * @throws Exception 插入之中发生的异常
     */
    int insertBeanAutoGenKeyReturnAffect(String sql, Object beanParameter) throws Exception;

    /**
     * 插入Map返回插入的主键
     *
     * @param sql          插入的语句
     * @param mapParameter map类型的参数，对应sql中的占位符
     * @return 自动生成的主键
     * @throws Exception 插入时发生的异常
     */
    long insertMapAutoGenKeyReturnKey(String sql, Map<String, Object> mapParameter) throws Exception;

    /**
     * 插入bean返回插入的主键
     *
     * @param sql           插入的语句
     * @param beanParameter bean对象类型的参数，对应sql中的占位符
     * @return 自动生成的主键
     * @throws Exception 插入时发生的异常
     */
    long insertBeanAutoGenKeyReturnKey(String sql, Object beanParameter) throws Exception;

    /**
     * 插入一条Map记录，返回插入的记录（含主键）
     *
     * @param insertSQL    插入语句
     * @param mapParameter map参数
     * @param pkName       主键列明
     * @return 所有字段组装成的map对象
     * @throws Exception 插入时发生的异常
     */
    Map<String, Object> insertMapAutoGenKeyReturnMap(String insertSQL, Map<String, Object> mapParameter, String pkName) throws Exception;

    /**
     * 插入一条Map记录，返回插入的记录（含主键）
     *
     * @param insertSQL    插入语句
     * @param mapParameter map参数
     * @param tableName    表名
     * @param pkName       主键列明
     * @return 所有字段组装成的map对象
     * @throws Exception 插入时发生的异常
     */
    @Deprecated
    Map<String, Object> insertMapAutoGenKeyReturnMap(String insertSQL, Map<String, Object> mapParameter, String tableName, String pkName) throws Exception;

    /**
     * 插入一条Bean记录，返回Map对象，参数类型为冒号
     *
     * @param insertSQL
     * @param beanParameter
     * @param pkName
     * @return
     * @throws Exception
     */
    Map<String, Object> insertBeanAutoGenKeyReturnMap(String insertSQL, Object beanParameter, String pkName) throws Exception;

    /**
     * 插入一条Bean记录，返回Map对象，参数类型为冒号
     *
     * @param insertSQL
     * @param beanParameter
     * @param tableName
     * @param pkName
     * @return
     * @throws Exception
     */
    @Deprecated
    Map<String, Object> insertBeanAutoGenKeyReturnMap(String insertSQL, Object beanParameter, String tableName, String pkName) throws Exception;

    /**
     * 插入一条记录，参数为Map返回一个对应的实体类
     *
     * @param insertSQL
     * @param mapParameter
     * @param clazz
     * @param pkName       主键列明
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T insertMapAutoGenKeyReturnBean(String insertSQL, Map<String, Object> mapParameter, Class<T> clazz, String pkName) throws Exception;

    /**
     * 插入一条记录，参数为Map返回一个对应的实体类
     *
     * @param insertSQL
     * @param mapParameter
     * @param clazz
     * @param tableName
     * @param pkName       主键列明
     * @param <T>
     * @return
     * @throws Exception
     */
    @Deprecated
    <T> T insertMapAutoGenKeyReturnBean(String insertSQL, Map<String, Object> mapParameter, Class<T> clazz, String tableName, String pkName) throws Exception;

    /**
     * 插入一条bean记录，返回插入记录（含主键）
     *
     * @param insertSQL    插入语句
     * @param beaParameter bean类型参数
     * @param clazz        对象匹配的class类型
     * @param pkName       主键列明
     * @param <T>          对象匹配的泛型类型
     * @return 插入的对象
     * @throws Exception 插入时发生的异常
     */
    <T> T insertBeanAutoGenKeyReturnBean(String insertSQL, Object beaParameter, Class<T> clazz, String pkName) throws Exception;

    /**
     * 插入一条bean记录，返回插入记录（含主键）
     *
     * @param insertSQL    插入语句
     * @param beaParameter bean类型参数
     * @param clazz        对象匹配的class类型
     * @param tableName    表名
     * @param pkName       主键列明
     * @param <T>          对象匹配的泛型类型
     * @return 插入的对象
     * @throws Exception 插入时发生的异常
     */
    @Deprecated
    <T> T insertBeanAutoGenKeyReturnBean(String insertSQL, Object beaParameter, Class<T> clazz, String tableName, String pkName) throws Exception;

    /**
     * execute method which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     *
     * @param sql             the statement of database
     * @param arrayParameters the params in statement
     * @return {@link Integer} the affect count of statement
     * @throws Exception the exception message of execution
     */
    int executeArray(final String sql, Object... arrayParameters) throws Exception;

    /**
     * execute method which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     *
     * @param sql          the statement of database
     * @param mapParameter the params in statement
     * @return {@link Integer} the affect count of statement
     * @throws Exception the exception message of execution
     */
    int executeMap(final String sql, Map<String, Object> mapParameter) throws Exception;

    /**
     * execute method which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     *
     * @param sql           the statement of database
     * @param beanParameter the params in statement
     * @return {@link Integer} the affect count of statement
     * @throws Exception the exception message of execution
     */
    int executeBean(final String sql, Object beanParameter) throws Exception;

    /**
     * execute batch of methods which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     *
     * @param sql           statement of database
     * @param mapParameters params in map way,a map refer a statement
     * @return {@link Integer[]} the affect count of statements
     * @throws Exception the exception message of execution
     */
    int[] executeBatchByArrayMaps(final String sql, Map<String, Object>... mapParameters) throws Exception;

    /**
     * execute batch of methods which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     * this methods is similar to {@link #executeBatchByArrayMaps(String, Map[])},but params changed from maps to beans
     *
     * @param sql            statement of database
     * @param beanParameters params in bean object way,a bean object refer a statement
     * @return {@link Integer[]} the affect count of statements
     * @throws Exception the exception message of execution
     */
    int[] executeBatchByArrayBeans(final String sql, Object... beanParameters) throws Exception;

    /**
     * execute batch of methods which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     * this method is similar to {@link #executeBatchByArrayMaps(String, Map[])},but params changed from map array to map collection
     *
     * @param sql           statement of database
     * @param mapParameters params in map collection way,a map object refer to a statement
     * @return {@link Integer[]} the affect count of statement
     * @throws Exception the exception message of execution
     */
    int[] executeBatchByCollectionMaps(final String sql, Collection<Map<String, Object>> mapParameters) throws Exception;

    /**
     * execute batch of methods which is <tt>INSERT</tt> or <tt>UPDATE</tt> or <tt>DELETE</tt>
     * this method is similar to {@link #executeBatchByCollectionMaps(String, Collection)},but params changed from map collection to bean collection
     *
     * @param sql            statement of database
     * @param beanParameters params in bean object way,a bean object refer to a statement
     * @return {@link Integer[]} the affect count of statement
     * @throws Exception the exception message of execution
     */
    int[] executeBatchByCollectionBeans(final String sql, Collection<?> beanParameters) throws Exception;

    /**
     * transfer math value by sql statement
     * when developer transfer the returned math value,he/she can use {@link Number#longValue()}
     * or other method to transfer to other type.
     * Besides,developer use {@link #queryNumberByArray(String, Class, Object...)} to transfer long value directly
     *
     * @param sql             statement of database
     * @param arrayParameters params in array way
     * @return {@link Number} the result of execution
     */
    Number findNumberByArray(final String sql, Object... arrayParameters);

    /**
     * transfer math value by sql statement
     * if need <tt>double</tt> value,setting the second param by <tt>Double.class</tt>
     *
     * @param sql             statement of database
     * @param numberClass     like <tt>Double.class</tt> or <tt>Float.class</tt> and so on
     * @param arrayParameters params in statement
     * @param <T>             refer to second param
     * @return {@link T} the result of execution
     */
    <T extends Number> T queryNumberByArray(final String sql, Class<T> numberClass, Object... arrayParameters);

    /**
     * transfer math value by sql statement
     * when developer transfer the returned math value,he/she can use {@link Number#longValue()}
     * or other method to transfer to other type.
     * Besides,developer use {@link #queryNumberByMap(String, Class, Map)} to transfer long value directly
     *
     * @param sql          statement of database
     * @param mapParameter params in map way
     * @return {@link Number} the result of execution
     */
    Number findNumberByMap(final String sql, Map<String, Object> mapParameter);

    /**
     * transfer math value by sql statement
     * if need <tt>double</tt> value,setting the second param by <tt>Double.class</tt>
     *
     * @param sql          statement of database
     * @param numberClass  like <tt>Double.class</tt> or <tt>Float.class</tt> and so on
     * @param mapParameter params in map way
     * @param <T>          refer to second param
     * @return {@link T} the result of execution
     */
    <T extends Number> T queryNumberByMap(final String sql, Class<T> numberClass, Map<String, Object> mapParameter);

    /**
     * transfer math value by sql statement
     * when developer transfer the returned math value,he/she can use {@link Number#longValue()}
     * or other method to transfer to other type.
     * Besides,developer use {@link #queryNumberByBean(String, Class, Object)} to transfer long value directly
     *
     * @param sql           statement of database
     * @param beanParameter params in bean way
     * @return {@link Number} the result of execution
     */
    Number findNumberByBean(final String sql, Object beanParameter);

    /**
     * transfer math value by sql statement
     * if need <tt>double</tt> value,setting the second param by <tt>Double.class</tt>
     *
     * @param sql           statement of database
     * @param numberClass   like <tt>Double.class</tt> or <tt>Float.class</tt> and so on
     * @param beanParameter params in bean way
     * @param <T>           refer to second param
     * @return {@link T} the result of execution
     */
    <T extends Number> T queryNumberByBean(final String sql, Class<T> numberClass, Object beanParameter);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass        可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param arrayParameters sql语句中的参数
     * @param <T>
     * @return
     */
    <T> T findPrimitiveByArray(final String sql, Class<T> objClass, Object... arrayParameters);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass     可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param mapParameter
     * @param <T>
     * @return
     */
    <T> T findPrimitiveByMap(final String sql, Class<T> objClass, Map<String, Object> mapParameter);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass      可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param beanParameter
     * @param <T>
     * @return
     */
    <T> T findPrimitiveByBean(final String sql, Class<T> objClass, Object beanParameter);

    /**
     * transfer a series of key-value objects
     * for sometimes does not ready a bean for selected results,using a map to collect them
     *
     * @param sql             statement of database
     * @param arrayParameters params in array
     * @return {@link Map} the select results in key-value
     */
    Map<String, Object> findMapByArray(final String sql, Object... arrayParameters);

    /**
     * transfer a series of key-value objects
     * for sometimes does not ready a bean for selected results,using a map to collect them
     *
     * @param sql          statement of database
     * @param mapParameter params in map
     * @return {@link Map} the select results in key-value
     */
    Map<String, Object> findMapByMap(final String sql, Map<String, Object> mapParameter);

    /**
     * 查找键值对集合，参数为冒号形式的对象
     *
     * @param sql
     * @param beanParameter
     * @return
     */
    Map<String, Object> findMapByBean(final String sql, Object beanParameter);

    /**
     * transfer object by sql statement,assigning return type
     *
     * @param sql             statement of database
     * @param clazz           return type
     * @param arrayParameters params in array
     * @param <T>             refer to second param
     * @return {@link T}
     */
    <T> T findBeanByArray(final String sql, Class<T> clazz, Object... arrayParameters);

    /**
     * transfer object by sql statement,assigning return type
     *
     * @param sql          statement of database
     * @param clazz        return type
     * @param mapParameter params in map
     * @param <T>          refer to second param
     * @return {@link T}
     */
    <T> T findBeanByMap(final String sql, Class<T> clazz, Map<String, Object> mapParameter);


    /**
     * 通过bean条件参数查找bean
     *
     * @param sql           查询的sql语句
     * @param clazz         需要匹配的对象class
     * @param beanParameter sql中的参数
     * @return
     */
    <T> T findBeanByBean(final String sql, Class<T> clazz, Object beanParameter);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass        可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param arrayParameters sql语句中的参数
     * @param <T>
     * @return
     */
    <T> List<T> findListPrimitiveByArray(final String sql, Class<T> objClass, Object... arrayParameters);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass     可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param mapParameter
     * @param <T>
     * @return
     */
    <T> List<T> findListPrimitiveByMap(final String sql, Class<T> objClass, Map<String, Object> mapParameter);

    /**
     * 查询一列单条数据
     *
     * @param sql
     * @param objClass      可以为String、基本类型的包装类型，出此两种情况其余都不可以
     * @param beanParameter
     * @param <T>
     * @return
     */
    <T> List<T> findListPrimitiveByBean(final String sql, Class<T> objClass, Object beanParameter);

    /**
     * transfer map collection by sql statement
     *
     * @param sql             statement of database
     * @param arrayParameters params in array
     * @return {@link List<Map>}
     */
    List<Map<String, Object>> findListMapByArray(final String sql, Object... arrayParameters);

    /**
     * transfer map collection by sql statement
     *
     * @param sql          statement of database
     * @param mapParameter params in map
     * @return {@link List<Map>}
     */
    List<Map<String, Object>> findListMapByMap(final String sql, Map<String, Object> mapParameter);

    /**
     * 查询list集合，参数为冒号形式的对象
     *
     * @param sql
     * @param beanParameter
     * @return
     */
    List<Map<String, Object>> findListMapByBean(final String sql, Object beanParameter);

    /**
     * transfer object list by sql statement,assigning object type
     *
     * @param <T>             refer to second param
     * @param sql             statement of database
     * @param clazz           return object type
     * @param arrayParameters params in array
     * @return {@link List<T>}
     */
    <T> List<T> findListBeanByArray(final String sql, Class<T> clazz, Object... arrayParameters);

    /**
     * transfer object list by sql statement,assigning object type
     *
     * @param <T>          refer to second param
     * @param sql          statement of database
     * @param clazz        return object type
     * @param mapParameter params in map
     * @return {@link List<T>}
     */
    <T> List<T> findListBeanByMap(final String sql, Class<T> clazz, Map<String, Object> mapParameter);

    /**
     * transfer object list by sql statement,assigning object type
     *
     * @param sql           statement of database
     * @param clazz         return object type
     * @param beanParameter params in bean
     * @param <T>           refer to second param
     * @return {@link List<T>}
     */
    <T> List<T> findListBeanByBean(final String sql, Class<T> clazz, Object beanParameter);

    /**
     * common page method,getting <tt>List&lt;Map&gt;</tt> to result in current page object
     *
     * @param sql             statement of database which select content in condition you want,only including service logic,excluding <tt>LIMIT</tt>
     * @param page            current page object
     * @param arrayParameters params in array
     * @return {@link Page}
     */
    Page findPageListMapByArray(final String sql, Page page, Object... arrayParameters);

    /**
     * common page method,getting <tt>List&lt;Map&gt;</tt> to result in current page object
     *
     * @param sql          statement of database which select content in condition you want,only including service logic,excluding <tt>LIMIT</tt>
     * @param page         current page object
     * @param mapParameter params in map
     * @return {@link Page}
     */
    Page findPageListMapByMap(final String sql, Page page, Map<String, Object> mapParameter);

    /**
     * common page method,getting <tt>List&lt;Map&gt;</tt> to result in current page object
     *
     * @param sql             statement of database which select content in condition you want,only including service logic,excluding <tt>LIMIT</tt>
     * @param clazz           type of result in current page
     * @param page            current page object
     * @param arrayParameters params in array
     * @return {@link Page}
     */
    <T> Page<T> findPageListBeanByArray(final String sql, Class<T> clazz, Page<T> page, Object... arrayParameters);

    /**
     * common page method,getting <tt>List&lt;Map&gt;</tt> to result in current page object
     *
     * @param sql          statement of database which select content in condition you want,only including service logic,excluding <tt>LIMIT</tt>
     * @param clazz        type of result in current page
     * @param page         current page object
     * @param mapParameter params in map
     * @return {@link Page}
     */
    <T> Page<T> findPageListBeanByMap(final String sql, Class<T> clazz, Page<T> page, Map<String, Object> mapParameter);

    /**
     * common page method,getting <tt>List&lt;Map&gt;</tt> to result in current page object
     *
     * @param sql           statement of database which select content in condition you want,only including service logic,excluding <tt>LIMIT</tt>
     * @param clazz         type of result in current page
     * @param page          current page object
     * @param beanParameter params in bean
     * @return {@link Page}
     */
    <T> Page<T> findPageListBeanByBean(final String sql, Class<T> clazz, Page<T> page, Object beanParameter);

}
