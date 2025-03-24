var successCallback = function () {};
layui.use(['common'], function () {
    var $ = layui.jquery
        , layer = layui.layer
        , form = layui.form
        , common = layui.common;
    //提交表单
    var dictId = $.trim($('#dictId').val());
    var submitBtnId = dictId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-dict');
    form.on('submit('+submitBtnId+')', function (data) {
        var url = dictId ? (ctx + 'system/dict/edit') : (ctx + 'system/dict/add');
        $.ajax({
            cache: false,
            type: "POST",
            url: url,
            data: data.field,
            async: false,
            error: function (res) {
                layer.msg("系统错误");
            },
            success: function (data) {
                if (data.code > 0) {
                    layui.layer.msg(data.msg);
                } else {
                    successCallback();
                }
            }
        });
        return false;
    });
});