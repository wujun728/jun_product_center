package com.sanri.tools.modules.database.controller;

import com.sanri.tools.modules.core.service.file.ConnectService;
import com.sanri.tools.modules.database.dtos.ExtendTableMetaData;
import com.sanri.tools.modules.database.dtos.TableMark;
import com.sanri.tools.modules.database.dtos.meta.*;
import com.sanri.tools.modules.database.service.JdbcService;
import com.sanri.tools.modules.database.service.TableMarkService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/db/metadata")
@Validated
public class MetadataController {
    @Autowired
    private JdbcService jdbcService;
    @Autowired
    private ConnectService connectService;
    @Autowired
    private TableMarkService tableMarkService;

    /**
     *
     * 作者:sanri <br/>
     * 时间:2017-4-21上午11:23:04<br/>
     * 功能:查询所有的连接 <br/>
     * 入参: <br/>
     */
    @GetMapping("/connections")
    public List<String> connections(){
        return connectService.names(JdbcService.module);
    }

    /**
     * 刷新连接获取所有的 catalogs 和 schema
     * @param connName
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping("/catalogs")
    public List<Catalog> catalogs(@NotNull String connName) throws IOException, SQLException {
        return jdbcService.refreshConnection(connName);
    }

    /**
     *
     * 作者:sanri <br/>
     * 时间:2017-4-21上午11:23:33<br/>
     * 功能: 首次查询连接所有的表,如果已经存在将会在缓存中获取<br/>
     * @return
     */
    @GetMapping("/tables")
    public Collection<TableMetaData> tables(@NotNull String connName, String catalog) throws SQLException, IOException {
        Collection<TableMetaData> tables = jdbcService.tables(connName, catalog);
        return tables;
    }

    /**
     * 刷新 catalog 或者刷新 schema
     * @param connName
     * @param catalog
     * @param schema
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping("/refreshCatalogOrSchema")
    public Collection<TableMetaData> refreshCatalogOrSchema(@NotNull String connName,String catalog,String schema) throws IOException, SQLException {
        return jdbcService.refreshCatalogOrSchema(connName,catalog,schema);
    }

    @GetMapping("/refreshTable")
    public TableMetaData refreshTable(@NotNull String connName, String catalog, String schema, @NotNull String tableName) throws IOException, SQLException {
        ActualTableName actualTableName = new ActualTableName(catalog,schema,tableName);
        return jdbcService.refreshTable(connName,actualTableName);
    }

    /**
     * 搜索表 , keyword 可以写成表达式的形式, 目前支持
     * table: column: tag:
     * 后面可以继续扩展操作符 , 像 everything 一样
     * @param connName
     * @param catalog
     * @param schemas
     * @param keyword
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @GetMapping("/searchTables")
    public List<ExtendTableMetaData> searchTables(@NotNull String connName, String catalog, String[] schemas, String keyword) throws IOException, SQLException {
        // 根据关键字进行过滤
        String searchSchema = "";
        if(StringUtils.isNotBlank(keyword) && keyword.contains(":")){
            String [] array = keyword.split(":",2);
            searchSchema = array[0];
            keyword = array[1];
        }
        Set<String> schemasSet = Arrays.stream(schemas).collect(Collectors.toSet());
        if (StringUtils.isNotBlank(keyword) && keyword.contains(".")){
            // 如果包含 . 的话, 前面是 schema , 后面是 keyword
            String [] array = keyword.split("\\.",2);
            String schema = array[0];
            schemasSet.add(schema);
            keyword = array[1];
        }

        List<TableMetaData> tableMetaDataList = null;
        if (StringUtils.isNotBlank(searchSchema) && "tag".equals(searchSchema)){
            tableMetaDataList = tableMarkService.searchTables(connName,catalog,schemasSet,keyword);
        }else {
            tableMetaDataList = jdbcService.searchTables(connName, catalog, schemasSet,searchSchema, keyword);
        }

        List<ExtendTableMetaData> extendTableMetaData = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(tableMetaDataList)){
            for (TableMetaData tableMetaData : tableMetaDataList) {
                ActualTableName actualTableName = tableMetaData.getActualTableName();
                TableMark tableMark = tableMarkService.getTableMark(connName, actualTableName);
                extendTableMetaData.add(new ExtendTableMetaData(tableMetaData,tableMark));
            }
        }

        return extendTableMetaData;
    }
}
