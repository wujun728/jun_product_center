<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新博网博客系统Powerby wujun</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-media.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/animate.css/3.5.2/animate.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/blog-pace.css" />
    <script data-pace-options='{ "ajax": false ,"eventLag": false}' src="${pageContext.request.contextPath}/res/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/lib/layui/layui.js"></script>
    <!-- 本页特有css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/timeline.css">
    <!-- 本页特有js -->
    <script src="${pageContext.request.contextPath}/res/js/modernizr.js"></script>
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
                <li class="layui-nav-item layui-this">
                    <a href="timeline.html"><i class="fa fa-snowflake-o fa-fw"></i>&nbsp;点点滴滴</a>
                </li>
                <li class="layui-nav-item">
                    <a href="production.html"><i class="fa fa-th-large fa-fw"></i>&nbsp;个人作品</a>
                </li>
                <li class="layui-nav-item">
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
                    <a><cite>轻言细语</cite></a>
                </span>
            </blockquote>
            <div class="blog-nav-two shadow">
                <div class="layui-breadcrumb" lay-separator="|">
                    <a href="timeline.html" class="selected"><span>轻言细语</span></a>
                    <a href="record.html"><span>文章归档</span></a>
                    <a href="note.html"><span>笔记标签</span></a>
                </div>
            </div>
            <div class="blog-panel">
                <section id="cd-timeline" class="cd-container">
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-picture">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-picture.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>图标1</h2>
                            <p>新博网2.0前端模板开源啦</p>
                            <a href="http://www.nb666.net" class="cd-read-more">阅读更多</a>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-movie">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-movie.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>图标2</h2>
                            <p>新博网2.0前端模板开源啦</p>
                            <a href="http://www.nb666.net" class="cd-read-more">阅读更多</a>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-location">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-location.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>图标3</h2>
                            <p>新博网2.0前端模板开源啦</p>
                            <a href="http://www.nb666.net" class="cd-read-more">阅读更多</a>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-movie">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-location.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>不带链接</h2>
                            <p>新博网2.0前端模板开源啦</p>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-movie">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-location.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>不带内容</h2>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-movie">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-location.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <p>不带标题</p>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                    <div class="cd-timeline-block">
                        <div class="cd-timeline-img cd-picture">
                            <img src="${pageContext.request.contextPath}/res/images/cd-icon-picture.svg" alt="Location">
                        </div>
                        <div class="cd-timeline-content">
                            <h2>新博网2.0</h2>
                            <p>新博网2.0开源啦哈哈哈哈哈哈哈哈</p>
                            <span class="cd-date">2019-10-09</span>
                        </div>
                    </div>
                </section>
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
        <li class="layui-nav-item layui-this">
            <a href="timeline.html"><i class="fa fa-snowflake-o fa-fw"></i>&nbsp;点点滴滴</a>
        </li>
        <li class="layui-nav-item">
            <a href="production.html"><i class="fa fa-th-large fa-fw"></i>&nbsp;个人作品</a>
        </li>
        <li class="layui-nav-item">
            <a href="comment.html"><i class="fa fa-comments fa-fw"></i>&nbsp;留言交流</a>
        </li>
        <li class="layui-nav-item">
            <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;关于本站</a>
        </li>
    </ul>
    <!-- 侧边导航遮罩 -->
    <div class="blog-mask animated layui-hide"></div>

    <script src="http://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>
    <!-- 本页特有js -->
    <script>
        layui.use('jquery', function () {
            var $ = layui.jquery;
            $(function () {
                var $timeline_block = $('.cd-timeline-block');
                $timeline_block.each(function () {
                    if ($(this).offset().top > $(window).scrollTop() + $(window).height() *
                        0.75) {
                        $(this).find('.cd-timeline-img, .cd-timeline-content').addClass(
                            'is-hidden');
                    }
                });
                //时光轴根据滚动条动画展示
                $(window).on('scroll', function () {
                    $timeline_block.each(function () {
                        if ($(this).offset().top <= $(window).scrollTop() + $(window)
                            .height() * 0.75 && $(this).find('.cd-timeline-img')
                            .hasClass('is-hidden')) {
                            $(this).find('.cd-timeline-img, .cd-timeline-content')
                                .removeClass('is-hidden').addClass('bounce-in');
                        }
                    });
                });
            });
        });
    </script>
</body>

</html>