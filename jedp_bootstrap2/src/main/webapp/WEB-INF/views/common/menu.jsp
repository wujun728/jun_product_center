<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 菜单栏 -->
<script type="text/javascript">
$(function(){
	
$('#menu').metisMenu();
	
	//点击一级菜单
	$("#menu li a").each(function(){
		var href = $($(this))[0].href;
		href = href.substr(0,href.lastIndexOf("/"));
		var url = String(window.location);
		url = url.substr(0,url.lastIndexOf("/"));
		// if($($(this))[0].href == String(window.location)){}
		if(href == url || href == url.substr(0,url.lastIndexOf("/"))){
			$(this).addClass('active');
		}
	});
	//点击二级菜单
	$("#menu li ul li a").each(function(){
		var href = $($(this))[0].href;
		href = href.substr(0,href.lastIndexOf("/"));
		var url = String(window.location);
		url = url.substr(0,url.lastIndexOf("/"));
		if(href == url || href == url.substr(0,url.lastIndexOf("/"))){
			$(this).addClass('active');
			$(this).parent().parent().addClass('collapse in');
			$(this).parent().parent().parent().addClass('active');
		}
	});
	
});
</script>
<!-- 菜单栏 -->
<nav class="nav nav-sidebar">
  <ul id="menu">
  	<c:forEach items="${menus}" var="menu">
  		 <li>
          <a <c:if test="${not empty menu.url}">href="${ctx}/${menu.url}"</c:if> ><i class="glyphicon glyphicon-folder-open"></i> ${menu.name}<c:if test="${fn:length(menu.childrens) > 0}"><i class="glyphicon arrow"></i></c:if></a>
           <c:if test="${fn:length(menu.childrens) > 0}">
           	<ul>
           		<c:forEach items="${menu.childrens}" var="children">
           			<li><a href="${ctx}/${children.url}"><i class="glyphicon glyphicon-file"></i> ${children.name}</a></li>
           		</c:forEach>
           	</ul>
           </c:if>
         </li>
  	</c:forEach>
  </ul>
</nav>