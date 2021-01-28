<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新博网博客系统 powerby wujun</title>
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
        .remark-list {
            min-height: 445px;
        }

        .remark-list li {
            position: relative;
            min-height: 50px;
            margin: 5px 0;
        }

        .remark-list li .user-avator {
            padding: 2px;
            border: 1px solid #ddd;
            position: absolute;
        }

        .remark-list li .remark-content {
            margin-left: 60px;
            border: 1px solid #efefef;
            height: 46px;
            padding: 5px 8px;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            font-size: 12px;
            overflow: hidden;
            line-height: 18px;
            background-color: #efefef;
            color: #666;
        }

        .remark-list li .remark-content::after {
            content: "";
            border: 10px solid rgba(255, 255, 255, 0.00);
            border-bottom: 8px solid rgba(255, 255, 255, 0.00);
            border-top: 8px solid rgba(255, 255, 255, 0.00);
            border-right-color: #efefef;
            position: absolute;
            top: 12px;
            left: 42px;
            display: block;
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
                <li class="layui-nav-item layui-this">
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
        <!-- 首页内容 -->
        <div class="layui-container">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="home-tips shadow">
                        <i style="float:left;line-height:17px;" class="fa fa-volume-up"></i>
                        <div class="home-tips-container">
                            <span style="color: red">新博网前后台框架已正式升级2.0，SpringBoot+LayUI前端框架</span>
                        </div>
                    </div>
                </div>
                <!--左边文章列表-->
                <div class="layui-col-md8">
                    <div class="left-box shadow" style="padding:5px;background-color:#fff;">
                        <!-- 此处Banner宽度需要根据Banner数量来动态设置，容器宽度为100*数量（%），Banner宽度为1/数量*100（%） -->
                        <div class="carousel-box" style="position:relative;background-color: #fff;overflow:hidden">
                            <div style="width:200%" class="banner">
                                <ul>
                                    <li style="width:33%">
                                        <a href="http://www.baidu.com">
                                            <img src="${pageContext.request.contextPath}/res/images/carousel/image1.jpg" />
                                            <p>新博网2.2上线</p>
                                        </a>
                                    </li>
                                    <li style="width:33%">
                                        <a href="http://www.baidu.com">
                                            <img src="${pageContext.request.contextPath}/res/images/carousel/image2.jpg" />
                                            <p>新博网2.2上线</p>
                                        </a>
                                    </li>
                                    <li style="width:33%">
                                        <a href="http://www.baidu.com">
                                            <img src="${pageContext.request.contextPath}/res/images/carousel/image3.jpg" />
                                            <p>新博网2.2上线</p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    
                    <div class="left-box">
                        <p class="left-box-title shadow"><i class="fa fa-diamond fa-fw" aria-hidden="true"></i>置顶博文</p>
                        <!-- 没有数据
                        <div class="emptybox shadow">
                                <p><i style="font-size:50px;color:#5fb878" class="layui-icon">&#xe69c;</i></p>
                                <p>暂时没有任何数据</p>
                        </div>
                         -->
                         <!--  -->
                        <div class="article shadow clearfix sr-listshow">
                            <div class="article-left">
                                <img src="${pageContext.request.contextPath}/res/images/cover/201708252044567037.jpg" alt=".NET Core 3.0正式版发布" />
                            </div>
                            <div class="article-right">
                                <div class="article-title">
                                    <a href="detail.html">.NET Core 3.0正式版发布</a>
                                </div>
                                <div class="article-abstract">
                                    今天凌晨微软在.NET Conf上正式发布了.NET Core 3.0，2018年12月4日，微软推出首个预览版.NET Core 3.0
                                    Preview1，时至今日，.NET Core 3.0正式版终于发布！
                                </div>
                                <div class="article-footer">
                                    <span class="layui-hide-xs"><i class="fa fa-tag" aria-hidden="true"></i>&nbsp;<a
                                            style="color:#009688" href="">.NET
                                            Core</a></span>
                                    <span><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;2019-9-24</span>
                                    <span class="article-viewinfo">99阅读</span>
                                    <span class="article-viewinfo">66评论</span>
                                    <span class="article-viewinfo">8赞</span>
                                    <a class="read layui-btn layui-btn-xs layui-btn-normal layui-hide-xs"
                                        href="detail.html" title="阅读全文：.NET Core 3.0正式版发布">阅读全文</a>
                                </div>
                                <div class="flag flag-left">推荐</div>
                            </div>
                        </div>
                        
                    </div>
		                <jsp:include page="${mainPage }"></jsp:include>
                    <%--
                    <div class="left-box">
                        <p class="left-box-title shadow sr-listshow"><i class="fa fa-signal fa-fw"
                                aria-hidden="true"></i>最新博文
                        </p>
                        <!-- 没有数据 -->
                        <!-- <div class="emptybox shadow">
                                <p><i style="font-size:50px;color:#5fb878" class="layui-icon">&#xe69c;</i></p>
                                <p>暂时没有任何数据</p>
                        </div> -->
                         <div class="article shadow clearfix sr-listshow">
                            <div class="article-left">
                                <img src="${pageContext.request.contextPath}/res/images/cover/201705102148110621.jpg" alt=".NET Core 3.0正式版发布" />
                            </div>
                            <div class="article-right">
                                <div class="article-title">
                                    <a href="detail.html">.NET Core 3.0正式版发布</a>
                                </div>
                                <div class="article-abstract">
                                    		今天凌晨微软在.NET Conf上正式发布了.NET Core 3.0，2018年12月4日，微软推出首个预览版.NET Core 3.0
                                    Preview1，时至今日，.NET Core 3.0正式版终于发布！
                                </div>
                                <div class="article-footer">
                                    <span class="layui-hide-xs"><i class="fa fa-tag" aria-hidden="true"></i>&nbsp;<a
                                            style="color:#009688" href="#">.NET
                                            Core</a></span>
                                    <span><i class="fa fa-clock-o" aria-hidden="true"></i>&nbsp;2019-10-24</span>
                                    <span class="article-viewinfo">99阅读</span>
                                    <span class="article-viewinfo">66评论</span>
                                    <span class="article-viewinfo">8赞</span>
                                    <a class="read layui-btn layui-btn-xs layui-btn-normal layui-hide-xs"
                                        href="detail.html" title="阅读全文：.NET Core 3.0正式版发布">阅读全文</a>
                                </div>
                            </div>
                        </div> 
                    </div>   
                    --%>
                </div>
                
                
                <!--右边小栏目-->
                <div class="layui-col-md4">
                    <div class="layui-row layui-col-space10">
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
<%-- 										<img src="${pageContext.request.contextPath}/static/images/user_icon.png"/> --%>
									<div class="user_image">
<%-- 										<img src="${pageContext.request.contextPath}/static/userImages/ABC"/> --%>
<%-- 										<img src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}"/> --%>
									</div>
<%-- 									<div class="nickName">${blogger.nickName }</div> --%>
<%-- 									<div class="userSign">(${blogger.sign })</div> --%>
								</div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="layui-col-sm12 layui-col-md12">
                            <div class="blogerinfo shadow">
                                <div class="blogerinfo-figure">
                                    <img src="${pageContext.request.contextPath}/res/images/20160804.jpg" alt="Wujun" />
                                </div>
                                <div class="blogerinfo-info">
                                    <p class="blogerinfo-nickname">${blogger.nickName }</p>
                                    <p class="blogerinfo-introduce">${blogger.sign }</p>
                                    <p class="blogerinfo-location"><i class="fa fa-location-arrow"></i>&nbsp;湖北 - 武汉</p>
                                </div>
                                <div class="blogerinfo-contact">
                                    <a target="_blank" title="QQ交流"
                                        href="https://graph.qq.com/oauth2.0/show?which=Login&display=pc&client_id=101019034&response_type=code&scope=get_info%2Cget_user_info&redirect_uri=https%3A%2F%2Fpassport.weibo.com%2Fothersitebind%2Fbind%3Fsite%3Dqq%26state%3DCODE-tc-1KhPiD-3mbWgu-djkiNhgE2raVds85e575d%26bentry%3Dminiblog%26wl%3D&display="><i
                                            class="fa fa-qq fa-2x"></i></a>
                                    <a target="_blank" title="给我写信"
                                        href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=wujun728@hotmail.com"
                                        style="text-decoration:none;"><i class="fa fa-envelope fa-2x"></i></a>
                                    <a target="_blank" title="新浪微博" href="http://weibo.com/laoluoyonghao"><i
                                            class="fa fa-weibo fa-2x"></i></a>
                                    <a target="_blank" title="GitHub" href="https://github.com/wujun728"><i
                                            class="fa fa-github fa-2x"></i></a>
                                </div>
                                <div class="bloginfo-statistics">
                                    <div class="item">
                                        <span>12</span>
                                        <p>博文</p>
                                    </div>
                                    <div class="item">
                                        <span>12</span>
                                        <p>细语</p>
                                    </div>
                                    <div class="item">
                                        <span>12</span>
                                        <p>评论</p>
                                    </div>
                                    <div class="item">
                                        <span>12</span>
                                        <p>留言</p>
                                    </div>
                                </div>
                                <div class="bloginfo-runtime">博客已运行<span style="margin-left:4px;">583天14时17分38秒</span>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe756;</i></span>
                                    <span class="text">热文排行</span>
                                </div>
                                <ul class="blog-card-ul">
                                    <li>
                                        <span class="layui-badge ">1</span><a href="detail.html"
                                            title="小程序黑科技之获取手机号码、通讯地址、地理位置">小程序黑科技之获取手机号码、通讯地址、地理位置</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">2</span><a href="detail.html"
                                            title="后台可以管理文章了，接下来开始做博客前台。">后台可以管理文章了，接下来开始做博客前台。</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge ">3</span><a href="detail.html"
                                            title="纯CSS实现文章左上角Flag标签">纯CSS实现文章左上角Flag标签</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">4</span><a href="detail.html"
                                            title="新功能上线 - 博文配乐（网页音乐播放器推荐）">新功能上线 - 博文配乐（网页音乐播放器推荐）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">5</span><a href="detail.html"
                                            title="ASP.NET MVC 接入QQ互联，使用QQ登陆网站。">ASP.NET MVC 接入QQ互联，使用QQ登陆网站。</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">6</span><a href="detail.html"
                                            title="新功能上线 - 博文打赏（网站分享组件推荐）">新功能上线 - 博文打赏（网站分享组件推荐）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">7</span><a href="detail.html"
                                            title="邮我组件 - 用户点击即可发送邮件">邮我组件 - 用户点击即可发送邮件</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge layui-bg-blue">8</span><a href="detail.html"
                                            title="ASP.NET Core第三方认证之QQ登录">ASP.NET Core第三方认证之QQ登录</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow sr-rightmodule">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe6c6;</i></span>
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
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe611;</i></span>
                                    <span class="text">最新评论</span>
                                </div>
                                <ul class="blog-card-padding remark-list">
                                    <li>
                                        <div data-name="大米饭" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=kzyk1Jw1egqJAWuL5P3Mrg&amp;s=40"
                                                alt="大米饭">
                                        </div>
                                        <a title="点击查看" href="/article/detail/44#remark-53">
                                            <div class="remark-content">
                                                <p><img src="http://www.leo96.com/lib/layui/images/face/56.gif"
                                                        alt="[赞]"><img
                                                        src="http://www.leo96.com/lib/layui/images/face/56.gif"
                                                        alt="[赞]"><img
                                                        src="http://www.leo96.com/lib/layui/images/face/54.gif"
                                                        alt="[good]"><img
                                                        src="http://www.leo96.com/lib/layui/images/face/54.gif"
                                                        alt="[good]">一个字</p>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="Hui" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=uZCxvvY8DmIEP33exklaow&amp;s=40&amp;t=1554960122"
                                                alt="Hui">
                                        </div>
                                        <a title="点击查看" href="/article/detail/39#remark-52">
                                            <div class="remark-content">
                                                	為什麼我的安卓設備管理器顯示缺少config.ini ?
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="蚯蚓" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/C1E74DC5CC7AE8407B54669472FE1A27/40"
                                                alt="蚯蚓">
                                        </div>
                                        <a title="点击查看" href="/article/detail/6#remark-51">
                                            <div class="remark-content">
                                                <p><span>QQLoginHelper代码分享一下吧，我正在做QQ登录<img
                                                            src="http://www.leo96.com/lib/layui/images/face/22.gif"
                                                            alt="[委屈]"></span></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="Kitty" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=5ibcolOhUmxopWC5lsNGYRA&amp;s=40"
                                                alt="Kitty">
                                        </div>
                                        <a title="点击查看" href="/article/detail/41#remark-50">
                                            <div class="remark-content">
                                                优秀啊<img src="http://www.leo96.com/lib/layui/images/face/1.gif"
                                                    alt="[嘻嘻]">
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="鸡你太美" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=icTcD1iaOMvAwn948KB5lvqQ&amp;s=40"
                                                alt="鸡你太美">
                                        </div>
                                        <a title="点击查看" href="/article/detail/37#remark-49">
                                            <div class="remark-content">
                                                看到博主的网站，我就看到了.net的希望
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="思君满月" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/71AEA4035857A271FBB8ABC151CBD899/40"
                                                alt="思君满月">
                                        </div>
                                        <a title="点击查看" href="/article/detail/36#remark-48">
                                            <div class="remark-content">
                                                你好啊
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="庞" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/70C4D69693E3A277A899C79AB49A84B8/40"
                                                alt="庞">
                                        </div>
                                        <a title="点击查看" href="/article/detail/29#remark-47">
                                            <div class="remark-content">
                                                666
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="放下" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/63333A59B914E33630702CC26AB7A30B/40"
                                                alt="放下">
                                        </div>
                                        <a title="点击查看" href="/article/detail/35#remark-46">
                                            <div class="remark-content">
                                                <img src="http://www.leo96.com/lib/layui/images/face/54.gif"
                                                    alt="[good]">。
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow sr-rightmodule">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe645;</i></span>
                                    <span class="text">最新留言</span>
                                </div>
                                <!-- 没有数据 -->
                                <!-- <div class="emptybox">
                                    <p><i style="font-size:50px;color:#5fb878" class="layui-icon">&#xe69c;</i></p>
                                    <p>暂时没有任何数据</p>
                                </div> -->
                                <ul class="blog-card-padding remark-list">
                                    <li>
                                        <div data-name="　　　　　" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/A966710FC08AC0C471CCDCBFDA0DEE94/40"
                                                alt="　　　　　">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                博客很酷！
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="Gum" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=RDG8lPEQoAlaaxPl7P9tnQ&amp;s=40&amp;t=1567648488"
                                                alt="Gum">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                求问layui-nav-item 中 layui-this如何动态切换的？
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="Leo" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/qqapp/101460869/551A0EA193845763D43A01B27CA1A798/40"
                                                alt="Leo">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                <p><span>ly96leo@gmail.com</span></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="iAmNothingAtAll" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=vSLc1zObjuhqSaLqdENWjQ&amp;s=40"
                                                alt="iAmNothingAtAll">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                大佬，，求留言板的前端源码，好好看啊
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="风《boy》" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=icWqMW7dFEd7gdqTBK1ptyw&amp;s=40"
                                                alt="风《boy》">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                你好啊，博客真棒！
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="boring" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=iau2FicsiaZIvpcx4UibTCEINg&amp;s=40"
                                                alt="boring">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                后台
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="boring" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=iau2FicsiaZIvpcx4UibTCEINg&amp;s=40"
                                                alt="boring">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                <p>博主请问什么时候能开源一下后天模板吗</p>
                                                <p><br></p>
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div data-name="✎﹏末学ℳ๓㎕" class="user-avator remark-user-avator">
                                            <img src="http://thirdqq.qlogo.cn/g?b=oidb&amp;k=kovHUnNzzgO3oudvF771vQ&amp;s=40"
                                                alt="✎﹏末学ℳ๓㎕">
                                        </div>
                                        <a title="点击前往留言板" href="/comment">
                                            <div class="remark-content">
                                                <p>博客挺轻巧的,换友链吗</p>
                                                <p>https://songzixian.com</p>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow sr-rightmodule">
                                <div class="blog-card-title">
                                    <span class="icon"><i class="layui-icon" aria-hidden="true">&#xe609;</i></span>
                                    <span class="text">一路走来</span>
                                </div>
                                <ul style="padding:15px 15px 0px 20px;" class="layui-timeline">
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe65e;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2018年04月09日</h3>
                                            <p>新增博文打赏功能。</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe6fc;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2018年04月07日</h3>
                                            <p>新增博文配乐功能。</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe60e;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2018年03月01日</h3>
                                            <p>博客2.0项目启动</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe60e;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2017年03月09日</h3>
                                            <p>博客1.0基本正式上线！</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe612;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2017年02月25日</h3>
                                            <p>新增QQ登陆功能。</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe60e;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <h3 class="layui-timeline-title">2017年02月18日</h3>
                                            <p>博客1.0项目启动</p>
                                        </div>
                                    </li>
                                    <li class="layui-timeline-item">
                                        <i class="layui-icon layui-timeline-axis">&#xe643;</i>
                                        <div class="layui-timeline-content layui-text">
                                            <div class="layui-timeline-title">回忆太多，伤神！</div>
                                        </div>
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
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <jsp:include page="particle/foot.jsp"></jsp:include>
    <!-- 移动端侧边导航 -->
    <ul class="layui-nav layui-nav-tree layui-nav-side blog-nav-left layui-hide" lay-filter="nav">
        <li class="layui-nav-item layui-this">
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
    <script src="${pageContext.request.contextPath}/res/js/homepage.js"></script>
</body>

</html>