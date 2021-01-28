/* 全局js
 * @Author: Leo 
 * @Date: 2019-10-09 11:36:17 
 * @Last Modified by:   Leo 
 * @Last Modified time: 2019-10-09 11:36:17 
 */
if (window.layui) {
    layui.use(['element', 'layer', 'form', 'util', 'flow', 'layedit'], function () {
        var element = layui.element
            , form = layui.form
            , util = layui.util
            , flow = layui.flow
            , $ = layui.jquery
            , layedit = layui.layedit
            , device = layui.device()
            //评论页面和留言页面共用
            , editIndex = layedit.build('remarkEditor', {
                height: 150,
                tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
            });

        var blog = {
            openBlogHelper: function () {
                //打开博客助手
                var area, shade;
                if (device.android || device.ios) {
                    area = ['100vw', '100vh'];
                    shade = 0;
                } else {
                    area = ['375px', '667px'];
                    shade = 0.8;
                }
                layer.open({
                    type: 2,
                    title: false,
                    closeBtn: 0,
                    area: area,
                    shade: shade,
                    scrollbar: false,
                    isOutAnim: false,
                    anim: -1,
                    resize: false,
                    move: false,
                    shadeClose: true,
                    skin: 'blogzone animated flipInY',
                    content: '/user/zone',
                    success: function (layero, index) {
                        blog.closeBlogHelper = function () {
                            $(layero).removeClass('flipInY').addClass('flipOutY');
                            setTimeout(function () {
                                layer.close(index);
                            }, 500);
                        }
                    }
                });
            }
            
            //单击事件定义
            , events: {
                //打开留言回复div
                commentReply: function () {
                    var commentId = $(this).data('id')
                        , targetId = $(this).data('targetid')
                        , targetName = $(this).data('targetname')
                        , $comment = $(this).parents('.comment-item');
                    //移除原有的回复编辑器
                    $('#commentReplyEritor').remove();
                    $('.comment-item').find('.btnDiv,.layui-layedit').remove();
                    //拼接新的编辑器
                    $comment.append('<textarea id="commentReplyEritor" style="display: none;"></textarea><div class="btnDiv" style="margin-left:-60px;padding:10px;"><button style="border-radius:0;" class="layui-btn layui-btn-xs layui-btn-normal">确定</button></div>');
                    var editor = layedit.build('commentReplyEritor', {
                        height: 80
                        , tool: ['face', '|', 'link']
                    });
                    $('.comment-item .layui-layedit-tool').append('<span style="float: right;margin-right:5px;margin-top: 3px;font-size: 12px;color: #ff5722;">@' + targetName + '</span>');
                    $('.comment-item').find('.btnDiv,.layui-layedit').click(function (e) {
                        layui.stope(e);
                    });
                    //绑定确定按钮单击事件
                    $('#commentReplyEritor').siblings('.btnDiv').find('.layui-btn').on('click', function () {
                        var content = layedit.getContent(editor);
                        if (content == "" || new RegExp("^[ ]+$").test(content)) {
                            layer.msg('至少得有一个字吧', { shift: 6 });
                            return;
                        }
                        var loading = layer.load(1);
                        $.ajax({
                            type: 'post'
                            , url: '/api/comment/reply'
                            , data: { remarkId: commentId, targetUserId: targetId, replyContent: content }
                            , success: function (res) {
                                layer.close(loading);
                                if (res.code === 1) {
                                    layer.msg('回复成功', { icon: 6 });
                                    var reply = res.data;
                                    //移除回复编辑器
                                    $('#commentReplyEritor').remove();
                                    $('.comment-item').find('.btnDiv,.layui-layedit').remove();
                                    //拼接当前回复div
                                    var html = '<div class="comment-reply"><div class="comment-item-left"><div class="useravator" title="{UserName}"><img src="{UserAvatar}" alt="{UserName}"></div></div><div class="comment-item-right"><div class="content"><span style="color:#01aaed;margin-right:5px">{UserName}：</span><span style="color:#ff6a00;margin-right:5px">@{TargetUserName}</span>{Content}</div><p class="createtime">{CreateTime}<a href="javascript:;" style="margin-left:5px;color:#0094ff;vertical-align:middle;display:none" blog-event="commentReply" data-id="{Id}" data-targetid="{UserId}" data-targetname="{UserName}">回复</a></p></div></div>'.replace(/{UserName}/g, reply.user.name).replace(/{UserId}/g, reply.user.id).replace(/{UserAvatar}/g, reply.user.avatar).replace(/{TargetUserName}/g, reply.targetUser.name).replace(/{Id}/g, reply.commentId).replace(/{Content}/g, reply.content).replace(/{CreateTime}/g, util.timeAgo(reply.createTime, false));

                                    $comment.append(html);
                                    //绑定事件
                                    $comment.find('.comment-reply:last').on('mouseover', function () {
                                        $(this).find('.createtime a').show();
                                    }).on('mouseout', function () {
                                        $(this).find('.createtime a').hide();
                                    });
                                    $comment.find('.comment-reply:last').find('*[blog-event]').on('click', function () {
                                        var eventName = $(this).attr('blog-event');
                                        typeof blog.events[eventName] == 'function' && blog.events[eventName].call(this);
                                    });
                                    $comment.find('.comment-reply:last').find('.createtime a').click(function (e) {
                                        layui.stope(e);
                                    });
                                } else {
                                    layer.msg(res.msg, { shift: 6, icon: 5 });
                                }
                            }
                            , error: function (e) {
                                layer.close(loading);
                                layer.msg('程序出错了', { shift: 6, icon: 5 });
                            }
                        });
                    });
                }
            }
        };

        window.blog = blog;

        //单击事件绑定
        $('*[blog-event]').on('click', function () {
            var eventName = $(this).attr('blog-event');
            typeof blog.events[eventName] == 'function' && blog.events[eventName].call(this);
        });

        if (layui.cache.user) {
            //工具块
            util.fixbar({
                bar1: "&#xe611;",
                click: function (type) {
                    if (type === 'bar1') {
                        blog.openBlogHelper();
                    }
                }
            });
            //获取未读消息数量
            $.get('/api/user/getunreadmsgcnt', function (res) {
                if (res.code === 1 && res.data !== 0) {
                    var $elemUser = $('.blog-user');
                    var $msg;
                    if (device.android || device.ios) {
                        $msg = $('<span style="margin-left:8px;cursor:pointer;" class="blog-msg layui-badge">' + res.data + '</span>');
                        $elemUser.append($msg);
                    } else {
                        $msg = $('<span style="margin-right:8px;cursor:pointer;" class="blog-msg layui-badge">' + res.data + '</span>');
                        $elemUser.prepend($msg);
                    }

                    $msg.on('click', blog.openBlogHelper);

                    layer.tips('你有 ' + res.data + ' 条未读消息', $msg, {
                        tips: 3
                        , tipsMore: true
                        , fixed: true
                    });
                    $msg.on('mouseenter', function () {
                        layer.closeAll('tips');
                    })
                }
            });
        } else {
            util.fixbar({});
        }

        $(function () {
            //滑动显示
            if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))) {
                window.sr = new ScrollReveal({ reset: false });
                sr.reveal('.sr-left', {
                    origin: 'left'
                    , scale: 1
                    , opacity: .1
                    , duration: 1200
                });
                sr.reveal('.sr-bottom', {
                    scale: 1
                    , opacity: .1
                    , distance: '60px'
                    , duration: 1000
                });
                sr.reveal('.sr-listshow', {
                    distance: '30px'
                    , duration: 1000
                    , scale: 1
                    , opacity: .1
                });
                sr.reveal('.sr-rightmodule', {
                    origin: 'top'
                    , distance: '60px'
                    , duration: 1000
                    , scale: 1
                    , opacity: .1
                });
            };

            //封面预览
            layer.photos({
                photos: '.article-left'
                , anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
            });
            layer.photos({
                photos: '.article-detail-content'
                , anim: 5
                , move: false
            });

        });

        //监听评论提交
        form.on('submit(formRemark)', function (data) {
            if ($(data.elem).hasClass('layui-btn-disabled'))
                return false;
            var index = layer.load(1);
            $.ajax({
                type: 'post',
                url: '/api/article/remark',
                data: data.field,
                success: function (res) {
                    layer.close(index);
                    if (res.code === 1) {
                        layer.msg(res.msg, { icon: 6 });
                        location.reload(true);
                    } else {
                        if (res.msg != undefined) {
                            layer.msg(res.msg, { icon: 5 });
                        } else {
                            layer.msg('程序异常，请重试或联系作者', { icon: 5 });
                        }
                    }
                },
                error: function (e) {
                    layer.close(index);
                    if (e.status === 401) {
                        layer.msg("请先登录", { shift: 6, icon: 5 });
                    } else {
                        layer.msg("请求异常", { shift: 6, icon: 2 });
                    }
                }
            });
            return false;
        });

        //监听留言提交
        form.on('submit(formComment)', function (data) {
            var loading = layer.load(1);
            $.ajax({
                type: 'post',
                url: '/api/comment/add',
                data: data.field,
                success: function (res) {
                    layer.close(loading);
                    if (res.code === 1) {
                        layer.msg(res.msg, { icon: 6 });
                        var comment = res.data
                            , html = '<div class="layui-col-md12"><div class="comment-item sr-bottom animated slideInDown"><div class="comment-item-left"><div class="useravator" title="{UserName}"><img src="{UserAvatar}" alt="{UserName}"></div><div class="reply"><button class="layui-btn layui-btn-xs layui-btn-primary" blog-event="commentReply" data-id="{Id}" data-targetid="{UserId}" data-targetname="{UserName}">回复</button></div></div><div class="comment-item-right"><div class="content">{Content}</div><p class="createtime">— 来自<span style="padding:0 3px;padding-right:10px;color:#0094ff">{UserName}</span>{CreateTime}</p></div></div></div>'.replace(/{UserName}/g, comment.user.name).replace(/{UserId}/g, comment.user.id).replace(/{UserAvatar}/g, comment.user.avatar).replace(/{Id}/g, comment.id).replace(/{Content}/g, comment.content).replace(/{CreateTime}/g, util.timeAgo(comment.createTime, false));
                        $('.commentlist').prepend(html);
                        //重置编辑器
                        $('#remarkEditor').val('');
                        $('.blog-editor .layui-layedit').remove();
                        editIndex = layedit.build('remarkEditor', {
                            height: 150,
                            tool: ['face', '|', 'left', 'center', 'right', '|', 'link'],
                        });
                        //绑定事件
                        $('.commentlist').find('.comment-item:first').on('mouseover', function () {
                            $(this).find('.reply').addClass('layui-show');
                        }).on('mouseout', function () {
                            $(this).find('.reply').removeClass('layui-show');
                        });
                        $('.commentlist').find('.comment-item:first').find('*[blog-event]').on('click', function () {
                            var eventName = $(this).attr('blog-event');
                            typeof blog.events[eventName] == 'function' && blog.events[eventName].call(this);
                        });
                        $('.commentlist').find('.comment-item:first').find('.reply').click(function (e) {
                            layui.stope(e);
                        });
                    } else {
                        if (res.msg != undefined) {
                            layer.msg(res.msg, { icon: 5 });
                        } else {
                            layer.msg('程序异常，请重试或联系作者', { icon: 5 });
                        }
                    }
                },
                error: function (e) {
                    layer.close(loading);
                    if (e.status === 401) {
                        layer.msg("请先登录", { shift: 6, icon: 5 });
                    } else {
                        layer.msg("请求异常", { shift: 6, icon: 2 });
                    }
                }
            });
            return false;
        });

        //监听搜索提交
        form.on('submit(formSearch)', function (data) {
            location.href = "/Article/Search?keywords=" + escape(data.field.keywords);
            return false;
        });

        //自定义验证规则
        form.verify({
            content: function (value) {
                value = $.trim(layedit.getText(editIndex));
                if (value == "") return "自少得有一个字吧";
                layedit.sync(editIndex);
            },
            replyContent: function (value) {
                if (value == "") return "自少得有一个字吧";
            }
        });

        //用户评论tips
        var tips;
        $('.remark-user-avator').on('mouseover', function () {
            tips = layer.tips('来自【' + $(this).data('name') + '】的评论', this, {
                tips: 4,
                time: 1000
            });
        });

        $(".blog-user img").hover(function () {
            var tips = layer.tips('点击退出', '.blog-user img', {
                tips: [3, '#009688'],
            });
        }, function () {
            layer.closeAll('tips');
        });

        $('.blog-navicon').click(function () {
            var sear = new RegExp('layui-hide');
            if (sear.test($('.blog-nav-left').attr('class'))) {
                leftIn();
            } else {
                leftOut();
            }
        });

        $('.blog-mask').click(function () {
            leftOut();
        });

        $('.blog-body,.blog-footer').click(function () {
            categoryOut();
        });

        $('.category-toggle').click(function (e) {
            e.stopPropagation();
            categroyIn();
        });

        $('.article-category').click(function () {
            categoryOut();
        });

        $('.article-category > a').click(function (e) {
            e.stopPropagation();
        });

        function categroyIn() {
            $('.category-toggle').addClass('layui-hide');
            $('.article-category').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

            $('.article-category').removeClass('categoryOut');
            $('.article-category').addClass('categoryIn');
            $('.article-category').addClass('layui-show');
        }

        function categoryOut() {
            $('.article-category').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.article-category').removeClass('layui-show');
                $('.category-toggle').removeClass('layui-hide');
            });

            $('.article-category').removeClass('categoryIn');
            $('.article-category').addClass('categoryOut');
        }



        function leftIn() {
            $('.blog-mask').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');
            $('.blog-nav-left').unbind('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend');

            $('.blog-mask').removeClass('maskOut');
            $('.blog-mask').addClass('maskIn');
            $('.blog-mask').removeClass('layui-hide');
            $('.blog-mask').addClass('layui-show');

            $('.blog-nav-left').removeClass('leftOut');
            $('.blog-nav-left').addClass('leftIn');
            $('.blog-nav-left').removeClass('layui-hide');
            $('.blog-nav-left').addClass('layui-show');
        }

        function leftOut() {
            $('.blog-mask').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.blog-mask').addClass('layui-hide');
            });
            $('.blog-nav-left').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
                $('.blog-nav-left').addClass('layui-hide');
            });

            $('.blog-mask').removeClass('maskIn');
            $('.blog-mask').addClass('maskOut');
            $('.blog-mask').removeClass('layui-show');

            $('.blog-nav-left').removeClass('leftIn');
            $('.blog-nav-left').addClass('leftOut');
            $('.blog-nav-left').removeClass('layui-show');
        }

        layui.blog = blog;
    });
}




//时间格式化
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
    str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}