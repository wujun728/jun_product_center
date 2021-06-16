/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(["admin"], function (exports) {
    var $ = layui.$,
        admin = layui.admin;

    $("#keyword-search").click(function () {
        var sc = $("input[name=keyword]").val();
        location.href = "/management/dict/keyword?keyword=" + sc;
    });

    $("#keyword-add").click(function () {
        var addKeyword = $("input[name=keyword]").val();
        if (addKeyword === '') {
            layer.msg("关键字称不能为空！");
        } else {
            admin.req({
                type: "post",
                dataType: "json",
                url: "/management/dict/keyword/add",
                data: {
                    keyword: addKeyword
                },
                success: function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 1000);
                }
            })
        }
    });


    $("div.keyword>button>span.delete").click(function () {
        var tbtn = $(this).parent();
        var kid = $(this).attr("data-kid");
        admin.req({
            type: "post",
            dataType: "json",
            url: "/management/dict/keyword/delete",
            data: {
                id: kid
            },
            success: function (resp) {
                layer.msg(resp.message);
                tbtn.remove();
                setTimeout(function () {
                    if (resp.code === 200) {
                        location.reload();
                    }
                }, 1000);
            }
        })
    });


    exports('keyword', {});
});