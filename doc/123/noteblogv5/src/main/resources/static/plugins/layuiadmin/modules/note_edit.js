/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
var editorMd;
layui.define(['element', 'form', 'layer'], function () {
    var form = layui.form;
    var element = layui.element;
    element.render();
    form.render();


    var post = function (data) {
        //markdown文本
        data.field.mdContent = editorMd.getMarkdown();
        //html文本
        data.field.content = editorMd.getHTML();
        NBV5.post("/management/note/update", data.field);
    };

    //监听提交
    form.on('submit(noteSubmit)', function (data) {
        post(data);
        return false;
    });

});

$(function () {

    $("#content-editor-note-edit").append("<div id='editormd-note-edit'></div>");
    $.getScript("/static/plugins/editormd/editormd.min.js", function () {
        editorMd = editormd("editormd-note-edit", {
            height: 640,
            watch: true,
            codeFold: true,
            toolbarIcons: function () {
                return [
                    "undo", "redo", "|",
                    "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                    "h1", "h2", "h3", "h4", "h5", "h6", "|",
                    "list-ul", "list-ol", "hr", "|",
                    "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                    "goto-line", "watch", "preview", "clear", "search", "|",
                    "help", "info"
                ]
            },
            pluginPath: '/static/plugins/editormd/plugins/',
            markdown: mdNoteContent,
            path: '/static/plugins/editormd/lib/',
            placeholder: '请在此书写你的内容',
            saveHTMLToTextarea: true,
            imageUpload: true,
            imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
            imageUploadURL: "/management/upload/editorMD?objectKeyCode=8"
        });
    });
});





