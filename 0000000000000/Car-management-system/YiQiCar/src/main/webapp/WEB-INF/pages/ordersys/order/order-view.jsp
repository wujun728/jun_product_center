<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="gbk" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="../../../css/main.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" language="javascript">
function showMaterials(){
	var date = new Date();
	var time = date.getTime();
	var obj = new Array('INSERT');
	//时间戳
	var url = "getmater.html";
	var resultValue = window.showModalDialog(url,obj,'dialogWidth:800px;dialogHeight:400px');

	//获取已经存在明细中的原料ID集合
	var materIds = document.getElementsByName('materId');
	if(resultValue!=null && resultValue!=undefined){
		for(var i=0;i<resultValue.length;i++){
			var tempAry = resultValue[i].split(',');
			var flag = false;
			//判断列表中是否已经选择了某种原料
			for(var j=0;j<materIds.length;j++){
				if(tempAry[0]==materIds[j].value){
					flag = true;
				}
			}
			if(!flag){
				insertMaterialMsg(tempAry);
			}
			
		}
		
	}
}


function insertMaterialMsg(tempAry){
	//原料名ID
	var id = tempAry[0];
	//原料名称
	var materName = tempAry[1];
	//原料库存
	var storage = tempAry[2];
	
	var trObj = attachmentList.insertRow();
	trObj.setAttribute("align","center");

	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = "<input type='hidden' name='materId' value='"+id+"'>"+materName;
	
	var tdObj = trObj.insertCell();
	tdObj.innerHTML = "<input type='text' name='count' value='' class='inputTextNormal'>";
	
	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = storage;
	
	var tdObj = trObj.insertCell();
	tdObj.setAttribute("align","left");
	tdObj.innerHTML = "<button onclick=\"deleteRow('attachmentList',this);\" class=\"btnIconDel\" title=\"删除\"></button>";
}


//删除行
function deleteRow(tableID,t){
	var tIndex = t.parentNode.parentNode.rowIndex;
		if(confirm('确定要执行此操作吗?')) {
			 t.parentNode.parentNode.parentNode.deleteRow(tIndex);
		}
		return false; 		

}

</script>
</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div class="content-pages-wrap" style="opacity:0.7">
    <div class="commonTitle">
        <h2>&gt;&gt; 订单详细信息</h2>
  </div>
        <form id="coursesCreat" name="coursesCreat" action="" method="post">
		  <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单序号：</td>
				<td width="15%" align="left">${imap.orderid}</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单编码：</td>
				<td width="15%" align="left">${imap.ordercode}</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单保存日期：</td>
				<td width="15%" align="left">${imap.orderdate}</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>订单状态：</td>
				<td width="15%" align="left">${imap.name}</td>
			  </tr>

		 </table>
	    <div align="left" style="margin-top:15px;margin-bottom:5px">
	    	<a href="javascript:void(0)" title="选择原料" class="btnShort">订单明细</a>
	    </div>
		<table width="90%" border="0" cellpadding="0" cellspacing="1" id="attachmentList" class="commonTable marginTopM">
		   <tr>	
		      <th>配件编号</th>
	          <th>配件名称</th>
	          <th>进货数量</th>
			  <th>库存数量</th>
	      </tr>
	      
	      <c:forEach items="${mlist}" var="s" varStatus="l">
	      		<tr>
	      			<td align="center">${s.partsid}</td>
	      			<td align="center">${s.partsname}</td>
	      			<td align="center" >${s.orderpartscount}</td>
	      			<td align="center">${s.partsreqcount}</td>
	      		</tr>
	      </c:forEach>
		</table>
		</form>
	 </div>
    <!--//commonTable-->
    <div id="formPageButton">
    	<ul>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul>
    </div>
    <!--//commonToolBar-->
</div>
<!--//content pages wrap-->
</body>
</html>

