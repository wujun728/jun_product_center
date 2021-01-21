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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/about.css">
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
                <li class="layui-nav-item">
                    <a href="comment.html"><i class="fa fa-comments fa-fw"></i>&nbsp;留言交流</a>
                </li>
                <li class="layui-nav-item layui-this">
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
            <div class="layui-row layui-col-space15">
                <div class="layui-col-xs12">
                    <blockquote class="layui-elem-quote sitemap shadow" style="margin-bottom:0px;">
                        <i class="layui-icon">&#xe715;</i>
                        <span class="layui-breadcrumb" lay-separator=">">
                            <a href="/">首页</a>
                            <a><cite>关于本站</cite></a>
                        </span>
                    </blockquote>
                </div>
                <div class="layui-col-xs12">
                    <ul class="blog-nav-follow">
                        <li class="current"><a href="#author">关于作者</a></li>
                        <li><a href="#site">关于网站</a></li>
                        <li><a href="#friend">友情链接</a></li>
                        <li><a href="#reward">小额赞赏</a></li>
                    </ul>
                    <div class="blog-about-container">
                        <span id="author" class="anchor"></span>
                        <div class="section">
                            <div class="section-title">
                                <span class="layui-badge">关于作者</span>
                            </div>
                            <div class="section-content">
                                <div class="author-bg">
                                    <div class="bg-box">
                                        <img class="bg" src="${pageContext.request.contextPath}/res/images/about_bg4.jpg" />
                                        <div class="mask"></div>
                                    </div>
                                    <div class="author-intro">
                                        <img src="${pageContext.request.contextPath}/res/images/20160804.jpg" alt="Wujun" />
                                        <h2>WujunLeo</h2>
                                        <h4>Java架构师，项目经理，写胶片也写代码同时整点项目管理那种！</h4>
                                    </div>
                                </div>
                                <div class="text-content">
                                    <p> 撸代码滴，Java开发，差不多写了十年代码了，也带了四五年项目了，技术及管理均有，精通Java后台开发。 擅长各类单体/分布式/微服务系统的研发和架构，包括SOA、微服务、负载均衡等。 
						                                     擅长分布式架构设计及实施，比如，分布式缓存及对象存储、分布式事务、对象存储、数据库分库分表、读写分离、以及各种高并发分布式系统等。 
						                                     本人长期承接各种项目及信息系统开发、企业个人网站建设、微信公众号小程序开发及建设、APP开发。 
						                                     以及各种管理系统开发、OA、MIS、HR、CRM、ERP等、有需要请联系本人，邮箱wujun728@hotmail.com或者微信公众号。</p>
                                    <br/>
                                    <p style="text-align:right;padding-right:5px;font-size:24px;font-family:Cambria">—— By Wujun</p>
                                </div>
                            </div>
                        </div>
                        <span id="site" class="anchor"></span>
                        <div class="section">
                            <div class="section-title">
                                <span class="layui-badge">关于网站</span>
                            </div>
                            <div class="section-content">
                                <div class="author-bg">
                                    <div class="bg-box">
                                        <img class="bg" src="${pageContext.request.contextPath}/res/images/about_bg2.jpg" />
                                        <div class="mask"></div>
                                    </div>
                                    <div class="author-intro">
                                        <img src="${pageContext.request.contextPath}/res/images/nb666.jpg" alt="新博网" />
                                        <h2>新博网</h2>
                                        <h4>俊哥博客，记录点滴，分享技术！</h4>
                                    </div>
                                </div>
                                <div class="text-content">
                                    <p>新博网是纯手工打造，非第三方建站程序制作的网站，它后端程序使用的是SpringBoot最新微服务框架
                                        	，前端则是Layui主导加各种插件组合而成。经历过一次大改，所以称新博网2.0。前端技术使用的是LayUI框架，页面模板使用的是JSP模板，后续前后端分离。</p>
                                    <br />
                                </div>
                            </div>
                        </div>
                        <span id="friend" class="anchor"></span>
                        <div class="section">
                            <div class="section-title">
                                <span class="layui-badge">友情链接</span>
                            </div>
                            <div class="section-content">
                                <div class="author-bg">
                                    <div class="bg-box">
                                        <img class="bg" src="${pageContext.request.contextPath}/res/images/about_bg3.jpg" />
                                        <div class="mask"></div>
                                    </div>
                                    <div class="author-intro">
                                        <img src="${pageContext.request.contextPath}/res/images/global/handshake.png" alt="友链互换" />
                                        <h2>友链互换</h2>
                                        <h4>
                                            <i class="fa fa-close"></i>经常宕机&nbsp;
                                            <i class="fa fa-close"></i>不合法规&nbsp;
                                            <span class="layui-hide-xs"><i class="fa fa-close"></i>插边球站&nbsp;</span>
                                            <span class="layui-hide-xs"><i class="fa fa-close"></i>红标报毒&nbsp;</span>
                                            <i class="fa fa-check"></i>原创优先&nbsp;
                                            <i class="fa fa-check"></i>技术优先
                                        </h4>
                                    </div>
                                </div>
                                <div class="layui-row layui-col-space15" style="margin-top:5px;">
                                    <div class="layui-col-lg3 layui-col-md4 layui-col-sm6">
                                        <a href="https://www.baidu.com" target="_blank" class="friendlink-item">
                                            <img src="https://www.baidu.com/favicon.ico" alt="百度" />
                                            <h2>百度</h2>
                                            <p>baidu.com</p>
                                        </a>
                                    </div>
                                    <div class="layui-col-lg3 layui-col-md4 layui-col-sm6">
                                        <a href="https://www.baidu.com" target="_blank" class="friendlink-item">
                                            <img src="https://www.baidu.com/favicon.ico" alt="百度" />
                                            <h2>百度</h2>
                                            <p>baidu.com</p>
                                        </a>
                                    </div>
                                    <div class="layui-col-lg3 layui-col-md4 layui-col-sm6">
                                        <a href="https://www.baidu.com" target="_blank" class="friendlink-item">
                                            <img src="https://www.baidu.com/favicon.ico" alt="百度" />
                                            <h2>百度</h2>
                                            <p>baidu.com</p>
                                        </a>
                                    </div>
                                    <div class="layui-col-lg3 layui-col-md4 layui-col-sm6">
                                        <a href="https://www.baidu.com" target="_blank" class="friendlink-item">
                                            <img src="https://www.baidu.com/favicon.ico" alt="百度" />
                                            <h2>百度</h2>
                                            <p>baidu.com</p>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <span id="reward" class="anchor"></span>
                        <div class="section">
                            <div class="section-title">
                                <span class="layui-badge">小额赞赏</span>
                            </div>
                            <div class="section-content">
                                <div class="author-bg">
                                    <div class="bg-box">
                                        <img class="bg" src="${pageContext.request.contextPath}/res/images/about_bg1.jpg" />
                                        <div class="mask"></div>
                                    </div>
                                    <div class="author-intro">
                                        <img src="${pageContext.request.contextPath}/res/images/global/reword.jpg" alt="小额赞赏" />
                                        <h2>小额赞赏</h2>
                                        <h4>
                                            若本站内容对你有所帮助，还望不吝赞赏！
                                        </h4>
                                    </div>
                                </div>
                                <div class="layui-row layui-col-space30" style="text-align:center;margin-top:5px">
                                    <div class="layui-col-sm6">
                                        <img style="width:300px;" src="${pageContext.request.contextPath}/res/images/wx_reward.jpg">
                                        <p style="margin-top:10px;color:#FF5722;font-size:18px;">微信打赏</p>
                                    </div>
                                    <div class="layui-col-sm6">
                                        <img style="width:300px;" src="${pageContext.request.contextPath}/res/images/ali_reward.jpg">
                                        <p style="margin-top:10px;color:#FF5722;font-size:18px;">支付宝打赏</p>
                                    </div>
                                </div>
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
        <li class="layui-nav-item layui-this">
            <a href="about.html"><i class="fa fa-info fa-fw"></i>&nbsp;关于本站</a>
        </li>
    </ul>
    <!-- 侧边导航遮罩 -->
    <div class="blog-mask animated layui-hide"></div>

    <script src="https://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>
    <!-- 本页特有js -->
    <script src="${pageContext.request.contextPath}/res/js/jquery.nav.js"></script>
    <script>
        layui.use(['jquery'], function () {
            var $ = layui.$;

            $('.blog-nav-follow').onePageNav({
                scrollThreshold: 0.1
            });

            $(window).scroll(function () {
                if ($(window).scrollTop() > 65) {
                    $('.blog-nav').addClass('layui-hide');
                } else {
                    $('.blog-nav').removeClass('layui-hide');
                }
                if ($(window).scrollTop() > 65) {
                    $('.blog-nav-follow').addClass('fixed');
                } else {
                    $('.blog-nav-follow').removeClass('fixed');
                }
            });
        });
    </script>
</body>

</html>