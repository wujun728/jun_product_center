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


    var userTable = table.render({
        elem: '#user-table'
        , url: '/management/user/list'
        , page: true
        , limit: 10
        , height: 'full'
        , method: 'post'
        , size: 'lg'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , cols: [[
            {field: 'id', title: 'ID', width: 50}
            , {
                field: 'role', title: '角色', templet: function (d) {
                    return d.role === 'ADMIN' ? '管理员' :
                        d.role === 'REG_USER' ? '注册用户' :
                            d.role === 'QQ_USER' ? 'QQ用户' :
                                d.role === 'GITHUB_USER' ? 'GITHUB用户' : '其他';
                }
            }
            , {
                field: 'avatar', title: '头像', width: 80, templet: function (d) {
                    return '<img src="' + d.avatar + '" style="width: 35px;height: 35px;" class="layui-circle">';
                }
            }
            , {field: 'createDate', title: '注册时间', sort: true, minWidth: 150}
            , {field: 'email', title: '电子邮箱'}
            , {field: 'nickname', title: '昵称', edit: 'text'}
            , {field: 'username', title: '用户名'}
            , {field: 'remainCoin', title: '硬币', width: 80, edit: 'text'}
            , {title: '状态', width: 95, align: 'center', toolbar: '#enableTpl', fixed: 'right'}
        ]]
    });


    form.on('switch(enable)', function (obj) {
        NBV5.ajax("/management/user/update",
            {id: this.value, enable: obj.elem.checked},
            function (json) {
                NBV5.okMsgHandle(json);
                layer.tips('状态：' + ((obj.elem.checked) ? "正常" : "隐藏"), obj.othis);
            });
    });

    //监听单元格事件
    table.on('tool(user)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.open({
                type: 1
                , offset: 'auto'
                , id: 'layerUser' + data.id //防止重复弹出
                , content: '<div style="padding: 20px;">' + data.comment.replace(/<[^<>]+?>/g, '') + '</div>'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
            });
        }
    });

    table.on('sort(user)', function (obj) {
        messageTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


    //监听单元格编辑
    table.on('edit(user)', function (obj) {
        NBV5.ajax("/management/user/update/" + obj.field, {
            id: obj.data.id,
            remainCoin: obj.value,
            nickname: obj.value
        }, function (json) {
            layer.msg(json.message);
            if (json.code === NBV5.status.ok) {
                setTimeout(function () {
                    location.reload()
                }, 1000);
            }
        })
    });

    var $ = layui.$, active = {
        reload: function () {
            var username = $("#username-search");
            var nickname = $("#nickname-search");
            //执行重载
            table.reload('user-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    username: username.val(),
                    nickname: nickname.val()
                }
            });
        }
    };

    $('#user-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });


    exports('user', {});
});







