/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */

layui.define(['layer', 'element', 'upload'], function (exports) {
    var layer = layui.layer,
        element = layui.element,
        upload = layui.upload;
    element.render();

    nbv5front.miniHeaderNavBtn(layer);

    upload.render({
        elem: '#avatar' //绑定元素
        , url: '/ubs/token/avatar/change?objectKeyCode=13' //上传接口
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

    $("#nickname-change").click(function () {
        $.post("/ubs/token/nickname/change"
            , {
                nickname: $("#nickname").val()
            }
            , function (resp) {
                layer.msg(resp.message);
                if (resp.code === 200) {
                    setTimeout(function () {
                        location.reload();
                    }, 1000);
                }
            });
    });

    $("#sendMailCode").click(function () {
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
        $.post("/sendMailCode", {}, function (resp) {
            layer.msg(resp.message);
        })
    });

    $("#pass-change").click(function () {
        $.post("/ubs/token/password/change", {
            pass: md5($("input[name=password]").val()),
            code: $("input[name=mailCode]").val()
        }, function (resp) {
            layer.msg(resp.message);
        })
    });

    var sign_temp = true;
    $("#sign").click(function () {
        if (sign_temp) {
            $.ajax({
                url: '/ubs/token/sign'
                , data: {}
                , type: "post"
                , dataType: "json"
                , beforeSend: function () {
                    sign_temp = false;
                }
                , success: function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 800);
                }, complete: function () {
                    sign_temp = true;
                }
            })
        } else {
            layer.msg("不要心急，请稍等一会...");
        }
    });

    var rechargeIndex;
    $("body").on("click", "#closeDialog,#cash-cancel", function () {
        layer.close(rechargeIndex);
    });

    $("#recharge").click(function () {
        rechargeIndex = layer.open({
            id: 'recharge-dialog',
            type: 1,
            title: false,
            area: ['550px', '460px'],
            closeBtn: false,
            resize: false,
            content: $("#dialog").html(),
            success: function (layero, index) {
                $("input[name=cashNo]").inputmask({mask: "****-****-****-****"});
            }
        });
    });

    $("body").on("click", "#cash-recharge", function () {
        if ($("#recharge-dialog input[name=cashNo]").inputmask("isComplete")) {
            $.post("/ubs/token/cash/recharge",
                {cashNo: $("#recharge-dialog input[name=cashNo]").val()}
                , function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 500);
                });
        } else {
            layer.msg("请填写正确的卡密");
        }
    });


    exports('ubs', {});

});

