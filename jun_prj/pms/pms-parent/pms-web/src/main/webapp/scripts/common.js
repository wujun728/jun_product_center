var common = {
	getFormData:function(formId){
		var tagName = $("#"+formId).get(0).tagName;
		if(tagName!="FORM"){
			console.error(formId+"不是formID");
		}else{
			var data  = {};
			var array = $("#"+formId).serializeArray();
			$.each(array, function() {
				data[this.name] = this.value;
			});
			return data;
		}
	},
    date: function (dt) {
        return {
            format: function (fmt) {
            	if(dt=='undefined'||dt==null){
            		 return "";
            	}else{
            		 return new Date(dt).format(fmt);
            	}
            }
        }
    },
    request: {
        url: function () {
            return window.location
        },
        getParam: function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null) {
                return unescape( r[2]);
            }
            else{
                return null;
            }
        }
    },
    layer: {
        //tips框，obj:jquery元素,msg:消息内容，operation:1上，2右(默认)，3下，4左，time:显示时间默认5000毫秒,0为不关闭，tipsMore 是否能显示多个
        tips: function (obj, msg, operation, time, tipsMore) {
            return parent.layer.tips(msg, obj, {
                tips: [operation, '#FF4040'],
                time: time == null ? 5000 : Number(time),
                tipsMore: true
            });
        },
        msg: function (msg, time) {

            if (time == null || isNaN(time) && Number(time) < 0) {
                time = 2000;
            } else {
                time = Number(time);
            }
            return parent.layer.msg(msg, { time: time });
        },
        loading:function(){
        	return parent.layer.msg('加载中...', {icon: 16,shade: 0.08,time:9999999999});
        },
        confirm: function (msg, callback, cancelcallback) {
            return parent.layer.confirm(msg,
                {
                    icon: 3,
                    shadeClose: true,
                    skin: 'layui-layer-rim',
                    shade: [0.5, '#000', true],
                    border: [6, 0.3, '#000000', true],
                    btn: ['确定','取消'],
                    scrollbar: false
                }, function (index) {
                    if (callback != null && typeof (callback) === "function") {
                        callback();
                    }
                    parent.layer.close(index);
                }, function (index) {
                    if (cancelcallback != null && typeof (cancelcallback) === "function") {
                        cancelcallback();
                    }
                    
                });
        },
        alert: function (msg, callback) {
            return parent.layer.alert(msg,
                 {
                     icon: 0,
                     skin: 'layui-layer-rim',
                     shade: [0.5, '#000', true],
                     border: [6, 0.3, '#000000', true],
                     scrollbar: false
                 }, function (index) {
                     if (callback != null && typeof (callback) === "function") {
                         callback();
                     }
                     parent.layer.close(index);
                 });
        },
        success: function (msg, callback,btnText) {
        	var btnText = btnText;
        	if(btnText == "undefined" || btnText == "" || btnText == undefined){
        		btnText = "确定";
        	}
            return parent.layer.alert(msg,
                    {
                        icon: 1,
                        skin: 'layui-layer-rim',
                        shade: [0.5, '#000', true],
                        border: [6, 0.3, '#000000', true],
                        scrollbar: false,
                        btn: [btnText]
                    }, function (index) {
                        if (callback != null && typeof (callback) === "function") {
                            callback();
                        }
                        parent.layer.close(index);
                    });
        },
        fail: function (msg, callback) {
            return parent.layer.alert(msg,
                  {
                      icon: 2,
                      skin: 'layui-layer-rim',
                      shade: [0.5, '#000', true],
                      border: [6, 0.3, '#000000', true],
                      scrollbar: false
                  }, function (index) {
                      if (callback != null && typeof (callback) === "function") {
                          callback();
                      }
                      parent.layer.close(index);
                  });
        },
        open: function (url, title, width, height, cancelcallback) {
            if (width == null || width == "" || isNaN(width) || Number(width) <= 0) {
                width = 480;
            }
            if (height == null || height == "" || isNaN(height) || Number(height) <= 0) {
                height = 280;
            }


            return parent.layer.open({
                type: 2,
                closeBtn: 1,
                title: title == null || title == "" ? false : title,
                skin: 'layui-layer-rim',
                shade: [0.5, '#000', true],
                border: [6, 0.3, '#000000', true],
                area: [width + "px", height + "px"],
                //offset: (($(window).height() < height ? height + 40 : $(window).height()) - height) / 2 + 'px',
                //maxmin: true,
                content: url,
                cancel: function (index) {
                    if (cancelcallback != null && typeof (cancelcallback) === "function") {
                        cancelcallback();
                    }
                }
            });
        },
        show: function (content, title, width, height, cancelcallback) {
            if (width == null || width == "" || isNaN(width) || Number(width) <= 0) {
                width = 480;
            }
            if (height == null || height == "" || isNaN(height) || Number(height) <= 0) {
                height = 270;
            }
            //页面层
            return layer.open({
                type: 1,
                closeBtn: 1,
                title: title == null || title == "" ? false : title,
                skin: 'layui-layer-rim', //加上边框
                area: [width, height],//宽高
                border: [6, 0.3, '#000000', true],
                //offset: (($(window).height() < height ? height + 40 : $(window).height()) - height) / 2 + 'px',
                content: content,
                cancel: function (index) {
                    if (cancelcallback != null && typeof (cancelcallback) === "function") {
                        cancelcallback();
                    }
                }
            });
        }
    }
}

