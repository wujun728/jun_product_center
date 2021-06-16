/** Created By Wuwenbin https://wuwenbin.me
 * mail to wuwenbinwork@163.com
 * 欢迎加入我们，QQ群：697053454
 * if you use the code,  please do not delete the comment
 * 如果您使用了此代码，请勿删除此头部注释
 * */
var __nbv5__article;
layui.define(['laytpl', 'timeago', 'laypage'], function (exports) {
    var tpl = layui.laytpl,
        timeago = layui.timeago,
        laypage = layui.laypage;
    var _tpl = '' +
        '<div class="article-detail layui-row layui-col-space15">' +


        '       {{# if(d.settings.article_page_style === "-1"){ }}' +
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
        '<span class="layui-badge-rim"><a href="/" target="_blank">{{item.name}} ({{item.cnt}})</a></span>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +

        '<div class="layui-card layui-tags" id="link-panel">' +
        '<div class="layui-card-body">' +
        '<p class="title">友情链接<small style="float: right;">' +
        '   <a style="cursor: pointer;" onclick="applyLink(\'{{= d.settings.link_apply_words}}\')" target="_blank"><i>申请 <i class="fa fa-info-circle"></i></i></a></small></p>' +
        '<hr>' +
        '{{# layui.each(d.linkList, function(index, item){ }}' +
        '<a href="{{linkSplit(item.name,1)}}" target="_blank" class="layui-text" style="margin-right: 10px;">{{ linkSplit(item.name,0) }}</a>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +


        '</div>' +
        '</div>' +
        '       {{# } }}' +

        '<div class="top-title layui-container layui-row">' +
        '       {{# if(d.settings.article_page_style === "-1"){ }}' +
        '<div class="layui-hide-xs layui-hide-sm layui-col-md4 layui-col-lg4 layui-show-md-inline-block layui-show-lg-inline-block">' +
        '<a class="layui-btn layui-btn-normal" href="#comment-list">写评论</a> ' +
        '<a class="layui-btn layui-btn-primary" onclick="emotion();"><i class="fa fa-thumbs-o-up"></i> 点个赞</a> ' +
        '</div>' +
        '<h2 class="layui-col-xs12 layui-col-sm12 layui-col-md8 layui-col-lg8 layui-elip">{{d.articleObj.title}}</h2>' +
        '       {{# } }}' +
        '       {{# if(d.settings.article_page_style === "1"||d.settings.article_page_style === "0"){ }}' +
        '<h2 class="layui-col-xs12 layui-col-sm12 layui-col-md8 layui-col-lg8 layui-elip">{{d.articleObj.title}}</h2>' +
        '<div class="layui-hide-xs layui-hide-sm layui-col-md4 layui-col-lg4 layui-show-md-inline-block layui-show-lg-inline-block">' +
        '<a class="layui-btn layui-btn-normal" href="#comment-list">写评论</a> ' +
        '<a class="layui-btn layui-btn-primary" onclick="emotion();"><i class="fa fa-thumbs-o-up"></i> 点个赞</a> ' +
        '</div>' +
        '       {{# } }}' +
        '</div>' +

        '       {{# if(d.settings.article_page_style === "0"){ }}' +
        '<div class="layui-col-xs12 animated fadeInUp">' +
        '       {{# }else if(d.settings.article_page_style === "-1"||d.settings.article_page_style === "1"){ }}' +
        '<div class="layui-col-md9 animated fadeInUp">' +
        '       {{# } }}' +
        '<div class="layui-card">' +
        '<div class="layui-card-body article-content" style="max-height: 1400px;">' +
        '<div class="text-center"><h3 class="article-title">{{d.articleObj.title}} ' +
        '       {{# if(d.articleObj.reprinted){ }}' +
        '<span class="zhuanzai">转载</span>' +
        '       {{# } }}' +
        '       {{# if(!d.articleObj.reprinted){ }}' +
        '<span class="yuanchuang">原创</span>' +
        '       {{# } }}' +
        '</h3></div>' +
        '   <div class="layui-row">' +
        '       <div class="layui-col-md12 text-center article-meta">' +
        '             <span><i class="fa fa-clock-o"></i> {{nbv5front.wholeCnDate(d.articleObj.post)}}</span>' +
        '              <span><i class="fa fa-user-o"></i> <span style="color: #FF5722;margin-right: 5px;">{{d.author}}</span><svg class="icon" aria-hidden="true"><use xlink:href="#icon-renzhengkaobei"></use></svg></span>' +
        '              <span><i class="fa fa-comment-o"></i> {{d.comments.total}}</span>' +
        '              <span><i class="fa fa-eye"></i> {{d.articleObj.views}}</span>' +
        '        </div>' +
        '      </div>' +
        '<hr>' +
        '       {{# if(!isRichTxt(d.articleObj)){ }}' +
        '<div id="doc-content" class="content layui-col-md12 markdown-body editormd-html-preview" style="margin-bottom: 20px;"></div>' +
        '       {{# } }}' +
        '       {{# if(isRichTxt(d.articleObj)){ }}' +
        '<div id="doc-content1" class="content layui-col-md12" style="margin-bottom: 20px;" >{{ d.articleObj.content}}</div>' +
        '       {{# } }}' +
        '<div id="custom-toc-container" style="margin-left: 15px;display: none;"></div>' +
        ' <div class="layui-row text-center layui-mt20">' +
        '       {{# if(d.articleObj.appreciable){ }}' +
        '     <div class="layui-btn layui-hide layui-show-md-inline-block" style="background-color: #FFB800;" onclick="money()"><i class="fa fa-rmb"></i> 打赏</div>' +
        '       {{# } }}' +
        '     <div class="layui-btn layui-btn-danger" onclick="emotion()"><i class="fa fa-thumbs-o-up"></i> 赞 (<span id="u-approve">{{d.articleObj.approveCnt}}</span>)</div>' +
        ' </div>' +
        ' <div class="layui-row layui-mt20">' +
        '     <blockquote class="layui-elem-quote text-center " style="border: none;">' +
        '         <span class="layui-show-md-inline-block layui-hide">文章出处：{{ d.settings.website_title }}</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '         <span class="layui-show-md-inline-block layui-hide">文章地址：{{= window.location.href}}</span>&nbsp;&nbsp;&nbsp;&nbsp;' +
        '         <span>转载注明下哦！o(≧v≦)o~~</span>' +
        '     </blockquote>' +
        ' </div>' +
        ' <div class="layui-row layui-mt20">' +
        '     <p class="blog-tags">' +
        '     标签：' +
        '{{# layui.each(d.tags, function(index, item){ }}' +
        '<span><i class="fa fa-hashtag"></i> {{item.name}} </span>' +
        '{{# });  }}' +
        '     </p>' +
        ' </div>' +
        '</div>' +
        '<div class="hide-article-box hide-article-pos text-center">' +
        '<a href="javascript:openPanel();">展开阅读全文 <i class="fa fa-angle-down"></i> </a>' +
        ' </div>' +
        '</div>' +

        '<div class="layui-card" id="comment-panel">' +
        '<div class="layui-card-body" style="padding: 20px 15px !important;">' +
        '<div class="layui-row">' +
        '<blockquote class="layui-elem-quote">{{d.settings.comment_top_text}}</blockquote> ' +
        '</div>' +
        '<div class="layui-row layui-form layui-mt20">' +
        '<div class="layui-col-sm1 layui-hide-xs layui-show-sm-inline-block layui-user-avatar">' +
        '       {{# if(d.nbv5su != null){ }}' +
        '<a href="/login?redirectUrl=/ubs/token/index"><img src="{{d.nbv5su.avatar}}" class="layui-circle"></a> ' +
        '       {{# }else{ }}' +
        '<a href="{{ loginUrl()}}" id="nologin"><img src="/static/assets/img/nologin.jpg" class="layui-circle"></a>' +
        '       {{# } }}' +
        '</div>' +
        '<div class="layui-col-sm11 layui-col-xs12">' +
        '<input type="hidden" name="replyId">' +
        '<input type="hidden" name="userId" value="{{d.nbv5su!=null?d.nbv5su.id:""}}">' +
        '<input type="hidden" name="nickname" value="{{d.nbv5su!=null?d.nbv5su.nickname:""}}">' +
        '<input type="hidden" name="articleId" value="{{d.articleObj.id}}">' +
        '<textarea name="comment" required lay-verify="required" placeholder="{{d.articleObj.commented && d.settings.global_comment_onoff == "1"?"输入你想说的话":"未开放评论功能"}}" ' +
        '       {{# if(!d.articleObj.commented || d.settings.global_comment_onoff == "0"){ }}' +
        ' disabled="disabled"' +
        '       {{# } }}' +
        ' class="layui-textarea comment" onfocus="commentFocus(this);" onblur="commentBlur(this);"></textarea>' +
        '       {{# if(d.articleObj.commented && d.settings.global_comment_onoff == "1"){ }}' +
        '<p class="layui-mt15"><button class="layui-btn layui-btn-normal layui-btn-sm" onclick="submitComment(this);">发表评论</button>' +
        '<button class="layui-btn layui-btn-primary layui-btn-sm" style="display: none;" id="cancelReply" onclick="cancelReply();">取消回复</button></p>' +
        '       {{# } }}' +
        '       {{# if(!d.articleObj.commented || d.settings.global_comment_onoff == "0"){ }}' +
        '<p class="layui-mt15"><button class="layui-btn layui-btn-normal layui-btn-sm layui-btn-disabled">未开放评论</button>' +
        '       {{# } }}' +
        '</div>' +
        '</div>' +

        '<div id="comment-list">' +
        '{{# layui.each(d.comments.records, function(index, item){ }}' +
        '<div class="layui-row comment-html">' +
        '<div class="layui-col-xs1 layui-user-avatar" style="min-width: 45px;">' +
        '<img src="{{item.avatar}}" class="layui-circle">' +
        '</div>' +
        '<div class="layui-col-xs11 comment-content" style="color: #929292;">' +
        '<label>' +
        '{{# if(item.role == 1){ }}' +
        '<span style="color: #ff262e;margin-right: 5px;">{{item.nickname}}</span>' +
        '<svg class="icon" aria-hidden="true"><use xlink:href="#icon-renzhengkaobei"></use></svg>' +
        '{{#}else{}}' +
        '<span>{{item.nickname}}</span>' +
        '{{#}}}' +
        ' (#{{item.floor}}</label>,' +
        '<span class="timeago" datetime="{{ nbv5front.timeAgo(item.post) }}"></span>)：' +
        '<span class="comment-txt" style="color: #000;line-height: 24px;word-break: break-all;">{{item.comment}}</span>' +
        '<a class="reply" data-comment-id="{{item.id}}" style="display: none;">回复</a> ' +
        '</div>' +
        '</div>' +
        '{{# });  }}' +
        '</div>' +

        '</div>' +
        '<div class="row layui-container">' +
        '       {{# if(d.comments.total>0){ }}' +
        '     <p id="comment-page"></p>' +
        '       {{# } }}' +
        '</div>' +
        '</div>' +

        '</div>' +

        '       {{# if(d.settings.article_page_style === "1"){ }}' +
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
        '<span class="layui-badge-rim"><a href="/" target="_blank">{{item.name}} ({{item.cnt}})</a></span>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +

        '<div class="layui-card layui-tags" id="link-panel">' +
        '<div class="layui-card-body">' +
        '<p class="title">友情链接<small style="float: right;">' +
        '   <a style="cursor: pointer;" onclick="applyLink(\'{{= d.settings.link_apply_words}}\')" target="_blank"><i>申请 <i class="fa fa-info-circle"></i></i></a></small></p>' +
        '<hr>' +
        '{{# layui.each(d.linkList, function(index, item){ }}' +
        '<a href="{{linkSplit(item.name,1)}}" target="_blank" class="layui-text" style="margin-right: 10px;">{{ linkSplit(item.name,0) }}</a>' +
        '{{# });  }}' +
        '</div>' +
        '</div>' +


        '</div>' +
        '</div>' +
        '       {{# } }}' +
        '</div>';


    exports('article', function (settings, author, articleObj, cateList, tagList, linkList, randomArticles, nbv5su, tags, comments) {

        __nbv5__article = {
            nbv5su: nbv5su,
            settings: settings,
            articleObj: articleObj
        };

        var obj = {
            settings: settings,
            author: author,
            articleObj: articleObj,
            cateList: cateList,
            tagList: tagList,
            linkList: linkList,
            randomArticles: randomArticles,
            nbv5su: nbv5su,
            tags: tags,
            comments: comments
        };

        tpl(_tpl).render(obj, function (html) {
            $("#main-body").prepend(html);
            stickySideBar();
            resolveMd(articleObj);
            loginTips();
            hoverReply();
            replyOne(nbv5su);
            timeago.render($(".timeago"));
            commentPage(laypage, comments, articleObj.id, tpl, timeago);
            imgView();
        });

    });

});

/**
 * 是否为富文本而不是md
 * @param article
 * @returns {boolean}
 */
function isRichTxt(article) {
    return article.mdContent == null || article.mdContent === "";
}

function openPanel() {
    $(".article-detail .layui-card-body.article-content").css("height", "auto");
    $(".article-detail .layui-card-body.article-content").css("max-height", '');
    $(".hide-article-box").hide();
}

function linkSplit(link, index) {
    var links = link.split("，");
    if (index === 0 || index === 1) {
        return links[index];
    } else {
        return "";
    }
}

function aboutSearch() {
    layer.msg("仅做标题和文章原始内容关键字匹配！");
}

function applyLink(txt) {
    layer.msg(txt, {time: 5000});
}

function stickySideBar() {
    nbv5front.clearSticky();
    new hcSticky("#affix-side", {
        stickTo: '#main-body'
        , innerSticker: '#info-panel'
        , queries: {980: {disable: true}}
        , top: 75
        , bottom: 15
    });
}

function resolveMd(article) {
    editormd.markdownToHTML("doc-content", {
        markdown: article.mdContent,//+ "\r\n" + $("#append-test").text(),
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
}

function money() {
    var alipay = __nbv5__article.settings.qrcode_alipay;
    var wechat = __nbv5__article.settings.qrcode_wechat;
    alipay = alipay === null || alipay === undefined || alipay === "" ? "/static/assets/img/noqrcode.jpg" : alipay;
    wechat = wechat === null || wechat === undefined || wechat === "" ? "/static/assets/img/noqrcode.jpg" : wechat;
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: ['640px', '300px'],
        shadeClose: true,
        skin: 'text-center',
        content:
            '<div class="layui-fluid">' +
            '   <div class="layui-row layui-mt20 qrcode">' +
            '       <div class="layui-col-md6">' +
            '           <img src="' + wechat + '" style="height: 250px;width: 250px;">' +
            '           <p class="text-center">微信</p>' +
            '       </div>' +
            '       <div class="layui-col-md6">' +
            '           <img src="' + alipay + '" style="height: 250px;width: 250px;">' +
            '           <p class="text-center">支付宝</p>' +
            '       </div>' +
            '   </div> ' +
            '</div>'
    });
}

function emotion() {
    var su = __nbv5__article.nbv5su;
    var articleId = __nbv5__article.articleObj.id;
    var approve = __nbv5__article.articleObj.approveCnt;
    var uid = su !== null ? su.id : "guest";
    if (nbv5front.getCookie("article::" + articleId + "::" + uid) != null) {
        layer.msg("近期您已经点过赞，感谢您的支持！");
    } else {
        $.post("/article/approve", {articleId: articleId}, function (json) {
            if (json.code === 200) {
                nbv5front.setCookie("article::" + articleId + "::" + uid, "nbv5 blog sys");
                var acnt = approve + 1;
                $("#u-approve").text(acnt);
                layer.msg("谢谢您的支持！");
            }
        })
    }
}

function commentFocus(t) {
    $(t).css("height", "100px");
}

function commentBlur(t) {
    var txt = $(t).val();
    if (txt.length === 0) {
        $(t).css("height", "40px");
    }
}

function loginUrl() {
    return "/login?redirectUrl=" + location.href;
}

function loginTips() {
    var __loginIndex;
    $("#nologin").hover(function () {
        __loginIndex = layer.tips('请先登录', this, {
            tips: [1, '#F44336']
        });
    }, function () {
        layer.close(__loginIndex);
    });
}

function hoverReply() {
    $(".comment-content").hover(function () {
        $(this).find(".reply").css("display", "inline-block");
    }, function () {
        $(this).find(".reply").css("display", "none");
    })
}

function replyOne(nbv5su) {
    $(".reply").on("click", function () {
        if (nbv5su === null) {
            layer.confirm('请先登录再操作，是否现在登录？', {icon: 4, title: '消息提示'}, function (index) {
                location.href = "/login?redirectUrl=" + location.href;
            });
        }
        var userTxt = $(this).parent(".comment-content").find("label").text();
        var replyId = $(this).attr("data-comment-id");
        $("input[name=replyId]").val(replyId);
        $("input[name=nickname]").val(userTxt);
        $("textarea[name=comment]").attr("placeholder", "回复@" + userTxt + "): ");
        $("#cancelReply").css("display", "inline-block");
    });
}

function cancelReply() {
    $("textarea[name=comment]").attr("placeholder", "输入你想说的话");
    $("input[name=replyId]").val("");
    $("#cancelReply").css("display", "none");
}

var tempTimes = false;

function submitComment(e) {
    if (!tempTimes) {
        var userId = $("input[name=userId]").val();
        var articleId = $("input[name=articleId]").val();
        if (userId === "") {
            layer.confirm('请先登录再操作，是否现在登录？', {icon: 4, title: '消息提示'}, function (index) {
                location.href = "/login?redirectUrl=" + location.href;
            });
        } else {
            var comment = $("textarea[name=comment]").val();
            var replyId = $("input[name=replyId]").val();
            var nickname = $("input[name=nickname]").val();
            comment = replyId !== null && replyId !== "" ? "回复@<span style='color: #00c4ff;'>" + nickname + ")</span>: " + comment : comment;
            if (comment === "") {
                layer.msg("请填写你想发表的内容！");
            } else {
                $.ajax({
                    url: "/token/comment/sub",
                    type: "post",
                    dataType: "json",
                    data: {
                        articleId: articleId,
                        userId: userId,
                        replyId: replyId,
                        enable: true,
                        comment: comment
                    },
                    success: function (resp) {
                        layer.msg(resp.message || resp.msg);
                        setTimeout(function () {
                            if (resp.code === 200) {
                                location.reload();
                            }
                        }, 1000);
                    },
                    beforeSend: function () {
                        tempTimes = true;
                        $(e).text("发送中...")
                    },
                    complete: function () {
                        $(e).text("发表评论");
                        tempTimes = false;
                    }
                })

            }
        }
    } else {
        layer.msg("不要着急，请等待一会！");
    }
}

function commentPage(laypage, comments, articleId, tpl, timeago) {
    laypage.render({
        elem: "comment-page"
        , count: comments.total
        , layout: ['prev', 'next']
        , jump: function (obj, first) {
            if (!first) {
                layer.msg('第 ' + obj.curr + ' 页');
                $.post("/article/comments", {
                    articleId: articleId
                    , current: obj.curr
                }, function (cs) {
                    var pageTpl = '{{# layui.each(d.records, function(index, item){ }}' +
                        '<div class="layui-row comment-html">' +
                        '<div class="layui-col-xs1 layui-user-avatar" style="min-width: 45px;">' +
                        '<img src="{{item.avatar}}" class="layui-circle">' +
                        '</div>' +
                        '<div class="layui-col-xs11 comment-content" style="color: #929292;">' +
                        '<label>' +
                        '{{# if(item.role == 1){ }}' +
                        '<span style="color: #ff262e;margin-right: 5px;">{{item.nickname}}</span>' +
                        '<svg class="icon" aria-hidden="true"><use xlink:href="#icon-renzhengkaobei"></use></svg>' +
                        '{{#}else{}}' +
                        '<span>{{item.nickname}}</span>' +
                        '{{#}}}' +
                        ' (#{{item.floor}}</label>,' +
                        '<span class="timeago" datetime="{{ nbv5front.timeAgo(item.post) }}"></span>)：' +
                        '<span class="comment-txt" style="color: #000;line-height: 24px;word-break: break-all;">{{item.comment}}</span>' +
                        '<a class="reply" data-comment-id="{{item.id}}" style="display: none;">回复</a> ' +
                        '</div>' +
                        '</div>' +
                        '{{# });  }}';
                    tpl(pageTpl).render(cs, function (html) {
                        $("#comment-list").html(html);
                        timeago.render($(".timeago"));
                    });
                })
            }
        }
    });
}

function purchaseContent(articleId, hideId) {
    layer.confirm('确定购买此内容吗？', {icon: 3, title: '提示'}, function (index) {
        $.ajax({
            type: "post",
            dataType: "json",
            url: "/article/token/purchase",
            data: {
                articleId: articleId,
                hideId: hideId
            },
            success: function (resp) {
                if (resp.code === 200) {
                    layer.msg(resp.message);
                    setTimeout(function () {
                        location.reload();
                    }, 888);
                } else if (resp.code === -1) {
                    layer.confirm('请先登录再操作，是否现在登录？', {icon: 4, title: '消息提示'}, function (index) {
                        window.location.href = "/login?redirectUrl=" + resp.base + "article/" + articleId;
                    });
                } else {
                    layer.msg(resp.message);
                }
            }, error: function () {
                layer.msg("请稍后再试！");
            }
        })
        layer.close(index);
    });
}

function imgView() {
    $("#main-body").on("click", ".article-detail .article-content img", function () {
        var src = $(this).attr("src");
        var url;
        if (src.startsWith("http")) {
            url = src;
        } else {
            url = location.protocol + "//" + location.host + src;
        }
        window.open(url, "_blank");
    })
}