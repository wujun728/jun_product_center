/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['laytpl', 'laypage', 'timeago'], function (exports) {
    var tpl = layui.laytpl,
        laypage = layui.laypage,
        timeago = layui.timeago;
    var col_l9r3 = '<div id="article-list-col93" class="layui-row layui-col-space20 col93" style="margin-bottom: 20px;">' +
        '<div class="layui-col-md9 animated fadeInUp">' +
        '{{# layui.each(d.page.records, function(index, item){ }}' +
        '<div class="layui-card" style="margin-bottom: 30px;">' +
        '<div class="layui-card-body">' +
        '<fieldset class="layui-elem-field layui-field-title">' +
        '  <legend class="center-to-head">' +
        '       {{# if(item.top){ }}' +
        '       <span class="layui-badge  layui-bg-red">置顶</span>' +
        '       {{# } }}' +
        '       {{# if(!item.reprinted){ }}' +
        '       <span class="yuanchuang">原创</span>' +
        '       {{# } }}' +
        '       {{# if(item.reprinted){ }}' +
        '       <span class="zhuanzai">转载</span>' +
        '       {{# } }}' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '       <a href="/article/u{{ item.urlSeq }}">{{ item.title }}</a>' +
        '       {{# }else{ }}' +
        '       <a href="/article/{{ item.id }}">{{ item.title }}</a>' +
        '       {{# } }}' +
        '</legend>' +
        '<blockquote class="layui-elem-quote" style="background-color: #f8f8f8;padding: 5px 5px 5px 10px;margin-top: 10px;">' +
        '{{# layui.each(d.catesMap[item.id], function(index, item){ }}' +
        '       <span class="layui-badge no-select" style="margin-right: 10px;padding: 5px; background-color: #e6e6e6;color: #0c0c0c;"><i class="fa fa-tag"></i> {{ item.name }} </span>' +
        '{{# });  }}' +
        '</blockquote>' +
        '</fieldset>' +
        '<div class="layui-fluid layui-row layui-col-space20">' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-col-md9 layui-col-xs12">' +
        '       {{# }else{ }}' +
        '<div class="layui-col-xs12">' +
        '       {{# } }}' +
        '   <div style="text-indent: 35px;line-height: 28px;color: #999 !important;word-break: break-all;">' +
        '{{ item.summary }}<a href="/article/{{ item.id }}">...</a></div>' +
        '</div>' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-hide-xs layui-hide-sm layui-show-md-block layui-col-md3" style="text-align: center;">' +
        '<a href="/article/{{item.id}}"><img src="{{item.cover}}" alt="" ' +
        'style="width: 100%;height:125px;display: inline-block;padding: 7px;border: 1px solid #dedede;' +
        'box-shadow: 0 1px 3px rgba(0, 0, 0, .3);border-radius: 4px;margin-top: 5px;cursor: pointer;"/> ' +
        '</a> ' +
        '</div>' +
        '       {{# } }}' +
        '</div>' +
        '<div class="layui-fluid layui-row">' +
        '       <p class="article-footer">' +
        '           <span class="layui-mr15"><i class="fa fa-thermometer-1"></i> 热度：<span style="font-weight: bold;font-style: italic;">{{ item.views }}℃</span></span>' +
        '           {{# if(d.articleCommentCountMap[item.id] > 0){ }}' +
        '           <span class="layui-mr15"><i class="fa fa-commenting-o"></i> 评论: <span style="font-weight: bold;font-style: italic;">{{ d.articleCommentCountMap[item.id] }}</span>条</span>' +
        '           {{# } }}' +
        '           <span class="layui-mr15"><i class="fa fa-user-o"></i> 作者：<span style="font-weight: bold;color: #986b0d;font-style: italic;">{{ d.articleAuthors[item.id] }}</span></span>' +
        '           <span><i class="fa fa-calendar"></i> 更新：<span style="font-weight: bold;font-style: italic;" class="timeago" datetime="{{ nbv5front.timeAgo(item.modify) }}"></span></span>' +
        '       </p>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +
        '    <div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>' +
        '</div>' +

        '<div class="layui-col-md3">' +
        '<div id="affix-side">' +

        '<div class="layui-card" id="info-panel">' +
        '<div class="layui-card-body text-center" style="padding: 20px;">' +
        '<a class="no-hover" href="/login" target="_blank"><img src="{{d.settings.info_label_logo}}" class="layui-circle" style="max-width: 150px;" alt="{{d.settings.info_label_nickname}}"></a>' +
        '<p class="layui-text nbv5-font" style="margin: 10px;color: #F44336;">Master：<b>{{d.settings.info_label_nickname}}</b></p>' +
        '<hr>' +
        '{{# if(d.nbv5su != null){}}' +
        '<p>当前登录: <a href="/login?redirectUrl=/ubs/token/index" class="no-hover"><img src="{{d.nbv5su.avatar}}" style="width: 30px;height: 30px;" class="layui-circle"></a>' +
        '<label style="color: #F44336;font-weight: bolder;margin-left: 10px;"><a href="/login?redirectUrl=/ubs/token/index">{{d.nbv5su.nickname}}</a></label>' +
        '</p>' +
        '{{#}else{}}' +
        '<p class="layui-text">未检测到用户，<a href="/login?redirectUrl=/ubs/token/index" class="no-hover" style="color: #F44336;">点我</a>登录</p>' +
        '{{#}}}' +
        '<div class="layui-mt10">{{d.settings.info_label_text}}</div>' +
        '</div>' +
        '</div>' +

        '<div class="layui-card" id="search-panel">' +
        '<div class="layui-card-body">' +
        ' <p class="title">搜索 <small style="float: right;">' +
        '   <a style="cursor: pointer;" onclick="aboutSearch();" target="_blank"><i>关于 <i class="fa fa-info-circle"></i></i></a></small> </p>' +
        '       <hr>' +
        '       <input name="words" onkeypress="searchAll(event);"' +
        '           placeholder="键入Enter键以搜索" autocomplete="off" class="layui-input search-box">' +
        '</div>' +
        '</div>' +

        '<div class="layui-card" id="cate-panel">' +
        '<div class="layui-card-body">' +
        ' <p class="title">分类 </p>' +
        ' <hr>' +
        ' <div class="layui-btn-container">' +
        '{{# layui.each(d.cateList, function(index, item){ }}' +
        '<a href="/s/c?q={{item.name}}" class="layui-btn layui-btn-primary layui-btn-sm"><i class="fa fa-dot-circle-o"></i> {{item.name}}</a>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +
        '</div>' +

        '<div class="layui-card" id="article-random-panel">' +
        '<div class="layui-card-body">' +
        ' <p class="title">随便看看 </p>' +
        ' <hr>' +
        '{{# layui.each(d.randomArticles, function(index, item){ }}' +
        '<blockquote class="layui-elem-quote" style="padding: 5px 10px;background: #f8f8f8;">' +
        '<p class="layui-text layui-elip">' +
        '<a href="/article/{{item.id}}" class="layui-word-aux">{{item.title}}</a>' +
        '</p>' +
        '</blockquote>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +


        '<div class="layui-card layui-tags" id="tag-panel">' +
        '<div class="layui-card-body">' +
        '<p class="title">标签墙</p>' +
        '<hr>' +
        '{{# layui.each(d.tagList, function(index, item){ }}' +
        '<span class="layui-badge-rim"><a href="/s/t?q={{item.name}}" target="_blank">{{item.name}} ({{item.cnt}})</a></span>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +

        '<div class="layui-card layui-tags" id="link-panel">' +
        '<div class="layui-card-body">' +
        '<p class="title">友情链接 <small style="float: right;">' +
        '   <a style="cursor: pointer;" onclick="applyLink(\'{{= d.settings.link_apply_words}}\')" target="_blank"><i>申请 <i class="fa fa-info-circle"></i></i></a></small></p>' +
        '<hr>' +
        '{{# layui.each(d.linkList, function(index, item){ }}' +
        '<a href="{{linkSplit(item.name,1)}}" target="_blank" class="layui-text" style="margin-right: 10px;">{{ linkSplit(item.name,0) }}</a>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +


        '</div>' +
        '</div>' +

        '</div>';


    exports('col93', function (nbv5su, settings, page, cateList, randomArticles, tagList, linkList, articleAuthors, catesMap, articleCommentCountMap) {
        var obj = {
            settings: settings,
            page: page,
            cateList: cateList,
            randomArticles: randomArticles,
            tagList: tagList,
            linkList: linkList,
            articleAuthors: articleAuthors,
            catesMap: catesMap,
            articleCommentCountMap: articleCommentCountMap,
            nbv5su: nbv5su
        };
        tpl(col_l9r3).render(obj, function (html) {
            $("#main-body").prepend(html);
            initPage(laypage, page);
            timeago.render($('.timeago'));
            stickySideBar(page);
        });
    });

});


function searchAll(e) {
    var keyNum = window.event ? e.keyCode : e.which;
    if (keyNum === 13) {
        var words = $("input[name=words]").val();
        location.href = "/s/w?q=" + encodeURI(words);
    }
}

function aboutSearch() {
    layer.msg("仅做标题和文章原始内容关键字匹配！");
}

function applyLink(txt) {
    layer.msg(txt, {time: 5000});
}

function linkSplit(link, index) {
    var links = link.split("，");
    if (index === 0 || index === 1) {
        return links[index];
    } else {
        return "";
    }
}

function stickySideBar(page) {
    if (page.total > 3) {
        nbv5front.clearSticky();
        new hcSticky("#affix-side", {
            stickTo: '#main-body'
            , innerSticker: '#info-panel'
            , queries: {980: {disable: true}}
            , top: 75
            , bottom: 15
        });
    }
}

function initPage(pageObj, page) {
    pageObj.render({
        elem: 'page-btns'
        , count: page.total
        , limit: page.size
        , curr: page.current
        , layout: ['count', 'prev', 'page', 'next']
        , jump: function (obj, first) {
            if (!first) {
                location.href = "/index?p=" + obj.curr
            }
        }
    });
}