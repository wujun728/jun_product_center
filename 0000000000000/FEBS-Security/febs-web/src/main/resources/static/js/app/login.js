$(document).ready(function () {

    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-green',
        radioClass: 'iradio_minimal-green',
        increaseArea: '20%'
    });

    var $formPanelTwo = $('.form-panel.two');

    var panelOne = $formPanelTwo.height();
    var panelTwo = $formPanelTwo[0].scrollHeight;

    $formPanelTwo.not('.form-panel.two.active').on('click', function (e) {
        e.preventDefault();

        $('.form-toggle').addClass('visible');
        $('.form-panel.one').addClass('hidden');
        $('.form-panel.two').addClass('active');
        $('.form').animate({
            'height': panelTwo
        }, 200);
    });

    $('.form-toggle').on('click', function (e) {
        e.preventDefault();
        $(this).removeClass('visible');
        $('.form-panel.one').removeClass('hidden');
        $('.form-panel.two').removeClass('active');
        $('.form').animate({
            'height': panelOne + 92
        }, 200);
    });

    var $type1 = $(".type1");
    var $type2 = $(".type2");
    var $form = $(".one").find("form");
    var $loginTitle = $("#login-title");
    $("#mobile-login").on('click', function () {
        $type1.hide();
        $type2.show();
        $form[0].reset();
        $loginTitle.text("短信验证码登录");
    });
    $("#form-login").on('click', function () {
        $type2.hide();
        $type1.show();
        $form[0].reset();
        $loginTitle.text("账户登录");
    });

});


function reloadCode() {
    $("#validateCodeImg").attr("src", ctx + "image/code?data=" + new Date() + "");
}

function formLogin() {
    var $loginButton = $("#form-login-button");
    var username = $(".one input[name='username']").val().trim();
    var password = $(".one input[name='password']").val().trim();
    var $form = $(".one").find("form");
    if (username === "") {
        $MB.n_warning("请输入账号！");
        return;
    }
    if (password === "") {
        $MB.n_warning("请输入密码！");
        return;
    }
    $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

    $.ajax({
        type: "post",
        url: ctx + "form/login",
        data: $form.serialize(),
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $form[0].reset();
                location.href = ctx + 'index';
            } else {
                if (r.msg !== '验证码不能为空！') reloadCode();
                $MB.n_warning(r.msg);
                $loginButton.html("登录");
            }
        }
    });
}

function sendCode() {
    var mobile = $(".one input[name='mobile']").val().trim();
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
            $MB.n_success("短信发送成功，请尽快使用！");
        } else if (r.code === 666) {
            $MB.n_warning("你的手速也太快了，休息一下吧！");
        } else {
            $MB.n_danger("短信验证码发送失败，请重新发送！");
        }

    });
}

function mobileLogin() {
    var $loginButton = $("#form-login-button");
    var mobile = $(".one input[name='mobile']").val().trim();
    var smsCode = $(".one input[name='smsCode']").val().trim();
    var $form = $(".one").find("form");
    var regex = /^1[0-9]{10}$/;
    if (mobile === "") {
        $MB.n_warning("请输入手机号！");
        return;
    }
    if (!regex.test(mobile)) {
        $MB.n_warning("请输入正确的手机号！");
        return;
    }
    $loginButton.html("").append("<div class='login-loder'><div class='line-scale'><div></div><div></div><div></div><div></div><div></div></div></div>");

    $.ajax({
        type: "post",
        url: ctx + "mobile/login",
        data: $form.serialize(),
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $form[0].reset();
                location.href = ctx + 'index';
            } else {
                if (r.msg === '验证码不能为空！') reloadCode();
                $MB.n_warning(r.msg);
                $loginButton.html("登录");
            }
        }
    });
}

function regist() {
    var username = $(".two input[name='username']").val().trim();
    var password = $(".two input[name='password']").val().trim();
    var cpassword = $(".two input[name='cpassword']").val().trim();
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
    $.ajax({
        type: "post",
        url: ctx + "user/regist",
        data: {
            "username": username,
            "password": password
        },
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                $MB.n_success("注册成功，请登录");
                $(".two input[name='username']").val("");
                $(".two input[name='password']").val("");
                $(".two input[name='cpassword']").val("");
                $('.form-toggle').trigger('click');
            } else {
                $MB.n_warning(r.msg);
            }
        }
    });
}
