<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${table.comment}</title>
    <link href="../../component/pear/css/pear.css" rel="stylesheet"/>
</head>
<body class="pear-container">
<div class="layui-card">
    <div class="layui-card-body">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">关键字</label>
                <div class="layui-input-inline">
                    <input type="text" name="keyWord" placeholder="" class="layui-input">
                </div>
                <button class="pear-btn pear-btn-md pear-btn-primary" lay-submit lay-filter="query">
                    <i class="layui-icon layui-icon-search"></i>
                    查询
                </button>
                <button type="reset" class="pear-btn pear-btn-md">
                    <i class="layui-icon layui-icon-refresh"></i>
                    重置
                </button>
            </div>
        </form>
    </div>
</div>
<div class="layui-card">
    <div class="layui-card-body">
        <table id="table" lay-filter="table-filter"></table>
    </div>
</div>

</body>

<script type="text/html" id="record-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-sm" lay-event="edit">
        <i class="layui-icon layui-icon-edit"></i>
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-sm" lay-event="remove">
        <i class="layui-icon layui-icon-delete"></i>
    </button>
</script>

<script type="text/html" id="table-toolbar">
    <button class="pear-btn pear-btn-primary pear-btn-md" lay-event="add">
        <i class="layui-icon layui-icon-add-1"></i>
        新增
    </button>
    <button class="pear-btn pear-btn-danger pear-btn-md" lay-event="batchRemove">
        <i class="layui-icon layui-icon-delete"></i>
        删除
    </button>
</script>

<script src="../../component/layui/layui.js"></script>
<script src="../../component/pear/pear.js"></script>
<script>
    layui.use(['easyAdmin'], function () {
        let easyAdmin = layui.easyAdmin;

        let cols = [
                [{
                    type: 'checkbox'
                },
                    <#list table.fields as field>
                    <#if !field.keyFlag && field.propertyName != 'enable'>
                    {
                        title: '${field.comment}',
                        field: '${field.propertyName}',
                        align: 'center',
                        width: 120
                    },
                    </#if>
                    <#if field.propertyName == 'enable'>
                    {
                        title: '字典状态',
                        field: 'enable',
                        align: 'center',
                        templet: function (d) {
                            if (d.enable) {
                                return '<input type="checkbox" lay-skin="switch" lay-text="启用|禁用" checked >';
                            }
                            return '<input type="checkbox" lay-skin="switch" lay-text="启用|禁用">';
                        }
                    },
                    </#if>
                    </#list>
                    {
                        title: '操作',
                        toolbar: '#record-toolbar',
                        align: 'center',
                        width: 180
                    }
                ]
            ]
        ;


        let module = "${cfg.easyMain}";
        let uri = "/${cfg.easyModule}/" + module;
        easyAdmin.tableRender({
            url: uri,
            cols: cols
        });

        easyAdmin.TableTool(uri, module);
        easyAdmin.TableToolBar(uri, module);
        easyAdmin.FormQuery();
    })
</script>
</html>
