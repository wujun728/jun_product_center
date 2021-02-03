/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['laytpl', 'timeago', 'laypage'], function (exports) {
    var tpl = layui.laytpl,
        timeago = layui.timeago,
        laypage = layui.laypage;

    var _tpl = '' +
        '{{# if(d.total === 0){ }}' +
        '<p class="layui-mt15 text-center">暂无结果...</p>' +
        '{{# } }}' +
        '<div class="animated fadeInUp">' +
        '{{# layui.each(d.records, function(index, item){ }}' +
        '<div class="layui-card">' +
        '<div class="layui-card-body search">' +
        '<h2 class="search-title">' +
        '{{# if(item.type === "article"){ }}' +
        '<span class="article"><i class="fa fa-file-text-o"></i> 文章</span>' +
        '{{# } }}' +
        '{{# if(item.type === "note"){ }}' +
        '<span class="note"><i class="fa fa-hashtag"></i> 笔记</span>' +
        '{{# } }}' +
        '{{# if(item.type === "article"){ }}' +
        '<a href="/article/{{item.id}}" target="_blank">' +
        '{{item.title}}' +
        '</a>' +
        '{{# }else if(item.type ==="note"){ }}' +
        '<a href="/note?w={{item.title}}" target="_blank">' +
        '{{item.title}}' +
        '</a>' +
        '{{#}else{}}' +
        '<span>{{item.title}}</span>' +
        '{{# } }}' +
        '</spa>' +
        '</h2>' +
        '<div class="search-content layui-mt15 layui-word-aux" style="word-break: break-word;">{{item.resContent}}...</div>' +
        '<p class="search-date layui-mt10">' +
        '<i class="fa fa-calendar-check-o"></i> ' +
        '<span class="timeago" datetime="{{nbv5front.timeAgo(item.post)}}"></span>' +
        '</p>' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +
        '</div>' +
        '    <div class="index-page-btn text-center layui-mt15">' +
        '        <div id="page-btns"></div>' +
        '    </div>';

    exports('search', function (pageObj, keywords, searchType) {
        tpl(_tpl).render(pageObj, function (html) {
            $("#main-body").append(html);
            timeago.render($(".timeago"));
            initPage(laypage, pageObj, keywords, searchType);
        })
    });
});


function searchFocus(t) {
    $(t).css("width", "50%");
}

function searchBlur(t) {
    var txt = $(t).val();
    if (txt.length === 0) {
        $(t).css("width", "200px");
    }
}

function searchBtn() {
    location.href = "/s/w?q=" + $("input[name=q]").val();
}


function initPage(laypage, pageObj, q, searchType) {
    laypage.render({
        elem: 'page-btns'
        , count: pageObj.total
        , limit: pageObj.size
        , curr: pageObj.current
        , layout: ['count', 'prev', 'page', 'next']
        , jump: function (obj, first) {
            if (!first) {
                location.href = "/s/" + searchType + "?q=" + q + "&p=" + obj.curr
            }
        }
    });
}