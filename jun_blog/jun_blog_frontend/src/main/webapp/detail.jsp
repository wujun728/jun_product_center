<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <!-- 本页特有的css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/article-detail.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/wangEditor.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/prettify.css">
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
            <a class="blog-logo" href="index.html">不落阁</a>
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
                <i class="layui-icon">&#xe715;</i>
                <span class="layui-breadcrumb" lay-separator=">">
                    <a href="index.html">首页</a>
                    <a href="${pageContext.request.contextPath}/index.html">博客列表</a>
                    <a><cite>${blog.title }</cite></a>
                </span>
            </blockquote>
            <div class="layui-row layui-col-space15 clearfix">
                <div class="layui-col-md8 left">
                    <div data-fontsize="14" class="article-detail shadow">
                        <div class="article-tool">
                            <div style="float:left;">
                                <button class="layui-btn layui-btn-primary layui-btn-xs"
                                    title="发布日期"><fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></button>
                                <div class="layui-btn-group">
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="博客类别">
                                        <i class="fa fa-comments fa-fw"></i><span style="margin-left:3px;">${blog.blogType.typeName}</span>
                                    </button>
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="浏览">
                                        <i class="fa fa-eye fa-fw"></i><span style="margin-left:3px;">${blog.clickHit}</span>
                                    </button>
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="评论">
                                        <i class="fa fa-comments fa-fw"></i><span style="margin-left:3px;">${blog.replyHit}</span>
                                    </button>
                                </div>
                            </div>
                            <div class="tool-box">
                                <div class="layui-btn-group">
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="静音">
                                        <i class="fa fa-volume-up fa-fw"></i>
                                    </button>
                                </div>
                                <div class="layui-btn-group layui-hide-xs">
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="字体缩小">
                                        <i class="fa fa-minus fa-fw"></i>
                                    </button>
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="字体还原">
                                        <i class="fa fa-undo fa-fw"></i>
                                    </button>
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="字体放大">
                                        <i class="fa fa-plus fa-fw"></i>
                                    </button>
                                </div>
                                <div class="layui-btn-group layui-hide-xs">
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="展开阅读">
                                        <i class="fa fa-arrows-h fa-fw"></i>
                                    </button>
                                    <button class="layui-btn layui-btn-primary layui-btn-xs" title="全屏阅读">
                                        <i class="fa fa-arrows-alt fa-fw"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="article-detail-title">${blog.title }</div>
                        <div class="article-detail-abstract">
                            <span class="layui-badge">摘要</span>
                            <span id="abstract">${blog.summary }</span>
                                <div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
                        </div>
                        <div class="article-detail-content w-e-text">
                        	${blog.content }
                        </div>
                        <div class="article-detail-content w-e-text">
								<font><strong>关键字：</strong></font>
								<c:choose>
									<c:when test="${keyWords==null}">
										&nbsp;&nbsp;无
									</c:when>
									<c:otherwise>
										<c:forEach var="keyWord" items="${keyWords }">
											&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/q.html?q=${keyWord}" target="_blank">${keyWord }</a>&nbsp;&nbsp;
										</c:forEach>
									</c:otherwise>
								</c:choose>
						</div>
                    </div>
                    <div class="article-detail-content w-e-text">
						${pageCode }
					</div>
                    
                    <div class="article-component">
                        <div class="component-box">
                            <a href="javascript:;" class="praise" blog-event="praise"><i
                                    class="fa fa-thumbs-up fa-fw"></i>点赞（<span id="praiseCnt">0</span>）</a>
                            <a href="javascript:;" class="reword" blog-event="reword">赏</a>
                            <a href="javascript:;" class="share" blog-event="share"><i
                                    class="fa fa-share-alt fa-fw"></i>分享（<span id="shareCnt">0</span>）</a>
                        </div>
                    </div>
                    
                    
                    
                    <div class="blog-card blog-card-padding shadow">
                        <fieldset class="layui-elem-field layui-field-title" style="margin-bottom:0">
                            <legend>来说两句吧</legend>
                            <div class="layui-field-box">
                                <form class="layui-form blog-editor" action="">
                                    <div class="layui-form-item">
                                        <textarea name="EditorContent" lay-verify="content" id="remarkEditor"
                                            placeholder="请输入内容" class="layui-textarea layui-hide"></textarea>
                                        <!-- 禁止评论 -->
                                        <!-- <div style="border:1px solid #f2f2f2;" class="emptybox">
                                            <p><i style="font-size:50px;color:#5fb878" class="layui-icon">&#x1007;</i>
                                            </p>
                                            <p>该文章已禁止评论</p>
                                        </div> -->
                                    </div>
                                    <div class="layui-form-item">
                                        <button class="layui-btn" lay-submit="formRemark"
                                            lay-filter="formRemark">提交评论</button>
                                    </div>
                                </form>
                            </div>
                        </fieldset>
                        <div class="blog-card-title">最新评论</div>
                        <ul class="blog-comment">
                            <li>
                                <div class="comment-parent">
                                    <a name="remark-@item.Id"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="张三" />
                                    <div class="info">
                                        <span class="username">张三</span>
                                    </div>
                                    <div class="content">评论扯淡呢</div>
                                    <p class="info info-footer"><span class="time">2019-10-08 17：39：22</span><a href="javascript:;" class="btn-reply" data-targetname="张三">回复</a></p>
                                </div>
                                <hr />
                                <div class="comment-child">
                                    <a name="reply-@reply.Id"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="麻花疼" />
                                    <div class="info">
                                        <span class="username">麻花疼</span>
                                        <span style="padding-right:0;margin-left:-5px;">回复</span>
                                        <span class="username">张三</span>
                                        <span>第一条回复</span>
                                    </div>
                                    <p class="info"><span class="time">2019-10-08 17：39：22</span><a href="javascript:;" class="btn-reply" data-targetname="麻花疼">回复</a></p>
                                </div>
                                <div class="comment-child">
                                    <a name="reply-@reply.Id"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="麻花疼" />
                                    <div class="info">
                                        <span class="username">麻花疼</span>
                                        <span style="padding-right:0;margin-left:-5px;">回复</span>
                                        <span class="username">张三</span>
                                        <span>第二条回复</span>
                                    </div>
                                    <p class="info"><span class="time">2019-10-08 17：39：22</span><a href="javascript:;" class="btn-reply" data-targetname="麻花疼">回复</a></p>
                                </div>
                                <!-- 回复编辑器 -->
                                <div class="replycontainer layui-hide">
                                    <form class="layui-form" action="">
                                        <div class="layui-form-item"><textarea name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>
                                        </div>
                                        <div class="layui-form-item">
                                            <button class="layui-btn layui-btn-xs" lay-submit="formReply" lay-filter="formReply">提交</button>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li>
                                <div class="comment-parent">
                                    <a name="remark-@item.Id"></a>
                                    <img src="${pageContext.request.contextPath}/res/images/wujun.jpg" alt="张三" />
                                    <div class="info">
                                        <span class="username">张三</span>
                                    </div>
                                    <div class="content">评论扯淡呢</div>
                                    <p class="info info-footer"><span class="time">2019-10-08 17：39：22</span><a href="javascript:;" class="btn-reply" data-targetname="张三">回复</a></p>
                                </div>
                                <!-- 回复编辑器 -->
                                <div class="replycontainer layui-hide">
                                    <form class="layui-form" action="">
                                        <div class="layui-form-item"><textarea name="replyContent" lay-verify="replyContent" placeholder="请输入回复内容" class="layui-textarea" style="min-height:80px;"></textarea>
                                        </div>
                                        <div class="layui-form-item">
                                            <button class="layui-btn layui-btn-xs" lay-submit="formReply" lay-filter="formReply">提交</button>
                                        </div>
                                    </form>
                                </div>
                            </li>
                        </ul>
                        <!-- 没有评论 -->
                        <!-- <div class="emptybox">
                            <p><i style="font-size:50px;color:#5fb878" class="layui-icon"></i></p>
                            <p>暂无评论，大侠不妨来一发？</p>
                        </div> -->
                    </div>
                </div>
                
                <div class="layui-col-md4 right">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-sm6 layui-col-md12 padding0">
                            <div class="article-category shadow">
                                <div class="article-category-title">分类导航</div>
                                <c:forEach var="blogTypeCount" items="${blogTypeCountList }">
                                	<a href="${pageContext.request.contextPath}/articlelist.html?typeId=${blogTypeCount.id }">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a>
								</c:forEach>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="layui-col-sm6 layui-col-md12 padding0">
                            <div class="article-category shadow">
                                <div class="article-category-title">日期导航</div>
								<c:forEach var="blogCount" items="${blogCountList }">
									<a href="${pageContext.request.contextPath}/articlelist.html?releaseDateStr=${blogCount.releaseDateStr }">${blogCount.releaseDateStr }(${blogCount.blogCount })</a>
								</c:forEach>
                                <div class="clear"></div>
                            </div>
                        </div>
                        
                        
                        <div class="layui-col-sm6 layui-col-md12">
                            <div class="blog-card shadow">
                                <div class="blog-card-title">相似文章</div>
                                <ul class="blog-card-ul">
                                    <li>
                                        <span class="layui-badge  ">1</span><a href="detail.html"
                                            title="博客升级到.NET Core 3.0的变化和问题">博客升级到.NET Core 3.0的变化和问题</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  ">2</span><a href="detail.html"
                                            title="ASP.NET Core第三方认证之QQ登录">ASP.NET Core第三方认证之QQ登录</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  ">3</span><a href="detail.html"
                                            title="EF Core进阶之加载关联数据">EF Core进阶之加载关联数据</a>
                                    </li>
                                </ul>
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
                                <div class="blog-card-title">随便看看</div>
                                <ul class="blog-card-ul">
                                    <li>
                                        <span class="layui-badge  ">1</span><a href="detail.html"
                                            title="新功能上线 - 博文打赏（网站分享组件推荐）">新功能上线 - 博文打赏（网站分享组件推荐）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  ">2</span><a href="detail.html"
                                            title="小程序黑科技之获取手机号码、通讯地址、地理位置">小程序黑科技之获取手机号码、通讯地址、地理位置</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  ">3</span><a href="detail.html"
                                            title="后台可以管理文章了，接下来开始做博客前台。">后台可以管理文章了，接下来开始做博客前台。</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  layui-bg-blue">4</span><a href="detail.html"
                                            title="Xamarin.Forms移动开发系列1：介绍和安装">Xamarin.Forms移动开发系列1：介绍和安装</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  layui-bg-blue">5</span><a href="detail.html"
                                            title="使用ASP.NET Core SignalR搭建聊天室（本站聊天室）">使用ASP.NET Core
                                            SignalR搭建聊天室（本站聊天室）</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  layui-bg-blue">6</span><a href="detail.html"
                                            title="Xamarin.Forms移动开发系列4 ：XAML基础">Xamarin.Forms移动开发系列4 ：XAML基础</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  layui-bg-blue">7</span><a href="detail.html"
                                            title="EF Core进阶之加载关联数据">EF Core进阶之加载关联数据</a>
                                    </li>
                                    <li>
                                        <span class="layui-badge  layui-bg-blue">8</span><a href="detail.html"
                                            title="Blocksit+Layui实现响应式瀑布流加载">Blocksit+Layui实现响应式瀑布流加载</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!--右侧悬浮分类导航 平板或手机设备显示-->
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
    <!-- 本页特有js -->
    <script src="${pageContext.request.contextPath}/res/js/prettify.js"></script>
    <script type="text/javascript">
        var shareIndex, $;
    
        prettyPrint(); //渲染文章中的代码
    
        layui.use(['layer', 'form'], function () {
            $ = layui.$;
            var form = layui.form
                , device = layui.device();
    
    
            //文章顶部工具栏按钮点击事件
            $('.tool-box button').on('click', function () {
                var title = $(this).attr('title');
                switch (title) {
                    case '全屏阅读':
                        var content = $('.article-detail').prop("outerHTML");
                        layer.open({
                            title: false,
                            type: 1,
                            content: content,
                            closeBtn: 0,
                            scrollbar: false,
                            area: ['100vw', '100vh'],
                            success: function (layero, index) {
                                $(layero).find('.article-tool').html('<div class="tool-box"><div class="layui-btn-group"><button class="layui-btn layui-btn-primary layui-btn-xs" title="关闭全屏"><i class="fa fa-compress fa-fw"></i></button></div></div>');
                                $(layero).find('.article-tool button').on('click', function () {
                                    layer.close(index);
                                });
                            }
                        });
                        break;
                    case '展开阅读':
                        $('.right').hide();
                        $('.left').css({
                            'width': '100%'
                        });
                        $(this).attr('title', '收缩阅读');
                        $(this).html('<i class="fa fa-compress fa-fw"></i>');
                        break;
                    case '收缩阅读':
                        $('.right').show();
                        $('.left').css('width', '');
                        $(this).attr('title', '展开阅读');
                        $(this).html('<i class="fa fa-arrows-h fa-fw"></i>');
                        break;
                    case '字体缩小':
                        var fontsize = Number($('.article-detail').data('fontsize'));
                        fontsize = fontsize - 1;
                        if (fontsize < 12) fontsize = 12;
                        $('.article-detail').data('fontsize', fontsize);
                        $('.article-detail-abstract,.article-detail-content').css('font-size', fontsize + 'px');
                        break;
                    case '字体还原':
                        $('.article-detail').data('fontsize', 14);
                        $('.article-detail-abstract,.article-detail-content').css('font-size', '');
                        break;
                    case '字体放大':
                        var fontsize = Number($('.article-detail').data('fontsize'));
                        fontsize = fontsize + 1;
                        if (fontsize > 20) fontsize = 20;
                        $('.article-detail').data('fontsize', fontsize);
                        $('.article-detail-abstract,.article-detail-content').css('font-size', fontsize + 'px');
                        break;
                    case '静音':
                        $(this).attr('title', '开启');
                        $(this).html('<i class="fa fa-volume-off fa-fw"></i>');
                        ap.volume(0, false);
                        break;
                    case '开启':
                        $(this).attr('title', '静音');
                        $(this).html('<i class="fa fa-volume-up fa-fw"></i>');
                        ap.volume(0.5, false);
                        break;
                    default:
                }
            });
    
            //回复按钮点击事件
            $('.btn-reply').on('click', function () {
                var targetId = $(this).data('targetid')
                    , targetName = $(this).data('targetname')
                    , $container = $(this).parent('p').parent().siblings('.replycontainer');
                if ($(this).text() == '回复') {
                    $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
                    $container.removeClass('layui-hide');
                    $container.find('input[name="targetUserId"]').val(targetId);
                    $(this).parents('.blog-comment li').find('.btn-reply').text('回复');
                    $(this).text('收起');
                } else {
                    $container.addClass('layui-hide');
                    $container.find('input[name="targetUserId"]').val(0);
                    $(this).text('回复');
                }
            });
    
            //监听留言回复提交
            form.on('submit(formReply)', function (data) {
                if ($(data.elem).hasClass('layui-btn-disabled')) {
                    return false;
                }
                var index = layer.load(1);
                $.ajax({
                    type: 'post',
                    url: '/api/article/reply',
                    data: data.field,
                    success: function (res) {
                        layer.close(index);
                        if (res.code === 1) {
                            layer.msg(res.msg, { icon: 6 });
                            setTimeout(function () {
                                location.reload(true);
                            }, 500);
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
                        layer.msg("请求异常", { icon: 2 });
                    }
                });
                return false;
            });
            var events = {
                //分享
                share: function () {
                    layer.msg('使用的百度分享组件');
                }
    
                //点赞
                , praise: function () {
                    var localdata = layui.data('blog')
                        , articleId = $('#hidArticleId').val()
                        , self = this;
                    if (localdata['praise' + articleId]) {
                        layer.tips('你已点过赞了，若收获颇大，可打赏作者！^_^', self, { tips: 1, time: 2000 });
                        return;
                    }
                    //服务器点赞数加一
                    //存储是否点赞该文
                    layui.data('blog', {
                        key: 'praise' + $('#hidArticleId').val()
                        , value: true
                    });
                    //点赞+1
                    var cnt = Number($('#praiseCnt').text()) + 1;
                    $('#praiseCnt').text(cnt);
                    layer.tips('Thank you ^_^', self, { tips: 1, time: 2000 });
                }
    
                //打赏
                , reword: function () {
                    layer.tab({
                        area: ['330px', '373px'],
                        shade: 0.6,
                        tab: [{
                            title: '微信',
                            content: '<img style="width:330px;height:330px;" src="${pageContext.request.contextPath}/res/images/wx_reward.png" />'
                        }, {
                            title: '支付宝',
                            content: '<img style="width:330px;height:330px;" src="${pageContext.request.contextPath}/res/images/ali_reward.jpg" />'
                        }]
                    });
    
                }
            };
    
            $('*[blog-event]').on('click', function () {
                var eventName = $(this).attr('blog-event');
                events[eventName] && events[eventName].call(this);
            });
    
            $('*[blog-event="reword"]').on('mouseover', function () {
                layer.tips('一元足以感动我 ^_^', this, { tips: 1, time: 2000 });
            });
        });
    </script>
</body>

</html>