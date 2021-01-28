<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${ctx}/index">SSM基础技术架构</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <!-- <ul class="nav navbar-nav navbar-left">
        <li class="active"><a href="#"><i class="fa fa-user"></i>系统管理</a></li>
        <li><a href="#">系统管理</a></li>
        <li><a href="#">系统管理</a></li>
        <li><a href="#">系统管理</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">系统管理<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">用户管理</a></li>
            <li><a href="#">角色管理</a></li>
            <li><a href="#">资源管理</a></li>
          </ul>
        </li>
      </ul> -->
      <ul class="nav navbar-nav navbar-right">
          <li><a href="#"><i class="fa fa-user"></i> 欢迎你，<shiro:principal property="name"/></a></li>
          <li><a href="#"><i class="fa fa-lock"></i> 修改密码</a></li>
 		  <li><a href="#"><i class="fa fa-info-circle"></i> 个人资料</a></li>
 		  <li><a href="${ctx}/logout"><i class="fa fa-power-off"></i> 退出</a></li>
      </ul>
    </div>
  </div>
</nav>