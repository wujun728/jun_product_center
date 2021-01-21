var layer = layui.use('layer', function () {
    return layui.layer;
});

layui.config({
    version: "3.0.0"
    , base: 'res/mods/'
}).extend({
    fly: 'index'
}).use(['fly', 'face'], function () {
    var $ = layui.$
        , fly = layui.fly;
    //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
    $('.detail-body').each(function () {
        var othis = $(this), html = othis.html();
        othis.html(fly.content(html));
    });
});


function logout() {
    $.post('api/user/logout', function (data) {
        window.location.href = "/";
    });
}

function commonMsg(content, time, callback) {
    var config = {
        time: time || 3000
    };
    if (typeof callback == 'function') {
        layer.msg(content, config, callback());
    } else {
        layer.msg(content, config);
    }
}

//获取QueryString的数组
function getQueryString() {
    var result = location.search.match(new RegExp("[\?\&][^\?\&]+=[^\?\&]+", "g"));
    if (result == null) {
        return "";
    }
    for (var i = 0; i < result.length; i++) {
        result[i] = result[i].substring(1);
    }
    return result;
}

//根据QueryString参数名称获取值
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return "";
    }
    return result[1];
}

//根据QueryString参数索引获取值
function getQueryStringByIndex(index) {
    if (index == null) {
        return "";
    }
    var queryStringList = getQueryString();
    if (index >= queryStringList.length) {
        return "";
    }
    var result = queryStringList[index];
    var startIndex = result.indexOf("=") + 1;
    result = result.substring(startIndex);
    return result;
}

function isEmptyStr(str) {
    if (str == '' || str == null || typeof str == 'undefined') {
        return true;
    } else {
        return false;
    }
}