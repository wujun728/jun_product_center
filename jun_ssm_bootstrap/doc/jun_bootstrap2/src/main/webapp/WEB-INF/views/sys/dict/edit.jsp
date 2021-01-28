<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ include file="/WEB-INF/views/common/include.jsp"%>
<script type="text/javascript">
$(function(){
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
				<li><a href="${ctx}/sys/dict/list">数据字典</a></li>
				<li class="active">编辑字典</li>
			 </ol>
			</div> 
			<h3 class="page-header">${empty dict.id? '新增字典':'更新字典'}</h3>
			<form:form id="editForm" modelAttribute="dict" action="${ctx}/sys/dict/${not empty dict.id? 'update':'save'}" method="post" class="form-horizontal">
			  <form:hidden path="id"/>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">类型</label>
			    <div class="col-sm-3">
			    	<form:input path="type" class="form-control" placeholder="类型" />
			    	<form:errors path="type" cssStyle="color:red" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">键名</label>
			    <div class="col-sm-3">
			    	<form:input path="key" class="form-control" placeholder="键名" />
			    	<form:errors path="key" cssStyle="color:red" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">键值</label>
			    <div class="col-sm-3">
			    	<form:input path="value" class="form-control" placeholder="键值" />
			    	<form:errors path="value" cssStyle="color:red" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">权重</label>
			    <div class="col-sm-3">
			    	<form:input path="weight" class="form-control" placeholder="权重" />
			    	<form:errors path="weight" cssStyle="color:red" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">备注</label>
			    <div class="col-sm-3">
			    	<form:textarea path="remark"  class="form-control" cols="20" rows="10" placeholder="备注" />
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