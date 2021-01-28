<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${root}/static/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    
    <div id="loadingData" style="display:none; opacity: 0.3;filter: alpha(opacity=30);position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #eee;z-index: 120;overflow: hidden;">
        <img src="${root}/static/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    
    <script type="text/JavaScript">
    /**
     * 当页面加载完毕关闭加载进度
     */   
    $(window).load(function(){
		$("#loading").hide();
    });


 	//$.parser.addParseComplete(function(){
    //        		
	//});
    
    </script>
