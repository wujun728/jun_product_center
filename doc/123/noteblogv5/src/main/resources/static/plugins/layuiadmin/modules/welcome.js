/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['element', 'form', 'upload'], function (exports) {
    var form = layui.form;
    var element = layui.element;
    var upload = layui.upload;
    element.render();
    form.render();

    form.on('switch(welcomeFilter)', function (data) {
        var status = data.elem.checked ? 1 : 0;
        var name = $(data.elem).attr("name");
        NBV5.ajax("/management/settings/update", {
            type: "switch"
            , name: name
            , value: status
        }, function (resp) {
            if (resp.code === NBV5.status.ok) {
                layer.tips('修改成功！', data.othis);
            } else {
                layer.msg(resp.message);
            }
        })
    });

    form.on('submit(card)', function (data) {
        if (data.field.cardNo === "01") {
            data.field.shape = data.field['shape01']
        } else if (data.field.cardNo === "02") {
            data.field.shape = data.field['shape02']
        } else if (data.field.cardNo === "03") {
            data.field.shape = data.field['shape03']
        } else {
            data.field.shape = data.field['shape04']
        }
        NBV5.ajax("/management/settings/welcome/card/update",
            data.field,
            function (resp) {
                layer.msg(resp.message);
                setTimeout(function () {
                    location.reload();
                }, 1000);
            })
    });

    upload.render({
        elem: '#card01-logo' //绑定元素
        , url: '/management/upload' //上传接口
        , exts: 'jpg|png|gif|bmp|jpeg|svg'
        , data: {
            reqType: 'lay',
            objectKeyCode: 14
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#card01-logo").find("img").attr("src", res.data.src);
                $(".card01 input[name=logoImgSrc]").val(res.data.src);
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }

        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });
    upload.render({
        elem: '#card02-logo' //绑定元素
        , url: '/management/upload' //上传接口
        , exts: 'jpg|png|gif|bmp|jpeg|svg'
        , data: {
            reqType: 'lay',
            objectKeyCode: 14
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#card02-logo").find("img").attr("src", res.data.src);
                $(".card02 input[name=logoImgSrc]").val(res.data.src);
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }

        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });
    upload.render({
        elem: '#card03-logo' //绑定元素
        , url: '/management/upload' //上传接口
        , exts: 'jpg|png|gif|bmp|jpeg|svg'
        , data: {
            reqType: 'lay',
            objectKeyCode: 14
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#card03-logo").find("img").attr("src", res.data.src);
                $(".card03 input[name=logoImgSrc]").val(res.data.src);
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }

        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });
    upload.render({
        elem: '#card04-logo' //绑定元素
        , url: '/management/upload' //上传接口
        , exts: 'jpg|png|gif|bmp|jpeg|svg'
        , data: {
            reqType: 'lay',
            objectKeyCode: 14
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#card04-logo").find("img").attr("src", res.data.src);
                $(".card04 input[name=logoImgSrc]").val(res.data.src);
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }

        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });

    exports('welcome', {});

});


