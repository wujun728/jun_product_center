<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新博网博客系统 powerby wujun 11</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/lib/font-awesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-animate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/site-media.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/animate.css/3.5.2/animate.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/blog-pace.css" />
    <script data-pace-options='{ "ajax": false ,"eventLag": false}' src="${pageContext.request.contextPath}/res/js/pace.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/lib/layui/layui.js"></script>
    <style>
        @media (max-width: 768px) {
            .layui-hide-xs {
                display: none !important;
            }
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
                <li class="layui-nav-item layui-this">
                    <a href="articlelist.html"><i class="fa fa-book fa-fw"></i>&nbsp;博客列表</a>
                </li>
                <li class="layui-nav-item">
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
                <i class="layui-icon"></i>
                <span class="layui-breadcrumb" lay-separator=">" style="visibility: visible;">
                    <a href="/">首页</a><span lay-separator="">&gt;</span>
                    <a><cite>学海无涯</cite></a>
                </span>
            </blockquote>
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md8">
                	<jsp:include page="${mainPage }"></jsp:include>
                </div>
                
                <div class="layui-col-md4">
                    <div class="layui-row layui-col-space10">
<!--                         <div class="layui-col-sm6 layui-col-md12 layui-hide-xs layui-hide-sm"> -->
							<div class="layui-col-sm6 layui-col-md12 padding0">
	                            <div class="blog-search">
	                                <form class="layui-form" action="">
	                                    <div class="layui-form-item">
	                                        <div class="search-keywords  shadow">
	                                            <input type="text" name="keywords" lay-verify="required"
	                                                placeholder="输入关键词搜索" autocomplete="off" class="layui-input">
	                                        </div>
	                                        <div class="search-submit  shadow">
	                                            <a class="search-btn" lay-submit="formSearch" lay-filter="formSearch"><i
	                                                    class="fa fa-search"></i></a>
	                                        </div>
	                                    </div>
	                                </form>
	                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12 padding0">
                            <div class="article-category shadow">
                                <div class="article-category-title">博主信息</div>
                                <div class="data_list">
										<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/>
									<div class="user_image">
<%-- 										<img src="${pageContext.request.contextPath}/static/userImages/ABC"/> --%>
<%-- 										<img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}"/> --%>
									</div>
									<div class="nickName">${blogger.nickName }</div>
									<div class="userSign">(${blogger.sign })</div>
								</div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12 padding0">
                            <div class="article-category shadow">
                                <div class="article-category-title">分类导航</div>
                                <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
                                	<a href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a>
								</c:forEach>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12 padding0">
                            <div class="article-category shadow">
                                <div class="article-category-title">日期导航</div>
								<c:forEach var="blogCount" items="${blogCountList }">
									<a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a>
								</c:forEach>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true"></i></span>
                                    <span class="text">作者推荐</span>
                                </div>
                                <ul class="blog-card-ul">
                                    <li>
                                        <span class="layui-badge ">1</span><a href="detail.html"
                                            title=".NET Core 3.0正式版发布">.NET Core 3.0正式版发布</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">2</span><a href="detail.html"
                                            title="JS动画效果之妙用Animate.css">JS动画效果之妙用Animate.css</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">3</span><a href="detail.html"
                                            title="使用ASP.NET Core SignalR搭建聊天室（本站聊天室）">使用ASP.NET Core
                                            SignalR搭建聊天室（本站聊天室）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">4</span><a href="detail.html"
                                            title="小程序黑科技之获取手机号码、通讯地址、地理位置">小程序黑科技之获取手机号码、通讯地址、地理位置</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">5</span><a href="detail.html"
                                            title="ASP.NET Core第三方认证之QQ登录">ASP.NET Core第三方认证之QQ登录</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow sr-rightmodule">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true"></i></span>
                                    <span class="text">随便看看</span>
                                </div>
                                <ul class="blog-card-ul">
                                    <li>
                                        <span class="layui-badge ">1</span><a href="detail.html"
                                            title="Xamarin.Forms移动开发系列1：介绍和安装">Xamarin.Forms移动开发系列1：介绍和安装</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">2</span><a href="detail.html"
                                            title="Visual Studio Code（VS Code）设置为中文">Visual Studio Code（VS
                                            Code）设置为中文</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">3</span><a href="detail.html"
                                            title="使用ASP.NET Core SignalR搭建聊天室（本站聊天室）">使用ASP.NET Core
                                            SignalR搭建聊天室（本站聊天室）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">4</span><a href="detail.html"
                                            title="博客升级到.NET Core 3.0的变化和问题">博客升级到.NET Core 3.0的变化和问题</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">5</span><a href="detail.html"
                                            title="JS动画效果之妙用Animate.css">JS动画效果之妙用Animate.css</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">6</span><a href="detail.html"
                                            title="ASP.NET Core第三方认证之QQ登录">ASP.NET Core第三方认证之QQ登录</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">7</span><a href="detail.html"
                                            title="Xamarin.Forms移动开发系列3：项目剖析">Xamarin.Forms移动开发系列3：项目剖析</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">8</span><a href="detail.html"
                                            title="后台可以管理文章了，接下来开始做博客前台。">后台可以管理文章了，接下来开始做博客前台。</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow sr-rightmodule" data-scroll-reveal>
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe64c;</i></span>
                                    <span class="text">友情链接</span>
                                </div>
                                <ul class="blogroll">
	                                <c:forEach var="link" items="${linkList }">
										<li><a target="_blank" href="${link.linkUrl }" title="${link.linkName }">${link.linkName }</a></li>
									</c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--右边悬浮 平板或手机设备显示-->
                    <div class="category-toggle"><i class="fa fa-chevron-left"></i></div>
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
        <li class="layui-nav-item layui-this">
            <a href="articlelist.html"><i class="fa fa-book fa-fw"></i>&nbsp;学海无涯</a>
        </li>
        <li class="layui-nav-item">
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

    <script src="https://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>
</body>

</html>