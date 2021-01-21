<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	
%>
<% 
  
	
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
 <script type="text/javascript">
 	var gotoOld = function(){
 		window.history.go(-1);
 	};
 </script>
</head>
<body style="height: 300px">
	<form method="post" class="form">
		<div class="easyui-tabs" style="width: 600px; height: 500px">
			<div title="员工信息格式如下；竖排" style="padding: 10px">
				<fieldset>
					<table class="table" style="width: 100%;border: 1px">
						<tr><th style="width: 250px">SGI No.</th><td> </td></tr>
						<tr><th style="width: 250px">姓名Name</th><td></td></tr>
						<tr><th style="width: 250px">客户</th><td></td></tr>
						<tr><th style="width: 250px">部门Department /SOA [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">岗位名称（中文）Position (Chinese) [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">岗位名称（英语）Position (English) [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">在职离职状况 In & Out [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">岗位类别(正式工／临时工) Post Category (Permenant/Temp) [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">成本中心Cost Center [YTD]</th><td></td></tr>
						<tr><th style="width: 250px">性别Gendar</th><td></td></tr>
						<tr><th style="width: 250px">身份证号码 ID Card No.</th><td></td></tr>
						<tr><th style="width: 250px">出生年月Birth Date</th><td></td></tr>
						<tr><th style="width: 250px">年龄Age</th><td></td></tr>
						<tr><th style="width: 250px">入司日期Join Company Date</th><td></td></tr>
						<tr><th style="width: 250px">离职日期Out Date</th><td></td></tr>
					</table>
					
					<div style="text-align: center; padding: 5px">
						<a href="javascript:void(0);" class="easyui-linkbutton"
							data-options="iconCls:'ext-icon-note_add',plain:true"
							onclick="gotoOld();">返回</a>
					</div>
			</div>
			
		</div>
	</form>
</body>
</html>