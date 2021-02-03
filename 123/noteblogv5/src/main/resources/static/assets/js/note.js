/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
var po;
layui.define(['laytpl', 'timeago', 'laypage'], function (exports) {
    var laytpl = layui.laytpl,
        timeago = layui.timeago,
        laypage = layui.laypage;

    var _tpl = '' +
        '   <div id="note-operate" class="layui-row">' +
        '       <div class="layui-col-lg4 layui-col-md4 layui-col-sm5 layui-col-xs12 animated fadeInUp" id="stickMe">' +
        '           <input name="words" onkeyup="searchNote(event)" ' +
        '                       placeholder="{{d.words}}"' +
        '                        autocomplete="off" class="layui-input search-box" style="height: 35px; ">' +
        '       </div>' +
        '       <div class="layui-col-lg-offset4 layui-col-md-offset4 layui-col-lg4 layui-show-md-inline-block  layui-hide-sm" style="text-align: right;">' +
        '           <div class="layui-btn-group">' +
        '               <button id="collaspan" class="layui-btn layui-btn-primary layui-btn-sm">' +
        '                    <i class="fa fa-angle-double-down"></i> 展开' +
        '                </button>' +
        '               <button id="expand" class="layui-btn layui-btn-primary layui-btn-sm">' +
        '                    <i class="fa fa-angle-double-up"></i> 收起' +
        '                </button>' +
        '<a onclick="clickOrder(\'desc\')" id="time-desc" class="layui-btn layui-btn-primary layui-btn-sm">' +
        '<i class="fa fa-angle-down"></i> 最近</a>' +
        '<a onclick="clickOrder(\'asc\')" id="time-asc" class="layui-btn layui-btn-primary layui-btn-sm">' +
        '<i class="fa fa-angle-up"></i> 最早</a>' +
        '           </div>' +
        '       </div>' +
        '</div>' +
        '   <hr class="animated fadeInUp">' +
        '   <ul class="layui-timeline" id="timeline">' +
        '       <li class="layui-timeline-item">' +
        '            <i class="layui-icon layui-timeline-axis">&#xe63f;</i>' +
        '            <div class="layui-timeline-content layui-text">' +
        '                <h3 class="layui-timeline-title">封面</h3>' +
        '            </div>' +
        '        </li>' +

        '{{# layui.each(d.pageObj.records, function(index, item){ }}' +
        '<li class="layui-timeline-item animated slideInUp" >' +
        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>' +
        '<div class="layui-timeline-content layui-text">' +
        '<h3 class="layui-timeline-title" nid="{{index}}">' +
        '{{# if(item.top){}}' +
        '<span class="layui-badge">置顶</span>\n' +
        '{{#}}}' +
        '<i class="fa fa-hashtag"></i> ' +
        '{{item.title}}' +
        '<small class="timeago" style="margin-left: 10px;font-style: italic;color: #cccccc;" datetime="{{nbv5front.timeAgo(item.post) }}">' +
        '</small>' +
        '</h3>' +
        '<div class="timeline-body ' +
        '{{# if(index===0){}}' +
        'open ' +
        '{{#}}}' +
        '" style="width: 80%;background-color: inherit;" id="mdc{{index}}">' +
        '</div>' +
        '</div>' +
        '</li>' +
        '{{# }); }}' +
        '<li class="layui-timeline-item layui-note-cover">' +
        '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>' +
        '<div class="layui-timeline-content layui-text">' +
        '<h3 class="layui-timeline-title">页末</h3>' +
        '</div>' +
        '    <div class="index-page-btn text-center">' +
        '        <div id="page-btns"></div>' +
        '    </div>' +
        '</li>' +

        '   </ul>' +
        '</div>';


    exports("note", function (pageObj, words) {
        po = pageObj;
        var obj = {
            pageObj: pageObj,
            words: words === null || words === "" ? '输入关键字，按【Enter/回车】键搜索' : words
        };
        laytpl(_tpl).render(obj, function (html) {
            $("#note-body").html(html);
            timeago.render($(".timeago"));
            initPage(pageObj, laypage);
            stickySearch();
            resolveMds(pageObj.records);
            clickNote();
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
            sticky = new hcSticky("#note-operate", {
                stickTo: '#note-body',
                top: 15
            });
            //下滚
        } else {
            if (sticky != null) {
                sticky.destroy();
            }
            sticky = new hcSticky("#note-operate", {
                stickTo: '#note-body',
                top: 65
            });
            //上滚
        }
        setTimeout(function () {
            t = p;
        }, 0)
    })
}

function searchNote(e) {
    var keyNum = window.event ? e.keyCode : e.which;
    if (keyNum === 13) {
        var words = $("input[name=words]").val();
        location.href = "/note?w=" + encodeURI(words);
    }
}

function clickOrder(orderDirection) {
    var w = nbv5front.getQueryString("w");
    var p = nbv5front.getQueryString("p");
    w = w === null ? "" : w;
    p = p === null && (typeof p !== 'number' || isNaN(p)) ? 1 : p;
    location.href = "/note?w=" + w + "&p=" + p + "&s=" + orderDirection;
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
                location.href = "/note?w=" + words + "&p=" + obj.curr
            }
        }
    });
}

function resolveMds(note) {
    for (var i = 0; i < note.length; i++) {
        editormd.markdownToHTML("mdc" + i, {
            markdown: note[i].mdContent,//+ "\r\n" + $("#append-test").text(),
            //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
            htmlDecode: "style,script,iframe",  // you can filter tags decode
            tocContainer: "#custom-toc-container", // 自定义 ToC 容器层
            //gfm             : false,
            //tocDropdown     : true,
            markdownSourceCode: false, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
            emoji: false,
            taskList: true,
            tex: true,  // 默认不解析
            flowChart: true,  // 默认不解析
            sequenceDiagram: true// 默认不解析
        });
        if (i > 0) {
            $("#mdc" + i).css("display", "none");
        }
    }
}

function clickNote() {
    var $body = $("body");
    $body.on("click", ".layui-timeline-title", function () {
        var $this = $(this);
        var $timelineBody = $this.next(".timeline-body");
        $(".timeline-body").slideUp();
        $(".timeline-body").removeClass("open");
        $timelineBody.slideDown();
        $timelineBody.addClass("open");
    });
    $body.on("click", "#note-operate #expand", function () {
        $(".timeline-body").slideUp()
    });
    $body.on("click", "#note-operate #collaspan", function () {
        $(".timeline-body").slideDown()
    });
}