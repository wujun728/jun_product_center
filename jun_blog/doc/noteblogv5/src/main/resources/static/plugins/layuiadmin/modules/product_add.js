/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
var editor, editorMd;
layui.define(['form', 'formSelects', 'upload', 'element', 'inputTags', 'admin'], function (exports) {
    var $ = layui.$,
        element = layui.element,
        form = layui.form,
        upload = layui.upload,
        inputTags = layui.inputTags,
        formSelects = layui.formSelects;
    element.render();
    form.render();

    formSelects.btns('cateId', []);

    formSelects.maxTips('cateId', function (id, vals, val, max) {
        layer.alert("最多只能选择3个");
    });

    inputTags.render({
        elem: '#inputTags',
        content: [],
        aldaBtn: false,
        done: function (value) {
        }
    });

    $("#refreshProductCate").click(function () {
        $.get("/management/dict/cate/list", function (resp) {
            if (resp.code === 200) {
                formSelects.data('cateId', 'local', {
                    arr: resp.data
                })
            }
        });
    });

    upload.render({
        url: '/management/upload?reqType=lay&objectKeyCode=5&generateNextArticleId=' + generateNextArticleId //上传接口
        , elem: '#productCoverImg' //绑定元素
        , done: function (res) {
            if (res.code === 0) {
                $("#productCoverImg")
                    .html('<p><img style="width: 100%;height: 120px;" src="' + res.data.src + '"></p>');
                layer.msg(res.msg || res.message);
            } else {
                layer.msg(res.message)
            }
        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });


    KindEditor.options.filterMode = false;


    var $titleInput = $("input[name=title]");
    var $urlSeqInput = $("input[name=urlSeq]");

    form.on('checkbox(customUrl)', function (data) {
        if (data.elem.checked) {
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


    var post = function (data, draft, msg) {
        data.field.draft = draft;
        data.field.cateIds = formSelects.value('cateId', 'val');
        data.field.mdContent = "";
        data.field.content = editor.html();
        data.field.cover = $("#productCoverImg").find("img").attr("src") || "";
        var $tags = $("#tags>span");
        var tns = [];
        $tags.each(function (index, elem) {
            tns.push($(elem).find("em").text())
        });
        data.field.tagNames = tns;
        NBV5.post("/management/product/create", data.field);

    };

    //监听提交9
    form.on('submit(postProductSubmit)', function (data) {
        post(data, false, "发布博文成功！");
        return false;
    });


    $("#addRemoveCover").click(function () {
        $("#coverImg").find("img").attr("src", "");
        $("#coverImg").html('  <i class="layui-icon"></i><p>点击上传博文封面，或将封面拖拽到此处</p>');
    });


    exports('product_add', {});
});

$(function () {
    editor = KindEditor.create('#product-editor', {
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
        uploadJson: '/management/upload?reqType=nk&objectKeyCode=2&generateNextArticleId=' + generateNextArticleId,
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
});


