var validator;
var mobileBindValidator;
var $userProfileForm = $("#update-profile-form");
var $mobileBindForm = $("#mobile-bind-form");
var $mobileUnbindForm = $("#mobile-unbind-form");
$(function () {
    $(".profile__img").find("img").attr("src", avatar);
    $("#update-profile").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#default-avatars").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#mobile-bind").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#mobile-unbind").bind('hide.bs.modal', function () {
        $(".modal-backdrop").remove();
    });
    $("#update-profile .btn-close").click(function () {
        $MB.closeAndRestModal("update-profile");
        validator.resetForm();
        $MB.resetJsTree("deptTree");
    });
    $("#mobile-bind .btn-close").click(function () {
        closeMobileBind();
    });
    $("#mobile-unbind .btn-close").click(function () {
        closeMobileUnbind();
    });
    $(".default_avatars_list").find("img").each(function () {
        var $this = $(this);
        $this.on("click", function () {
            var target_src = $(this).attr("src");
            $.post(ctx + "user/changeAvatar", {"imgName": target_src}, function (r) {
                if (r.code === 0) {
                    $("#close_update_avatar_button").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                    $(".user__img").attr("src", ctx + target_src);
                } else $MB.n_danger(r.msg);
            });
        });
    });
    $(".profile__img__edit").on('click', function () {
        $('#default-avatars').modal();
    });
    validateRule();
    validateMobileBindRule();
    createDeptTree();

    $("#update-profile .btn-save").click(function () {
        getDept();
        var validator = $userProfileForm.validate();
        var flag = validator.form();
        if (flag) {
            $.post(ctx + "user/updateUserProfile", $userProfileForm.serialize(), function (r) {
                if (r.code === 0) {
                    $("#update-profile .btn-close").trigger("click");
                    $MB.n_success(r.msg);
                    refreshUserProfile();
                } else $MB.n_danger(r.msg);
            });
        }
    });
    $("#mobile-bind .btn-save").click(function () {
        var validator = $mobileBindForm.validate();
        var flag = validator.form();
        if (flag) {
            $.get(ctx + "mobile/bind", $mobileBindForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeMobileBind();
                    refreshUserProfile();
                    $MB.n_success("绑定成功，后续您可用该手机号进行登录");
                } else {
                    $MB.n_warning(r.msg);
                }
            });
        }
    });

    $("#mobile-unbind .btn-save").click(function () {
        $MB.confirm({
            text: "确定解绑？解绑后无法继续使用该手机号登录系统。",
            confirmButtonText: "确定解绑"
        }, function () {
            $.get(ctx + "mobile/unbind", $mobileUnbindForm.serialize(), function (r) {
                if (r.code === 0) {
                    closeMobileUnbind();
                    refreshUserProfile();
                    $MB.n_success("解绑成功！");
                } else {
                    $MB.n_warning(r.msg);
                }
            });
        });
    });

});

function refreshUserProfile() {
    $.post(ctx + "user/profile", function (r) {
        $main_content.html("").append(r);
    });
}

function editUserProfile() {
    $.post(ctx + "user/getUserProfile", {"userId": userId}, function (r) {
        if (r.code === 0) {
            var $form = $('#update-profile');
            var $deptTree = $('#deptTree');
            $form.modal();
            var user = r.msg;
            $form.find("input[name='username']").val(user.username).attr("readonly", true);
            $form.find("input[name='oldusername']").val(user.username);
            $form.find("input[name='userId']").val(user.userId);
            $form.find("input[name='email']").val(user.email);
            $form.find("input[name='description']").val(user.description);
            $("input:radio[value='" + user.ssex + "']").attr("checked", true);
            $deptTree.jstree().open_all();
            $deptTree.jstree('select_node', user.deptId, true);
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $userProfileForm.validate({
        rules: {
            email: {
                email: true
            },
            ssex: {
                required: true
            },
            description: {
                maxlength: 100
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
            email: icon + "邮箱格式不正确",
            ssex: icon + "请选择性别",
            description: icon + "个人描述不能超过100个字符"
        }
    });
}

function createDeptTree() {
    $.post(ctx + "dept/tree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#deptTree').jstree({
                "core": {
                    'data': data.children,
                    'multiple': false
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getDept() {
    var ref = $('#deptTree').jstree(true);
    $("[name='deptId']").val(ref.get_selected()[0]);
}

function mobileBind() {
    var $form = $('#mobile-bind');
    $form.modal();
}

function closeMobileBind() {
    $MB.closeAndRestModal("mobile-bind");
    mobileBindValidator.resetForm();
}

function sendCode(type) {
    var mobile;
    if (type === 1) {
        mobile = $mobileBindForm.find("input[name='mobile']").val().trim();
    } else {
        mobile = $mobileUnbindForm.find("input[name='mobile']").val().trim();
    }
    var regex = /^1[0-9]{10}$/;
    if (mobile === "") {
        $MB.n_warning("请输入手机号！");
        return;
    }
    if (!regex.test(mobile)) {
        $MB.n_warning("请输入正确的手机号！");
        return;
    }
    $.get(ctx + "sms/code", {"mobile": mobile}, function (r) {
        if (r.code === 0) {
            $MB.n_success("短信发送成功，请尽快使用");
        } else if (r.code === 666) {
            $MB.n_warning("你的手速也太快了，休息一下吧！");
        } else {
            $MB.n_danger("短信验证码发送失败，请重新发送");
        }

    });
}

function validateMobileBindRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    mobileBindValidator = $mobileBindForm.validate({
        rules: {
            mobile: {
                checkPhone: true,
                required: true
            }
        }
    });
}

function mobileUnbind() {
    var $form = $('#mobile-unbind');
    $form.modal();
}

function closeMobileUnbind() {
    $MB.closeAndRestModal("mobile-unbind");
}

// 处理 QQ 微信 绑定和解绑
(function () {
    var $connectQQ = $("a#connet_qq");
    var $connectQQForm = $("form#connect_qq_form");
    var $disConnectQQForm = $("form#disconnect_qq_form");
    var $connectWechat = $("a#connect_wechat");
    var $connectWechatForm = $("form#connect_wechat_form");
    var $disConnectWechatForm = $("form#disconnect_wechat_form");
    $.get(ctx + "connect").done(function (r) {
        if (r.qq) {
            $connectQQ.find("img").prop("src", ctx + "img/social/QQ_bind.svg")
                .end().prop("title", "已绑定QQ号，点击解绑").bind('click', function () {
                $MB.confirm({
                    text: "确定解绑该QQ号？解绑后无法继续使用该QQ号登录系统。",
                    confirmButtonText: "确定解绑"
                }, function () {
                    $disConnectQQForm.find("input[name='_method']").val("DELETE").end()[0].submit();
                });
            });
        } else {
            $connectQQ.find("img").prop("src", ctx + "img/social/QQ.svg")
                .end().prop("title", "未绑定QQ号，点击绑定").bind('click', function () {
                $connectQQForm[0].submit();
            });
        }
        if (r.weixin) {
            $connectWechat.find("img").prop("src", ctx + "img/social/wechat_bind.svg")
                .end().prop("title", "已绑定微信，点击解绑").bind('click', function () {
                $MB.confirm({
                    text: "确定解绑该微信号？解绑后无法继续使用该微信号登录系统。",
                    confirmButtonText: "确定解绑"
                }, function () {
                    $disConnectWechatForm.find("input[name='_method']").val("DELETE").end()[0].submit();
                });
            });
        } else {
            $connectWechat.find("img").prop("src", ctx + "img/social/wechat.svg")
                .end().prop("title", "未绑定微信，点击绑定").bind('click', function () {
                $connectWechatForm[0].submit();
            });
        }
    });
}());