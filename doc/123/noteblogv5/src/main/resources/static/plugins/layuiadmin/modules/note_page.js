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
    var noteTable = table.render({
        elem: '#note-table'
        , height: 'full'
        , url: "/management/note/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , where: {
            order: 'desc'
            , sort: 'post'
        }
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , initSort: {
            field: 'post'
            , type: 'desc'
        }
        , cols: [[
            {type: 'numbers'}
            , {
                field: 'title', title: '笔记标题', sort: true, templet: function (d) {
                    return '<a href="/note?t=' + encodeURI(d.title) + '" class="layui-blue" target="_blank">' + d.title + '</a>';
                }
            }
            , {field: 'post', title: '发布时间', sort: true, width: 200}
            , {title: '状态', width: 100, align: 'center', toolbar: '#showTpl'}
            , {title: '置顶', field: 'top', width: 100, templet: '#topTpl'}
            , {title: '操作', width: 200, align: 'center', toolbar: '#noteBar'}
        ]]
        , page: true
    });

    var $ = layui.$, active = {
        reload: function () {
            var noteSearch = $('#title-search');
            var contentSearch = $('#content-search');
            //执行重载
            table.reload('note-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: noteSearch.val(),
                    clearContent: contentSearch.val()
                }
            });
        }
    };

    $('#LAY-note-list').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

//监听工具条
    table.on('tool(note)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                obj.del();
                NBV5.post("/management/note/delete", {id: data.id});
                layer.close(index);
            });
        }
    });

    table.on('sort(note)', function (obj) {
        noteTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    form.on('switch(show)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/note/update/field", {
            id: objId,
            field: "show",
            status: obj.elem.checked
        });
    });


    form.on('switch(top)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/note/update/field    ", {
            id: objId,
            field: "top",
            status: obj.elem.checked
        });
    });

    exports('note_page', {});

});







