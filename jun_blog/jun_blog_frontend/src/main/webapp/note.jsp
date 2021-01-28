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
    <style>
        .grid {
            padding: 10px;
            background: #fff;
            float: left;
            margin: 8px;
            -moz-transition: top 1s ease, left 1s ease;
            -o-transition: top 1s ease, left 1s ease;
            -webkit-transition: top 1s ease, left 1s ease;
            transition: top 1s ease, left 1s ease;
            border: 1px solid #ddd;
            position: relative;
        }

        .grid .title {
            text-align: center;
            color: #333;
            font-weight: bold;
            font-family: 'Adobe Kaiti Std';
            font-size: 16px;
        }

        .grid .content {
            line-height: 1.5;
            padding: 10px;
            background-color: #333;
            color: #ddd;
            min-height: 80px;
            word-break: break-all;
            margin: 10px 0;
        }

        .grid .tags {
            margin-bottom: 10px;
        }

        .grid .tags span+span {
            margin-left: 5px;
        }

        .grid .time {
            color: #999;
            text-align: center;
            font-size: 12px;
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
                    <a><cite>文章归档</cite></a>
                </span>
            </blockquote>
            <div class="blog-nav-two shadow">
                <div class="layui-breadcrumb" lay-separator="|">
                    <a href="timeline.html"><span>轻言细语</span></a>
                    <a href="record.html"><span>文章归档</span></a>
                    <a href="note.html" class="selected"><span>笔记标签</span></a>
                </div>
            </div>
            <div class="blog-panel">
                <div id="container" style="position:relative;margin:-8px;"></div>
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
            <a href="articlelist.html"><i class="fa fa-book fa-fw"></i>&nbsp;学海无涯</a>
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

    <script src="https://cdn.bootcss.com/scrollReveal.js/3.3.6/scrollreveal.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/site.js"></script>
    <!-- 本页特有js -->
    <script src="${pageContext.request.contextPath}/res/js/blocksit.js"></script>
    <script>
        layui.use(['jquery', 'flow', 'util'], function ($, flow, util) {

            function col() {
                var width = $(window).width(),
                    colNum = 4;

                if (width >= 1200) {
                    colNum = 4; //大屏幕 4列
                } else if (width >= 992) {
                    colNum = 3; //中屏幕 3列
                } else if (width >= 768) {
                    colNum = 2; //小屏幕 2列
                } else {
                    colNum = 1; //超小屏幕 1列
                }

                return colNum;
            };
            //模拟数据
            var dataM = [{
                    "title": "分页获取总页数",
                    "content": "int totalPage = (totalCount  +  pageSize  - 1) / pageSize; ",
                    "tag": "分页",
                    "status": 1,
                    "createTime": "2018-05-09 00:00:00",
                    "id": 2
                },
                {
                    "title": "mousedown模拟双击事件",
                    "content": "var lastTime = 0;\n <br/>          $(elem).on('mousedown', function () {\n <br/>              var thisTime = new Date().getTime();\n <br/>              if (thisTime - lastTime < 300) {\n <br/>                  //触发双击事件\n <br/>              }\n <br/>              lastTime = thisTime;\n <br/>          });",
                    "tag": "mousedown、双击事件",
                    "status": 1,
                    "createTime": "2018-05-09 16:00:55",
                    "id": 10
                },
                {
                    "title": "计算滚动条宽度",
                    "content": "    function getScrollbarWidth() {\n        var oP = document.createElement('p'),\n            styles = {\n                width: '100px',\n                height: '100px',\n                overflowY: 'scroll'\n            },\n            i,\n            scrollbarWidth;\n        for (i in styles) oP.style[i] = styles[i];\n        document.body.appendChild(oP);\n        scrollbarWidth = oP.offsetWidth - oP.clientWidth;\n        oP.remove();\n        return scrollbarWidth;\n    }",
                    "tag": "js、滚动条宽度",
                    "status": 1,
                    "createTime": "2018-05-09 16:29:11",
                    "id": 11
                },
                {
                    "title": "保留两位小数并补全",
                    "content": "function toDecimal2(x) {\n            var f = parseFloat(x);\n            if (isNaN(f)) {\n                return false;\n            }\n            var f = Math.round(x * 100) / 100;\n            var s = f.toString();\n            var rs = s.indexOf('.');\n            if (rs < 0) {\n                rs = s.length;\n                s += '.';\n            }\n            while (s.length <= rs + 2) {\n                s += '0';\n            }\n            return s;\n        }",
                    "tag": "js、小数保留",
                    "status": 1,
                    "createTime": "2018-05-09 16:30:27",
                    "id": 12
                },
                {
                    "title": "查询父子级数据",
                    "content": ";with temp as (\n\nselect * from Sys_Dept where SysNo = 1 \n\nunion all \n\nselect Sys_Dept.* from temp inner JOIN Sys_Dept ON temp.SysNo = Sys_Dept.ParentSysNo  \n\nwhere Sys_Dept.State <> 0) \n\n\n\nselect * from temp",
                    "tag": "SQL、父子级、树形结构",
                    "status": 1,
                    "createTime": "2018-05-09 16:32:08",
                    "id": 13
                },
                {
                    "title": "javascript生成Guid",
                    "content": "function getGuid() {\n                return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {\n                    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);\n                    return v.toString(16);\n                });\n            }",
                    "tag": "js、guid",
                    "status": 1,
                    "createTime": "2018-05-09 16:45:16",
                    "id": 14
                },
                {
                    "title": "javascript获取QueryString值",
                    "content": "function getQueryStringByName(name){\n\n     var result = location.search.match(new RegExp(\"[\\?\\&]\" + name+ \"=([^\\&]+)\",\"i\"));\n\n     if(result == null || result.length < 1){\n\n         return \"\";\n\n     }\n\n     return result[1];\n\n}",
                    "tag": "js、querystring",
                    "status": 1,
                    "createTime": "2018-05-09 16:50:22",
                    "id": 15
                },
                {
                    "title": "SQL自定义函数split分隔字符串",
                    "content": "Create FUNCTION [dbo].[F_Split]\n (\n     @SplitString nvarchar(max),  --源字符串\n     @Separator nvarchar(10)=' '  --分隔符号，默认为空格\n )\n RETURNS @SplitStringsTable TABLE  \n (\n     [id] int identity(1,1),\n     [value] nvarchar(max)\n )\n AS\n BEGIN\n     DECLARE @CurrentIndex int;\n     DECLARE @NextIndex int;\n     DECLARE @ReturnText nvarchar(max);\n\n     SELECT @CurrentIndex=1;\n     WHILE(@CurrentIndex<=len(@SplitString))\n         BEGIN\n             SELECT @NextIndex=charindex(@Separator,@SplitString,@CurrentIndex);\n             IF(@NextIndex=0 OR @NextIndex IS NULL)\n                 SELECT @NextIndex=len(@SplitString)+1;\n                 SELECT @ReturnText=substring(@SplitString,@CurrentIndex,@NextIndex-@CurrentIndex);\n                 INSERT INTO @SplitStringsTable([value]) VALUES(@ReturnText);\n                 SELECT @CurrentIndex=@NextIndex+1;\n             END\n     RETURN;\n END",
                    "tag": "SQL、字符串分割",
                    "status": 1,
                    "createTime": "2019-02-27 15:22:14",
                    "id": 16
                }
            ];
            flow.load({
                elem: '#container',
                end: ' ',
                isAuto: true,
                done: function (page, next) {
                    var lis = [],
                        pageSize = 8;
                    //Ajax请求数据
                    $.ajax({
                        url: '/api/all/getnotes',
                        data: {
                            pageSize: pageSize,
                            pageIndex: page
                        },
                        success: function (res) {
                            if (res.code === 1) {
                                //根据后台响应的数据组织html
                                layui.each(res.data, function (index, item) {
                                    var html =
                                        '<div class="grid animated fadeInUp"><h4 class="title">' +
                                        item.title +
                                        '</h4><div class="content">' + item
                                        .content + '</div><div class="tags">';
                                    var tags = item.tag.split('、');
                                    layui.each(tags, function (index2, item2) {
                                        html +=
                                            '<span class="layui-badge-rim"><i class="fa fa-tag fa-fw"></i>' +
                                            item2 + '</span>';
                                    });
                                    html += '</div><p class="time">' + util
                                        .timeAgo(item.createTime, false) +
                                        '</p></div>';
                                    lis.push(html);
                                });

                                //计算总页数
                                var pages = (res.count + pageSize - 1) / pageSize;

                                //将数据渲染在容器中
                                next(lis.join(''), page < pages);

                                //使用blocksit进行瀑布流布局
                                $('#container').BlocksIt({
                                    numOfCol: col(),
                                    offsetX: 8,
                                    offsetY: 8
                                });
                            }
                        },
                        error: function () {
                            //这是加载模拟数据
                            layui.each(dataM, function (index, item) {
                                var html =
                                    '<div class="grid animated fadeInUp"><h4 class="title">' +
                                    item.title +
                                    '</h4><div class="content">' + item
                                    .content + '</div><div class="tags">';
                                var tags = item.tag.split('、');
                                layui.each(tags, function (index2, item2) {
                                    html +=
                                        '<span class="layui-badge-rim"><i class="fa fa-tag fa-fw"></i>' +
                                        item2 + '</span>';
                                });
                                html += '</div><p class="time">' + util
                                    .timeAgo(item.createTime, false) +
                                    '</p></div>';
                                lis.push(html);
                            });

                            //计算总页数
                            var pages = 5;

                            //将数据渲染在容器中
                            next(lis.join(''), page < pages);

                            //使用blocksit进行瀑布流布局
                            $('#container').BlocksIt({
                                numOfCol: col(),
                                offsetX: 8,
                                offsetY: 8
                            });
                        }
                    });
                }
            });

            $(window).resize(function () {
                $('#container').BlocksIt({
                    numOfCol: col(),
                    offsetX: 8,
                    offsetY: 8
                });
            });
        });
    </script>
</body>

</html>