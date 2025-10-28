
AMIS_JSON={
    "type": "page",
    "definitions":{
        "generateJavaCode":{
            "title": "生成表-${tableName}",
            "size": "full",
            "body": {
                "type": "form",
                "initApi": "/tableInfo/generateJavaCode?tableName=${id}",
                "actions":[],
                "tabs":[{
                    "title":"实体",
                    "body": [
                        {
                            "type": "editor",
                            "name": "model",
                            "size":"xxl",
                            "language": "java",
                            "disabled": false,
                            "allowFullscreen":true
                        }
                    ]
                },{
                    "title":"查询sql",
                    "body": [
                        {
                            "type": "editor",
                            "name": "querySql",
                            "size":"xxl",
                            "language": "sql",
                            "disabled": false,
                            "allowFullscreen":true
                        }
                    ]
                },{
                    "title":"导出sql",
                    "body": [
                        {
                            "type": "editor",
                            "name": "exportSql",
                            "size":"xxl",
                            "language": "sql",
                            "disabled": false,
                            "allowFullscreen":true
                        }
                    ]
                }]
            }
        },
        "jsonForm":{
            "title": "json-${tableName}",
            "size": "full",
            "body": {
                "type": "form",
                "initApi": "/tableInfo/getJson?tableName=${tableName}",
                "api": "/tableInfo/saveJson",
                "actions":[],
                "body": [
                    {
                        "type": "editor",
                        "name": "json",
                        "size":"xxl",
                        "language": "json",
                        "disabled": false,
                        "allowFullscreen":true
                    }
                ]
            }
        }
    },
    "body": {
        "type": "crud",
        "api": "post:/tableInfo/queryTable",
        "syncLocation": false,
        "affixHeader":true,
        "autoFillHeight": true,
        "headerToolbar": [
            "filter-toggler",
            {
                "label": "新建",
                "primary":true,
                "type": "button",
                "actionType": "dialog",
                "dialog": {
                    "title":"新建",
                    "size":"full",
                    "body":{
                        "type":"iframe",
                        "src":"/admin/page/tableEdit.html",
                        "height":"calc( 100% - 10px )"
                    },
                    "actions":[]
                }
            },
            {
                "type": "button",
                "actionType": "dialog",
                "label": "JSON新增",
                "size":"sm",
                "icon": "fa fa-plus pull-left",
                "primary": true,
                "dialog":{
                    "$ref":"jsonForm"
                }
            }
        ],
        "filter": {
            "title":["<span style='line-height:30px;'>数据库表</span>",
                ,{
                    "type":"button",
                    "label":"刷新页面",
                    "actionType": "url",
                    "url":"",
                    "blank":false,
                    "level":"secondary",
                    "className":"m-l float-right relative jqp-top-btn"
                }],
            "submitText": "",
            "mode": "horizontal",
            "body": [
                {
                    "type":"grid",
                    "columns":[{
                        "type": "input-text",
                        "name": "tableName",
                        "label":"表名",
                        "placeholder": "表名"
                    },
                    {
                        "type": "input-text",
                        "name": "tableComment",
                        "label":"注释",
                        "placeholder": "注释"
                    },{
                        "label": "查询",
                        "primary":true,
                        "type": "submit"
                    }]
                }
            ]
        },
        "columns": [
            {
                "name": "tableName",
                "label": "表名"
            },
            {
                "name": "tableComment",
                "label": "注释"
            },
            {
                "name": "tableRows",
                "label": "大概数据行数"
            },
            {
                "type": "operation",
                "label": "操作",
                "buttons": [
                    {
                        "label": "编辑",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "title":"编辑-${tableName}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/tableEdit.html?tableName=${tableName}",
                                "height":"calc( 100% - 10px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "复制",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "title":"复制-${tableName}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/tableEdit.html?tableName=${tableName}&type=copy",
                                "height":"calc( 100% - 10px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "java代码",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "$ref":"generateJavaCode"
                        }
                    },{
                        "type": "button",
                        "level":"primary",
                        "actionType": "ajax",
                        "label": "一键生成",
                        //"disabledOn": "tableName=='test'",
                        "confirmText": "确定生成${oldTableName},页面,表单,菜单?存在会自动跳过",
                        "api": "/tableInfo/oneTouch?tableName=${oldTableName}"
                    },{
                        "type": "button",
                        "level":"danger",
                        "actionType": "ajax",
                        "label": "删除",
                        //"disabledOn": "tableName=='test'",
                        "confirmText": "您确认要删除${oldTableName}?",
                        "api": "/tableInfo/dropTable?tableName=${oldTableName}"
                    },{
                        "label": "编辑JSON",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "$ref":"jsonForm"
                        }
                    }
                ]
            }
            /*,{
                "type":"each",
                "label":"循环",
                "name":"buttons",
                "className":"antd-OperationField",
                "items":{
                    "type":"button",
                    "size":"sm",
                    "label":"${name}",
                    "actionType": "ajax",
                    "api":"/xxxx.xxx"
                }
            }
            */
        ]
    }
}
