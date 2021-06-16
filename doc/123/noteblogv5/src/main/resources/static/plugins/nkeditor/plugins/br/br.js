KindEditor.plugin('br', function (K) {
    var editor = this, name = 'br';
    // 点击图标时执行
    editor.clickToolbar(name, function () {
        editor.appendHtml("<br />");
    });
});