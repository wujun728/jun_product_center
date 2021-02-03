/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['form', 'layer', 'table', 'element'], function (exports) {
    var table = layui.table
        , element = layui.element
        , layer = layui.layer
        , form = layui.form;
    element.render();


    var messageTable = table.render({
        elem: '#message-table'
        , url: '/management/message/list'
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
            , {field: 'clearComment', minWidth: 250, title: '评论内容', event: 'detail'}
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
        NBV5.ajax("/management/message/update",
            {id: this.value, enable: obj.elem.checked},
            function (json) {
                NBV5.okMsgHandle(json);
                layer.tips('状态：' + ((obj.elem.checked) ? "正常" : "隐藏"), obj.othis);
            });
    });

    //监听单元格事件
    table.on('tool(message)', function (obj) {
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

    table.on('sort(message)', function (obj) {
        messageTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    var $ = layui.$, active = {
        reload: function () {
            var comment = $("#message-search");
            var nickname = $("#nickname-search");
            //执行重载
            table.reload('message-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    clearComment: comment.val(),
                    nickname: nickname.val()
                }
            });
        }
    };

    $('#message-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    exports('message', {});
});







