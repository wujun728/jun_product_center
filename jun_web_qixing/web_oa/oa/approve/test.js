
<script>
    layui.use(['layer', 'table', 'tableX', 'notice', 'xnUtil'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var table = layui.table;
        var tableX = layui.tableX;
        var admin = layui.admin;
        var form = layui.form;
        var notice = layui.notice;
        var xnUtil = layui.xnUtil;

        // 渲染字典下拉
        xnUtil.rendDictDropDown(null, 'status', 'sys_normal_disable', '请选择', null);

        /* 渲染表格 */
        var insTb = tableX.render({
            elem: '#dataTable',
            headers: {"Authorization": localStorage.getItem("Authorization")},
            parseData: function(res){
                return {
                    "code": res.code==200?0:res.code, //解析接口状态
                    "msg": res.msg, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.rows //解析数据列表
                };
            },
            request: {
                pageName: 'pageNum', // 页码的参数名称，默认：page
                limitName: 'pageSize' // 每页数据条数的参数名，默认：limit
            },
            url: /* getProjectUrl() + */ '/dev-api/system/post/list',
            page: true,
            toolbar: ['<p>',
                '<button lay-event="add" perm-show="post:add" class="layui-btn layui-btn-sm icon-btn"><i class="layui-icon">&#xe654;</i>新增</button>&nbsp;',
                '<button lay-event="delete" perm-show="post:delete" class="layui-btn layui-btn-sm layui-btn-danger icon-btn"><i class="layui-icon">&#xe640;</i>删除</button>',
                '</p>'].join(''),
            cellMinWidth: 100,
            cols: [
                [
                    {type: 'checkbox'},
                    {field: 'id', title: 'id', hide:true, sort: true},
                    {field: 'postId', title: '职位编号', sort: true},
                    {field: 'postCode', title: '职位编码', sort: true},
                    {field: 'postName', title: '职位名称', sort: true},
                    {field: 'postSort', title: '岗位排序', sort: true},
                    {field: 'status', title: '状态', sort: true, templet: function (d) {  return xnUtil.rendDataTableDict(d.status, 'sys_normal_disable'); } },
                    {field: 'createTime', title: '创建时间', sort: true},
                    // {field: 'remark', title: '备注', sort: true},
                    {title: '操作', toolbar: '#tableBar', align: 'center', width: 200, minWidth: 200}
                ]
            ],
            done: function(res, curr, count) {
                xnUtil.tableDone(insTb, res, curr, count);
            }
        });

        /* 表格搜索 */
        form.on('submit(tableSearch)', function (data) {
            insTb.reload({where: data.field, page: {curr: 1}});
            return false;
        });

        /* 表格工具条点击事件 */
        table.on('tool(dataTable)', function (obj) {
            /* 删除 */
            if (obj.event === 'delete') {
                layer.confirm('确定要操作该数据吗？', {
                    skin: 'layui-layer-admin',
                    shade: .1
                }, function () {
                    admin.req(getProjectUrl() + '/dev-api/system/post'+"/"+obj.data.postId, JSON.stringify([{'postId': obj.data.postId}]), function(res){
                        layer.msg(res.msg, {icon: 1, time: 1000}, function () {
                            insTb.reload();
                        });
                    }, 'delete');
                });
            }
            /* 编辑 */
            if (obj.event === 'edit') {
                showAddOrUpdateModel(obj.data);
            }
        });

        /* 表格头工具栏点击事件 */
        table.on('toolbar(dataTable)', function (obj) {
            if (obj.event === 'add') { // 添加
                showAddOrUpdateModel();
            }
            if (obj.event === 'delete') { // 删除
                var checkRows = table.checkStatus('dataTable');
                if (checkRows.data.length === 0) {
                    notice.msg('请选择要操作的数据', {icon: 2});
                    return;
                }
                layer.confirm('确定要操作该数据吗？', {
                    skin: 'layui-layer-admin',
                    shade: .1
                }, function () {
                    var ids = checkRows.data.map(function (d) {
                        return /*{ "postId":  */d.postId/* } */;
                    });
                    admin.req(getProjectUrl() + '/dev-api/system/post/'+ids, JSON.stringify(ids), function(res){
                        layer.msg(res.msg, {icon: 1, time: 1000}, function () {
                            insTb.reload();
                        });
                    }, 'DELETE');
                });
            }
        });

        // 显示表单弹窗
        function showAddOrUpdateModel(data) {
            var layIndex = admin.open({
                title: (data ? '修改' : '添加') + '职位',
                url: getProjectUrl() + '/system/post/form.html',
                data: { data: data },     // 传递数据到表单页面
                end: function () {
                    var layerData = admin.getLayerData(layIndex, 'formOk');
                    if (layerData) {  // 判断表单操作成功标识
                        insTb.reload();  // 成功刷新表格
                    }
                },
                success: function (layero, dIndex) {
                    // 弹窗超出范围不出现滚动条
                    //$(layero).children('.layui-layer-content').css('overflow', 'visible');
                    $(layero).find('[lay-submit]').focus();
                }
            });
        }
    });
</script>