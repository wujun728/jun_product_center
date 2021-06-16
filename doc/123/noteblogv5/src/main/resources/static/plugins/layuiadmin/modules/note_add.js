/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['element', 'form', 'layer'], function (exports) {
    var form = layui.form;
    var element = layui.element;
    element.render();
    form.render();


    var post = function (data) {
        //markdown文本
        data.field.mdContent = editorMd.getMarkdown();
        //html文本
        data.field.content = editorMd.getHTML();
        NBV5.post("/management/note/create", data.field);

    };


    //监听提交
    form.on('submit(noteSubmit)', function (data) {
        post(data);
        return false;
    });

    exports('note_add', {});

});


$(function () {
    $("#content-editor-note-add").append("<div id='editormd-note-add'></div>");
    $.getScript("/static/plugins/editormd/editormd.min.js", function () {
        editorMd = editormd("editormd-note-add", {
            height: 640,
            watch: false,
            codeFold: true,
            toolbarIcons: function () {
                return [
                    "undo", "redo", "|",
                    "bold", "del", "italic", "quote", "uppercase", "lowercase", "|",
                    "h1", "h2", "h3", "h4", "h5", "h6", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "code", "preformatted-text", "code-block", "datetime", "html-entities", "|",
                     "clear", "|"
                ]
            },
            pluginPath: '/static/plugins/editormd/plugins/',
            markdown: "### 随笔内容",
            path: '/static/plugins/editormd/lib/',
            placeholder: '请在此书写你的内容',
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
            imageUploadURL: "/management/upload/editorMD?objectKeyCode=7"
        });
    });


});



