<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
<script type="text/javascript">
$(function(){
	$('[data-toggle="tooltip"]').tooltip();
	$("#moreSearchBtn").click(function(){
		//$("#searchDiv").show();
		$("#searchDiv").attr("style","display:block;");//显示div
	});
});
</script>
</head>
<body>
<!-- header begin -->
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<!-- header end -->
<!-- content begin -->
<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <!-- 菜单栏 -->
          <%@ include file="/WEB-INF/views/common/menu.jsp"%>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<div class="breadcrumbs">
			 <ol class="breadcrumb">
				<li><a href="#"><i class="glyphicon glyphicon-hand-right"></i> 系统管理</a></li>
				<li><a href="#">用户管理</a></li>
				<li class="active">用户列表</li>
			 </ol>
			</div>
			<!-- 提示消息 -->
        	<%@ include file="/WEB-INF/views/common/message.jsp"%>
        	<!-- 路径导航 -->
			<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/list" method="post" class="form-inline" role="form">
			 <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			 <!-- 工具栏 -->
			 <div class="toolbar">
       			<a href="${ctx}/sys/user/save" class="btn btn-primary" role="button"><i class="fa fa-plus"></i> 新增</a>
				<a href="#" onclick="update('${ctx}/sys/user/update');" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-pencil"></i> 编辑</a>
				<a href="#" onclick="deleteAll('确认要删除选中的吗？','${ctx}/sys/user/delete');" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-trash"></i> 删除</a>
        		<div class="pull-right">
        			<div class="input-group">
				      <form:input path="username" maxlength="50" size="30" class="form-control" placeholder="用户名" />
				      <span class="input-group-btn">
				        <button class="btn btn-primary" type="submit"><i class="fa fa-search"></i> 搜索</button>
				      </span>
				    </div><!-- /input-group -->
				    <button id="moreSearchBtn" type="button" class="btn btn-link">更多筛选条件 <i class="fa fa-caret-down"></i></button>
        		</div>
        		<div class="clearfix"></div>
        	</div>
        	<!-- 高级搜索 -->
        	<div id="searchDiv" class="searchbar" style="display:none;">
        		<div class="row">
        			<div class="col-md-3">
        				<div class="form-group">
						    <label>昵称</label>
				    		<form:input path="nickname" maxlength="50" class="form-control" placeholder="用户昵称" />
					    </div>
        			</div>
        			<div class="col-md-3">
        				<div class="form-group">
						    <label>性别</label>
						    <form:select path="sex" class="form-control select2" placeholder="请选择性别">
					    		<form:option value="">请选择性别</form:option>
					    		<form:option value="0">男</form:option>
					    		<form:option value="1">女</form:option>
					    		<form:option value="2">保密</form:option>
					    	</form:select>
					    </div>
        			</div>
        			<div class="col-md-3">
        				<div class="form-group">
						    <label>邮箱</label>
				    		<form:input path="email" maxlength="50" class="form-control" placeholder="邮箱" />
					    </div>
        			</div>
        			<div class="col-md-3">
        				<div class="form-group">
						    <label>手机号码</label>
				   			<form:input path="mobile" maxlength="50" class="form-control" placeholder="手机号码" />
					    </div>
        			</div>
        		</div>
        		<div class="row">
        			<div class="col-md-3">
        				<div class="form-group">
						    <label>状态</label>
				   			<form:select path="isLock" class="form-control select2" placeholder="请选择状态">
					    		<form:option value="">请选择状态</form:option>
					    		<form:option value="0">正常</form:option>
					    		<form:option value="1">锁定</form:option>
					    	</form:select>
					    </div>
        			</div>
        			<div class="col-md-6">
        				<div class="form-group">
						    <label>时间</label>
						    <form:input path="createStartTime" class="form-control" placeholder="开始时间" />
						    <label>-</label>
						    <form:input path="createEndTime" class="form-control" placeholder="结束时间" />
					    </div>
        			</div>
        		</div>
        		<div class="row">
        			<div class="col-md-2 form-inline">
        			<button type="submit" class="btn btn-danger"><i class="fa fa-search"></i> 搜索</button>
        			</div>
        		</div>
		    </div>
			</form:form>
			<div class="table-responsive">
			  <table class="table table-bordered table-striped table-hover">
			    <thead>
			      <tr>
			      	<th width="2%"><input type="checkbox" id="chkAll" name="chkAll" onclick="checkAll(this,'checkbox');" /></th>
			        <th>编号</th>
			        <th>昵称</th>
			        <th>用户名</th>
			        <th>性别</th>
			        <th>手机号码</th>
			        <th>邮箱</th>
			        <th>创建人</th>
			        <th>创建时间</th>
			        <th>修改人</th>
			        <th>修改时间</th>
			        <th>备注</th>
			        <th>操作</th>
			      </tr>
			    </thead>
			    <tbody>
			    <c:forEach items="${page.list}" var="user" varStatus="status">
			   	<tr>
			   	 <td><input type="checkbox" id="checkbox" name="checkbox" value="${user.id}"></td>
			   	 <td>${status.index+1}</td>
			   	 <td>${user.nickname}</td>
			     <td><a href="${ctx}/sys/user/update/${user.id}">${user.username}</a></td>
			     <td>
			     	<c:if test="${user.sex eq 0}">男</c:if>
			   	 	<c:if test="${user.sex eq 1}">女</c:if>
			   	 	<c:if test="${user.sex eq 2}">保密</c:if>
			     </td>
			     <td>${user.mobile}</td>
			     <td>${user.email}</td>
			     <td>${user.createUser.username}</td>
			     <td><spring:eval expression="user.createTime"></spring:eval></td>
			     <td>${user.updateUser.username}</td>
			     <td><spring:eval expression="user.updateTime"></spring:eval></td>
			     <td>${user.remark}</td>
			     <td>
		     		<a href="${ctx}/sys/user/update/${user.id}">
						<span class="glyphicon glyphicon-pencil"></span> 
					</a>
		     		<c:if test="${user.id != 1}">
		     			<a href="${ctx}/sys/user/delete/${user.id}" title="删除"> 
							<span class="glyphicon glyphicon-trash"></span>${currentUser.id}
						</a>
		     		</c:if>
			     </td>
			   	</tr>
				</c:forEach>
			    </tbody>
			  </table>
			</div>
			<div class="pagination">${page}</div>
        </div><!-- main -->
      </div>
    </div>
</body> 
</html>