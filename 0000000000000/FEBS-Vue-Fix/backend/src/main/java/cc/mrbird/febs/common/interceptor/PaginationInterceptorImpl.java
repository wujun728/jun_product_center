package cc.mrbird.febs.common.interceptor;

import cc.mrbird.febs.common.annotation.DataFilter;
import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.enums.FilterType;
import cc.mrbird.febs.common.utils.FebsUtil;
import cc.mrbird.febs.system.domain.Role;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisDefaultParameterHandler;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.SqlInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.extension.plugins.pagination.DialectModel;
import com.baomidou.mybatisplus.extension.toolkit.JdbcUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlParserUtils;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.util.Assert;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


/**
 * @author : wx
 * @version : 1
 * @date : 2019/9/27 17:33
 */
/**
 * 分页拦截器
 *
 * @author hubin
 * @since 2016-01-23
 */
@Setter
@Accessors(chain = true)
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PaginationInterceptorImpl extends PaginationInterceptor {
    /**
     * COUNT SQL 解析
     */
    private ISqlParser countSqlParser;
    /**
     * 溢出总页数，设置第一页
     */
    private boolean overflow = false;
    /**
     * 单页限制 500 条，小于 0 如 -1 不受限制
     */
    private long limit = 500L;
    /**
     * 方言类型
     */
    private String dialectType;
    /**
     * 方言实现类<br>
     * 注意！实现 com.baomidou.mybatisplus.extension.plugins.pagination.dialects.IDialect 接口的子类
     */
    private String dialectClazz;

    /**
     * 查询SQL拼接Order By
     *
     * @param originalSql 需要拼接的SQL
     * @param page        page对象
     * @return ignore
     */
    public static String concatOrderBy(String originalSql, IPage<?> page) {
        if (CollectionUtils.isNotEmpty(page.orders())) {
            try {
                List<OrderItem> orderList = page.orders();
                Select selectStatement = (Select) CCJSqlParserUtil.parse(originalSql);
                if (selectStatement.getSelectBody() instanceof PlainSelect) {
                    PlainSelect plainSelect = (PlainSelect) selectStatement.getSelectBody();
                    List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
                    List<OrderByElement> orderByElementsReturn = addOrderByElements(orderList, orderByElements);
                    plainSelect.setOrderByElements(orderByElementsReturn);
                    return plainSelect.toString();
                } else if (selectStatement.getSelectBody() instanceof SetOperationList) {
                    SetOperationList setOperationList = (SetOperationList) selectStatement.getSelectBody();
                    List<OrderByElement> orderByElements = setOperationList.getOrderByElements();
                    List<OrderByElement> orderByElementsReturn = addOrderByElements(orderList, orderByElements);
                    setOperationList.setOrderByElements(orderByElementsReturn);
                    return setOperationList.toString();
                } else if (selectStatement.getSelectBody() instanceof WithItem) {
                    // todo: don't known how to resole
                    return originalSql;
                } else {
                    return originalSql;
                }

            } catch (JSQLParserException e) {
                logger.warn("failed to concat orderBy from IPage, exception=" + e.getMessage());
            }
        }
        return originalSql;
    }

    @NotNull
    private static List<OrderByElement> addOrderByElements(List<OrderItem> orderList, List<OrderByElement> orderByElements) {
        if (orderByElements == null || orderByElements.isEmpty()) {
            orderByElements = new ArrayList<>(orderList.size());
        }
        for (OrderItem item : orderList) {
            OrderByElement element = new OrderByElement();
            element.setExpression(new Column(item.getColumn()));
            element.setAsc(item.isAsc());
            orderByElements.add(element);
        }
        return orderByElements;
    }

    /**
     * Physical Page Interceptor for all the queries with parameter {@link RowBounds}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        boolean dataFilter=false;
        // SQL 解析
        this.sqlParser(metaObject);

        // 先判断是不是SELECT操作  (2019-04-10 00:37:31 跳过存储过程)
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT != mappedStatement.getSqlCommandType()
                || StatementType.CALLABLE == mappedStatement.getStatementType()) {
            return invocation.proceed();
        }
        //数据权限过滤
        DataFilter anno=null;
        if(SqlCommandType.SELECT == mappedStatement.getSqlCommandType()){
            String id=mappedStatement.getId();
            String className=id.substring(0,id.lastIndexOf("."));
            String methodName=id.replace(className+".","");
            System.out.println(methodName);
            Class clazz=Class.forName(className);
            if(clazz.isAnnotationPresent(DataFilter.class)){
                anno=(DataFilter)clazz.getAnnotation(DataFilter.class);
               String[] methods= anno.filterMethods();
                String[] ruledOutMethods=anno.ruledOutMethods();
               if(methods.length==0){
                   if(ruledOutMethods.length==0){
                       dataFilter=true;
                   }else{
                       String str=","+StringUtils.join(ruledOutMethods,",")+",";
                       if(str.indexOf(methodName)==-1){
                           dataFilter=true;
                       }
                   }
               }else{
                   Optional<String> optional=Arrays.stream(ruledOutMethods).parallel().filter(me -> me.equals(methodName)).findAny();
                   if(!optional.isPresent()){
                       optional=Arrays.stream(methods).parallel().filter(me -> me.equals(methodName)).findAny();
                       if(optional.isPresent()){
                           dataFilter=true;
                       }
                   }
               }
            }else{
                try {
                    Method method = clazz.getDeclaredMethod(methodName);
                    if (method != null && method.isAnnotationPresent(DataFilter.class)) {
                        anno=(DataFilter)method.getAnnotation(DataFilter.class);
                        dataFilter = true;
                    }
                }catch (Exception ex){}
            }
        }
        // 针对定义了rowBounds，做为mapper接口方法的参数
        BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");
        Object paramObj = boundSql.getParameterObject();

        // 判断参数里是否有page对象
        IPage<?> page = null;
        if (paramObj instanceof IPage) {
            page = (IPage<?>) paramObj;
        } else if (paramObj instanceof Map) {
            for (Object arg : ((Map<?, ?>) paramObj).values()) {
                if (arg instanceof IPage) {
                    page = (IPage<?>) arg;
                    break;
                }
            }
        }

        /*
         * 不需要分页的场合，如果 size 小于 0 返回结果集
         */
        if (null == page || page.getSize() < 0) {
            return invocation.proceed();
        }

        /*
         * 处理单页条数限制
         */
        if (limit > 0 && limit <= page.getSize()) {
            page.setSize(limit);
        }

        String originalSql = boundSql.getSql();
        //数据范围过滤
        if(dataFilter){
            User me= FebsUtil.getCurrentUser();
            String fieldId = anno.filterFieldId(),filterType = anno.filterType().getType(),joinSql=anno.joinSql();
            if(filterType.equals(FilterType.FIELD.getType())){
                List<Role> roles= FebsUtil.getUserRoles();
                boolean allData=false,deptData=false,selfData=false;
                for(Role role:roles){
                    if(FebsConstant.DATA_FILTER_ALL ==role.getDataScope())allData=true;
                    if(FebsConstant.DATA_FILTER_DEPT==role.getDataScope())deptData=true;
                    if(FebsConstant.DATA_FILTER_OWN==role.getDataScope())selfData=true;
                }
                String subordinates=FebsUtil.getUserSubordinates(me.getDeptId());
                if(deptData){
                    if(StringUtils.isNotEmpty(subordinates)){
                        if(!StringUtils.containsIgnoreCase(originalSql,"where")){
                            originalSql=originalSql+" where "+fieldId+" in ("+subordinates+")";
                        }else if (originalSql.contains("where")){
                            String[] sqlParts=originalSql.split("where");
                            Assert.isTrue(sqlParts.length==2);
                            originalSql=sqlParts[0]+"where "+fieldId+" in ("+subordinates+") and "+sqlParts[1];
                        }else if (originalSql.contains("WHERE")){
                            String[] sqlParts=originalSql.split("WHERE");
                            Assert.isTrue(sqlParts.length==2);
                            originalSql=sqlParts[0]+"WHERE "+fieldId+" in ("+subordinates+") and "+sqlParts[1];
                        }
                    }
                }else if(selfData){
                    if(!StringUtils.containsIgnoreCase(originalSql,"where")){
                        originalSql=originalSql+" where "+fieldId+" in ("+me.getUserId()+")";
                    }else if (originalSql.contains("where")){
                        String[] sqlParts=originalSql.split("where");
                        Assert.isTrue(sqlParts.length==2);
                        originalSql=sqlParts[0]+"where "+fieldId+" in ("+me.getUserId()+") and "+sqlParts[1];
                    }else if (originalSql.contains("WHERE")){
                        String[] sqlParts=originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length==2);
                        originalSql=sqlParts[0]+"WHERE "+fieldId+" in ("+me.getUserId()+") and "+sqlParts[1];
                    }
                }else if (allData){}
            }else if(filterType.equals(FilterType.JOIN.getType())){
                if(StringUtils.isNotEmpty(joinSql)){
                    if(!StringUtils.containsIgnoreCase(originalSql,"where")){
                        originalSql=originalSql+" "+joinSql+ " where "+ fieldId+"="+me.getUserId();
                    }else if (originalSql.contains("where")){
                        String[] sqlParts=originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length==2);
                        originalSql=sqlParts[0]+joinSql+" where "+fieldId+"="+me.getUserId()+" and "+sqlParts[1];
                    }else if (originalSql.contains("WHERE")){
                        String[] sqlParts=originalSql.split("WHERE");
                        Assert.isTrue(sqlParts.length==2);
                        originalSql=sqlParts[0]+joinSql+" WHERE "+fieldId+"="+me.getUserId()+" and "+sqlParts[1];
                    }
                }
            }
        }
        Connection connection = (Connection) invocation.getArgs()[0];
        DbType dbType = StringUtils.isNotEmpty(dialectType) ? DbType.getDbType(dialectType)
                : JdbcUtils.getDbType(connection.getMetaData().getURL());

        if (page.isSearchCount()) {
            SqlInfo sqlInfo = SqlParserUtils.getOptimizeCountSql(page.optimizeCountSql(), countSqlParser, originalSql);
            this.queryTotal(overflow, sqlInfo.getSql(), mappedStatement, boundSql, page, connection);
            if (page.getTotal() <= 0) {
                return null;
            }
        }

        String buildSql = concatOrderBy(originalSql, page);
        DialectModel model = DialectFactory.buildPaginationSql(page, buildSql, dbType, dialectClazz);
        Configuration configuration = mappedStatement.getConfiguration();
        List<ParameterMapping> mappings = new ArrayList<>(boundSql.getParameterMappings());
        Map<String, Object> additionalParameters = (Map<String, Object>) metaObject.getValue("delegate.boundSql.additionalParameters");
        model.consumers(mappings, configuration, additionalParameters);
        metaObject.setValue("delegate.boundSql.sql", model.getDialectSql());
        metaObject.setValue("delegate.boundSql.parameterMappings", mappings);
        return invocation.proceed();
    }

    /**
     * 查询总记录条数
     *
     * @param sql             count sql
     * @param mappedStatement MappedStatement
     * @param boundSql        BoundSql
     * @param page            IPage
     * @param connection      Connection
     */
    @Override
    protected void queryTotal(boolean overflowCurrent, String sql, MappedStatement mappedStatement, BoundSql boundSql, IPage<?> page, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            DefaultParameterHandler parameterHandler = new MybatisDefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), boundSql);
            parameterHandler.setParameters(statement);
            long total = 0;
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    total = resultSet.getLong(1);
                }
            }
            page.setTotal(total);
            /*
             * 溢出总页数，设置第一页
             */
            long pages = page.getPages();
            if (overflowCurrent && page.getCurrent() > pages) {
                // 设置为第一条
                page.setCurrent(1);
            }
        } catch (Exception e) {
            throw ExceptionUtils.mpe("Error: Method queryTotal execution error of sql : \n %s \n", e, sql);
        }
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        String dialectType = prop.getProperty("dialectType");
        String dialectClazz = prop.getProperty("dialectClazz");
        if (StringUtils.isNotEmpty(dialectType)) {
            this.dialectType = dialectType;
        }
        if (StringUtils.isNotEmpty(dialectClazz)) {
            this.dialectClazz = dialectClazz;
        }
    }
}
