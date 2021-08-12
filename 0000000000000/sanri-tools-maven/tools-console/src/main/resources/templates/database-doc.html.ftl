<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="application/msword"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <style>

    </style>
</head>

<body>
<h1 style="text-align: center;">${connName}-${catalog}-${schema!}数据库设计文档</h1>
<h2>表汇总</h2>
<table width="100%">

    <thead style="background-color: #b9c9fe;font-weight: bold;">
    <tr>
        <td>名称</td>
        <td>备注</td>
    </tr>
    </thead>
    <tbody>
    <#list tables as item>
        <tr style="background-color: #e8edff;">
            <td>${item.table.actualTableName.tableName}</td>
            <td>${item.table.remark!}</td>
        </tr>
    </#list>
    </tbody>
</table>
<h2>表明细</h2>
<#list tables as item>
    <br>
    <h3>${item.table.remark!}(${item.table.actualTableName.tableName})</h3>
    <table   width="100%">
        <thead style="background-color: #b9c9fe;font-weight: bold;">
        <tr>
            <td>列名</td>
            <td>类型</td>
            <td>长度</td>
            <td>可否为空</td>
            <td>注释</td>
        </tr>
        </thead>
        <tbody>
        <#list item.columns as column>
            <tr style="background-color: #e8edff;">
                <td>${column.columnName}</td>
                <td>${column.typeName}</td>
                <td>${column.columnSize!}</td>
                <td>${column.nullable?c}</td>
                <td>${column.remark!}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</#list>

</body>
</html>
