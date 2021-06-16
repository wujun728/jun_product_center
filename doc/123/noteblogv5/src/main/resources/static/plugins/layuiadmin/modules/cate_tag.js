/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(["admin"], function (exports) {
    var $ = layui.$,
        admin = layui.admin;

    $("#tag-search,#cate-search,#download-cate-search").click(function () {
        var sc = $("input[name=cateName]").val();
        var st = $("input[name=tagName]").val();
        var sd = $("input[name=downloadCateName]").val();
        location.href = "/management/dict/catetag?cateName=" + sc + "&tagName=" + st + "&downloadCateName=" + sd;
    });

    $("#cate-add").click(function () {
        var addCate = $("input[name=cateName]").val();
        if (addCate === '') {
            layer.msg("分类名称不能为空！");
        } else {
            admin.req({
                type: "post",
                dataType: "json",
                url: "/management/dict/cate/add",
                data: {
                    cateName: addCate
                },
                success: function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 1000);
                }
            })
        }
    });

    $("#download-cate-add").click(function () {
        var addDownloadCate = $("input[name=downloadCateName]").val();
        if (addDownloadCate === '') {
            layer.msg("下载分类名称不能为空！");
        } else {
            admin.req({
                type: "post",
                dataType: "json",
                url: "/management/dict/downloadCate/add",
                data: {
                    downloadCateName: addDownloadCate
                },
                success: function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 1000);
                }
            })
        }
    });

    $("#tag-add").click(function () {
        var addTag = $("input[name=tagName]").val();
        if (addTag === '') {
            layer.msg("标签名称不能为空！");
        } else {
            admin.req({
                type: "post",
                dataType: "json",
                url: "/management/dict/tag/add",
                data: {
                    tagName: addTag
                },
                success: function (resp) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        if (resp.code === 200) {
                            location.reload();
                        }
                    }, 1000);
                }
            })
        }
    });

    $("div.cates>button>span.delete").click(function () {
        var tbtn = $(this).parent();
        var cid = $(this).attr("data-cid");
        admin.req({
            type: "post",
            dataType: "json",
            url: "/management/dict/cate/delete",
            data: {
                id: cid
            },
            success: function (resp) {
                layer.msg(resp.message);
                setTimeout(function () {
                    if (resp.code === 200) {
                        tbtn.remove();
                        location.reload();
                    }
                }, 1000);
            }
        })
    });

    $("div.downloadCates>button>span.delete").click(function () {
        var tbtn = $(this).parent();
        var cid = $(this).attr("data-cid");
        admin.req({
            type: "post",
            dataType: "json",
            url: "/management/dict/downloadCate/delete",
            data: {
                id: cid
            },
            success: function (resp) {
                layer.msg(resp.message);
                setTimeout(function () {
                    if (resp.code === 200) {
                        tbtn.remove();
                        location.reload();
                    }
                }, 1000);
            }
        })
    });

    $("div.tags>button>span.delete").click(function () {
        var tbtn = $(this).parent();
        var tid = $(this).attr("data-tid");
        admin.req({
            type: "post",
            dataType: "json",
            url: "/management/dict/tag/delete",
            data: {
                id: tid
            },
            success: function (resp) {
                layer.msg(resp.message);
                setTimeout(function () {
                    if (resp.code === 200) {
                        tbtn.remove();
                        location.reload();
                    }
                }, 1000);
            }
        })
    });

    exports('cate_tag', {});
});