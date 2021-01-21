<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	sy.model.base.SessionInfo sessionInfo = (sy.model.base.SessionInfo) session.getAttribute(sy.util.base.ConfigUtil.getSessionInfoName());
	java.util.Set<sy.model.base.Syrole> roles = sessionInfo.getUser().getSyroles();//用户的角色
	java.util.Set<sy.model.base.Syorganization> organizations = sessionInfo.getUser().getSyorganizations();//用户的机构
	java.util.List<sy.model.base.Syresource> resources = new java.util.ArrayList<sy.model.base.Syresource>();//用户可访问的资源
	for (sy.model.base.Syrole role : roles) {
		resources.addAll(role.getSyresources());
	}
	for (sy.model.base.Syorganization organization : organizations) {
		resources.addAll(organization.getSyresources());
	}
	resources = new java.util.ArrayList<sy.model.base.Syresource>(new java.util.HashSet<sy.model.base.Syresource>(resources));//去重
	java.util.List<sy.model.easyui.Tree> resourceTree = new java.util.ArrayList<sy.model.easyui.Tree>();
	for (sy.model.base.Syresource resource : resources) {
		sy.model.easyui.Tree node = new sy.model.easyui.Tree();
		sy.util.base.BeanUtils.copyNotNullProperties(resource, node);
		node.setText(resource.getName());
		if (resource.getSyresource() != null) {
			node.setPid(resource.getSyresource().getId());
		}
		resourceTree.add(node);
	}
	String resourceTreeJson = com.alibaba.fastjson.JSON.toJSONString(resourceTree);
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../inc.jsp"></jsp:include>
<%
	out.println("<script>var resourceTreeJson = '" + resourceTreeJson + "';</script>");
%>
<script type="text/javascript">
	$(function() {
		$('#resources').tree({
			parentField : 'pid',
			data : eval("(" + resourceTreeJson + ")")
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table style="width: 100%;">
			<tr>
				<td><fieldset>
						<legend>用户信息</legend>
						<table class="table" style="width: 100%;">
							<tr>
								<th>用户ID</th>
								<td><%=sessionInfo.getUser().getId()%></td>
								<th>使用IP</th>
								<td><%=sessionInfo.getUser().getIp()%></td>
							</tr>
							<tr>
								<th>登录名</th>
								<td><%=sessionInfo.getUser().getLoginname()%></td>
								<th>姓名</th>
								<td><%=sessionInfo.getUser().getName()%></td>
							</tr>
							<tr>
								<th>性别</th>
								<td>
									<%
										if (sessionInfo.getUser().getSex() != null && sessionInfo.getUser().getSex().equals("1")) {
											out.print("男");
										} else {
											out.print("女");
										}
									%>
								</td>
								<th>年龄</th>
								<td><%=sessionInfo.getUser().getAge()%></td>
							</tr>
							<tr>
								<th>创建时间</th>
								<td><%=sy.util.base.DateUtil.dateToString(sessionInfo.getUser().getCreatedatetime())%></td>
								<th>最后修改时间</th>
								<td><%=sy.util.base.DateUtil.dateToString(sessionInfo.getUser().getUpdatedatetime())%></td>
							</tr>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td>
					<fieldset>
						<legend>权限信息</legend>
						<table class="table" style="width: 100%;">
							<thead>
								<tr>
									<th>角色</th>
									<th>机构</th>
									<th>权限</th>
								</tr>
							</thead>
							<tr>
								<td valign="top">
									<%
										out.println("<ul>");
										for (sy.model.base.Syrole role : roles) {
											out.println(sy.util.base.StringUtil.formateString("<li title='{1}'>{0}</li>", role.getName(), role.getDescription()));
										}
										out.println("</ul>");
									%>
								</td>
								<td valign="top">
									<%
										out.println("<ul>");
										for (sy.model.base.Syorganization organization : organizations) {
											out.println(sy.util.base.StringUtil.formateString("<li>{0}</li>", organization.getName()));
										}
										out.println("</ul>");
									%>
								</td>
								<td valign="top"><ul id="resources"></ul></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>