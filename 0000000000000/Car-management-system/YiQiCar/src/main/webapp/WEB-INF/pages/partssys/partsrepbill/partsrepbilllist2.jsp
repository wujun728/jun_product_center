<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js" type="text/javascript"></script>



<script>
	function getNum(){
		var num = document.getElementById("no").value;
		var reg = /^[0-9]*$/;
		
		if(reg.test(num)){
			window.open("../jump?num="+num,"_self");
		}else{
			alert("请输入正确的页数！")
		}	
		
	}
	
	$(function(){
		$("#select").blur(function(){
			$("#select1").empty();
			var inout = document.getElementById("select").value;
			$.getJSON("getJsons?inout="+inout,{},function(data){
				for(var i = 0; i < data.length; i++){
					$("#select1").append($("<option></option>").text(data[i].name).val(data[i].code))
				}
			}); 
		})	
	})
	
	
</script>

</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div class="content-pages-wrap" style="opacity:0.7">
	<div class="commonTitle">
	  <h2>&gt;&gt; 配件库存流水账查询</h2>
	</div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch" style="font-size:13px;">
       	<form id="form-search" name="form-search" action="" method="post">
        <tr>
            <th align="right">配件名称：</th>
            <td><input type="text" name="partsname" class="inputTextNormal" id="textfield2" style="width:93px"/></td>
            <th align="right">出/入库：</th>
            <td>
            	<select style="width:93px;" name="billflag" id="select">
						<option value="">请选择</option>
						<option value="out">出库</option>
						<option value="in">入库</option>
				</select>
			</td>
            <th align="right">出入库类型：</th>
            <td>
            	<select style="width:93px;" id="select1" name="billtype">
						<option value="">请选择</option>
				</select>
            </td>
            <th align="right">
				<input type="submit" class="btnShort" value="检索" />
			</th>
        </tr>
       	</form>
    </table>
	<br>

    <!--//commonTableSearch-->

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>出入库类别</th>
            <th>配件名称</th>
            <th>数量</th>
            <th>时间</th>
            <th>操作人</th>
        </tr>
       
       <c:forEach items="${info}" var="s" varStatus="l" >
       		<tr>
            <td align="center">${l.count}</td>
            <td align="center">${s.billtype}</td>
			<td align="center">${s.partsname}</td>
			<td align="center">${s.billcount}</td>
			<td align="center">${s.billtime}</td>
			<td align="center">${s.empname}</td>
        </tr>
       </c:forEach>
       
        
        <!-- <tr>
            <td align="center">1</td>
            <td align="center">出库</td>
            <td align="center">订单出库</td>
			<td align="center">滤清器</td>
			<td align="center">30</td>
			<td align="center">2017-10-20</td>
			<td align="center">张飞</td>
        </tr> -->
        
        
  </table>
    <!--//commonTable-->
    <%-- <div id="pagelist">
    	<ul class="clearfix">
				<li><a href="${info.firstPage}">首页</a></li>
				<li><a href="${info.prePage}">上页</a></li>
				<li><a href="${info.nextPage}">下页</a></li>
				<li class="current"><input type="text"
					style="text-align: right" size="1" id="no"
					value="${info.pageNum}"></li>
				<li><a href="javascript:void(0)" onclick="getNum()">跳转</a></li>
				<li><a href="${info.lastPage}">尾页</a></li>
				<li class="pageinfo">第${info.pageNum}页</li>
				<li class="pageinfo">共${info.pages}页</li>
			</ul>
    </div> --%>
</div>
<!--//content pages wrap-->
</body>
</html>