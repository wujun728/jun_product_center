/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.use(['form', 'jquery'], function () {
    var form = layui.form
        , $ = layui.$;

    form.verify({
        username: function (value) {
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }
            if (value.length < 4 || value.length > 12) {
                return "用户名必须4~12位，且不能包含空格";
            }
        }
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
        , pass2: function (value) {
            var repeatPass = $("input[name=nbv5regPassword]").val();
            if (repeatPass !== value) {
                return "两次输入的密码不一致";
            }
        }
    });

    form.on('submit(nbv5reg)', function (data) {
        if (isOpenRegister) {
            data.field.nbv5regPassword = md5(data.field.nbv5regPassword);
            $.post("/registration", data.field, function (resp) {
                    if (resp.code === 200) {
                        layer.msg("注册成功！");
                        setTimeout(function () {
                            location.href = "/login" || resp.data;
                        }, 1000);
                    } else {
                        layer.msg(resp.message);
                    }
                }
            );
        } else {
            layer.msg("未开放注册！");
        }
        return false;
    });

    var reEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
    $("#sendMailCode").click(function () {
        var email = $("input[name=nbv5regMail]").val();
        if (reEmail.test(email)) {
            var $sendCodeBtn = $("#sendCode");
            $sendCodeBtn.text("已发送");
            $("#sendSeconds").text("(60)");
            var $this = $("#sendMailCode");
            $this.addClass("send-code");
            $this.attr("disabled", true);
            var num = 60;
            var i = setInterval(function () {
                num--;
                var $second = $("#sendSeconds");
                $second.text("(" + num + ")");
                if (num === 0) {
                    clearInterval(i);
                    $sendCodeBtn.text("重新发送");
                    $second.text("");
                    $this.removeClass("send-code");
                    $this.attr("disabled", false);
                }
            }, 1000);
            $.post("/sendMailCode", {
                email: email
            }, function (resp) {
                layer.msg(resp.message);
            })
        } else {
            layer.msg("请正确填写邮箱！");
        }

    });

});

