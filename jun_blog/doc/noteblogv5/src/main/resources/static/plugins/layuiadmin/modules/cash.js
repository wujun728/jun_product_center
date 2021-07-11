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
        elem: '#userId-search',	//定义输入框input对象 必填
        checkedKey: 'id', //表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: 'nickname',	//搜索输入框的name值 默认keyword
        searchPlaceholder: '用户昵称搜索',	//搜索输入框的提示文字 默认关键词搜索
        table: {	//定义表格参数，与LAYUI的TABLE模块一致，只是无需再定义表格elem
            height: 'full'
            , url: "/management/user/list"
            , limit: 5
            , size: 'md'
            , request: {
                pageName: 'current' //页码的参数名称，默认：page
                , limitName: 'size' //每页数据量的参数名，默认：limit
            }
            , cols: [[
                {type: 'radio'}
                , {field: 'nickname', title: '用户昵称', sort: true}
                , {field: 'username', title: '用户账号', sort: true}
            ]]
            , page: true
            , method: "post"
        },
        done: function (elem, data) {
            console.log(data)
            $("#userId-search").val(data.data[0].id)
        }
    });


    var cashTable = table.render({
        elem: '#cash-table'
        , url: '/management/cash/list'
        , page: true
        , limit: 10
        , height: 'full'
        , method: 'post'
        , request: {
            pageName: 'current' //页码的参数名称，默认：page
            , limitName: 'size' //每页数据量的参数名，默认：limit
        }
        , cols: [[
            {field: 'id', title: 'ID', width: 80}
            , {field: 'cashNo', minWidth: 220, title: '充值卡号'}
            , {field: 'cashValue', title: '卡密含硬币'}
            , {field: 'createTime', title: '创建时间', sort: true, minWidth: 150}
            , {field: 'rechargeTime', title: '充值时间', sort: true, minWidth: 150}
            , {field: 'userId', title: '用户ID', width: 80}
            , {field: 'nickname', title: '用户昵称'}
            , {title: '状态', width: 110, align: 'center', toolbar: '#enableTpl', fixed: 'right'}
        ]]
    });


    form.on('switch(enable)', function (obj) {
        NBV5.ajax("/management/cash/update",
            {id: this.value, enable: obj.elem.checked},
            function (json) {
                NBV5.okMsgHandle(json);
                layer.tips('状态：' + ((obj.elem.checked) ? "正常" : "隐藏"), obj.othis);
            });
    });


    table.on('sort(cash)', function (obj) {
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
            var userId = $("#userId-search");
            var nickname = $("#nickname-search");
            var cashNo = $("#cashNo-search");
            //执行重载
            table.reload('cash-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    userId: userId.val(),
                    nickname: nickname.val(),
                    cashNo: cashNo.val()
                }
            });
        },
        generate: function () {
            layer.prompt({
                formType: 2,
                value: '输入硬币值和数量,换行区分（第一行硬币值,第二行卡片数量）',
                title: '请输入值',
                area: ['250', '80px']
            }, function (value, index, elem) {
                NBV5.post("/management/cash/generate", {cashes: value}, function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === NBV5.status.ok) {
                            location.reload();
                        }
                    }, 1000);
                });
                layer.close(index);
            });
        }
    };

    $('#cash-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    exports('cash', {});

});







