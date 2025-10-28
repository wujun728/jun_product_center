AMIS_JSON={
    "type": "page",
    "definitions":{
        "jsonForm":{
            "title": "json-${name}",
            "size": "full",
            "body": {
                "type": "form",
                "initApi": "/admin/form/getJson?id=${id}",
                "api": "/admin/form/saveJson",
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
        "api": "post:/admin/form/query",
        "syncLocation": false,
        "filterTogglable": false,
        "affixHeader":true,
        "autoFillHeight": true,
        "headerToolbar": [
            "filter-toggler",{
                "label": "新增",
                "type": "button",
                "primary": true,
                "actionType": "dialog",
                "dialog": {
                    "title":"新增",
                    "size":"full",
                    "body":{
                        "type":"iframe",
                        "src":"/admin/page/formEdit.html",
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
            "title":["<span style='line-height:30px;'>表单管理</span>"
                /*,{
                    "type":"button",
                    "label":"刷新页面",
                    "actionType": "url",
                    "url":"",
                    "blank":false,
                    "level":"secondary",
                    "className":"m-l float-right relative jqp-top-btn"
                }*/],
            "submitText": "",
            "mode": "horizontal",
            "body": [
                {
                    "type":"grid",
                    "columns":[{
                        "type": "input-text",
                        "name": "code",
                        "label":"编号"
                    },
                    {
                        "type": "input-text",
                        "name": "name",
                        "label":"名称"
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
                "name": "code",
                "label": "编号"
            },
            {
                "name": "name",
                "label": "名称"
            },
            {
                "name": "tableName",
                "label": "表"
            },
            {
                "name": "js",
                "label": "js脚本"
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
                            "title":"编辑-${name}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/formEdit.html?id=${id}",
                                "height":"calc( 100% - 10px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "复制",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "title":"复制-${name}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/formEdit.html?id=${id}&type=copy",
                                "height":"calc( 100% - 10px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "预览",
                        "type": "button",
                        "actionType": "url",
                        "url":"/form/${code}"
                    },{
                        "label": "预览js",
                        "type": "button",
                        "actionType": "url",
                        "url":"/admin/form/js/${code}.js"
                    },{
                        "type": "button",
                        "level":"danger",
                        "actionType": "ajax",
                        "label": "删除",
                        "confirmText": "您确认要删除${name}?",
                        "api": "/admin/common/form/delete/${id}"
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
