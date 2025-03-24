var successCallback = function () {};
layui.use(['common'], function () {
    var $ = layui.jquery
        ,layer = layui.layer
        ,form = layui.form
        ,common = layui.common;
    var configId = $.trim($('#configId').val());
    var submitBtnId = configId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-config');
    form.on('submit('+submitBtnId+')', function(data){
        data.field.status = data.field.status === "on" ? "1":"0";
        var url = configId ? (ctx + 'system/config/edit') : (ctx + 'system/config/add');
        $.ajax({
            type : "post",
            url : url,
            data : data.field,
            async : false,
            error : function(res) {
                layer.msg("系统错误");
            },
            success : function(data) {
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