/* 留言页js  留言提交代码在site.js
 * @Author: Leo 
 * @Date: 2019-10-09 11:35:08 
 * @Last Modified by: Leo
 * @Last Modified time: 2019-10-09 11:35:28
 */
layui.use(['flow', 'laytpl'], function () {
    var $ = layui.$,
        flow = layui.flow,
        laytpl = layui.laytpl;

    $('.comment-item').on('mouseover', function () {
        $(this).find('.reply').addClass('layui-show');
    }).on('mouseout', function () {
        $(this).find('.reply').removeClass('layui-show');
    });

    $('.comment-reply').on('mouseover', function () {
        $(this).find('.createtime a').show();
    }).on('mouseout', function () {
        $(this).find('.createtime a').hide();
    });

    $('.blog-body').on('click', function (e) {
        //移除回复编辑器
        $('#commentReplyEritor').remove();
        $('.comment-item').find('.btnDiv,.layui-layedit').remove();
    });

    $('.comment-item').find('.btnDiv,.layui-layedit,.createtime a,.reply').click(function (e) {
        layui.stope(e);
    });

    //流加载（从服务器加载Json，不利于SEO，建议换种方式）
    flow.load({
        elem: '.commentlist',
        isAuto: true,
        end: '没有更多了',
        mb: 200,
        done: function (page, next) { //到达临界点（默认滚动触发），触发下一页
            var pages, pageSize = 10;
            var lis = [];
            if (page == 1) {
                next(lis.join(''), page < 999999);
                return;
            }
            $.ajax({
                type: 'get',
                url: '/api/comment/getcommentsbypage',
                data: {
                    pageIndex: page,
                    pageSize: pageSize
                },
                success: function (res) {
                    if (res.code === 1) {
                        var tpl = commentTpl.innerHTML;
                        laytpl(tpl).render(res.data, function (html) {
                            lis.push(html);
                        });
                        pages = (res.count + pageSize - 1) / pageSize;
                        next(lis.join(''), page < pages);

                        //绑定事件
                        sr.reveal('.sr-bottom', {
                            scale: 1,
                            opacity: .1,
                            distance: '60px',
                            duration: 1000
                        });

                        $('.comment-item').off('mouseover').on('mouseover', function () {
                            $(this).find('.reply').addClass('layui-show');
                        }).off('mouseout').on('mouseout', function () {
                            $(this).find('.reply').removeClass('layui-show');
                        });

                        $('.comment-reply').off('mouseover').on('mouseover', function () {
                            $(this).find('.createtime a').show();
                        }).off('mouseout').on('mouseout', function () {
                            $(this).find('.createtime a').hide();
                        });
                        $('.byflow').find('*[blog-event]').on('click', function () {
                            var eventName = $(this).attr('blog-event');
                            typeof blog.events[eventName] == 'function' && blog.events[eventName].call(this);
                        }).removeClass('byflow');
                        $('.comment-item').find('.btnDiv,.layui-layedit,.createtime a,.reply').click(function (e) {
                            layui.stope(e);
                        });
                    } else {
                        layer.msg('获取数据失败', {
                            icon: 2,
                            shift: 6
                        });
                    }
                },
                error: function (e) {
                    layer.msg('获取数据失败', {
                        icon: 2,
                        shift: 6
                    });
                }
            });
        }
    });

});




//聊天代码
layui.use(['jquery', 'layedit', 'laytpl'], function ($, layedit, laytpl) {
    var $chatbody = $('.chatroom-body'),
        chatEditor = layedit.build('chateditor', {
            tool: ['face', 'image'],
            height: '70'
        }),
        systemMsg = function (text) {
            return '<p class="systemmsg">' + text + '</p>';
        },
        resetChatEditor = function () {
            //移除原有编辑器
            $('.chatroom-editor').html('<textarea id="chateditor" style="display: none;"></textarea>');
            //新建编辑器
            chatEditor = layedit.build('chateditor', {
                tool: ['face', 'image'],
                height: '70'
            });
        };

    var events = {
        //聊天室发送按钮点击
        chatsend: function () {
            var content = layedit.getContent(chatEditor);
            if (content == '') {
                $chatbody.append(systemMsg('至少输入一个字吧！'));
                msg_end.scrollIntoView(false);
                return;
            }
            var data = {
                msg: content,
                userAvatar: '../res/images/Leo.jpg',
                userName: 'Leo',
                class: 'msg-mine'
            };
            laytpl(chatMsgTpl.innerHTML).render(data, function (html) {
                $chatbody.append(html);
                msg_end.scrollIntoView(false);
            });
            //重置编辑器
            resetChatEditor();
        }
    };

    //单击事件绑定
    $('*[comment-event]').on('click', function () {
        var eventName = $(this).attr('comment-event');
        typeof events[eventName] == 'function' && events[eventName].call(this);
    });

    $(function () {
        //在线状态切换
        $('.chatroom .layui-card-header .status').on('click', function (e) {
            var html = statusSelHtml.innerHTML,
                self = this;
            layer.open({
                offset: [e.clientY + 20, e.clientX - 20],
                type: 1,
                shade: 0.1,
                shadeClose: true,
                title: false,
                closeBtn: 0,
                content: html,
                success: function (layero, index) {
                    $(layero).find('li').click(function () {
                        var status = $(this).data('status');
                        if (status === 'offline') {
                            //断开连接
                            $(self).removeClass('online').attr('title', '离线');
                            $('.chatroom .layui-card-header .onlineCount').remove();
                        } else if (status === 'online') {
                            //连接聊天服务器
                            var connectLoading = layer.msg('连接聊天服务器中...', {
                                icon: 16
                            });
                            layer.close(connectLoading);
                            $(self).addClass('online').attr('title', '在线');
                            $chatbody.append('<p class="systemmsg">成功连接聊天室</p>');
                            msg_end.scrollIntoView(false);
                        }
                        layer.close(index);
                    });
                }
            });
        });
    });
});