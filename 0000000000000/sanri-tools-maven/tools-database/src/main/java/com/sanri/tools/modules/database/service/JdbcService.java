package com.sanri.tools.modules.database.service;

import com.sanri.tools.modules.core.dtos.PluginDto;
import com.sanri.tools.modules.core.dtos.UpdateConnectEvent;
import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.core.service.plugin.PluginManager;
import com.sanri.tools.modules.database.dtos.ConnectionMetaData;
import com.sanri.tools.modules.database.dtos.DynamicQueryDto;
import com.sanri.tools.modules.database.dtos.meta.*;
import com.sanri.tools.modules.core.dtos.param.AuthParam;
import com.sanri.tools.modules.core.dtos.param.ConnectParam;
import com.sanri.tools.modules.core.dtos.param.DatabaseConnectParam;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
@Slf4j
public class JdbcService implements ApplicationListener<UpdateConnectEvent> {
    @Autowired
    private ConnectService connectService;
    @Autowired
    private PluginManager pluginManager;

    public static final String module = "database";

    // connName ==> DataSource
    private Map<String, DataSource> dataSourceMap = new ConcurrentHashMap<>();
    
    // connName => ActualTableName => TableMetaData
    private Map<String,Map<ActualTableName,TableMetaData>> tableMetaDataMap = new ConcurrentHashMap<>();

    /**
     * 首次加载所有的数据表
     * @param connName
     * @param catalog
     * @return
     */
    public Collection<TableMetaData> tables(String connName, String catalog) throws IOException, SQLException {
        Map<ActualTableName, TableMetaData> actualTableNameTableMetaDataMap = tableMetaDataMap.get(connName);
        if (actualTableNameTableMetaDataMap == null){
            actualTableNameTableMetaDataMap = refreshTableInfo(connName, catalog, null);
            tableMetaDataMap.put(connName,actualTableNameTableMetaDataMap);

            return actualTableNameTableMetaDataMap.values();
        }
        if (StringUtils.isBlank(catalog)){
            return actualTableNameTableMetaDataMap.values();
        }

        // 需要判断指定的 catalog 是否已经加载,对于 mysql 数据库来说
        // 查找所有的 catalog ,如果没有就加载一下,最后返回指定的 catalog 数据
        Set<String> existCatalogs = actualTableNameTableMetaDataMap.keySet().stream().map(ActualTableName::getCatalog).collect(Collectors.toSet());
        if (!existCatalogs.contains(null) && !existCatalogs.contains(catalog)){
            Map<ActualTableName, TableMetaData> newCatalogTables = refreshTableInfo(connName, catalog, null);
            actualTableNameTableMetaDataMap.putAll(newCatalogTables);
        }

        // 过滤出指定 catalog 的数据
        Collection<TableMetaData> catalogTables = new ArrayList<>();
        Iterator<TableMetaData> iterator = actualTableNameTableMetaDataMap.values().iterator();
        while (iterator.hasNext()){
            TableMetaData tableMetaData = iterator.next();
            String currentCatalog = tableMetaData.getActualTableName().getCatalog();
            if (currentCatalog == null || catalog.contains(currentCatalog)){
                catalogTables.add(tableMetaData);
            }
        }

        return catalogTables;
    }

    /**
     * 过滤出指定 schema 的表
     * @param connName
     * @param catalog
     * @param schema
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public List<TableMetaData> filterSchemaTables(String connName, String catalog, Set<String> schemas) throws IOException, SQLException {
        Collection<TableMetaData> tables = tables(connName, catalog);

        // 首次过滤, 过滤 catalog 和 schema
        List<TableMetaData> filterTables = new ArrayList<>(tables);
        Iterator<TableMetaData> iterator = filterTables.iterator();
        while (iterator.hasNext()){
            TableMetaData tableMetaData = iterator.next();
            ActualTableName actualTableName = tableMetaData.getActualTableName();
            String currentCatalog = actualTableName.getCatalog();
            String currentSchema = actualTableName.getSchema();

            if (StringUtils.isNotBlank(currentCatalog)){
                if (StringUtils.isNotBlank(catalog) && !catalog.equals(currentCatalog)){
                    iterator.remove();
                    continue;
                }
            }

            if (StringUtils.isNotBlank(currentSchema)){
                if (CollectionUtils.isNotEmpty(schemas) && !schemas.contains(currentSchema)){
                    iterator.remove();
                    continue;
                }
            }
        }
        return filterTables;
    }

    /**
     * 过滤出需要的表格元数据
     * @param connName
     * @param catalog
     * @param schema
     * @param tableNames
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public List<TableMetaData> filterChoseTables(String connName, String catalog, List<ActualTableName> tables) throws IOException, SQLException {
        Set<String> schemas = tables.stream().map(actualTableName -> actualTableName.getSchema()).collect(Collectors.toSet());
        List<String> tableNames = tables.stream().map(ActualTableName::getTableName).collect(Collectors.toList());

        List<TableMetaData> tableMetaDataList = filterSchemaTables(connName, catalog, schemas);

        List<TableMetaData> filterTables = new ArrayList<>();
        Iterator<TableMetaData> iterator = tableMetaDataList.iterator();
        while (iterator.hasNext()){
            TableMetaData tableMetaData = iterator.next();
            ActualTableName actualTableName = tableMetaData.getActualTableName();
            String tableName = actualTableName.getTableName();
            if (tableNames.contains(tableName)){
                filterTables.add(tableMetaData);
            }
        }
        return filterTables;
    }

    /**
     * 根据连接得到所有的 catalog 和 schema
     * @param connName
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public List<Catalog> refreshConnection(String connName) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        ResultSet resultSet = null;
        List<Catalog> catalogList = new ArrayList<>(); ;
        try {
            resultSet = databaseMetaData.getCatalogs();
            ResultSetHandler<List<String>> resultSetHandler = new ColumnListHandler<String>();
            List<String> catalogs = resultSetHandler.handle(resultSet);

            resultSet = databaseMetaData.getSchemas();
            List<Schema> schemaList = schemaListProcessor.handle(resultSet);

            if(CollectionUtils.isNotEmpty(schemaList)){     // postgresql
                List<String> schemas = schemaList.stream().map(Schema::getSchema).collect(Collectors.toList());
                for (String catalog : catalogs) {
                    catalogList.add(new Catalog(catalog,schemas));
                }
            }else{      //  mysql
                Map<String, List<String>> catalogMap = schemaList.stream()
                        .collect(Collectors.groupingBy(Schema::getCatalog,Collectors.mapping(Schema::getSchema,Collectors.toList())));

                for (String catalog : catalogs) {
                    List<String> catalogSchemas = catalogMap.get(catalog);
                    catalogList.add(new Catalog(catalog,catalogSchemas));
                }
            }

        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }
        return catalogList;
    }

    /**
     * 刷新指定 schema 的表,同时更新缓存
     * @param connName
     * @param catalog
     * @param schema
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public Collection<TableMetaData> refreshCatalogOrSchema(String connName, String catalog, String schema) throws IOException, SQLException {
        Map<ActualTableName, TableMetaData> newTableMetaData = refreshTableInfo(connName, catalog, schema);

        // 刷新缓存
        Map<ActualTableName, TableMetaData> oldTableMetaData = tableMetaDataMap.get(connName);
        oldTableMetaData.putAll(newTableMetaData);

        return newTableMetaData.values();
    }

    public List<Column> refreshTableColumns(String connName, ActualTableName actualTableName) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        List<Column> columns;
        ResultSet columnsResultSet = null;
        try {
            String catalog = actualTableName.getCatalog();
            String schema = actualTableName.getSchema();
            String tableName = actualTableName.getTableName();
            columnsResultSet = databaseMetaData.getColumns(catalog, schema, tableName, "%");
            columns = columnListProcessor.handle(columnsResultSet);

            TableMetaData tableMetaData = tableMetaDataMap.get(connName).get(actualTableName);
            tableMetaData.setColumns(columns);
        } finally {
            DbUtils.closeQuietly(columnsResultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }

        return columns;
    }

    public List<Index> refreshTableIndexs(String connName, ActualTableName actualTableName) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        List<Index> indices;ResultSet resultSet = null;
        try {
            String catalog = actualTableName.getCatalog();
            String schema = actualTableName.getSchema();
            String tableName = actualTableName.getTableName();

            resultSet = databaseMetaData.getIndexInfo(catalog, schema, tableName, false,true);
            indices = indexListProcessor.handle(resultSet);

            TableMetaData tableMetaData = tableMetaDataMap.get(connName).get(actualTableName);
            tableMetaData.setIndexs(indices);
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }

        return indices;
    }

    public List<PrimaryKey> refreshTablePrimaryKeys(String connName, ActualTableName actualTableName) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        List<PrimaryKey> primaryKeys;ResultSet resultSet = null;
        try {
            String catalog = actualTableName.getCatalog();
            String schema = actualTableName.getSchema();
            String tableName = actualTableName.getTableName();
            resultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            primaryKeys = primaryKeyListProcessor.handle(resultSet);

            TableMetaData tableMetaData = tableMetaDataMap.get(connName).get(actualTableName);
            tableMetaData.setPrimaryKeys(primaryKeys);
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }

        return primaryKeys;
    }

    /**
     * 数据表信息刷新,同时更新缓存
     * @param connName
     * @param actualTableName
     * @return
     */
    public TableMetaData refreshTable(String connName, ActualTableName actualTableName) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        List<PrimaryKey> primaryKeys;ResultSet resultSet = null;
        try {
            String catalog = actualTableName.getCatalog();
            String schema = actualTableName.getSchema();
            String tableName = actualTableName.getTableName();

            resultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            primaryKeys = primaryKeyListProcessor.handle(resultSet);

            resultSet = databaseMetaData.getIndexInfo(catalog, schema, tableName, false,true);
            List<Index> indices = indexListProcessor.handle(resultSet);

            resultSet = databaseMetaData.getColumns(catalog, schema, tableName, "%");
            List<Column> columns = columnListProcessor.handle(resultSet);

            TableMetaData tableMetaData = tableMetaDataMap.get(connName).get(actualTableName);
            tableMetaData.setPrimaryKeys(primaryKeys);
            tableMetaData.setIndexs(indices);
            tableMetaData.setColumns(columns);

            return tableMetaData;
        } finally {
            DbUtils.closeQuietly(resultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }
    }

    /**
     * 表搜索 , 可根据 schema 表名 , 表注释 , 字段名 , 字段注释进行搜索
     * 支持精确搜索和模糊搜索
     *   table:xx
     *   column:xxx
     * @param connName
     * @param catalog
     * @param schema
     * @param keyword
     * @return
     */
    public List<TableMetaData> searchTables(String connName,String catalog,Set<String> schemas,String searchSchema,String keyword) throws IOException, SQLException {
        List<TableMetaData> firstFilterTables = filterSchemaTables(connName, catalog, schemas);

        // 空搜索返回所有表
        if(StringUtils.isBlank(keyword)){
            return firstFilterTables;
        }

        // oracle 的特殊处理
        DataSource dataSource = dataSourceMap.get(connName);
        if (dataSource instanceof OracleDataSource){
            //如果为 oracle ,搜索关键字转大写
            keyword = keyword.toUpperCase();
        }

        List<TableMetaData> findTables = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(firstFilterTables)){
            for (TableMetaData tableMetaData : firstFilterTables) {
                ActualTableName actualTableName = tableMetaData.getActualTableName();
                String tableName = actualTableName.getTableName();
                Table table = tableMetaData.getTable();
                String tableComments = table.getRemark();
                if(StringUtils.isBlank(searchSchema) || "table".equalsIgnoreCase(searchSchema)) {
                    if (tableName.contains(keyword) || (StringUtils.isNotBlank(tableComments) && tableComments.contains(keyword))) {
                        findTables.add(tableMetaData);
                        continue;
                    }
                }

                //再看是否有列是匹配的
                List<Column> columns = tableMetaData.getColumns();
                if(CollectionUtils.isNotEmpty(columns)){
                    for (Column column : columns) {
                        String columnName = column.getColumnName();
                        String columnComments = column.getRemark();

                        if(StringUtils.isBlank(searchSchema) || "column".equalsIgnoreCase(searchSchema)) {
                            if (columnName.contains(keyword) || (StringUtils.isNotBlank(columnComments) && columnComments.contains(keyword))) {
                                findTables.add(tableMetaData);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return findTables;
    }

    /**
     * 查找 某一张表
     * @param connName
     * @param actualTableName
     * @return
     */
    public TableMetaData findTable(String connName, ActualTableName actualTableName) throws IOException, SQLException {
        List<TableMetaData> tableMetaDataList = filterChoseTables(connName, actualTableName.getCatalog(), Collections.singletonList(actualTableName));
        if (CollectionUtils.isNotEmpty(tableMetaDataList)){
            return tableMetaDataList.get(0);
        }
        return null;
    }

    /**
     * 执行 sql ,在某个连接上
     * @param connName
     * @param sql
     * @return
     */
    public List<Integer> executeUpdate(String connName, List<String> sqls) throws SQLException, IOException {
        List<Integer> updates = new ArrayList<>();
        DataSource dataSource = dataSource(connName);
        QueryRunner queryRunner = new QueryRunner(dataSource);
        for (String sql : sqls) {
            int update = queryRunner.update(sql);
            updates.add(update);
        }

        return updates;
    }

    /**
     * 执行查询
     * @param connName
     * @param sql
     * @param resultSetHandler
     * @param params
     * @param <T>
     * @return
     * @throws SQLException
     */
    public <T> T executeQuery(String connName,String sql,ResultSetHandler<T> resultSetHandler,Object...params) throws SQLException, IOException {
        DataSource dataSource = dataSource(connName);
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner.query(sql,resultSetHandler,params);
    }

    /**
     * 给出 sql ,查询出数据,将头信息和结果一并给出
     * @param connName
     * @param sqls
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public List<DynamicQueryDto> executeDynamicQuery(String connName,List<String> sqls) throws IOException, SQLException {
        List<DynamicQueryDto> dynamicQueryDtos = new ArrayList<>();
        DataSource dataSource = dataSource(connName);
        QueryRunner queryRunner = new QueryRunner(dataSource);
        for (String sql : sqls) {
            try {
                DynamicQueryDto dynamicQueryDto = queryRunner.query(sql, dynamicQueryProcessor);
                dynamicQueryDto.setSql(sql);
                dynamicQueryDtos.add(dynamicQueryDto);
            } catch (SQLException e) {
                log.error("当前 sql [{}],在 connName [{}] 执行失败，原因为 [{}]",sql,connName,e.getMessage(),e);
            }
        }
        return dynamicQueryDtos;
    }

    /**
     * 对比两个连接的结构差异,并生成 alter 语句
     * 假设 from 为开发 to 到测试 , 则为 开发到测试的结构变更
     * @param fromConnName
     * @param toConnName
     */
    public void compareMetaData(String fromConnName,String fromCatalog,String toConnName,String toCatalog) throws IOException, SQLException {
        Collection<TableMetaData> fromTables = tables(fromConnName, fromCatalog);
        Collection<TableMetaData> toTables = tables(toConnName, toCatalog);

        List<String> alters = new ArrayList<>();

        // 这一个循环无法检测出 from 有 to 没有的数据
        Map<ActualTableName, TableMetaData> fromMeta = fromTables.stream().collect(Collectors.toMap(TableMetaData::getActualTableName, t -> t));
        for (TableMetaData toTable : toTables) {
            ActualTableName actualTableName = toTable.getActualTableName();
            TableMetaData fromTable = fromMeta.get(actualTableName);
            if (fromTable == null){
                // from 没有 to 有 ; 则需要删除结构
                String schema = actualTableName.getSchema();
                if (StringUtils.isNotBlank(schema)) {
                    alters.add("drop table " + schema + "." + actualTableName.getTableName());
                }else{
                    alters.add("drop table " + actualTableName.getTableName());
                }
                continue;
            }
            // 比较两个表的数据列
            List<Column> toTableColumns = toTable.getColumns();
            List<Column> fromTableColumns = fromTable.getColumns();

        }
    }


    /**
     * 获取数据库连接的详细信息
     * @param connName
     * @return
     */
    public ConnectionMetaData connectionMetaData(String connName) throws IOException, SQLException {
        DatabaseConnectParam databaseConnectParam = (DatabaseConnectParam) connectService.readConnParams(module, connName);
        AuthParam authParam = databaseConnectParam.getAuthParam();
        DataSource dataSource = dataSource(connName);
        String connectionURL = "";
        if (dataSource instanceof ExMysqlDataSource){
            ExMysqlDataSource exMysqlDataSource = (ExMysqlDataSource) dataSource;
            connectionURL = exMysqlDataSource.getURL();
        }else if (dataSource instanceof PGSimpleDataSource){
            PGSimpleDataSource pgSimpleDataSource = (PGSimpleDataSource) dataSource;
            connectionURL = pgSimpleDataSource.getURL();
        }else if (dataSource instanceof OracleDataSource){
            OracleDataSource oracleDataSource = (OracleDataSource) dataSource;
            connectionURL = oracleDataSource.getURL();
        }
        return new ConnectionMetaData(authParam,databaseConnectParam.driverClass(),connectionURL);
    }

    protected Map<ActualTableName, TableMetaData> refreshTableInfo(String connName, String catalog, String schema) throws IOException, SQLException {
        DatabaseMetaData databaseMetaData = databaseMetaData(connName);
        ResultSet tablesResultSet = null;

        Map<ActualTableName, TableMetaData> tableNameTableMetaDataMap;
        try {
            tablesResultSet = databaseMetaData.getTables(catalog, schema, "%", new String[]{"TABLE"});
            List<Table> tables = tableListProcessor.handle(tablesResultSet);

            Map<ActualTableName, List<Column>> tableColumnsMap = refreshColumns(databaseMetaData, catalog, schema);
            Map<ActualTableName, List<Index>> tableIndexMap = refreshIndexs(databaseMetaData, catalog, schema,tables);
            Map<ActualTableName, List<PrimaryKey>> primaryKeyMap = refreshPrimaryKeys(databaseMetaData, catalog, schema,tables);

            tableNameTableMetaDataMap = new HashMap<>();

            for (Table table : tables) {
                ActualTableName actualTableName = table.getActualTableName();
                List<Column> columns = tableColumnsMap.get(actualTableName);
                List<Index> indexs = tableIndexMap.get(actualTableName);
                List<PrimaryKey> primaryKeys = primaryKeyMap.get(actualTableName);
                TableMetaData tableMetaData = new TableMetaData(actualTableName, table, columns, indexs, primaryKeys);

                tableNameTableMetaDataMap.put(actualTableName,tableMetaData);
            }
        } finally {
            DbUtils.closeQuietly(tablesResultSet);
            DbUtils.closeQuietly(databaseMetaData.getConnection());
        }
        return tableNameTableMetaDataMap;
    }

    protected Map<ActualTableName,List<Column>> refreshColumns(DatabaseMetaData databaseMetaData, String catalog, String schema) throws IOException, SQLException {
        ResultSet columnsResultSet = databaseMetaData.getColumns(catalog, schema, "%", "%");
        List<Column> columns = columnListProcessor.handle(columnsResultSet);
        Map<ActualTableName, List<Column>> collect = columns.stream().collect(Collectors.groupingBy(Column::getActualTableName));
        columnsResultSet.close();
        return collect;
    }

    protected Map<ActualTableName,List<Index>> refreshIndexs(DatabaseMetaData databaseMetaData, String catalog, String schema,List<Table> tables) throws IOException, SQLException {
        Map<ActualTableName,List<Index>> indexMap = new HashMap<>();
        for (Table table : tables) {
            String tableName = table.getActualTableName().getTableName();
            ResultSet columnsResultSet = databaseMetaData.getIndexInfo(catalog, schema, tableName, false,true);
            List<Index> indices = indexListProcessor.handle(columnsResultSet);
            indexMap.put(table.getActualTableName(),indices);
            columnsResultSet.close();
        }
        return indexMap;
    }

    // 为什么 getPrimaryKeys(catalog, schema,"%") 不行
    protected Map<ActualTableName,List<PrimaryKey>> refreshPrimaryKeys(DatabaseMetaData databaseMetaData, String catalog, String schema, List<Table> tables) throws IOException, SQLException {
        Map<ActualTableName,List<PrimaryKey>> primaryKeyMap = new HashMap<>();
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            String tableName = table.getActualTableName().getTableName();
            ResultSet columnsResultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableName);
            List<PrimaryKey> indices = primaryKeyListProcessor.handle(columnsResultSet);
            primaryKeyMap.put(table.getActualTableName(),indices);
            columnsResultSet.close();
        }

        return primaryKeyMap;
    }

    // 数据处理器
    static SchemaListProcessor schemaListProcessor = new SchemaListProcessor();
    static TableListProcessor tableListProcessor = new TableListProcessor();
    static ColumnListProcessor columnListProcessor = new ColumnListProcessor();
    static IndexListProcessor indexListProcessor = new IndexListProcessor();
    static PrimaryKeyListProcessor primaryKeyListProcessor = new PrimaryKeyListProcessor();
    public static DynamicQueryProcessor dynamicQueryProcessor = new DynamicQueryProcessor();

    @Override
    public void onApplicationEvent(UpdateConnectEvent updateConnectEvent) {
        UpdateConnectEvent.ConnectInfo connectInfo = (UpdateConnectEvent.ConnectInfo) updateConnectEvent.getSource();
        if (connectInfo.getClazz() == DatabaseConnectParam.class) {
            String connName = connectInfo.getConnName();
            dataSourceMap.remove(connName);
            tableMetaDataMap.remove(connName);
            log.info("[{}]模块[{}]配置变更,将移除存储的元数据信息",module,connName);
        }
    }

    private static class SchemaListProcessor implements ResultSetHandler<List<Schema>>{
        @Override
        public List<Schema> handle(ResultSet rs) throws SQLException {
            List<Schema> schemaList = new ArrayList<>();
            while (rs.next()){
                String schema = rs.getString("TABLE_SCHEM");
                String catalog = rs.getString("TABLE_CATALOG");
                schemaList.add(new Schema(schema,catalog));
            }
            return schemaList;
        }
    }
    private static class TableListProcessor implements ResultSetHandler<List<Table>>{
        @Override
        public List<Table> handle(ResultSet rs) throws SQLException {
            List<Table> tables = new ArrayList<>();
            while (rs.next()){
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                String remarks = rs.getString("REMARKS");
//                String remarks = null;
//                try {
//                    // 解决注释字段乱码问题
//                    byte[] remarksBytes = rs.getBytes("REMARKS");
//                    if (remarksBytes != null) {
//                        remarks = new String(remarksBytes, "UTF-8");
//                    }
//                } catch (UnsupportedEncodingException e) {}
                ActualTableName actualTableName = new ActualTableName(catalog, schema, tableName);
                Table table = new Table(actualTableName, remarks);
                tables.add(table);
            }
            return tables;
        }
    }
    private static class ColumnListProcessor implements ResultSetHandler<List<Column>>{

        @Override
        public List<Column> handle(ResultSet rs) throws SQLException {
            List<Column> columns = new ArrayList<>();
            while (rs.next()){
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                ActualTableName actualTableName = new ActualTableName(catalog, schema, tableName);

                String columnName = rs.getString("COLUMN_NAME");
                int dataType = rs.getInt("DATA_TYPE");
                String typeName = rs.getString("TYPE_NAME");
                int columnSize = rs.getInt("COLUMN_SIZE");
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");
                int nullableInt = rs.getInt("NULLABLE");
                String remarks = rs.getString("REMARKS");
//                String remarks = null;
//                try {
//                    byte[] remarksBytes = rs.getBytes("REMARKS");
//                    if (remarksBytes != null) {
//                        remarks = new String(remarksBytes, "UTF-8");
//                    }
//                } catch (UnsupportedEncodingException e) {}
                String autoIncrement = rs.getString("IS_AUTOINCREMENT");

                boolean nullable = nullableInt == 1 ? true: false;
                boolean isAutoIncrement = "YES".equals(autoIncrement) ? true : false;
                Column column = new Column(actualTableName, columnName, dataType, typeName, columnSize, decimalDigits, nullable, remarks, isAutoIncrement);
                columns.add(column);
            }
            return columns;
        }
    }
    private static class IndexListProcessor implements ResultSetHandler<List<Index>>{

        @Override
        public List<Index> handle(ResultSet rs) throws SQLException {
            List<Index> indices = new ArrayList<>();
            while (rs.next()){
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                ActualTableName actualTableName = new ActualTableName(catalog, schema, tableName);

                boolean nonUnique = rs.getBoolean("NON_UNIQUE");
                String indexName = rs.getString("INDEX_NAME");
                short type = rs.getShort("TYPE");
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");
                String columnName = rs.getString("COLUMN_NAME");
                Index index = new Index(actualTableName, !nonUnique, indexName, type, ordinalPosition, columnName);
                indices.add(index);
            }
            return indices;
        }
    }
    private static class PrimaryKeyListProcessor implements ResultSetHandler<List<PrimaryKey>>{

        @Override
        public List<PrimaryKey> handle(ResultSet rs) throws SQLException {
            List<PrimaryKey> primaryKeys = new ArrayList<>();
            while (rs.next()){
                String catalog = rs.getString("TABLE_CAT");
                String schema = rs.getString("TABLE_SCHEM");
                String tableName = rs.getString("TABLE_NAME");
                ActualTableName actualTableName = new ActualTableName(catalog, schema, tableName);

                String columnName = rs.getString("COLUMN_NAME");
                short keySeq = rs.getShort("KEY_SEQ");
                String pkName = rs.getString("PK_NAME");

                PrimaryKey primaryKey = new PrimaryKey(actualTableName, columnName, keySeq, pkName);
                primaryKeys.add(primaryKey);
            }
            return primaryKeys;
        }
    }

    public static class DynamicQueryProcessor implements ResultSetHandler<DynamicQueryDto>{
        @Override
        public DynamicQueryDto handle(ResultSet resultSet) throws SQLException {
            DynamicQueryDto dynamicQueryDto = new DynamicQueryDto();

            //添加头部
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnLabel = metaData.getColumnLabel(i);
                int columnType = metaData.getColumnType(i);
                String columnTypeName = metaData.getColumnTypeName(i);
                dynamicQueryDto.addHeader(new DynamicQueryDto.Header(columnLabel,columnType,columnTypeName));
            }

            // 添加数据
            while (resultSet.next()) {
                Map<String,Object> row = new LinkedHashMap();
                for (int i = 1; i <= columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i);
                    Object columnData = resultSet.getObject(i);
                    row.put(columnLabel,columnData);
                }

                dynamicQueryDto.addRow(row);
            }

            return dynamicQueryDto;
        }
    }

    /**
     * 获取 jdbc 连接
     * @param connName
     * @return
     */
    public Connection connection(String connName) throws IOException, SQLException {
        DataSource dataSource = dataSource(connName);
        Connection connection = dataSource.getConnection();
        return connection;
    }

    DatabaseMetaData databaseMetaData(String connName) throws SQLException, IOException {
        DataSource dataSource = dataSource(connName);

        Connection connection = dataSource.getConnection();
        return connection.getMetaData();
    }

    /**
     * 获取默认数据源和指定数据库的数据源
     * @param connName
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public DataSource dataSource(String connName) throws IOException, SQLException {return dataSource(connName,null);}
    public DataSource dataSource(String connName,String databaseName) throws SQLException, IOException {
        if (connName.contains("@")){
            String[] split = StringUtils.split(connName, '@');
            connName = split[0];
            databaseName = split[1];
        }

        DatabaseConnectParam databaseConnectParam = (DatabaseConnectParam) connectService.readConnParams(JdbcService.module, connName);

        String database = databaseConnectParam.getDatabase();
        if (StringUtils.isBlank(databaseName)) {
            databaseName = database;
        }

        DataSource dataSource = dataSourceMap.get(connName+"@"+databaseName);

        if (dataSource == null){
            String dbType = databaseConnectParam.getDbType();
            ConnectParam connectParam = databaseConnectParam.getConnectParam();
            AuthParam authParam = databaseConnectParam.getAuthParam();

            switch (dbType){
                case DatabaseConnectParam.dbType_mysql:
                    ExMysqlDataSource mysqlDataSource = new ExMysqlDataSource();
                    mysqlDataSource.setServerName(connectParam.getHost());
                    mysqlDataSource.setPort(connectParam.getPort());
                    mysqlDataSource.setDatabaseName(databaseName);
                    mysqlDataSource.setUser(authParam.getUsername());
                    mysqlDataSource.setPassword(authParam.getPassword());
                    dataSource = mysqlDataSource;
                    break;
                case DatabaseConnectParam.dbType_postgresql:
                    PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
                    pgSimpleDataSource.setServerName(connectParam.getHost());
                    pgSimpleDataSource.setPortNumber(connectParam.getPort());
                    pgSimpleDataSource.setDatabaseName(databaseName);
                    pgSimpleDataSource.setUser(authParam.getUsername());
                    pgSimpleDataSource.setPassword(authParam.getPassword());
                    dataSource = pgSimpleDataSource;
                    break;
                case DatabaseConnectParam.dbType_oracle:
                    OracleDataSource oracleDataSource = new OracleDataSource();
                    oracleDataSource.setServerName(connectParam.getHost());
                    oracleDataSource.setPortNumber(connectParam.getPort());
                    oracleDataSource.setDatabaseName(databaseName);
                    oracleDataSource.setUser(authParam.getUsername());
                    oracleDataSource.setPassword(authParam.getPassword());
                    oracleDataSource.setDriverType("thin");
                    oracleDataSource.setURL("jdbc:oracle:thin:@"+connectParam.getHost()+":"+connectParam.getPort()+":"+ database);
                    dataSource = oracleDataSource;
            }

            dataSourceMap.put(connName+"@"+databaseName,dataSource);
        }
        return dataSource;
    }

    @PostConstruct
    public void register(){
        pluginManager.register(PluginDto.builder()
                .module(module).name("metadata").author("sanri").envs("default")
                .logo("mysql.jpg")
                .desc("数据库元数据,支持mysql,postgresql,oracle 和支持元数据据的数据库,可扩展功能数据库文档,代码生成")
                .help("数据表处理工具.md")
                .build());
    }
}
