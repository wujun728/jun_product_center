<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="孙宇 - sypro demo">
<meta name="robots" content="all">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="孙宇 [89333367@qq.com]">

<%-- 让IE8支持HTML5元素和媒体查询 --%>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/jsLib/html5shiv/html5shiv.min.js"></script>
<script src="${pageContext.request.contextPath}/jsLib/respond/respond.min.js"></script>
<![endif]-->

<%-- jquery插件 --%>
<script src="${pageContext.request.contextPath}/jsLib/jquery/jquery.min.js" charset="utf-8"></script>

<%-- 常用js方法工具 --%>
<script src="${pageContext.request.contextPath}/jsLib/sy-base.js" charset="utf-8"></script>

<%-- 让IE8支持placeholder属性的插件 --%>
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/jsLib/placeholder/jquery.placeholder.js" charset="utf-8"></script>
<script>$(function(){$('input, textarea').placeholder();});</script>
<![endif]-->

<%-- 缩略图占位插件，使用方法：<img data-src="holder.js/200x100"> --%>
<script src="${pageContext.request.contextPath}/jsLib/holder/holder.js" charset="utf-8"></script>

<%-- jquery表单插件 --%>
<script src="${pageContext.request.contextPath}/jsLib/jquery-form/jquery.form.js" charset="utf-8"></script>

<%-- jqGrid插件 --%>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jsLib/jquery-ui/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/jsLib/Guriddo_jqGrid/css/ui.jqgrid.css" />
<script src="${pageContext.request.contextPath}/jsLib/Guriddo_jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/jsLib/Guriddo_jqGrid/js/jquery.jqGrid.src.js" type="text/javascript"></script>

<%-- my97日期时间插件 --%>
<script src="${pageContext.request.contextPath}/jsLib/My97DatePicker/WdatePicker.js" charset="utf-8"></script>

<%-- zTree插件 --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jsLib/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/zTree/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jsLib/zTree/js/jquery.ztree.exhide-3.5.min.js"></script>
