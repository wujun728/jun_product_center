/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.use('form', function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.$;

    layer.config({
        skin: 'layui-layer-lan'
    });

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
        }
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
        , repeatPass: function (value) {
            var pass = $("input[name=password]").val();
            if (pass !== value) {
                return '两次输入的密码不一致';
            }
        }
        , mail: [
            /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/
            , '请输入正确的邮箱！'
        ],
        qiniu: function (value) {
            var uploadType = $("select[name=upload_type]").val();
            if ("qiniu_server" === uploadType && (value === null || value.length === 0)) {
                return "选择七牛服务器必须填写相关参数！";
            }
        }
    });

    $("#next").on('click', function () {
        $(this).hide();
        $("#prev").show();
        $("#base-form").hide();
        $("#advance-form").show();
        $("#submit").show();
    });

    $("#prev").on('click', function () {
        $(this).hide();
        $("#base-form").show();
        $("#next").show();
        $("#advance-form").hide();
        $("#submit").hide();
    });


    form.on('select(uploadMethod)', function (data) {
        if (data.value === 'QINIU') {
            $("#qiniu").show();
            $("#local").hide();
        } else if (data.value === 'LOCAL') {
            layer.alert('请确保在「application-noteblogv5.properties」文件中配置了本地服务器上传路径', {icon: 0});
            $("#local").show();
            $("#qiniu").hide();
        } else {
            layer.alert("请选择文件上传方式！");
            $("#qiniu").hide();
            $("#local").hide();
        }
    });

    form.on('submit(nbInit)', function (data) {
        var html = '<p style="color: #FF5722;">请确认您所填的信息</p>';
        var obj = data.field;
        var originUploadMethod = data.field.upload_type;
        for (var o in obj) {
            if (obj.hasOwnProperty(o)
                && originUploadMethod !== 'QINIU'
                && (o === 'qiniu_accessKey' || o === 'qiniu_secretKey' || o === 'qiniu_bucket' || o === 'qiniu_domain')) {
                continue;
            }
            if (obj.hasOwnProperty(o) && o !== 'repeatPass') {
                var labelName = $("input[name=" + o + "], select[name=" + o + "]").parents("div.layui-form-item").find("label").text();
                if (o === 'upload_type') {
                    obj[o] = $("option[value=" + obj[o] + "]").text();
                    html += '<p>' + labelName + '：' + obj[o] + '</p>';
                } else {
                    html += '<p>' + labelName + '：' + obj[o] + '</p>';
                }
            }
        }
        obj.upload_type = originUploadMethod;
        var index = layer.confirm(html, {
            btn: ['确定', '重填']
            , title: '确认信息'
        }, function () {
            if (obj.upload_type === 'QINIU') {
                if (obj.qiniu_accessKey === '' || obj.qiniu_secretKey === '' || obj.qiniu_bucket === '' || obj.qiniu_domain === '') {
                    layer.alert('请正确填写七牛云的相关数据！');
                    return false;
                }
            }
            obj.password = md5(data.field.password);
            $.post('/init/submit'
                , obj
                , function (json) {
                    layer.msg("     " + json.message, {icon: 1});
                    if (json.code === 200) {
                        setTimeout(function () {
                            location.href = "/";
                        }, 1000)
                    }
                    layer.close(index);
                });
        });
        return false;
    });

});


