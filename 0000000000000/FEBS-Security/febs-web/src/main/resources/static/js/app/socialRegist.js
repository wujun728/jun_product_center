$(function () {
    $("#providerId").val(socialUser.providerId);
    var social_provider;
    switch (socialUser.providerId) {
        case 'qq':
            social_provider = "QQ号";
            break;
        case 'weixin':
            social_provider = "微信号";
            break;
        default:
            social_provider = "账号";
    }
    social_provider += '<span style="font-weight: 600;color: #32c787"> ' + socialUser.nickname + ' </span>';
    $("img").prop("src", socialUser.headImg);
    $(".form-header").find("div").html("").append(
        '当前' + social_provider + '尚未绑定任何账号，请先绑定。</br><span class="type1">没有账号？<a href="javascript:void(0)" id="social-regist" style="color: #32c787;font-weight: 600">立即注册</a></span>' +
        '<span class="type2" style="display: none">已有账号？<a href="javascript:void(0)" id="social-bind"  style="color: #32c787;font-weight: 600;">立即绑定</a></span>'
    );
    var $type1 = $(".type1");
    var $type2 = $(".type2");
    var $form = $(".one").find("form");
    $("#social-regist").on('click', function () {
        $type1.hide();
        $type2.show();
        $form[0].reset();
    });
    $("#social-bind").on('click', function () {
        $type1.show();
        $type2.hide();
        $form[0].reset();
    });
});

function socialBind() {
    var $socialBindButton = $("#social-bind-button");
    var username = $(".one .type1 input[name='username']").val().trim();
    var password = $(".one .type1 input[name='password']").val().trim();
    var providerId = $("#providerId").val();
    if (username === "") {
        $MB.n_warning("请输入账号！");
        return;
    }
    if (password === "") {
        $MB.n_warning("请输入密码！");
        return;
    }
    $socialBindButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");
    $.ajax({
        type: "get",
        url: ctx + "social/bind",
        data: {
            username: username,
            password: password,
            providerId: providerId
        },
        dataType: "json",
        success: function (r) {
            var msg = r.msg;
            if (r.code === 0) {
                $MB.n_success("绑定成功，2秒后跳转到登录页！");
                setTimeout(function () {
                    window.location.href = ctx + "index";
                }, 2000);
            } else {
                $MB.n_warning(msg);
            }
            $socialBindButton.html("绑定");
        }
    });
}

function socialRegist() {
    var $socialRegistButton = $("#social-regist-button");
    var username = $(".one .type2 input[name='username']").val().trim();
    var password = $(".one .type2 input[name='password']").val().trim();
    var cpassword = $(".one .type2 input[name='password2']").val().trim();
    var providerId = $("#providerId").val();
    if (username === "") {
        $MB.n_warning("账号不能为空！");
        return;
    } else if (username.length > 10) {
        $MB.n_warning("账号长度不能超过10个字符！");
        return;
    } else if (username.length < 3) {
        $MB.n_warning("账号长度不能少于3个字符！");
        return;
    }
    if (password === "") {
        $MB.n_warning("密码不能为空！");
        return;
    }
    if (cpassword === "") {
        $MB.n_warning("请再次输入密码！");
        return;
    }
    if (cpassword !== password) {
        $MB.n_warning("两次密码输入不一致！");
        return;
    }
    $socialRegistButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");
    $.ajax({
        type: "get",
        url: ctx + "social/regist",
        data: {
            username: username,
            password: password
        },
        dataType: "json",
        success: function (r) {
            var msg = r.msg;
            if (r.code === 0) {
                $MB.n_success("注册绑定成功，2秒后跳转到登录页！");
                setTimeout(function () {
                    window.location.href = ctx + "index";
                }, 2000);
            } else {
                $MB.n_warning(msg);
            }
            $socialRegistButton.html("注册并绑定");
        }
    });
}