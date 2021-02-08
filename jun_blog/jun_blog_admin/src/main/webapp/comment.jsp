<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新博网博客系统 powerby wujun</title>
    <!-- 有些资源用不上，请自行斟酌 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-media.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/animate.css/3.5.2/animate.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/blog-pace.css" />
    <script data-pace-options='{ "ajax": false ,"eventLag": false}' src="${pageContext.request.contextPath}/res/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/lib/layui/layui.js"></script>
    <!-- 本页特有css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/comment.css">
</head>

<body>
    <!-- 头部 -->
    <nav class="blog-nav layui-header">
        <div class="layui-container">
            <!-- 登陆后 -->
            <!-- <span class="blog-user">
                <a href="">
                    <img src="#" alt="" title="" />
                </a>
            </span> -->
            <!-- 未登陆 -->
            <a href="javacript:;" class="blog-user">
                <i class="fa fa-qq"></i>
            </a>
            <a class="blog-logo" href="index.html">新博网</a>
            <ul class="blog-nav-list" lay-filter="nav">
                <li class="layui-nav-item">
                    <a href="index.html"><i class="fa fa-home fa-fw"></i>&nbsp;网站首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="articlelist.html"><i class="fa fa-book fa-fw"></i>&nbsp;博客列表</a>
                </li>
                <li class="layui-nav-item">
                    <a href="timeline.html"><i class="fa fa-snowflake-o fa-fw"></i>&nbsp;点点滴滴</a>
                </li>
                <li class="layui-nav-item">
                    <a href="production.html"><i class="fa fa-th-large fa-fw"></i>&nbsp;个人作品</a>
                </li>
                <li class="layui-nav-item layui-this">
                    <a href="comment.html"><i class="fa fa-comments fa-fw"></i>&nbsp;留言交流</a>
                </li>
                <li class="layui-nav-item">
                    <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;关于本站</a>
                </li>
            </ul>
            <a class="blog-navicon" href="javascript:;">
                <i class="fa fa-navicon"></i>
            </a>
        </div>
    </nav>
    <!-- 主体 -->
    <div class="blog-body">
        <!-- 页面主体内容 -->
        <div class="layui-container">
            <blockquote class="layui-elem-quote sitemap shadow">
                <i class="layui-icon">&#xe715;</i>
                <span class="layui-breadcrumb" lay-separator=">">
                    <a href="index.html">首页</a>
                    <a><cite>留言交流</cite></a>
                </span>
            </blockquote>
            <div class="layui-row layui-col-space15">
                <!-- 左边 -->
                <div class="layui-col-md8">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md12">
                            <div class="topdiv">
                                <div class="topdiv-figure">
                                    <img src="${pageContext.request.contextPath}/res/images/messagewall.png" />
                                </div>
                                <p class="topdiv-nickname">留言板</p>
                                <p class="topdiv-introduce">可留言、可吐槽、可提问。欢迎灌水，杜绝广告！</p>
                            </div>
                        </div>
                        <div class="layui-col-md12">
                            <div class="comment-editor sr-bottom">
                                <form class="layui-form blog-editor">
                                    <div class="layui-form-item">
                                        <textarea name="content" lay-verify="content" id="remarkEditor"
                                            placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                    </div>
                                    <div class="layui-form-item">
                                        <button class="layui-btn layui-btn-normal layui-btn-sm" lay-submit
                                            lay-filter="formComment">提交留言</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="layui-col-md12">
                            <div class="layui-row layui-col-space8 commentlist">
                                <div class="layui-col-md12">
                                    <!-- 留言 -->
                                    <div class="comment-item sr-bottom">
                                        <div class="comment-item-left">
                                            <div class="useravator" title="张三">
                                                <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="张三" />
                                            </div>
                                            <div class="reply">
                                                <button class="layui-btn layui-btn-xs layui-btn-primary"
                                                    blog-event="commentReply" data-targetname="张三">回复</button>
                                            </div>
                                        </div>
                                        <div class="comment-item-right">
                                            <div class="content">加点来看吉安市劳动局撒讲道理</div>
                                            <p class="createtime">— 来自<span
                                                    style="padding:0 3px;padding-right:10px;color:#0094ff">张三</span>2小时前
                                            </p>
                                        </div>
                                        <hr style="margin-right: 10px;" />
                                        <!-- 留言回复 -->
                                        <div class="comment-reply">
                                            <div class="comment-item-left">
                                                <div class="useravator" title="麻花疼">
                                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="麻花疼" />
                                                </div>
                                            </div>
                                            <div class="comment-item-right">
                                                <div class="content">
                                                    <span style="color:#01aaed;margin-right:5px;">麻花疼：</span><span
                                                        style="color:#ff6a00;margin-right:5px;">张三</span>你说尼玛
                                                </div>
                                                <p class="createtime">2019-10-09<a href="javascript:;"
                                                        style="margin-left:5px;color:#0094ff;vertical-align: middle;display:none;"
                                                        blog-event="commentReply" data-targetname="麻花疼">回复</a></p>
                                            </div>
                                        </div>
                                        <div class="comment-reply">
                                            <div class="comment-item-left">
                                                <div class="useravator" title="麻花疼">
                                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="麻花疼" />
                                                </div>
                                            </div>
                                            <div class="comment-item-right">
                                                <div class="content">
                                                    <span style="color:#01aaed;margin-right:5px;">麻花疼：</span><span
                                                        style="color:#ff6a00;margin-right:5px;">张三</span>你说尼玛
                                                </div>
                                                <p class="createtime">2019-10-09<a href="javascript:;"
                                                        style="margin-left:5px;color:#0094ff;vertical-align: middle;display:none;"
                                                        blog-event="commentReply" data-targetname="麻花疼">回复</a></p>
                                            </div>
                                        </div>
                                        <!-- 留言回复END -->
                                    </div>
                                    <!-- 留言END -->
                                </div>

                                <div class="layui-col-md12">
                                    <!-- 留言 -->
                                    <div class="comment-item sr-bottom">
                                        <div class="comment-item-left">
                                            <div class="useravator" title="张三">
                                                <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="张三" />
                                            </div>
                                            <div class="reply">
                                                <button class="layui-btn layui-btn-xs layui-btn-primary"
                                                    blog-event="commentReply" data-targetname="张三">回复</button>
                                            </div>
                                        </div>
                                        <div class="comment-item-right">
                                            <div class="content">加点来看吉安市劳动局撒讲道理</div>
                                            <p class="createtime">— 来自<span
                                                    style="padding:0 3px;padding-right:10px;color:#0094ff">张三</span>2小时前
                                            </p>
                                        </div>
                                        <hr style="margin-right: 10px;" />
                                        <!-- 留言回复 -->
                                        <div class="comment-reply">
                                            <div class="comment-item-left">
                                                <div class="useravator" title="麻花疼">
                                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="麻花疼" />
                                                </div>
                                            </div>
                                            <div class="comment-item-right">
                                                <div class="content">
                                                    <span style="color:#01aaed;margin-right:5px;">麻花疼：</span><span
                                                        style="color:#ff6a00;margin-right:5px;">张三</span>你说尼玛
                                                </div>
                                                <p class="createtime">2019-10-09<a href="javascript:;"
                                                        style="margin-left:5px;color:#0094ff;vertical-align: middle;display:none;"
                                                        blog-event="commentReply" data-targetname="麻花疼">回复</a></p>
                                            </div>
                                        </div>
                                        <!-- 留言回复END -->
                                    </div>
                                    <!-- 留言END -->
                                </div>

                                <div class="layui-col-md12">
                                    <!-- 留言 -->
                                    <div class="comment-item sr-bottom">
                                        <div class="comment-item-left">
                                            <div class="useravator" title="张三">
                                                <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="张三" />
                                            </div>
                                            <div class="reply">
                                                <button class="layui-btn layui-btn-xs layui-btn-primary"
                                                    blog-event="commentReply" data-targetname="张三">回复</button>
                                            </div>
                                        </div>
                                        <div class="comment-item-right">
                                            <div class="content">加点来看吉安市劳动局撒讲道理</div>
                                            <p class="createtime">— 来自<span
                                                    style="padding:0 3px;padding-right:10px;color:#0094ff">张三</span>2小时前
                                            </p>
                                        </div>
                                    </div>
                                    <!-- 留言END -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 左边END -->
                <!-- 右边 -->
                <div class="layui-col-md4">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-md-12 layui-hide-xs">
                            <div class="layui-card chatroom sr-bottom">
                                <div class="layui-card-header">聊天室<span class="status" title="离线"><i
                                            class="fa fa-circle"></i></span></div>
                                <div class="scroll">
                                    <div class="layui-card-body chatroom-body">

                                    </div>
                                    <div id="msg_end" style="height:0px; overflow:hidden"></div>
                                </div>
                                <div class="chatroom-editor">
                                    <textarea id="chateditor" style="display: none;"></textarea>
                                </div>
                                <div class="chatroom-send">
                                    <button class="layui-btn layui-btn-xs layui-btn-primary"
                                        comment-event="chatsend">发送</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 右边END -->
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <jsp:include page="particle/foot.jsp"></jsp:include>
    <!-- 移动端侧边导航 -->
    <ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
        <li class="layui-nav-item">
            <a href="index.html"><i class="fa fa-home fa-fw"></i>&nbsp;网站首页</a>
        </li>
        <li class="layui-nav-item">
            <a href="articlelist.html"><i class="fa fa-book fa-fw"></i>&nbsp;博客列表</a>
        </li>
        <li class="layui-nav-item">
            <a href="timeline.html"><i class="fa fa-snowflake-o fa-fw"></i>&nbsp;点点滴滴</a>
        </li>
        <li class="layui-nav-item">
            <a href="production.html"><i class="fa fa-th-large fa-fw"></i>&nbsp;个人作品</a>
        </li>
        <li class="layui-nav-item layui-this">
            <a href="comment.html"><i class="fa fa-comments fa-fw"></i>&nbsp;留言交流</a>
        </li>
        <li class="layui-nav-item">
            <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;关于本站</a>
        </li>
    </ul>
    <!-- 侧边导航遮罩 -->
    <div class="blog-mask animated layui-hide"></div>

    <script src="https://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>

    <!-- 本页特有 -->
    <script id="commentTpl" type="text/html">
        {{#  layui.each(d, function(index, item){ }}
        <div class="layui-col-md12">
            <div class="comment-item sr-bottom byflow">
                <div class="comment-item-left">
                    <div class="useravator" title="{{ item.user.name }}">
                        <img src="{{ item.user.avatar }}" alt="{{ item.user.name }}" />
                    </div>
                    <div class="reply">
                        <button class="layui-btn layui-btn-xs layui-btn-primary" blog-event="commentReply"
                            data-id="{{ item.id }}" data-targetid="{{ item.userId }}"
                            data-targetname="{{ item.user.name }}">回复</button>
                    </div>
                </div>
                <div class="comment-item-right">
                    <div class="content">
                        {{ item.content }}
                    </div>
                    <p class="createtime">— 来自<span
                            style="padding:0 3px;padding-right:10px;color:#0094ff">{{ item.user.name }}</span>{{ layui.util.timeAgo(item.createTime, false) }}
                    </p>
                </div>
                {{# if(item.commentReplys.length > 0){ }}
                <hr style="margin-right: 10px;" />
                {{#  } }}

                {{#  layui.each(item.commentReplys, function(index, reply){ }}
                <div class="comment-reply">
                    <div class="comment-item-left">
                        <div class="useravator" title="{{ reply.user.name }}">
                            <img src="{{ reply.user.avatar }}" alt="{{ reply.user.name }}" />
                        </div>
                    </div>
                    <div class="comment-item-right">
                        <div class="content">
                            <span style="color:#01aaed;margin-right:5px;">{{ reply.user.name }}：</span><span
                                style="color:#ff6a00;margin-right:5px;">@@{{ reply.targetUser.name }}</span>{{ reply.content }}
                        </div>
                        <p class="createtime">{{ layui.util.timeAgo(reply.createTime, false) }}<a href="javascript:;"
                                style="margin-left:5px;color:#0094ff;vertical-align: middle;display:none;"
                                blog-event="commentReply" data-id="{{ item.id }}" data-targetid="{{ reply.userId }}"
                                data-targetname="{{ reply.user.name }}">回复</a></p>
                    </div>
                </div>
                {{#  }); }}
            </div>
        </div>
        {{#  }); }}
    </script>
    <script id="statusSelHtml" type="text/html">
        <div class="status-select">
            <ul>
                <li data-status="online"><i class="fa fa-circle" style="color:#09F175;margin-right:10px;"></i>在线</li>
                <li data-status="offline"><i class="fa fa-circle" style="color:#ADADAD;margin-right:10px;"></i>离线</li>
            </ul>
        </div>
    </script>
    <script id="chatMsgTpl" type="text/html">
        <div class="msg {{=d.class }}">
            <div class="msg-user">
                <img src="{{=d.userAvatar }}" />
            </div>
            <div class="msg-text">
                <p class="name">{{ d.userName }}</p>
                <div class="content">
                    {{ d.msg }}
                </div>
            </div>
        </div>
    </script>
    <script src="${pageContext.request.contextPath}/res/js/comment.js"></script>
</body>

</html>