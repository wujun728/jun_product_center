
AMIS_JSON={
    "type": "page",
    "definitions":{
        "jsonForm":{
            "title": "json-${name}",
            "size": "full",
            "body": {
                "type": "form",
                "initApi": "/admin/page/getJson?id=${id}",
                "api": "/admin/page/saveJson",
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
        "api": "post:/admin/page/query",
        // "syncLocation": false,
        "filterTogglable": false,
        "affixHeader":true,
        "autoFillHeight": true,
        "headerToolbar": [
            "filter-toggler",
            {
                "label": "新增",
                "type": "button",
                "actionType": "dialog",
                "primary":true,
                "dialog": {
                    "title":"新增",
                    "size":"full",
                    "body":{
                        "type":"iframe",
                        "src":"/admin/page/pageEdit.html",
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
        // "filter": {
        //     "title":["<span style='line-height:30px;'>列表管理</span>"
        //         /*,{
        //             "type":"button",
        //             "label":"刷新页面",
        //             "actionType": "url",
        //             "url":"",
        //             "blank":false,
        //             "level":"secondary",
        //             "className":"m-l float-right relative jqp-top-btn"
        //         }*/],
        //     "submitText": "",
        //     "mode": "horizontal",
        //     "body": [
        //         {
        //             "type": "input-text",
        //             "name": "code",
        //             "label":"编号"
        //         },
        //         {
        //             "type": "input-text",
        //             "name": "name",
        //             "label":"名称"
        //         },{
        //             "label": "查询",
        //             "primary":true,
        //             "type": "submit"
        //         }
        //     ]
        // },
        "autoGenerateFilter":true,
        "columns": [
            {
                "name": "code",
                "label": "编号",
                "width":200,
                "searchable":true
            },
            {
                "name": "name",
                "label": "名称",
                "width":200
            },
            // {
            //     "name": "querySql",
            //     "label": "查询sql"
            // },
            {
                "name": "pageType",
                "label": "页面类型",
                "width":200
            },
            {
                "name": "orderBy",
                "label": "排序",
                "width":200
            },
            {
                "type": "operation",
                "label": "操作",
                "width":350,
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
                                "src":"/admin/page/pageEdit.html?id=${id}",
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
                                "src":"/admin/page/pageEdit.html?id=${id}&type=copy",
                                "height":"calc( 100% - 10px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "预览",
                        "type": "button",
                        "actionType": "url",
                        "url":"/crud/${code}"
                    },{
                        "label": "预览js",
                        "type": "button",
                        "actionType": "url",
                        "url":"/admin/page/js/${code}.js"
                    },{
                        "type": "button",
                        "level":"danger",
                        "actionType": "ajax",
                        "label": "删除",
                        "confirmText": "您确认要删除${name}?",
                        "api": "/admin/common/page/delete/${id}"
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
