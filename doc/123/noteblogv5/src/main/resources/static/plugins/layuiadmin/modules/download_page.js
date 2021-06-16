/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['form', 'table', 'element'], function (exports) {
    var table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var downloadTable = table.render({
        elem: '#download-table'
        , height: 'full'
        , url: "/management/download/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , initSort: {
            field: 'createTime'
            , type: 'desc'
        }
        , cols: [[
            {type: 'numbers'}
            , {
                field: 'title', title: '标题', sort: true, templet: function (d) {
                    return '<a href="' + d.downloadUrl + '" class="layui-blue" target="_blank">' + d.title + '</a>';
                }
            }
            , {field: 'createTime', title: '发布时间', sort: true, width: 200}
            , {title: '置顶', field: 'top', width: 100, templet: '#topTpl'}
            , {title: '操作', width: 200, align: 'center', toolbar: '#downloadBar'}
        ]]
        , page: true
    });

    var $ = layui.$, active = {
        reload: function () {
            var downloadSearch = $('#title-search');
            //执行重载
            table.reload('download-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: downloadSearch.val(),
                }
            });
        }
    };

    $('#LAY-download-list').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

//监听工具条
    table.on('tool(download)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                obj.del();
                NBV5.post("/management/download/delete", {id: data.id});
                layer.close(index);
            });
        }
    });

    table.on('sort(download)', function (obj) {
        downloadTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


    form.on('switch(top)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/download/update/top", {
            id: objId,
            status: obj.elem.checked
        });
    });

    exports('download_page', {});

});







