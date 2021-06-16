/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(["form", "table", "element"], function (exports) {
    var $ = layui.$,
        table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var articleTable = table.render({
        elem: '#article-table'
        , height: 'full'
        , url: "/management/article/list"
        , cellMinWidth: 90
        , toolbar: true
        , defaultToolbar: ['filter']
        , limit: 10
        , size: 'lg'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , where: {
            order: 'desc'
            , sort: 'post'
        }
        , initSort: {
            field: 'post'
            , type: 'desc'
        }
        , cols: [[
            {type: 'numbers'}
            , {
                field: 'title', title: '标题', sort: true, minWidth: 250, templet: function (d) {
                    return '<a href="/article/' + d.id + '" class="layui-blue" target="_blank">' + d.title + '</a>';
                }
            }
            , {field: 'post', title: '发布时间', minWidth: 180, sort: true, hide: true}
            , {
                field: 'draft', title: '状态', width: 100, templet: function (d) {
                    return d.draft ? '<span class="layui-badge layui-bg-orange">未发布</span>' : '<span class="layui-badge layui-bg-blue">已发布</span>';
                }
            }
            , {field: 'views', title: '浏览数', width: 90, sort: true,}
            , {title: '评论', width: 100, align: 'center', toolbar: '#commentedTpl'}
            , {title: '打赏', width: 100, align: 'center', toolbar: '#appreciableTpl'}
            , {field: 'top', title: '置顶', width: 110, templet: '#topTpl'}
            , {title: '操作', width: 190, align: 'center', toolbar: '#articleBar', fixed: 'right'}
        ]]
        , page: true
    });
    var active = {
        reload: function () {
            var titleSearch = $('#article-title-search');
            //执行重载
            table.reload('article-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    title: titleSearch.val()
                }
            });
        }
    };

    $('button[data-type]').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

//监听工具条
    table.on('tool(article)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('删除不可恢复（包括文章的评论），确认删除吗？', function (index) {
                obj.del();
                NBV5.post("/management/article/delete", {id: data.id});
                layer.close(index);
            });
        }
    });

    table.on('sort(article)', function (obj) {
        articleTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    form.on('switch(appreciable)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/article/update/appreciable", {
            id: objId,
            status: obj.elem.checked
        });
    });

    form.on('switch(commented)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/article/update/commented", {
            id: objId,
            status: obj.elem.checked
        });
    });

    form.on('checkbox(top)', function (obj) {
        var objId = this.value;
        NBV5.post("/management/article/update/top", {
            id: objId,
            status: obj.elem.checked
        });
    });

    exports('article_page', {});
});