## ${connName}-${catalog!}-${schema!} 数据库文档

<#list tables as table>

### ${table.actualTableName.tableName} ${table.remark!}
| 列名 | 类型 | 可为空 | 注释 |
| ---- | -----| ----|----|
<#list table.columns as column>
|${column.columnName}|${column.typeName}(${column.columnSize},${column.decimalDigits!})|${column.nullable?c}|${column.remark!}|
</#list>

</#list>