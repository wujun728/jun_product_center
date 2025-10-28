AMIS_JSON={
    "type": "page",
    "definitions":{
        "formTabs":{
            "tabs":[{
                "title":"基本信息",
                "body": [
                    {
                        "type": "input-text",
                        "name": "code",
                        "label": "编号",
                        "required": true
                    },
                    {
                        "type": "input-text",
                        "name": "name",
                        "label": "名称",
                        "required": true
                    },
                    {
                        "type": "input-text",
                        "name": "tableName",
                        "label": "表名",
                        "required": false
                    },
                    {
                        "type": "input-text",
                        "name": "initApi",
                        "placeholder":"默认配置:post:/admin/common/{formCode}/get?id=${id}",
                        "label": "初始化接口"
                    },
                    {
                        "type": "input-text",
                        "name": "api",
                        "placeholder":"默认配置:post:/admin/common/{formCode}/saveOrUpdate",
                        "label": "保存接口"
                    },
                    {
                        "type": "select",
                        "name": "size",
                        "label": "窗口大小",
                        "options":[{
                            "label":"较小",
                            "value":"sm"
                        },{
                            "label":"标准",
                            "value":"default"
                        },{
                            "label":"较大",
                            "value":"lg"
                        },{
                            "label":"很大",
                            "value":"xl"
                        },{
                            "label":"全屏",
                            "value":"full"
                        }]
                    },
                    {
                        "type": "input-number",
                        "name": "fieldWidth",
                        "label": "字段宽度"
                    },
                    {
                        "type":"select",
                        "name": "disabled",
                        "label": "是否只读",
                        "required": false,
                        "options":[{
                            "label":"YES",
                            "value":"YES"
                        },{
                            "label":"NO",
                            "value":"NO"
                        }]
                    },
                    {
                        "type": "editor",
                        "name": "initSql",
                        "label": "初始化sql",
                        "language": "sql",
                        "required": false
                    },
                    {
                        "type": "textarea",
                        "name": "beforeApi",
                        "label": "前置接口",
                        "required": false
                    },
                    {
                        "type": "textarea",
                        "name": "afterApi",
                        "label": "后置接口",
                        "required": false
                    }
                ]
            },{
                title:"表单字段",
                body:[
                    {
                        "type":"button",
                        "label":"刷新",
                        "actionType": "ajax",
                        "disabledOn":"tableName==null || tableName==''",
                        "api": "post:/admin/form/formFields",
                        //"reload":"resultFields?resultFields=${resultFields}"
                    },{
                        "type": "input-table",
                        "label":"查询结果",
                        "name": "formFields",
                        "addable": true,
                        "removable": true,
                        "needConfirm":false,
                        "draggable": true,
                        //"copyable": true,
                        "editable": true,
                        "required": true,
                        "headerToolbar": [
                            "filter-toggler"
                        ],
                        "columns": [
                            {
                                "type":"input-text",
                                "name": "field",
                                "label": "字段",
                                "required": true,
                                "fixed": "left"
                                //"disabled":true
                            },
                            {
                                "type":"input-text",
                                "name": "label",
                                "label": "名称",
                                "required": true,
                                "fixed": "left"
                            },
                            {
                                "type":"input-number",
                                "name": "width",
                                "label": "宽度",
                                "placeholder":"宽度",
                                "required": false
                            },
                            {
                                "type":"select",
                                "name": "type",
                                "label": "字段类型",
                                "required": true,
                                "placeholder":"字段类型",
                                "options":[{
                                    "label":"字符串",
                                    "value":"string"
                                },{
                                    "label":"长文本",
                                    "value":"long-string"
                                },{
                                    "label":"超长文本",
                                    "value":"big-string"
                                },{
                                    "label":"SQL",
                                    "value":"sql"
                                },{
                                    "label":"js脚本",
                                    "value":"js"
                                },{
                                    "label":"文章",
                                    "value":"article"
                                },{
                                    "label":"短整数",
                                    "value":"int"
                                },{
                                    "label":"长整数",
                                    "value":"long"
                                },{
                                    "label":"小数",
                                    "value":"double"
                                },{
                                    "label":"日期",
                                    "value":"date"
                                },{
                                    "label":"数据字典",
                                    "value":"dic"
                                },{
                                    "label":"选择器",
                                    "value":"selector"
                                }]
                            },
                            {
                                "type":"input-text",
                                "name": "format",
                                "placeholder":"格式化",
                                "label": "格式化"
                            },
                            {
                                "type":"select",
                                "source":"/options/componentType",
                                "searchable":true,
                                "clearable":true,
                                "placeholder":"组件类型",
                                "name": "componentType",
                                "label": "组件类型"
                            },
                            {
                                "type":"select",
                                "name": "hidden",
                                "label": "是否隐藏",
                                "placeholder":"是否隐藏",
                                "options":[{
                                    "label":"YES",
                                    "value":"YES"
                                },{
                                    "label":"NO",
                                    "value":"NO"
                                }]
                            },
                            {
                                "type":"select",
                                "name": "multi",
                                "label": "是否多选",
                                "placeholder":"是否多选",
                                "options":[{
                                    "label":"YES",
                                    "value":"YES"
                                },{
                                    "label":"NO",
                                    "value":"NO"
                                }]
                            },
                            {
                                "type":"select",
                                "name": "must",
                                "label": "是否必填",
                                "placeholder":"是否必填",
                                "options":[{
                                    "label":"YES",
                                    "value":"YES"
                                },{
                                    "label":"NO",
                                    "value":"NO"
                                }]
                            },
                            {
                                "type":"select",
                                "name": "disabled",
                                "label": "是否只读",
                                "placeholder":"是否只读",
                                "required": false,
                                "options":[{
                                    "label":"YES",
                                    "value":"YES"
                                },{
                                    "label":"NO",
                                    "value":"NO"
                                }]
                            },
                            {
                                "type":"input-text",
                                "name": "value",
                                "placeholder":"默认值",
                                "label": "默认值",
                            },
                            {
                                "type":"input-text",
                                "name": "labelRemark",
                                "placeholder":"标签提示",
                                "label": "标签提示",
                            },
                            {
                                "type":"select",
                                "name": "checkRepeatType",
                                "label": "校验重复类型",
                                "required": false,
                                "placeholder":"校验重复类型",
                                "source":"/options/checkRepeatType",
                                "clearable":true
                            },
                            {
                                "type":"input-text",
                                "name": "checkRepeatConfig",
                                "placeholder":"校验重复配置",
                                "label": "校验重复配置"
                            },
                            {
                                "type":"input-text",
                                "name": "checkRepeatTip",
                                "placeholder":"校验重复提示",
                                "label": "校验重复提示"
                            },
                            {
                                "type":"input-text",
                                "name": "validations",
                                "placeholder":"表单项校验,参考amis官方文档",
                                "size":"lg",
                                "label": "表单项校验"
                            },
                            {
                                "type":"textarea",
                                "name": "optionSql",
                                "label": "选项sql"
                            }
                        ]
                    }
                ]
            },{
                title:"关联子表",
                body:[{
                    "type": "input-table",
                    "label":"关联子表",
                    "name": "formRefs",
                    "addable": true,
                    "removable": true,
                    "needConfirm":false,
                    "draggable": true,
                    //"copyable": true,
                    "editable": true,
                    "required": false,
                    "headerToolbar": [
                        "filter-toggler"
                    ],
                    "columns": [
                        {
                            "type":"input-text",
                            "name": "refPageCode",
                            "label": "关联页面编号",
                            "required": true,
                            //"disabled":true
                        },
                        {
                            "type":"input-text",
                            "name": "refField",
                            "label": "关联字段",
                            "required": true
                        }
                    ]
                }
                ]
            },{
                title:"表单按钮",
                body:[{
                    "type": "input-table",
                    "name": "formButtons",
                    "addable": true,
                    "removable": true,
                    "needConfirm":false,
                    "draggable": true,
                    "strictMode":true,
                    //"copyable": true,
                    //"editable": true,
                    "label":"表单按钮",
                    "required": false,
                    "columns": [
                        {
                            "type":"input-text",
                            "name": "label",
                            "label": "名称",
                            "required": true
                        },
                        {
                            "type":"input-text",
                            "name": "code",
                            "label": "编号",
                            "required": false
                        },
                        {
                            "type":"select",
                            "name": "optionType",
                            "label": "操作类型",
                            "required": true,
                            "options":[{
                                "label":"弹出表单",
                                "value":"form"
                            },{
                                "label":"弹出Iframe",
                                "value":"iframe"
                            },{
                                "label":"弹出页面",
                                "value":"page"
                            },{
                                "label":"请求",
                                "value":"ajax"
                            },{
                                "label":"打开新窗口",
                                "value":"openNew"
                            }]
                        },
                        {
                            "type":"input-text",
                            "name": "optionValue",
                            "label": "操作配置",
                            "required": false,
                            "size": "lg"
                        },
                        {
                            "type":"select",
                            "name": "level",
                            "label": "样式",
                            "required": true,
                            "options":[{
                                "label":"默认",
                                "value":"default"
                            },{
                                "label":"链接",
                                "value":"link"
                            },{
                                "label":"主要",
                                "value":"primary"
                            },{
                                "label":"次要",
                                "value":"secondary"
                            },{
                                "label":"信息",
                                "value":"info"
                            },{
                                "label":"成功",
                                "value":"success"
                            },{
                                "label":"警告",
                                "value":"warning"
                            },{
                                "label":"危险",
                                "value":"danger"
                            },{
                                "label":"高亮",
                                "value":"light"
                            },{
                                "label":"黑暗",
                                "value":"dark"
                            }]
                        },
                        {
                            "type":"select",
                            "name": "close",
                            "label": "关闭弹出层",
                            "required": false,
                            "options":[{
                                "label":"YES",
                                "value":"YES"
                            },{
                                "label":"NO",
                                "value":"NO"
                            }]
                        },
                        {
                            "type":"input-text",
                            "name": "confirmText",
                            "label": "二次确认提示"
                        },
                        {
                            "type":"input-text",
                            "name": "jsRule",
                            "label": "规则",
                            "size": "lg"
                        }
                    ]
                }]
            }]
        },
        "form":{
            "title": "${IF(ISEMPTY(name),'新增',CONCATENATE('编辑-',name))}",
            "size": "full",
            "body": {
                "type": "form",
                "initApi": "/admin/form/get?id=${id}",
                "api": "post:/admin/form/save",
                "$ref":"formTabs"
            }
        },
        "copyForm":{
            "title": "复制-${tableName}",
            "size": "xl",
            "body": {
                "type": "form",
                "initApi": "/admin/form/copyForm?id=${id}",
                "api": "post:/admin/form/save",
                "$ref":"formTabs"
            }
        },
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
        "filterTogglable": true,
        "headerToolbar": [
            "filter-toggler",
            {
                "type": "button",
                "actionType": "dialog",
                "label": "新增",
                "size":"sm",
                "icon": "fa fa-plus pull-left",
                "primary": true,
                "dialog":{
                    "$ref":"form"
                }
            },{
                "label": "新增",
                "type": "button",
                "actionType": "dialog",
                "dialog": {
                    "title":"新增",
                    "size":"full",
                    "body":{
                        "type":"iframe",
                        "src":"/admin/page/formEdit.html",
                        "height":"calc( 100% - 5px )"
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
            "title": "条件搜索<button type='button' onclick='window.location.reload()' style='float:right;position:relative;top:-8px;' class='cxd-Button cxd-Button--sm cxd-Button--default is-active'>刷新页面</button>",
            "submitText": "",
            "body": [
                {
                    "type": "input-text",
                    "name": "code",
                    "label":"编号"
                },
                {
                    "type": "input-text",
                    "name": "name",
                    "label":"名称"
                },{
                    "label": "搜索",
                    "type": "submit"
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
                            "$ref":"form"
                        }
                    },{
                        "label": "复制",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "$ref":"copyForm"
                        }
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
                    },{
                        "label": "字段配置",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "title":"字段配置-${name}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/fieldConfig.html?id=${id}",
                                "height":"calc( 100% - 5px )"
                            },
                            "actions":[]
                        }
                    },{
                        "label": "编辑",
                        "type": "button",
                        "actionType": "dialog",
                        "dialog": {
                            "title":"编辑-${name}",
                            "size":"full",
                            "body":{
                                "type":"iframe",
                                "src":"/admin/page/formEdit.html?id=${id}",
                                "height":"calc( 100% - 5px )"
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
                                "height":"calc( 100% - 5px )"
                            },
                            "actions":[]
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
