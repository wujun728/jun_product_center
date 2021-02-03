/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['form', 'layer', 'table', 'element', 'tableSelect'], function (exports) {
    var table = layui.table
        , element = layui.element
        , layer = layui.layer
        , form = layui.form,
        tableSelect = layui.tableSelect;
    element.render();


    tableSelect.render({
        elem: '#articleId-search',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'title',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '文章标题搜索',	//搜索输入框的提示文字 默认关键词搜索
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            height: 'full'
            , url: "/management/article/list"
            , limit: 5
            , size: 'md'
            , request: {
                pageName: 'current' //页码的参数名称，默认：page
                , limitName: 'size' //每页数据量的参数名，默认：limit
            }
            , cols: [[
                {type: 'checkbox'}
                , {
                    field: 'title', title: '标题', sort: true, minWidth: 250, templet: function (d) {
                        return '<a href="/article/' + d.id + '" class="layui-blue" target="_blank">' + d.title + '</a>';
                    }
                }
            ]]
            , page: true
        },
        done: function (elem, data) {
            var articleList = data.data;
            var articleIds = [];
            for (var i = 0; i < articleList.length; i++) {
                articleIds.push(articleList[i].id);
            }
            $("#articleId-search").val(articleIds.join(","))
        }
    });


    var commentTable = table.render({
        elem: '#comment-table'
        , url: '/management/comment/list'
        , page: true
        , limit: 10
        , height: 'full'
        , method: 'get'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , cols: [[
            {field: 'nickname', title: '用户昵称'}
            , {
                field: 'articleId', title: '文章', width: 80, templet: function (d) {
                    return '<a href="/article/' + d.articleId + '" target="_blank" style="color: blue;">查看</a>';
                }
            }
            , {
                field: 'clearComment', minWidth: 250, title: '评论内容', event: 'detail'
            }
            , {
                field: 'post', title: '发布时间', sort: true, minWidth: 150, templet: function (d) {
                    return nbv5front.formatDate(d.post, 'yy/MM/dd HH:mm:ss');
                }
            }
            , {field: 'ipAddr', title: 'IP地址'}
            , {field: 'ipInfo', title: 'IP具体地址'}
            , {field: 'userAgent', title: '用户代理'}
            , {title: '状态', width: 95, align: 'center', toolbar: '#enableTpl', fixed: 'right'}
        ]]
    });


    form.on('switch(enable)', function (obj) {
        NBV5.ajax("/management/comment/update",
            {id: this.value, enable: obj.elem.checked},
            function (json) {
                NBV5.okMsgHandle(json);
                layer.tips('状态：' + ((obj.elem.checked) ? "正常" : "隐藏"), obj.othis);
            });
    });

    //监听单元格事件
    table.on('tool(comment)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.open({
                type: 1
                , offset: 'auto'
                , id: 'layerDemo' + data.id //防止重复弹出
                , content: '<div style="padding: 20px;">' + data.comment.replace(/<[^<>]+?>/g, '') + '</div>'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
            });
        }
    });

    table.on('sort(comment)', function (obj) {
        commentTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    var $ = layui.$, active = {
        reload: function () {
            var comment = $("#comment-search");
            var nickname = $("#nickname-search");
            var articleIdsInput = $("#articleId-search");
            var aIds = articleIdsInput.val().split(",");
            aIds = articleIdsInput.val() === '' || aIds.length === 0 ? null : aIds;
            //执行重载
            table.reload('comment-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    clearComment: comment.val(),
                    nickname: nickname.val(),
                    articleIds: aIds
                }
            });
        }
    };

    $('#comment-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    exports('comment', {});

});







