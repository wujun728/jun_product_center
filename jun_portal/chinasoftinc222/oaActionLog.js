//var saveLogIp="saveActionLog"/*tpa=http://ics.chinasoftinc.com/oaActionLog/saveActionLog*/;
var saveLogIp="";
//获取协议
var protocolStr = document.location.protocol;

	if(protocolStr == "http:")
	{
		saveLogIp="saveActionLog"/*tpa=http://ics.chinasoftinc.com/oaActionLog/saveActionLog*/;
	}else{
		saveLogIp="https://ics.chinasoftinc.com/oaActionLog/saveActionLog";
			}





	  /**
	  * 判断是初次加载还是刷新加载
       */
        $(document).ready(function () {
           
			if (getCookie('actionSum') == '' || typeof(getCookie('actionSum')) == 'undefined'){
			getAjaxJsonp(saveLogIp+"?behavior=登录")
			setCookie('actionSum',1)
			}
				
		});



	//获取Cookie
       function getCookie(c_name){
            if(document.cookie.length > 0){
              var allCookie = document.cookie.split("; ")
              for(var i = 0;i < allCookie.length;i++){
                var temp = allCookie[i].split("=")
                if(temp[0] === c_name){
                  return unescape(temp[1])
                }
              }
            }
          }
	//存储COOKIE
		function setCookie(key,value){
			var exp =new Date();
			exp.setTime(exp.getTime()+10*60*1000);//设置过期时间 10分钟
			if(key){				
				document.cookie=key+ '=' +escape(value)+';expires='+exp.toGMTString;
			}
		}
	/**获取浏览器版本*/
	function getExplorerInfo() {
	 var explorer = window.navigator.userAgent.toLowerCase();
	 //ie
	 if (explorer.indexOf("msie") >= 0) {
		return "IE";
	 }
	 //firefox
	 else if (explorer.indexOf("firefox") >= 0) {
		
		return "Firefox";
	 }
	 //Chrome
	 else if(explorer.indexOf("chrome") >= 0){
		
		 return "Chrome";
	 }
	 //Opera
	 else if(explorer.indexOf("opera") >= 0){
	 return "Opera";
	 }
	 //Safari
	 else if(explorer.indexOf("Safari") >= 0){
	 
	 return "Safari"
	}
	//其他浏览器返回未知
	else{
		
	return "UNKnown"
		};
	 }
//子系统A标签的点击事件
	$(function(){
		
	 /**$('.css12').each(function(i,n){
			$(this).click(function(){
			var behavior = $(this).text();//子系统名称
			if(behavior=="退出"){
				setCookie('actionSum','')	
			}
			getAjaxJsonp(saveLogIp+"?behavior="+behavior)
				
			})
		})	
		*/
		
	$('.css2 a ').each(function(i,n){
			$(this).click(function(){
			var behavior = $(this).text();//子系统名称
			getAjaxJsonp(saveLogIp+"?behavior="+behavior)
				
			})
		})
	})
	
	//调取后台持久化日志接口
	function getAjaxJsonp(saveLogIp_param){
	
		var source="门户";
		var RoneUserName = getCookie("RoneUserName"); //获取cookie指定字段参数//工号
		var explorer=getExplorerInfo();//浏览器类型
		var nameTemp= $('.hello').text().trim();
		var temp= $("#nowpw").text();
		var userName=(nameTemp.split(temp)[0].split('您好')[1]).trim();
		var param=saveLogIp_param+"&RoneUserName="+RoneUserName+"&explorer="+explorer+"&source="+source+"&userName="+userName;
		
		$.ajax({
             type: "get",
             async: true,
             url: param,
             dataType: "jsonp",
             jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
             jsonpCallback:"flightHandler",//自定义的jsonp回调函数名称，默认为jQuery自动生成的随机函数名
             success: function(json){
               return true;
             },
             error: function(){
                return true;
             }
         });		
	}
	
	