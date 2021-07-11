/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['element', 'form', 'layer', 'formSelects'], function (exports) {
    var form = layui.form,
        element = layui.element,
        formSelects = layui.formSelects;

    element.render();
    form.render();

    formSelects.btns('downloadCateId', []);

    formSelects.maxTips('downloadCateId', function (id, vals, val, max) {
        layer.alert("最多只能选择3个");
    });


    $("#refreshDownloadCate").click(function () {
        $.get("/management/dict/downloadCate/list", function (resp) {
            if (resp.code === 200) {
                formSelects.data('downloadCateId', 'local', {
                    arr: resp.data
                })
            }
        });
    });


    //监听提交
    form.on('submit(downloadSubmit)', function (data) {
        data.field.downloadCateIds = formSelects.value('downloadCateId', 'val');
        NBV5.post("/management/download/update", data.field);
        return false;
    });

    exports('download_edit', {});

});



