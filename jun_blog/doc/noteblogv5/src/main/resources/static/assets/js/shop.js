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


    var card_tpl =
        '<div id="article-list-card-media" class="layui-row layui-col-space20 card-media animated fadeInUp">' +
        '{{# layui.each(d.page.records, function(index, item){ }}' +
        '<div class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">' +
        '<div class="layui-card nbv5-card">' +
        '<div class="layui-card-body">' +

        '<div class="layui-row">' +
        '<fieldset class="layui-col-xs12 layui-elem-field layui-field-title">' +
        '  <legend class="center-to-head" style="display: inline-block;">' +
        '       {{# if(item.top){ }}' +
        '       <span class="layui-badge layui-bg-red">置顶</span>' +
        '       {{# } }}' +
        '       {{# if(!item.reprinted){ }}' +
        '       <span class="yuanchuang">原创</span>' +
        '       {{# } }}' +
        '       {{# if(item.reprinted){ }}' +
        '       <span class="zhuanzai">转载</span>' +
        '       {{# } }}' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '       <a href="/article/u{{ item.urlSeq }}">{{item.title}}</a>' +
        '       {{# }else{ }}' +
        '       <a href="/article/{{ item.id }}">{{item.title}}</a>' +
        '       {{# } }}' +
        '</legend>' +
        '<blockquote class="layui-elem-quote card-cates" style="background-color: #f8f8f8;padding: 5px 5px 5px 10px;margin-top: 10px;">' +
        '{{# layui.each(d.catesMap[item.id], function(index, item){ }}' +
        '       <span class="layui-badge no-select" style="margin:4px 4px 4px 0;padding: 4px; background-color: #e6e6e6;color: #0c0c0c;border-radius: 4px;"><i class="fa fa-tag"></i> {{item.name}} </span>' +
        '{{# });  }}' +
        '</blockquote>' +
        '</fieldset>' +
        '</div>' +

        '<div class="layui-row">' +
        '       {{# if(item.cover == null || item.cover ==""){ }}' +
        '<div class="layui-col-xs12">' +
        '<div style="text-indent: 35px;line-height: 28px;color: #999 !important;word-break: break-all;">' +
        '{{ nbv5front.substr(item.summary,150) }}' +
        '<a href="/article/{{ item.id }}">...</a>' +
        '</div>' +
        '</div>' +
        '       {{# } }}' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-col-xs12" style="text-align: center;overflow: hidden;border-radius: 4px;">' +
        '<a href="/article/{{item.id}}" style="display:block;padding: 7px;border: 1px solid #dedede;box-shadow: 0 1px 3px rgba(0, 0, 0, .3);border-radius: 4px;">' +
        '<figure  class="card-img" style="background-image:url({{item.cover}});' +
        'cursor: pointer;background-size: cover;margin:0;" ></figure>' +
        '<div class="card-content">{{nbv5front.substr(item.summary,120)}}</div> ' +
        '</a> ' +
        '</div>' +
        '       {{# } }}' +
        '</div>' +

        '<hr class="card-line">' +


        '<div class="layui-row card-tags">' +
        '{{# layui.each(d.tagsMap[item.id], function(index, item){ }}' +
        '       {{# if(index<3){ }}' +
        '<span style="margin-right: 15px;display: inline-block;"><i class="fa fa-hashtag"></i>' +
        '{{ item.name }}</span>' +
        '       {{# } }}' +
        '{{# });  }}' +
        '</div>' +

        '<div class="layui-row card-meta">' +
        '       <p class="article-footer">' +
        '           <span class="layui-mr15"><i class="fa fa-thermometer-1"></i><span style="font-weight: bold;font-style: italic;">{{ item.views }}℃</span></span>' +
        '           <span class="layui-mr15"><i class="fa fa-calendar"></i> <span style="font-weight: bold;font-style: italic;" class="timeago" datetime="{{ nbv5front.timeAgo(item.post) }}"></span></span>' +
        '           <span class="layui-mr15"><i class="fa fa-comment-o"></i> <span style="font-weight: bold;font-style: italic;">{{d.articleCommentCountMap[item.id] === 0?"暂无":d.articleCommentCountMap[item.id] }}</span></span>' +
        '       </p>' +
        '</div>' +


        '</div>' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +

        '</div>' +

        '<div class="layui-row">' +
        '    <div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>' +
        '</div>';


    exports('card_media', function (clazz, page, articleAuthors, tagsMap, catesMap, articleCommentCountMap) {
        var obj = {
            clazz: clazz,
            page: page,
            articleAuthors: articleAuthors,
            tagsMap: tagsMap,
            catesMap: catesMap,
            articleCommentCountMap: articleCommentCountMap
        };
        tpl(card_tpl).render(obj, function (html) {
            $("#main-body").prepend(html);
            initPage(laypage, page);
            timeago.render($('.timeago'));
            cardContent();
        });
    });

});


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


function cardContent() {
    var $cardContents = $("div.card-content");
    for (var i = 0; i < $cardContents.length; i++) {
        if ($("div.card-content:eq(" + i + ")").prev("figure.card-img").width() - $("div.card-content:eq(" + i + ")").width() >= 50) {
            $("div.card-content:eq(" + i + ")").css("width", "100%");
        }
    }
}
