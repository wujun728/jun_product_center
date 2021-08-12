<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"
	type="text/javascript"></script>

<script>
	function getNum() {
		var num = document.getElementById("no").value;
		var reg = /^[0-9]*$/;

		if (reg.test(num)) {
			window.open("../jump?num=" + num, "_self");
		} else {
			alert("请输入正确的页数！")
		}

	}
</script>

</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

	<div class="content-pages-wrap" style="opacity:0.7">
		<div class="commonTitle">
			<h2>&gt;&gt; 配件管理</h2>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="commonTableSearch">
			<form id="form-search" name="form-search" action="" method="post">
				<tr>
					<th align="right">配件编码：</th>
					<td><input name="partsid" type="text" class="inputTextNormal"
						id="textfield2" /></td>
					<th align="right">配件名称：</th>
					<td><input name=partsname type="text" class="inputTextNormal"
						id="textfield2" /></td>
					<th align="right"><input type="submit" class="btnShort"
						value="检索" /></th>
				</tr>
			</form>
		</table>


		<!--//commonTableSearch-->

		<input type="button" class="btnNormal" value="配件出入库"
			onclick="location.href='${pageContext.request.contextPath }/pages/partssys/partsrep/partsrep'" />

		<form action="getSelected" method="post" id="form1" name="form1">

			<table width="101%" border="0" cellpadding="0" cellspacing="1"
				class="commonTable">
				<tr>
					<th>是否选择</th>
					<th>序号</th>
					<th>配件编码</th>
					<th>配件名称</th>
					<th>库存数量</th>
				</tr>

				<!-- <tr>
            <td align="center">1</td>
            <td align="center">100</td>
            <td align="center">滤清器</td>
			<td align="center">30</td>
        </tr> -->

				<c:forEach items="${ilist}" var="l" varStatus="s">
					<tr>
						<td align="center"><input type="checkbox" name="c"
							value="${l.partsid}"></td>
						<td align="center">${s.count}</td>
						<td align="center">${l.partsid}</td>
						<td align="center">${l.partsname}</td>
						<td align="center">${l.partsreqcount}</td>
					</tr>

				</c:forEach>

			</table>

		</form>

		<div id="pagelist">
			<ul class="clearfix">
				<li><a href="#" onclick="document:form1.submit()">确定</a></li>
			</ul>
		</div>

		<!--//commonTable-->
		<%-- <div id="pagelist">
			<ul class="clearfix">
				<li><a href="${pageInfo.firstPage}">首页</a></li>
				<li><a href="${pageInfo.prePage}">上页</a></li>
				<li><a href="${pageInfo.nextPage}">下页</a></li>
				<li class="current"><input type="text"
					style="text-align: right" size="1" id="no"
					value="${pageInfo.pageNum}"></li>
				<li><a href="javascript:void(0)" onclick="getNum()">跳转</a></li>
				<li><a href="${pageInfo.lastPage}">尾页</a></li>
				<li class="pageinfo">第${pageInfo.pageNum}页</li>
				<li class="pageinfo">共${pageInfo.pages}页</li>
			</ul>
		</div> --%>
	</div>
	<!--//content pages wrap-->
</body>
</html>