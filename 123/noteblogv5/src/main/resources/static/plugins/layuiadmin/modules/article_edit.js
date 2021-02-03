/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */

var editor, formSelects;
layui.define(['element', 'form', 'layer', 'upload', 'formSelects', 'inputTags'], function (exports) {
    var form = layui.form,
        element = layui.element,
        $ = layui.$,
        inputTags = layui.inputTags;
    var upload = layui.upload;
    formSelects = layui.formSelects;
    element.render();
    form.render();


    formSelects.btns('cateId', []);

    formSelects.maxTips('cateId', function (id, vals, val, max) {
        layer.alert("最多只能选择3个");
    });


    var tagList = [];
    for (var j = 0; j < tags.length; j++) {
        tagList.push(tags[j].name)
    }

    inputTags.render({
        elem: '#inputTags',
        content: tagList,
        aldaBtn: false,
        done: function (value) {
        }
    });

    var post = function (data, draft, msg) {
        data.field.draft = draft;
        data.field.urlSeq = $urlSeqInput.val();
        data.field.cateIds = formSelects.value('cateId', 'val');
        if (data.field.editor === 'html') {
            data.field.mdContent = "";
            data.field.content = editor.html();
        }
        if (data.field.editor === 'markdown') {
            data.field.mdContent = editorMd.getMarkdown();
            data.field.content = editorMd.getHTML();
            // data.field.content = editorMd.getPreviewedHTML();
        }
        data.field.cover = $("#coverImg").find("img").attr("src") || "";
        var $tags = $("#tags>span");
        var tns = [];
        $tags.each(function (index, elem) {
            tns.push($(elem).find("em").text())
        });
        data.field.tagNames = tns;
        if (!hasUrlSeq) {
            data.field.urlSeq = "";
        }
        NBV5.post("/management/article/update", data.field);

    };

    //监听提交
    form.on('submit(postSubmit)', function (data) {
        post(data, false, "修改文章成功！");
        return false;
    });

    form.on('submit(draftSubmit)', function (data) {
        post(data, true, "修改成功，保存草稿！");
        return false;
    });

    var $titleInput = $("input[name=title]");
    var $urlSeqInput = $("input[name=urlSeq]");

    form.on('checkbox(customUrl)', function (data) {
        if (data.elem.checked) {
            hasUrlSeq = true;
            $urlSeqInput.removeAttr("disabled");
            $urlSeqInput.val("/" + $titleInput.val());
            $titleInput.bind("input propertychange", function () {
                var title = $titleInput.val();
                if (title.length > 0) {
                    $urlSeqInput.val("/" + title);
                } else {
                    $urlSeqInput.val("");
                }
            });
        } else {
            $urlSeqInput.val("文章链接为系统自动生成的ID，如：/article/#{文章ID}#，中文会转化为拼音");
            $urlSeqInput.attr("disabled", true);
            $titleInput.unbind("input propertychange");
        }
    });
    form.on('radio(editor)', function (data) {
        if (data.value === "markdown") {
            editor.remove();
            $("#content-editor-article-edit").append("<div id='editormd-article-edit'></div>");
            $.getScript("/static/plugins/editormd/editormd.min.js", function () {
                editorMd = editormd("editormd-article-edit", {
                    height: 640,
                    watch: false,
                    codeFold: true,
                    toolbarIcons: function () {
                        return [
                            "undo", "redo", "|",
                            "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                            "h1", "h2", "h3", "h4", "h5", "h6", "|",
                            "list-ul", "list-ol", "hr", "|",
                            "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                            "goto-line", "watch", "preview", "fullscreen", "clear", "search", "|",
                            "help", "info"
                        ]
                    },
                    pluginPath: '/static/plugins/editormd/plugins/',
                    markdown: mdContents,
                    path: '/static/plugins/editormd/lib/',
                    placeholder: '请在此书写你的内容',
                    saveHTMLToTextarea: true,
                    searchReplace: true,
                    taskList: true,
                    tex: true,// 开启科学公式TeX语言支持，默认关闭
                    flowChart: true,//开启流程图支持，默认关闭
                    sequenceDiagram: true,//开启时序/序列图支持，默认关闭,
                    imageUpload: true,
                    imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
                    imageUploadURL: "/management/upload/editorMD?objectKeyCode=3&generateNextArticleId=" + articleId,
                    onfullscreen: function () {
                        $("#right-card").css("z-index", "-1");
                        editorMd.width("100%");
                    },
                    onfullscreenExit: function () {
                        $("#right-card").css("z-index", "999");
                        editorMd.width("100%");
                    }
                });
            });
        } else if (data.value === "html") {
            editorMd.editor.remove();
            KindEditor.options.filterMode = false;
            editor = KindEditor.create('#editor-article-edit', {
                cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
                width: "auto",
                height: "600px",
                items: [
                    'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
                    'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                    'link', 'unlink', 'about', 'br', '|', 'hide4comment', 'hide4login', 'hide4purchase'
                ],
                uploadJson: '/management/upload?reqType=nk&objectKeyCode=4&generateNextArticleId=' + articleId,
                dialogOffset: 0, //对话框距离页面顶部的位置，默认为0居中，
                allowImageUpload: true,
                allowMediaUpload: true,
                themeType: 'black',
                fixToolBar: true,
                autoHeightMode: true,
                filePostName: 'file',//指定上传文件form名称，默认imgFile
                resizeType: 1,//可以改变高度
                afterCreate: function () {
                    var self = this;
                    KindEditor.ctrl(document, 13, function () {
                        self.sync();
                        K('form[name=example]')[0].submit();
                    });
                    KindEditor.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                        KindEditor('form[name=example]')[0].submit();
                    });
                },
                //错误处理 handler
                errorMsgHandler: function (message, type) {
                    try {
                        JDialog.msg({type: type, content: message, timer: 2000});
                    } catch (Error) {
                        alert(message);
                    }
                }
            });
        }
    });

    upload.render({
        elem: '#coverImg' //绑定元素
        , url: '/management/upload?reqType=lay&objectKeyCode=6&generateNextArticleId=' + articleId //上传接口
        , error: function () {
            layer.msg("上传失败！");
        }
        , done: function (res) {
            if (res.code === 0) {
                $("#coverImg").html('<p><img style="width: 100%;height: 120px;" src="' + res.data.src + '"></p>');
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }
        }
    });

    exports('article_edit', {});
});

$(function () {
    if (!isMd) {
        try {
            editorMd.editor.remove();
        } catch (e) {
        }
        KindEditor.options.filterMode = false;
        editor = KindEditor.create('#editor-article-edit', {
            cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
            width: "auto",
            height: "600px",
            items: [
                'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
                'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
                'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen', '/',
                'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
                'insertfile', 'table', 'hr', 'emoticons', 'pagebreak',
                'link', 'unlink', 'about', 'br', '|', 'hide4comment', 'hide4login', 'hide4purchase'
            ],
            uploadJson: '/management/upload?reqType=nk&objectKeyCode=4&generateNextArticleId=' + articleId,
            dialogOffset: 0, //对话框距离页面顶部的位置，默认为0居中，
            allowImageUpload: true,
            allowMediaUpload: true,
            themeType: 'black',
            fixToolBar: true,
            autoHeightMode: true,
            filePostName: 'file',//指定上传文件form名称，默认imgFile
            resizeType: 1,//可以改变高度
            afterCreate: function () {
                var self = this;
                KindEditor.ctrl(document, 13, function () {
                    self.sync();
                    K('form[name=example]')[0].submit();
                });
                KindEditor.ctrl(self.edit.doc, 13, function () {
                    self.sync();
                    KindEditor('form[name=example]')[0].submit();
                });
            },
            //错误处理 handler
            errorMsgHandler: function (message, type) {
                try {
                    JDialog.msg({type: type, content: message, timer: 2000});
                } catch (Error) {
                    alert(message);
                }
            }
        });
    } else {
        $("#content-editor-article-edit").append("<div id='editormd-article-edit'></div>");
        $.getScript("/static/plugins/editormd/editormd.min.js", function () {
            editorMd = editormd("editormd-article-edit", {
                height: 640,
                watch: false,
                codeFold: true,
                toolbarIcons: function () {
                    return [
                        "undo", "redo", "|",
                        "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase", "|",
                        "h1", "h2", "h3", "h4", "h5", "h6", "|",
                        "list-ul", "list-ol", "hr", "|",
                        "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "html-entities", "pagebreak", "|",
                        "goto-line", "watch", "preview", "fullscreen", "clsummaryear", "search", "|",
                        "help", "info"
                    ]
                },
                pluginPath: '/static/plugins/editormd/plugins/',
                markdown: mdContents,
                path: '/static/plugins/editormd/lib/',
                placeholder: '请在此书写你的内容',
                saveHTMLToTextarea: true,
                searchReplace: true,
                taskList: true,
                tex: true,// 开启科学公式TeX语言支持，默认关闭
                flowChart: true,//开启流程图支持，默认关闭
                sequenceDiagram: true,//开启时序/序列图支持，默认关闭,
                imageUpload: true,
                imageFormats: ["jpg", "jpeg", "gif", "png", "bmp"],
                imageUploadURL: "/management/upload/editorMD?objectKeyCode=3&generateNextArticleId=" + articleId,
                onfullscreen: function () {
                    $("#right-card").css("z-index", "-1");
                    editorMd.width("100%");
                },
                onfullscreenExit: function () {
                    $("#right-card").css("z-index", "999");
                    editorMd.width("100%");
                }
            });
        });
    }

    $("#editRemoveCover").click(function () {
        $("#coverImg").find("img").attr("src", "");
        $("#coverImg").html('  <i class="layui-icon"></i><p>点击上传博文封面，或将封面拖拽到此处</p>');
    });
});





