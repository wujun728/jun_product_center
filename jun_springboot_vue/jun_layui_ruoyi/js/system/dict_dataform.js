var successCallback = function () {};
layui.use(['common'], function () {
    var $ = layui.jquery
        , layer = layui.layer
        , form = layui.form
        , laydate = layui.laydate
        , common = layui.common;
    //日期初始化
    laydate.render({
        elem: '.ss-laydate'
    });
    //提交表单
    var dictId = $.trim($('#dictId').val());
    var submitBtnId = dictId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-dict_data');
    form.on('submit('+submitBtnId+')', function (data) {
        var url = dictId ? (ctx + 'system/dict/data/edit') : (ctx + 'system/dict/data/add');
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