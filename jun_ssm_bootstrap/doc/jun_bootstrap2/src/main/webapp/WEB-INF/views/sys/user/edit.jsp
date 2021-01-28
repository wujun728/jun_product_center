<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
<script type="text/javascript">
$(function(){

	$(".multiple").select2({
		theme: "bootstrap",
		placeholder: "请选择",
		language: "zh-CN"
	});
	
	
	$("#nickname").focus();
	
	/* 
	$("#editForm").validate({
		rules: {
			nickname: {
				required: true,
				maxlength:20
			},
			username: {
				required: true,
				maxlength:20
			},
			password: {
				required: true
			},
			confirmPassword: {
				required: true,
				equalTo:"#password"
			},
			sex: {
				required: true
			},
			email:"email",
			roleIds: {
				required: true
			}
		},
		errorElement:"span",
		errorPlacement: function(error, element) {
			if (element.is(":radio")){
				error.appendTo(element.parent().next().next());
			}else if(element.is(":checkbox")){
				error.appendTo(element.parent().next());
			}else if(element.is("select")){
				error.insertAfter(element.next());
			}else{
				error.insertAfter(element);
			} 
			//error.appendTo(element.parent("div").next());
		},
		submitHandler: function(form){
			loading();
			form.submit();
		}
		
	}); */
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
        	<!-- 路径导航 -->
        	<div class="breadcrumbs">
			 <ol class="breadcrumb">
				<li><a href="#"><i class="glyphicon glyphicon-hand-right"></i> 系统管理</a></li>
				<li><a href="${ctx}/sys/user/list">用户管理</a></li>
				<li class="active">编辑用户</li>
			 </ol>
			</div>
			<h3 class="page-header">${empty user.id? '新增用户':'更新用户'}</h3>
			<form:form id="editForm" modelAttribute="user" action="${ctx}/sys/user/${not empty user.id? 'update':'save'}" method="post" class="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">昵称</label>
			    <div class="col-sm-3">
			    	<form:input path="nickname" class="form-control" placeholder="昵称" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-3">
			    	<c:choose>
			    		<c:when test="${user.id eq 1}">
			    			<form:hidden path="username"/>
			    			<p class="form-control-static">${user.username}</p>
			    		</c:when>
			    		<c:otherwise>
			    			<form:input path="username" class="form-control" placeholder="用户名" />
			    		</c:otherwise>
			    	</c:choose>
			    </div>
			  </div>
			  <c:if test="${empty user.id}">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-3">
			    	<input type="password" id="password" name="password" value="${user.password}" class="form-control" placeholder="密码" /> 
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-3">
			    	<input type="password" id="confirmPassword" name="confirmPassword" value="${user.confirmPassword}" class="form-control" placeholder="确认密码" /> 
			    </div>
			  </div>
			  </c:if>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">性别</label>
			    <div class="col-sm-3">
			    	<div class="radio">
			    		<label><form:radiobutton  path="sex" value="0"/>男</label>
			    		<label><form:radiobutton path="sex" value="1"/>女</label>
			    		<label><form:radiobutton path="sex" value="2"/>保密</label>
			    	</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">邮箱</label>
			    <div class="col-sm-3">
			    	<form:input path="email" class="form-control" placeholder="邮箱" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">手机号码</label>
			    <div class="col-sm-3">
			    	<form:input path="mobile" class="form-control" placeholder="手机号码" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">角色</label>
			    <div class="col-sm-3">
			    	<%-- <div class="checkbox-inline">
				    	<form:checkboxes path="roleIds" items="${allRoles}" itemLabel="name" itemValue="id"/>
			    	</div> --%>
			    	<form:select id="roleIds" path="roleIds" class="form-control select2 multiple" multiple="multiple">
			    		<c:forEach items="${allRoles}" var="r">
			    			<form:option value="${r.id}">${r.name}</form:option>
			    		</c:forEach>
			    	</form:select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">备注</label>
			    <div class="col-sm-3">
			    	<form:textarea path="remark"  class="form-control" cols="10" rows="4" placeholder="备注" />
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-3">
			    	<input type="button" value="取消" class="btn btn-default" onclick="history.go(-1)"/>
			    	<input type="submit" value="保存" class="btn btn-primary" />
			    </div>
			  </div>
			</form:form>
        </div><!-- main -->
      </div>
    </div>
</body> 
</html>