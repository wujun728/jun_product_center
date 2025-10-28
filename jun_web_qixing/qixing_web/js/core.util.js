/*工具类*/
var CoreUtil = (function () {
    var coreUtil = {};
    var datas ;

    /*GET*/
    coreUtil.sendGet = function(url, params, ft){
        this.sendAJAX(url, params, ft, "GET")
    }
    /*GET*/
    coreUtil.sendGet2 = function(url, params, ft){
        this.sendAJAX2(url, params, ft, "GET")
    }
    
    /*POST*/
    coreUtil.sendPost = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "POST")
    }
    /*PUT*/
    coreUtil.sendPut = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "PUT")
    }
    /*DELETE*/
    coreUtil.sendDelete = function(url, params, ft){
        this.sendAJAX(url, JSON.stringify(params), ft, "DELETE")
    }


    /*ajax*/
    coreUtil.sendAJAX = function(url, params, ft, method){
        var loadIndex = top.layer.load(0, {shade: false});
        $.ajax({
            url: url,
            cache: false,
            async: true,
            data: params,
            type: method,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            beforeSend: function(request) {
                request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
            },
            success: function (res) {
                top.layer.close(loadIndex);
                if (res.code==0){
                    if(ft!=null&&ft!=undefined){
                        ft(res);
                    }
                }else if(res.code==401001){ //凭证过期重新登录
                    layer.msg("凭证过期请重新登录", {time:2000}, function () {
                        top.window.location.href="/login.html"
                    })
                }else if(res.code==401008){ //凭证过期重新登录
                    layer.msg("抱歉！您暂无权限", {time:2000})
                }else if(res.code==500001){ //凭证过期重新登录
                    layer.msg(res.msg+",请联系管理员!", {time:2000});
                    return;
                } else {
                    layer.msg(res.msg, {time:2000});
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                top.layer.close(loadIndex);
                if(XMLHttpRequest.status==404){
                    top.window.location.href="/404.html";
                }else{
                    layer.msg("服务器好像除了点问题！请稍后试试");
                }
            }
        })
    };


    /*ajax*/
    coreUtil.sendAJAX2 = function(url, params, ft, method){
        $.ajax({
            url: url,
            cache: false,
            async: false,
            data: params,
            type: method,
            contentType: 'application/json; charset=UTF-8',
            dataType: "json",
            beforeSend: function(request) {
                request.setRequestHeader("authorization", CoreUtil.getData("access_token"));
            },
            success: function (res) {
                if (res.code==0){
                    if(ft!=null&&ft!=undefined){
                        ft(res);
                    }
                }else if(res.code==401001){ //凭证过期重新登录
                    layer.msg("凭证过期请重新登录", {time:2000}, function () {
                        top.window.location.href="/login.html"
                    })
                }else if(res.code==401008){ //凭证过期重新登录
                    layer.msg("抱歉！您暂无权限", {time:2000})
                } else {
                    layer.msg(res.msg);
                }
            },
            error:function (XMLHttpRequest, textStatus, errorThrown) {
                if(XMLHttpRequest.status==404){
                    top.window.location.href="/404.html";
                }else{
                    layer.msg("服务器好像除了点问题！请稍后试试");
                }
            }
        })
    };
    

    /*存入本地缓存*/
    coreUtil.setData = function(key, value){
        layui.data('LocalData',{
            key :key,
            value: value
        })
    };
    /*从本地缓存拿数据*/
    coreUtil.getData = function(key){
        var localData = layui.data('LocalData');
        return localData[key];
    };

    //判断字符是否为空的方法
    coreUtil.isEmpty = function(obj){
        if(typeof obj == "undefined" || obj == null || obj == ""){
            return true;
        }else{
            return false;
        }
    }

    //字典数据回显
    coreUtil.selectDictLabel = function (datas, value) { 
        //datas = JSON.parse(datas);
        var label = "";
        if(datas.code != 500){
            $.each(datas, function(index, dict) {
                if(dict.value){
                    if (dict.value == ('' + value)) {
                        label = dict.label;
                        return false;
                    }
                }
                if(dict.code){
                    if (dict.code == ('' + value)) {
                        label = dict.name;
                        return false;
                    }
                }
            });
            //匹配不到，返回未知
            if (CoreUtil.isEmpty(label)) {
                return "未知";
            }
        }
        return label;
    };
     
    

    //字典数据回显
    coreUtil.selectDictLabelV2 = function (datas, value) {
		CoreUtil.sendGet("/sysDict/getType/content_type", null, function (res) {
	        	console.log(res);
	      		datas = res;
	    });
        var label = "";
        $.each(datas, function(index, dict) {
            if (dict.value == ('' + value)) {
                label = dict.label;
                return false;
            }
        });
        //匹配不到，返回未知
        if (CoreUtil.isEmpty(label)) {
            return "未知";
        }
        return label;
    }

    return coreUtil;
})(CoreUtil, window);

if (!String.prototype.includes) {
  String.prototype.includes = function(search, start) {
    'use strict';
    if (typeof start !== 'number') {
      start = 0;
    }

    if (start + search.length > this.length) {
      return false;
    } else {
      return this.indexOf(search, start) !== -1;
    }
  };
}
