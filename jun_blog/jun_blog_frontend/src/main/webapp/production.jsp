<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
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
    <!-- 本页特有样式 -->
    <style>
        .production-box {
            background-color: #fff;
            -moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.18);
            -webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.18);
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.18);
        }

        .production-cover {
            padding: 3px;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            border-bottom: 1px solid #e2e2e2;
        }

        .production-cover img {
            width: 100%;
        }

        .production-title {
            text-align: center;
            margin-top: 7px;
            margin-bottom: 5px;
        }

        .production-title>a {
            font-size: 16px;
            font-weight: bold;
            line-height: 18px;
            color: #01AAED;
        }

        .production-abstract {
            padding: 3px;
            text-align: center;
            font-size: smaller;
            color: #838383;
            height: 25px;
            overflow: hidden;
        }

        .production-footer {
            font-size: small;
            border-top: 1px solid #e7e7e7;
            box-shadow: 0 1px 0 0 #fff inset;
            color: #8B8B8C;
            height: 50px;
            line-height: 50px;
            text-align: center;
        }

        .production-footer i {
            font-size: 12px !important;
        }
    </style>
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
                <li class="layui-nav-item layui-this">
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
                    <a><cite>个人作品</cite></a>
                </span>
            </blockquote>
            <div class="layui-row layui-col-space15">
                    <div class="layui-col-lg3 layui-col-sm4">
                        <div class="production-box">
                            <div class="production-cover">
                                <a href="http://github.com/wujun728" target="_blank">
                                    <img src="${pageContext.request.contextPath}/res/images/product.jpg" alt="jun_plugin开发组件(Java)">
                                </a>
                            </div>
                            <h1 class="production-title"><a href="http://github.com/wujun728" target="_blank">jun_plugin快速开发组件(Java)</a></h1>
                            <p class="production-abstract">jun_plugin快速开发组件(Java)</p>
                            <div class="production-footer">
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728" target="_blank"><i class="fa fa-eye fa-fw"></i>查看</a>
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728"  target="_blank"><i class="fa fa-download fa-fw"></i>下载</a>
                            </div>
                        </div>
                    </div>
                     
                     <div class="layui-col-lg3 layui-col-sm4">
                        <div class="production-box">
                            <div class="production-cover">
                                <a href="http://github.com/wujun728" target="_blank">
                                    <img src="${pageContext.request.contextPath}/res/images/product.jpg" alt="jun_spring开发组件(Java)">
                                </a>
                            </div>
                            <h1 class="production-title"><a href="http://github.com/wujun728" target="_blank">jun_spring开发组件(Java)</a></h1>
                            <p class="production-abstract">jun_spring开发组件(Java)</p>
                            <div class="production-footer">
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728" target="_blank"><i class="fa fa-eye fa-fw"></i>查看</a>
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728"  target="_blank"><i class="fa fa-download fa-fw"></i>下载</a>
                            </div>
                        </div>
                    </div>
                     <div class="layui-col-lg3 layui-col-sm4">
                        <div class="production-box">
                            <div class="production-cover">
                                <a href="http://github.com/wujun728" target="_blank">
                                    <img src="${pageContext.request.contextPath}/res/images/product.jpg" alt="jun_springcloud开发组件(Java)">
                                </a>
                            </div>
                            <h1 class="production-title"><a href="http://github.com/wujun728" target="_blank">jun_springcloud开发组件(Java)</a></h1>
                            <p class="production-abstract">jun_springcloud开发组件(Java)</p>
                            <div class="production-footer">
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728" target="_blank"><i class="fa fa-eye fa-fw"></i>查看</a>
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728"  target="_blank"><i class="fa fa-download fa-fw"></i>下载</a>
                            </div>
                        </div>
                    </div>
                     <div class="layui-col-lg3 layui-col-sm4">
                        <div class="production-box">
                            <div class="production-cover">
                                <a href="http://github.com/wujun728" target="_blank">
                                    <img src="${pageContext.request.contextPath}/res/images/product.jpg" alt="jun_plugin开发框架(Java)">
                                </a>
                            </div>
                            <h1 class="production-title"><a href="http://github.com/wujun728" target="_blank">jedp_framework开发框架(Java)</a></h1>
                            <p class="production-abstract">jedp_framework开发框架(Java)</p>
                            <div class="production-footer">
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728" target="_blank"><i class="fa fa-eye fa-fw"></i>查看</a>
                                <a class="layui-btn layui-btn-sm layui-btn-primary" href="https://github.com/wujun728"  target="_blank"><i class="fa fa-download fa-fw"></i>下载</a>
                            </div>
                        </div>
                    </div>
                     
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
        <li class="layui-nav-item layui-this">
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

    <script src="https://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>
</body>

</html>