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
        	<!-- modal begin -->
	        <%@ include file="/WEB-INF/views/common/modal.jsp"%>
	        <!-- modal end -->
        	<!-- 提示消息 -->
        	<%@ include file="/WEB-INF/views/common/message.jsp"%>
        	<!-- 路径导航 -->
        	<div class="breadcrumbs">
			 <ol class="breadcrumb">
				<li><a href="#"><i class="glyphicon glyphicon-hand-right"></i> 系统管理</a></li>
				<li><a href="${ctx}/sys/dict/list">数据字典</a></li>
				<li class="active">字典列表</li>
			 </ol>
			</div>
			<!-- 工具栏 -->
			<div class="toolbar">
				<a href="${ctx}/sys/dict/save" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-plus"></i> 新增</a>
				<a href="#" onclick="update('${ctx}/sys/dict/update');" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-pencil"></i> 编辑</a>
				<a href="#" onclick="deleteAll('确认要删除选中的吗？','${ctx}/sys/dict/delete');" class="btn btn-primary" role="button"><i class="glyphicon glyphicon-trash"></i> 删除</a>
			</div>
			
			<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/list" method="post" class="form-inline" role="form">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<!-- 搜索栏 -->
			 <div class="searchbar">
			 	<div class="form-group">
				    <label>类型</label>
				    <form:select path="type" class="form-control select2" placeholder="请选择类型">
			    		<form:option value="">请选择类型</form:option>
			    		<c:forEach items="${types}" var="d">
			    			<form:option value="${d.type}">${d.type}</form:option>
			    		</c:forEach>
			    	</form:select>
			    </div>
			    <div class="form-group">
				    <label>键名</label>
				    <form:input path="key" maxlength="50" class="form-control" placeholder="键名"/>
			    </div>
			    <div class="form-group">
				    <label>键值</label>
				    <form:input path="value" maxlength="50" class="form-control" placeholder="键值"/>
			    </div>
			  	<button type="submit" class="btn btn-primary"><i class="glyphicon glyphicon-search"></i> 搜索</button>
			 </div>
			</form:form>
			<div class="table-responsive">
			  <table class="table table-bordered table-hover">
			    <thead>
			      <tr class="blue">
			      	<th width="2%"><input type="checkbox" id="chkAll" name="chkAll" onclick="checkAll(this,'checkbox');" /></th>
			        <th>编号</th>
			        <th>类型</th>
			        <th>键名</th>
			        <th>键值</th>
			        <th>权重</th>
			        <!-- <th>创建人</th>
			        <th>创建时间</th>
			        <th>更新人</th>
			        <th>更新时间</th> -->
			        <th>操作</th>
			      </tr>
			    </thead>
			    <tbody>
			    <c:forEach items="${page.list}" var="dict" varStatus="status">
			   	<tr>
			   	 <td><input type="checkbox" id="checkbox" name="checkbox" value="${dict.id}"></td>
			   	 <td>${status.index+1}</td>
			     <td>${dict.type}</td>
			     <td><a href="${ctx}/sys/dict/update/${dict.id}">${dict.key}</a></td>
			     <td>${dict.value}</td>
			     <td>${dict.weight}</td>
			     <%-- <td>${dict.createUser.username}</td>
			     <td>
			     	<spring:eval expression="dict.createTime"></spring:eval>
			     </td>
			     <td>${dict.updateUser.username}</td>
			     <td>
			     	<spring:eval expression="dict.updateTime"></spring:eval>
			     </td> --%>
			     <td>
			     	<a href="${ctx}/sys/dict/update/${dict.id}" title="编辑"><span class="glyphicon glyphicon-pencil"></span></a>
			     	<a href="${ctx}/sys/dict/delete/${dict.id}" title="删除"><span class="glyphicon glyphicon-trash"></span></a>
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