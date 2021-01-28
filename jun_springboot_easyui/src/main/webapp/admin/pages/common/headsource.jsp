<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:set var="root" value="${pageContext.request.contextPath}" scope="application"></c:set>
	<c:set var="easyuiVersion" value="jquery-easyui-1.5.3" scope="application"></c:set>
	<c:set var="easyuiThemeName" value="material" scope="application"></c:set>

	<c:set var="nowDate" scope="request" value="<%=new java.util.Date()%>"></c:set>
		
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${root}/static/easyui/${easyuiVersion}/themes/${easyuiThemeName }/easyui.css"/>  
    <link rel="stylesheet" type="text/css" href="${root}/static/easyui/${easyuiVersion}/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${root}/static/css/form.css"/>
    
    
    <script type="text/javascript" src="${root}/static/easyui/${easyuiVersion}/jquery.min.js"></script>  
    <script type="text/javascript" src="${root}/static/easyui/${easyuiVersion}/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${root}/static/easyui/${easyuiVersion}/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="${root}/static/js/calutil.js"></script>
	<script type="text/javascript" src="${root}/static/js/klg.util.js"></script>
	
	<script type="text/javascript" src="${root}/static/easyui/easyui-extend/dataTable.js"></script>
	<script type="text/javascript" src="${root}/static/easyui/easyui-extend/common.js"></script>
	<script type="text/javascript" src="${root}/static/easyui/easyui-extend/jeasyuiex.js"></script>
	<script type="text/javascript" src="${root}/res/js/dictData.js?t=${nowDate.time}"></script>
	
	
	<script type="text/javascript"> 
	
		var basePath = "${root}";
		var loginPath = "${root}/login";
		var adminActionPath=basePath+"/admin/action"
		//字典默认配置
		$.extend($.fn.combobox.defaults,{valueField:'code',textField:'name',panelHeight:'auto',editable:false});
		
	</script>