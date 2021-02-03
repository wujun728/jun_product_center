/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['laytpl', 'laypage'], function (exports) {
    var tpl = layui.laytpl,
        laypage = layui.laypage;
    var col12_tpl = '<div class="layui-row simple">' +
        '<div id="article-list-col12" class="layui-container">' +
        '{{# layui.each(d.page.records, function(index, item){ }}' +
        '<blockquote class="layui-elem-quote simple-article animated fadeInUp  layui-row layui-col-space15">' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-col-xs12 layui-col-sm12 layui-col-md10">' +
        '       {{# }else{ }}' +
        '<div class="layui-col-xs12 layui-col-sm12 layui-col-md12">' +
        '       {{# } }}' +
        '   <div class="article-title center-to-head">' +
        '       {{# if(item.top){ }}' +
        '       <span class="layui-badge  layui-bg-cyan">置顶</span>' +
        '       {{# } }}' +
        '       {{# if(!item.reprinted){ }}' +
        '       <span class="yuanchuang">原创</span>' +
        '       {{# } }}' +
        '       {{# if(item.reprinted){ }}' +
        '       <span class="zhuanzai">转载</span>' +
        '       {{# } }}' +
        '       {{# layui.each(d.catesMap[item.id], function(index, item){ }}' +
        '       <span class="layui-badge" style="background: #F44336;">{{ item.name }} </span>' +
        '       {{# });  }}' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '       <a href="/article/u{{ item.urlSeq }}">{{ item.title }}</a>' +
        '       {{# }else{ }}' +
        '       <a href="/article/{{ item.id }}">{{ item.title }}</a>' +
        '       {{# } }}' +
        '   </div>' +
        '   <div class="article-title sm">' +
        '       {{# if(item.top){ }}' +
        '       <span class="layui-badge layui-bg-cyan">置顶</span>' +
        '       {{# } }}' +
        '{{# layui.each(d.catesMap[item.id], function(index, item){ }}' +
        '       <span class="layui-badge" style="background: #F44336;">{{ item.name }} </span>' +
        '{{# });  }}' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '       <a href="/article/u{{ item.urlSeq }}">{{ item.title }}</a>' +
        '       {{# }else{ }}' +
        '       <a href="/article/{{ item.id }}">{{ item.title }}</a>' +
        '       {{# } }}' +
        '   </div>' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '   <div class="article-body normal" style="text-indent: 35px;margin-bottom: 20px;">{{ item.summary }}<a href="/article/u{{ item.urlSeq }}">...</a></div>' +
        '       {{# }else{ }}' +
        '   <div class="article-body normal" style="text-indent: 35px;margin-bottom: 20px;">{{ item.summary }}<a href="/article/{{ item.id }}">...</a></div>' +
        '       {{# } }}' +
        '       {{# if(item.urlSeq != null && item.urlSeq !=""){ }}' +
        '   <div class="article-body sm" style="text-indent: 35px;margin-bottom: 20px;">{{  item.summary.substring(0,Math.ceil(nbv5front.lenStat(item.summary)/2))  }}<a href="/article/u{{ item.urlSeq }}">...</a></div>' +
        '       {{# }else{ }}' +
        '   <div class="article-body sm" style="text-indent: 35px;margin-bottom: 20px;">{{ item.summary.substring(0,Math.ceil(nbv5front.lenStat(item.summary)/2)) }}<a href="/article/{{ item.id }}">...</a></div>' +
        '       {{# } }}' +
        '   <div class="article-footer">' +
        '       <p>' +
        '           <span class="layui-mr15"><i class="fa fa-thermometer-1"></i> 热度：<span>{{ item.views }}℃</span></span>' +
        '           {{# if(d.articleCommentCountMap[item.id] > 0){ }}' +
        '           <span class="layui-mr15"><i class="fa fa-commenting-o"></i> 评论：<span>{{ d.articleCommentCountMap[item.id] }}</span>条</span>' +
        '           {{# } }}' +
        '           {{# if(d.articleCommentCountMap[item.id] == 0){ }}' +
        '           <span class="layui-mr15"><i class="fa fa-user-o"></i> 用户：<span>{{ d.articleAuthors[item.id] }}</span></span>' +
        '           {{# } }}' +
        '           <span class="layui-mr15"><i class="fa fa-calendar"></i> 更新：<span class="detail-date">{{ nbv5front.wholeCnDate(item.modify) }}</span><span class="simple-date">{{ nbv5front.simpleDate(item.modify) }}</span></span>' +
        '       </p>' +
        '   </div>' +
        '</div>' +
        '       {{# if(item.cover != null && item.cover !=""){ }}' +
        '<div class="layui-hide-xs layui-hide-sm layui-show-md-inline-block layui-col-md2">' +
        '   <a target="_blank" href="/article/{{item.id}}"><img class="simple-article-cover" src="{{item.cover}}" ></a> ' +
        '</div> ' +
        '       {{# } }}' +
        '</blockquote>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +
        '    <div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>';


    exports('col12', function (page, catesMap, articleAuthors, articleCommentCountMap) {
        var obj = {
            page: page,
            catesMap: catesMap,
            articleAuthors: articleAuthors,
            articleCommentCountMap: articleCommentCountMap
        };
        tpl(col12_tpl).render(obj, function (html) {
            $("#main-body").prepend(html);
            laypage.render({
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
        });
    });
});




