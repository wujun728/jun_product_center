var successCallback = function () {};
layui.use(['common'], function () {
    var $ = layui.jquery
        ,layer = layui.layer
        ,form = layui.form
        ,common = layui.common;
    //提交表单
    var jobId = $.trim($('#jobId').val());
    var submitBtnId = jobId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-job');
    form.verify(common.verify);
    form.on('submit('+submitBtnId+')', function(data){
        data.field.status = data.field.status === "on" ? "1":"0";
        var url = jobId ? (ctx + 'monitor/job/edit') : (ctx + 'monitor/job/add');
        if(true){
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
        }
        return false;
    });
});