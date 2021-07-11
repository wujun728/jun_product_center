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
        '<div id="article-list-long-card" class="layui-row long-card animated fadeInUp" style="margin-bottom: 20px;">' +
        '<div class="animated fadeInUp">' +
        '{{# layui.each(d.page.records, function(index, item){ }}' +

        '<div class="layui-card layui-col-xs12" style="margin-bottom: 30px;">' +
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
        '       {{# } }}'+
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
        '<div class="layui-fluid layui-row">' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-col-md9 layui-col-xs12">' +
        '       {{# }else{ }}' +
        '<div class="layui-col-xs12">' +
        '       {{# } }}' +
        '<div style="text-indent: 35px;line-height: 28px;color: #999 !important;word-break: break-all;">' +
        '{{ item.summary }}' +
        '<a href="/article/{{ item.id }}">...</a>' +
        '</div>' +
        '</div>' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-hide-xs layui-hide-sm layui-show-md-block layui-col-md3" style="text-align: right;">' +
        '<a href="/article/{{item.id}}"><img src="{{item.cover}}" alt="" ' +
        'style="width: 75%;height:125px;display: inline-block;padding: 7px;border: 1px solid #dedede;' +
        'box-shadow: 0 1px 3px rgba(0, 0, 0, .3);border-radius: 4px;margin-top: 5px;cursor: pointer;"/> ' +
        '</a> ' +
        '</div>' +
        '       {{# } }}' +
        '</div>' +
        '<div class="layui-fluid layui-row" {{= item.cover == null || item.cover ==""? "style=margin-top:25px;" : ""}}>' +
        '       <p class="article-footer">' +
        '           <span style="margin-right: 20px;"><i class="fa fa-thermometer-1"></i> 热度：<span style="font-weight: bold;font-style: italic;">{{ item.views }}℃</span></span>' +
        '           {{# if(d.articleCommentCountMap[item.id] > 0){ }}' +
        '           <span class="layui-mr15"><i class="fa fa-commenting-o"></i> 评论: <span style="font-weight: bold;font-style: italic;">{{ d.articleCommentCountMap[item.id] }}</span>条</span>' +
        '           {{# } }}' +
        '           <span style="margin-right: 20px;"><i class="fa fa-user-o"></i> 作者：<span style="font-weight: bold;color: #986b0d;font-style: italic;">{{ d.articleAuthors[item.id] }}</span></span>' +
        '           <span style="margin-right: 20px;"><i class="fa fa-calendar"></i> 更新：<span style="font-weight: bold;font-style: italic;" class="timeago" datetime="{{ nbv5front.timeAgo(item.modify) }}"></span></span>' +
        '       </p>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +

        '    <div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>' +

        '</div>' +

        '</div>';


    exports('long_card', function (page, articleAuthors, tagsMap, catesMap, articleCommentCountMap) {
        var obj = {
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