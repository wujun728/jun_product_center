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

    //监听提交
    form.on('submit(profileForm)', function (data) {
        var pass = data.field.password1;
        if (pass !== null && pass !== "") {
            data.field.password1 = md5(data.field.password1);
            data.field.password2 = md5(data.field.password2);
        }
        data.field.avatar = $("#avatar").find("img").attr("src");
        NBV5.post("/management/settings/profile/update", data.field);
        return false;
    });

    form.on('submit(set_system_email)', function (data) {
        NBV5.post("/management/settings/mail/update", data.field, function (json) {
            NBV5.okMsgHandle(json, "修改邮件服务配置成功！");
        });
    });


    form.on('switch(switchFilter)', function (data) {
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

    upload.render({
        elem: '#avatar' //绑定元素
        , url: '/management/upload' //上传接口
        , data: {
            reqType: 'lay',
            objectKeyCode: 9
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#avatar").find("img").attr("src", res.data.src);
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }

        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });


    //执行实例
    var alipay = upload.render({
        elem: '#alipay' //绑定元素
        , url: '/management/upload' //上传接口
        , data: {
            reqType: 'lay',
            payType: "alipay",
            objectKeyCode: 10
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#a").find("img").attr("src", res.data.src);
                NBV5.post("/management/settings/pay/update", {
                    name: 'qrcode_alipay',
                    value: res.data.src,
                    msg: "支付宝二维码"
                });
            } else {
                layer.msg(res.message)
            }
        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });

    var wechat = upload.render({
        elem: '#wechat' //绑定元素
        , url: '/management/upload' //上传接口
        , data: {
            reqType: 'lay',
            payType: "wechat_pay",
            objectKeyCode: 11
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#w").find("img").attr("src", res.data.src);
                NBV5.post("/management/settings/pay/update", {
                    name: 'qrcode_wechat',
                    value: res.data.src,
                    msg: "微信支付二维码"
                });
            } else {
                layer.msg(res.message)
            }
        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });

    exports('profile', {});

});


