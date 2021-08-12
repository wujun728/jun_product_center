<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="gbk" />
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
			window.open("../jump1?num=" + num, "_self");
		} else {
			alert("请输入正确的页数！")
		}
	}
	
	function getDouble()
</script>
</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div class="content-pages-wrap" style="opacity:0.7">
	<div class="commonTitle">
	  <h2>&gt;&gt; 订单管理</h2>
	</div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="commonTableSearch">
       	<form id="form-search" name="form-search" action="../getChecks" method="post">
        <tr>
            <th align="right">订单编码：</th>
            <td><input name="ordercode" type="text" class="inputTextNormal" id="textfield1" /></td>
            <th align="right">订单保存时间：</th>
            <td><input name="orderdate" type="text" class="inputTextNormal" id="textfield2" /></td>
            <td align="right">订单状态：</td>
            <td>
            		<select id="orderstatus" name="orderflag">
						<option value="" selected>请选择</option>
						<option value="0">未提交</option>
						<option value="1">待审核</option>
						<option value="2">审核通过</option>
						<option value="3">审核不通过</option>
					</select></td>

            <th align="right">
				<input type="submit" class="btnShort" value="检索" />
			</th>
        </tr>
        <tr>

          </tr>
       	</form>
    </table>


 
	<br>

    <table width="101%" border="0" cellpadding="0" cellspacing="1" class="commonTable">
        <tr>
            <th>序号</th>
            <th>订单编码</th>
            <th>订单保存时间</th>
            <th>订单状态</th>
            <th class="editColDefault">操作</th>
        </tr>
       
       <c:forEach items="${info.list}" var="s" varStatus="l">
      	 <tr>
            <td align="center">${((info.pageNum-1)*info.pageSize)+l.count}</td>
            <td align="center">${s.ordercode}</td>
            <td align="center">${s.orderdate}</td>
			<td align="center" class="s">${s.name}</td>
            <td align="center" ><span class="a">
            	<a href="../updateFlag?orderid=${s.orderid}" ><font color="BLUE">通过</font></a>
            	<a href="../updateFlag1?orderid=${s.orderid}" ><font color="BLUE">不通过</font></a></span>
            </td>
        </tr>
       </c:forEach>
           
           
        <script>
		    	var s = document.getElementsByClassName("s");
		    	var b = document.getElementsByClassName("a");
		    	for(index in s ){
		    		var value = s[index].textContent;
		    		console.log(value);
		    		//if(value.innerText == )
		    		var a = "";
		    		if (value == "已通过") {
		    			s[index].style = 'color:#FF0000';
		    			b[index].style = 'display:none';
		                
		    		} else if (value == '待审核') {
		    			s[index].style = 'color:BLUE';
		    			b[index].style = 'color:BLUE';
		    		} else if (value == '未提交') {
		    			s[index].style = 'color:#FF8800';
		    			b[index].style = 'color:BLUE';
		    		} else if (value == '不通过'){
		    			s[index].style = 'color:#8A8A8A';
		    			b[index].style = 'display:none';
		    			
		    		}
		    	}
		    </script>   
        
  </table>
    <!--//commonTable-->
    <div id="pagelist">
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
    </div>
</div>
<!--//content pages wrap-->
</body>
</html>
