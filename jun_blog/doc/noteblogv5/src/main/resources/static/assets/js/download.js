/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
layui.define(['laytpl', 'timeago', 'laypage'], function (exports) {
    var laytpl = layui.laytpl,
        timeago = layui.timeago,
        laypage = layui.laypage;

    var _tpl = '' +
        '   <div id="download-operate" class="layui-row layui-mt20">' +
        '       <div class="layui-col-lg4 layui-col-md4 layui-col-sm5 layui-col-xs12 " id="stickMe">' +
        '           <input name="words" onkeyup="searchDownload(event)" ' +
        '                       placeholder="{{d.words}}"' +
        '                        autocomplete="off" class="layui-input search-box" style="height: 35px; ">' +
        '       </div>' +
        '</div>' +
        '   <hr>' +
        '<div class="layui-mt20 animated fadeInUp">' +
        '{{# layui.each(d.pageObj.records, function(index, item){ }}' +
        '<div class="dl layui-card" style="display: inline-block;width: 100%;">' +
        '<div class="layui-card-body layui-row layui-col-space20">' +
        '<div class="layui-col-md7 layui-col-xs12"> ' +
        '<fieldset class="layui-elem-field layui-field-title">' +
        '  <legend class="center-to-head">' +
        '       {{# if(item.top){ }}' +
        '       <span class="layui-badge  layui-bg-red">置顶</span>' +
        '       {{# } }}' +
        '       <i class="fa fa-sun-o"></i>  <a href="{{ item.downloadUrl }}" target="_blank">{{ item.title }}</a>' +
        '</legend>' +
        '</fieldset>' +
        '</div>' +
        '<div class="layui-col-md2 dl-item layui-hide-sm layui-hide-xs layui-show-md-inline-block">' +
        '{{# layui.each(d.downloadCatesMap[item.id], function(index, item){ }}' +
        '       <span class="layui-badge no-select" style="margin-right: 10px;padding: 5px; background-color: #e6e6e6;color: #0c0c0c;"><i class="fa fa-tag"></i> {{ item.name }} </span>' +
        '{{# });  }}' +
        '</div>' +
        '<div class="layui-col-md1 dl-item layui-hide-sm layui-hide-xs layui-show-md-inline-block"><span class="timeago" datetime="{{ nbv5front.timeAgo(item.createTime) }}"></span></div>' +
        '<div class="layui-col-md1 dl-item layui-hide-sm layui-hide-xs layui-show-md-inline-block">{{(item.downloadKey == null||item.downloadKey == \'\')?\'- \':item.downloadKey}}</div>' +
        '<div class="layui-col-md1 dl-item dl-btn layui-hide-sm layui-hide-xs layui-show-md-inline-block"><a href="{{ item.downloadUrl }}" target="_blank"><i class="fa fa-download"></i></a> </div>' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +
        '</div>' +

        '<div class="layui-row layui-container layui-mt20">  ' +
        '<div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>' +
        '</div>  ';


    exports("download", function (pageObj, words, downloadCatesMap) {
        var po = {
            pageObj: pageObj,
            words: words === null || words === "" ? '输入关键字，按【Enter/回车】键搜索' : words,
            downloadCatesMap: downloadCatesMap
        };
        laytpl(_tpl).render(po, function (html) {
            $("#download-body").html(html);
            timeago.render($(".timeago"));
            initPage(pageObj, laypage);
            stickySearch();
        });
    });

});

function stickySearch() {
    nbv5front.clearSticky();

    var p = 0, t = 0;
    var sticky;

    $(window).scroll(function () {
        p = $(this).scrollTop();
        if (t < p) {
            if (sticky != null) {
                sticky.destroy();
            }
            sticky = new hcSticky("#download-operate", {
                stickTo: '#download-body',
                top: 15
            });
            //下滚
        } else {
            if (sticky != null) {
                sticky.destroy();
            }
            sticky = new hcSticky("#download-operate", {
                stickTo: '#download-body',
                top: 65
            });
            //上滚
        }
        setTimeout(function () {
            t = p;
        }, 0)
    })
}

function searchDownload(e) {
    var keyNum = window.event ? e.keyCode : e.which;
    if (keyNum === 13) {
        var words = $("input[name=words]").val();
        location.href = "/dl?w=" + encodeURI(words);
    }
}


function initPage(pageObj, page) {
    page.render({
        elem: 'page-btns'
        , count: pageObj.total
        , limit: pageObj.size
        , curr: pageObj.current
        , layout: ['count', 'prev', 'page', 'next']
        , jump: function (obj, first) {
            if (!first) {
                var words = nbv5front.getQueryString("w");
                words = words === null ? "" : words;
                location.href = "/dl?w=" + words + "&p=" + obj.curr
            }
        }
    });
}


